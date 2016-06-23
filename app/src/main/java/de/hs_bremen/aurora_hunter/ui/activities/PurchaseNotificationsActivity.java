package de.hs_bremen.aurora_hunter.ui.activities;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.vending.billing.IInAppBillingService;
import com.crashlytics.android.answers.AddToCartEvent;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.PurchaseEvent;
import com.crashlytics.android.answers.StartCheckoutEvent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.hs_bremen.aurora_hunter.R;

public class PurchaseNotificationsActivity extends Activity implements ServiceConnection {
    public static final String TAG = PurchaseNotificationsActivity.class.getSimpleName();

    private static final int BILLING_RESPONSE_RESULT_OK = 0;
    private static final int BILLING_RESPONSE_RESULT_USER_CANCELED = 1;
    private static final int BILLING_RESPONSE_RESULT_SERVICE_UNAVAILABLE = 2;
    private static final int BILLING_RESPONSE_RESULT_BILLING_UNAVAILABLE = 3;
    private static final int BILLING_RESPONSE_RESULT_ITEM_UNAVAILABLE = 4;
    private static final int BILLING_RESPONSE_RESULT_DEVELOPER_ERROR = 5;
    private static final int BILLING_RESPONSE_RESULT_ERROR = 6;
    private static final int BILLING_RESPONSE_RESULT_ITEM_ALREADY_OWNED = 7;
    private static final int BILLING_RESPONSE_RESULT_ITEM_NOT_OWNED = 0;

    private static final String sSkuId = "notifications";
    private String mPrice = "";
    private boolean mAlreadyPurchased = false;

    private IInAppBillingService mService;

    private TextView mHeadlineTextView;
    private TextView mBuySuccessfullTextView;
    private Button mBuyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_notification);

        // Bind Google Play AppBilling Service - Begin
        Intent serviceIntent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        serviceIntent.setPackage("com.android.vending");
        bindService(serviceIntent, this, Context.BIND_AUTO_CREATE);
        // Bind Google Play AppBilling Service - End

        mHeadlineTextView = (TextView)  findViewById(R.id.button_purchase_activity_headline);
        mBuySuccessfullTextView = (TextView) findViewById(R.id.text_purchase_activity_buy_successfull);
        mBuyButton = (Button) findViewById(R.id.button_purchase_activity_buy);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // Unbind Google Play AppBilling Service - Begin
        if (mService != null) {
            unbindService(this);
        }
        // Unbind Google Play AppBilling Service - End
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {

        mService = IInAppBillingService.Stub.asInterface(service);

        ArrayList<String> skuList = new ArrayList<>();
        skuList.add(sSkuId);

        Bundle query = new Bundle();
        query.putStringArrayList("ITEM_ID_LIST", skuList);

        try {
            Bundle skuDetails = mService.getSkuDetails(3, getPackageName(), "inapp", query);

            int responseCode = skuDetails.getInt("RESPONSE_CODE");
            if (responseCode == BILLING_RESPONSE_RESULT_OK) {

                ArrayList<String> responseList = skuDetails.getStringArrayList("DETAILS_LIST");
                if(responseList != null && responseList.isEmpty()) {
                    //Log.d(TAG, "Billing Service found not items.");
                }
                
                for (String thisResponse : responseList) {
                    try {
                        JSONObject object = new JSONObject(thisResponse);

                        String sku = object.getString("productId");
                        String price = object.getString("price");

                        if(sku.equals(sSkuId)) {
                            mPrice = price;
                            //Log.d(TAG, "Notifications cost " + price + "  at the moment.");

                            mHeadlineTextView.setText(getString(R.string.activity_purchase_headline) + " " + price);

                            Answers.getInstance().logAddToCart(new AddToCartEvent()
                                    .putItemName("Notification")
                                    .putItemType("Notification")
                                    .putItemId("sku-"+ sku));

                            Bundle buyIntentBundle = mService.getBuyIntent(3, getPackageName(),
                                    "android.test.purchased", "inapp", "bGoa+V7g/yqDXvKRqq+JTFn4uQZbPiQJo4pf9RzJ");

                            if(checkIfAlreadyBought("android.test.purchased")) {
                                mAlreadyPurchased = true;

                                mBuySuccessfullTextView.setVisibility(View.VISIBLE);
                                mBuySuccessfullTextView.setText("You have already bought Notifications!");
                                mBuyButton.setEnabled(false);

                                savePurchaseLocal();
                                finish();
                            }
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            } else if (responseCode == BILLING_RESPONSE_RESULT_SERVICE_UNAVAILABLE){
                //Log.d(TAG, "Billing Service is unavailable.");
            } else if (responseCode == BILLING_RESPONSE_RESULT_ERROR) {
                //Log.e(TAG, "Billing Service Error");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        //Log.d(TAG, "onServiceDisconnected: ");
        mService = null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Log.d(TAG, "onActivityResult: ");

        if (requestCode == 1001) {
            int responseCode = data.getIntExtra("RESPONSE_CODE", 0);
            String purchaseData = data.getStringExtra("INAPP_PURCHASE_DATA");
            String dataSignature = data.getStringExtra("INAPP_DATA_SIGNATURE");

            if (resultCode == RESULT_OK) {
                try {
                    JSONObject jo = new JSONObject(purchaseData);
                    String sku = jo.getString("productId");

                    Answers.getInstance().logPurchase(new PurchaseEvent()
                            .putItemName("Notifications")
                            .putItemType("Notifications")
                            .putItemId("sku-" + sku)
                            .putSuccess(true));

                    mBuySuccessfullTextView.setVisibility(View.VISIBLE);
                    mBuyButton.setEnabled(false);

                    savePurchaseLocal();
                    finish();
                }
                catch (JSONException e) {
                    //Log.d(TAG, "onActivityResult: Buy fail");
                    e.printStackTrace();
                }
            }
        }
    }

    public void savePurchaseLocal() {
        Context context = getApplicationContext();
        SharedPreferences sharedPref = context.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(getString(R.string.preference_notifications_purchased), true);
        editor.apply();
    }

    public void buyNotifications(View view) {
        Answers.getInstance().logStartCheckout(new StartCheckoutEvent()
                .putItemCount(1));

        try {
            Bundle buyIntentBundle = mService.getBuyIntent(3, getPackageName(),
                    "android.test.purchased", "inapp", "bGoa+V7g/yqDXvKRqq+JTFn4uQZbPiQJo4pf9RzJ");

            if(checkIfAlreadyBought("android.test.purchased")) {
                //Log.e(TAG, "buy: Already bought" );

                mAlreadyPurchased = true;
                return;
            }

            PendingIntent pendingIntent = buyIntentBundle.getParcelable("BUY_INTENT");
            IntentSender sender = null;
            if (pendingIntent != null) {
                sender = pendingIntent.getIntentSender();
            }

            startIntentSenderForResult(sender, 1001, new Intent(), 0, 0, 0);

        } catch (RemoteException | IntentSender.SendIntentException e) {
            e.printStackTrace();
        }
    }

    public boolean checkIfAlreadyBought(String pSku) {
        boolean alreadyBought = false;
        try {
            Bundle purchasesIntentBundle = mService.getPurchases(3, getPackageName(), "inapp", null);

            ArrayList<String> purchaseDataList = purchasesIntentBundle.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");

            if (purchaseDataList != null) {
                for(String purchaseData : purchaseDataList) {
                    //Log.d(TAG, "checkIfAlreadyBought: " + purchaseData);

                    if(pSku.equals(purchaseData)) {
                        alreadyBought = true;
                    }
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return alreadyBought;
    }
}
