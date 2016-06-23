package de.hs_bremen.aurora_hunter.ui.views.sunmooninfo;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class SunView extends AltitudeView {
    public static final String TAG = SunView.class.getSimpleName();

    public static final int moonColor = Color.YELLOW;

    private RectF sun = new RectF();

    public SunView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas g) {

        int width = (int)Math.round(getWidth()*0.8);
        int height = (int)Math.round(getHeight()*0.8);

        Bitmap bitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        Canvas sunCanvas = new Canvas(bitmap);

        //erase something from
        sun.set(getWidth()-width,getHeight()-height,width,height);
        Paint paintSun = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintSun.setColor(moonColor);
        paintSun.setAlpha(200);
        sunCanvas.drawArc(sun,0,360,true,paintSun);

        this.translateCanvasViaAltitude(g);

        g.drawBitmap(bitmap,0,0,null);
    }

}