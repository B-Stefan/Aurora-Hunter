package de.hs_bremen.aurora_hunter.ui.fragments.placeSearch;


import android.annotation.SuppressLint;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.style.CharacterStyle;
import android.text.style.MetricAffectingSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.ContentViewEvent;
import com.crashlytics.android.answers.SearchEvent;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.data.DataBufferUtils;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.AutocompletePredictionBuffer;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hs_bremen.aurora_hunter.R;
import de.hs_bremen.aurora_hunter.helpers.permissions.PermissionManager;
import de.hs_bremen.aurora_hunter.notifications.locationUpdate.LocationService;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {


    private GoogleApiClient mGoogleApiClient;

    private FloatingSearchView mFloatingSearchView;

    private LocationChangedInterface mLocationChangedListener;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        final View view = inflater.inflate(R.layout.fragment_search, container, false);

        final GoogleApiConnectionClass callbacks = new GoogleApiConnectionClass();

        mFloatingSearchView = (FloatingSearchView) view.findViewById(R.id.floating_search_view);

        mFloatingSearchView.bringToFront();

        //On query search
        mFloatingSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String newQuery) {
                if (!oldQuery.equals("") && newQuery.equals("")) {
                    mFloatingSearchView.clearSuggestions();
                } else {


                    mFloatingSearchView.showProgress();


                    Answers.getInstance().logSearch(new SearchEvent()
                            .putQuery(newQuery));


                    List<Integer> filterTypes = new ArrayList<Integer>();
                    filterTypes.add( Place.TYPE_LOCALITY );
                    filterTypes.add( Place.TYPE_ADMINISTRATIVE_AREA_LEVEL_1 );
                    filterTypes.add( Place.TYPE_COUNTRY );

                    final AutocompleteFilter autocompleteFilter = AutocompleteFilter.create(filterTypes);

                    PendingResult<AutocompletePredictionBuffer> result = Places.GeoDataApi.getAutocompletePredictions(mGoogleApiClient, newQuery,null,autocompleteFilter);

                    result.setResultCallback(new ResultCallback<AutocompletePredictionBuffer>() {
                        @Override
                        public void onResult(AutocompletePredictionBuffer autocompletePredictions) {

                            mFloatingSearchView.clearSuggestions();

                            mFloatingSearchView.swapSuggestions(createSearchSuggestions(autocompletePredictions));

                            mFloatingSearchView.hideProgress();


                        }
                    });
                }
            }
        });

        //on menue item Click
        mFloatingSearchView.setOnMenuItemClickListener(new FloatingSearchView.OnMenuItemClickListener() {
            @Override
            public void onActionMenuItemSelected(MenuItem item) {

                if (item.getItemId() == R.id.action_location) {
                    setToCurrentLocation();

                }else if (item.getItemId() == R.id.action_refresh){

                    mLocationChangedListener.onRefreshRequested();
                } else{
                    Snackbar.make(getView(), item.getTitle(), Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        //On search open
        mFloatingSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {
                final PlaceSuggestion placeSuggestion= (PlaceSuggestion) searchSuggestion;

                PendingResult<PlaceBuffer> placeBufferPendingResult = Places.GeoDataApi.getPlaceById(mGoogleApiClient,placeSuggestion.getPlaceId());

                placeBufferPendingResult.setResultCallback(new ResultCallback<PlaceBuffer>() {
                    @Override
                    public void onResult(PlaceBuffer places) {

                        if(places.getStatus().isSuccess()){
                            Place place = places.get(0);
                            Location location = new Location("own");
                            location.setLatitude(place.getLatLng().latitude);
                            location.setLongitude(place.getLatLng().longitude);
                            mLocationChangedListener.onLocationChanged(location);
                        }else {
                            Snackbar.make(getView(),places.getStatus().getStatusMessage(),Snackbar.LENGTH_LONG);
                        }
                    }
                });

            }

            @Override
            public void onSearchAction(String currentQuery) {
                if(!currentQuery.isEmpty() && !currentQuery.equals(getString(R.string.search_default_hint))){

                }

            }
        });

        mGoogleApiClient = new GoogleApiClient.Builder(this.getContext())
                .addConnectionCallbacks(callbacks)
                .addOnConnectionFailedListener(callbacks)
                .addApi(Places.GEO_DATA_API)
                .addApi(LocationServices.API)
                .addApi(Places.PLACE_DETECTION_API)
                .build();

        return view;
    }

    private void setToCurrentLocation(){

        PermissionManager.getInstance().ensureAllPermissions(this.getActivity(), new PermissionManager.PermissionResultCallback() {
            @Override
            public void granted() {

                if(LocationServices.FusedLocationApi.getLocationAvailability(mGoogleApiClient).isLocationAvailable()){
                    Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

                    Geocoder geocoder = new Geocoder(getContext());
                    try {
                        mLocationChangedListener.onLocationChanged(location);
                        Address address = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1).get(0);
                        if(address != null){
                            mFloatingSearchView.setSearchText(address.getLocality() + ", " + address.getCountryName());
                        }else {
                            Snackbar.make(getView(), "Can't find city name for your location", Snackbar.LENGTH_SHORT).show();
                            Answers.getInstance().logContentView(new ContentViewEvent()
                                    .putContentName("Search")
                                    .putContentType("User-Interaction")
                                    .putContentId("place-not-found"));
                        }
                    }catch (IOException e){
                        Snackbar.make(getView(), e.getMessage(), Snackbar.LENGTH_SHORT).show();
                        Answers.getInstance().logContentView(new ContentViewEvent()
                                .putContentName("Search")
                                .putContentType("User-Interaction")
                                .putContentId("place-exception"));
                    }
                }else {
                    Snackbar.make(getView(), "Location not available! Activate GPS?", Snackbar.LENGTH_SHORT).show();
                    Answers.getInstance().logContentView(new ContentViewEvent()
                            .putContentName("Search")
                            .putContentType("User-Interaction")
                            .putContentId("gps-deactivated"));
                }
            }

            @Override
            public void denied() {
                Snackbar.make(getView(), "Permissions denied, please restart app and confirm all permissions ", Snackbar.LENGTH_SHORT).show();
            }
        });

    }

    protected List<PlaceSuggestion> createSearchSuggestions(AutocompletePredictionBuffer autocompletePredictions){
        final List<PlaceSuggestion> list = new ArrayList<>();

        final List<AutocompletePrediction> bufferList = DataBufferUtils.freezeAndClose(autocompletePredictions);
        autocompletePredictions.release();
        for(AutocompletePrediction autocompletePrediction: bufferList){
            list.add(new PlaceSuggestion(autocompletePrediction));

        }

        return list;
    }
    public void onStart() {
        mGoogleApiClient.connect();

        try {
            mLocationChangedListener = (LocationChangedInterface) this.getParentFragment();
        }catch (ClassCastException e){
            throw  new RuntimeException(e);
        }

        super.onStart();
    }

    public void onStop() {
        mGoogleApiClient.disconnect();
        mLocationChangedListener = null;
        super.onStop();
    }

    private class GoogleApiConnectionClass implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

        @Override
        public void onConnected(Bundle bundle) {
            setToCurrentLocation();
            //Log.i("SearchFragement", "Google client is there");
        }

        @Override
        public void onConnectionSuspended(int i) {
            Snackbar.make(getView(),"Google Play service connection suspended, please restart the app",Snackbar.LENGTH_LONG).show();
        }

        @Override
        public void onConnectionFailed(ConnectionResult connectionResult) {
            Snackbar.make(getView(),"Google Play service connection failed, please restart the app",Snackbar.LENGTH_LONG).show();

        }
    }

    private class PlaceSuggestion implements SearchSuggestion {

        public  final Creator<PlaceSuggestion> CREATOR = new Creator<PlaceSuggestion>() {
            @Override
            public PlaceSuggestion createFromParcel(Parcel in) {
                return new PlaceSuggestion(in);
            }

            @Override
            public PlaceSuggestion[] newArray(int size) {
                return new PlaceSuggestion[size];
            }
        };

        private String mCityName;

        private String mPlaceId;

        private boolean mIsHistory = false;


        public PlaceSuggestion(AutocompletePrediction autocompletePrediction){
            this.mIsHistory = false;
            this.mCityName = autocompletePrediction.getFullText(null).toString();
            this.mPlaceId = autocompletePrediction.getPlaceId();
        }
        public PlaceSuggestion(Parcel source) {
            this.mCityName = source.readString();
            this.mIsHistory = source.readInt() != 0;
            this.mPlaceId = source.readString();
        }

        @Override
        public String getBody() {
            return mCityName;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.mCityName);
            parcel.writeInt(this.mIsHistory ? 1 : 0);
            parcel.writeString(this.mPlaceId);
        }

        public boolean isIsHistory() {
            return mIsHistory;
        }

        public void setIsHistory(boolean mIsHistory) {
            this.mIsHistory = mIsHistory;
        }
        public String getPlaceId() {
            return mPlaceId;
        }


    }
    public interface LocationChangedInterface {
        public void  onLocationChanged(Location location);
        public void  onRefreshRequested();
    }

}
