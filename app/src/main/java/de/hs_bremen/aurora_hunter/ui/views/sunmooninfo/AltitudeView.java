package de.hs_bremen.aurora_hunter.ui.views.sunmooninfo;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public abstract class AltitudeView extends View{
    public static final String TAG = AltitudeView.class.getSimpleName();


    protected double mAltitude = Math.PI /2 ;

    public AltitudeView(Context context) {
        super(context);

    }

    public AltitudeView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public AltitudeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    /**
     * Returns the current altitude value in relation to the current height
     * @return percent
     */
    protected double getAltitudeInPercent(){
        double  percent = mAltitude/(Math.PI/4);

        if(percent > 1){
            percent = 1;
        }
        return percent;
    }
    protected void translateCanvasViaAltitude(Canvas canvas){

        double height = this.getHeight();
        double percent = this.getAltitudeInPercent();
        canvas.translate(0, (float) (height/2 - (height- height/2 )* percent));
    }

    public double getAltitude() {
        return mAltitude;
    }

    public void setAltitude(double mAltitude) {
        this.mAltitude = mAltitude;
    }


}
