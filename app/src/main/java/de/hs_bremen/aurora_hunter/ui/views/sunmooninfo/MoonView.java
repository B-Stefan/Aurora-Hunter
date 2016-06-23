package de.hs_bremen.aurora_hunter.ui.views.sunmooninfo;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;

public class MoonView extends AltitudeView{
    public static final String TAG = MoonView.class.getSimpleName();

    public static final int moonColor = Color.parseColor("#fbf9fa");

    private Paint paintMoonPhase;

    private double mPhasePercent = 0.5;

    private RectF moonBg = new RectF();
    private RectF moonPhase = new RectF();

    public MoonView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas g) {
        int width = getWidth();
        int height = getHeight();

        // draw the whole moon disk, in moonColor:

        Bitmap bitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        bitmap.eraseColor(Color.TRANSPARENT);
        Canvas moonCanvas = new Canvas(bitmap);

        //draw whole moon

        double phase = this.getPhasePercent();

        double offset;

        if(phase<=0.5){
            offset = width * phase + 1 ;
            moonPhase.set(Math.round(offset),Math.round(offset)*-1,width,height+Math.round(offset));
        }else {
            offset = width * (1-phase) * -1 + 1;
            moonPhase.set(0,Math.round(offset),width + Math.round(offset),height+Math.round(offset)*-1);
        }
        //erase something from
        moonBg.set(0,0,width, height);
        paintMoonPhase = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintMoonPhase.setColor(moonColor);
        paintMoonPhase.setAlpha(200);
        moonCanvas.drawArc(moonBg,0,360,true,paintMoonPhase);


        paintMoonPhase.setColor(Color.TRANSPARENT);
        paintMoonPhase.setAlpha(200);
        paintMoonPhase.setMaskFilter(null);
        paintMoonPhase.setXfermode(new PorterDuffXfermode(
                PorterDuff.Mode.CLEAR));
        paintMoonPhase.setAntiAlias(true);

        moonCanvas.drawArc(moonPhase,0,360,true,paintMoonPhase);

        this.translateCanvasViaAltitude(g);
        //Log.i("height",offsetHeight + "");
        //Log.i("height2",height-(height * offsetHeight)  + " ");
        g.scale(0.6f, 0.6f);
        g.translate(width*0.2f,0);

        paintMoonPhase = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintMoonPhase.setColor(moonColor);
        paintMoonPhase.setAlpha(100);
        g.drawBitmap(bitmap,0,0,null);
        g.drawArc(moonBg,0,360,true,paintMoonPhase);
    }

    public double getPhasePercent() {
        return mPhasePercent;
    }

    public void setPhasePercent(double mPhaseAngle) {
        this.mPhasePercent = mPhaseAngle;
    }


}