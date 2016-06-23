/**
 * Copyright 2012 John Ericksen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.hs_bremen.aurora_hunter.ui.views;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.hs_bremen.aurora_hunter.R;
import de.hs_bremen.aurora_hunter.commons.prediction.models.Probability;
import de.hs_bremen.aurora_hunter.helpers.DpToPixelUtil;
import de.hs_bremen.aurora_hunter.ui.fragments.PredictionFragment;

public class PredictionGraphView extends View implements OnTouchListener {
    public static final String TAG = PredictionGraphView.class.getSimpleName();

    private static final float STROKE_WIDTH = 5f;

    private PredictionFragment.NOTIFICATION_MODE mViewMode = PredictionFragment.NOTIFICATION_MODE.NORMAL;
    private double mNotificationLevel = 0;

    private List<Point> mPoints = new ArrayList<>();

    private Paint mNotificationLevelPaint = new Paint();
    private Paint mNotificationLevelTextPaint = new Paint();
    private Paint mGraphPaint = new Paint();
    private Paint mTextPaint = new Paint();
    private double mScaleFactor = 1;
    private double mMax = 0;

    public PredictionGraphView(Context pContext, AttributeSet pAttributeSet) {
        super(pContext, pAttributeSet);

        setFocusable(true);
        setFocusableInTouchMode(true);
        setOnTouchListener(this);



        int color = ContextCompat.getColor(getContext(), R.color.colorAccent);
        mNotificationLevelPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mNotificationLevelPaint.setColor(color);

        mNotificationLevelTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mNotificationLevelTextPaint.setTextAlign(Paint.Align.LEFT);
        mNotificationLevelTextPaint.setTextSize(80);
        mNotificationLevelTextPaint.setColor(color);

        mGraphPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //mGraphPaint.setStyle(Paint.Style.STROKE);
        mGraphPaint.setStrokeWidth(STROKE_WIDTH);
        mGraphPaint.setColor(Color.BLACK);
        mGraphPaint.setStyle(Paint.Style.FILL);

        mGraphPaint.setDither(true);
        mGraphPaint.setStrokeJoin(Paint.Join.ROUND);
        mGraphPaint.setStrokeCap(Paint.Cap.ROUND);
        mGraphPaint.setAntiAlias(true);
        mGraphPaint.setAlpha(220);

        mTextPaint.setStrokeWidth(10f);
        mTextPaint.setDither(true);
        mTextPaint.setStrokeJoin(Paint.Join.ROUND);
        mTextPaint.setStrokeCap(Paint.Cap.ROUND);
        mTextPaint.setPathEffect(new CornerPathEffect(10));
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize(30);
        mTextPaint.setFakeBoldText(true);
        mTextPaint.setAlpha(220);
    }

    public void setToStaticData(){
        clear();

        double factor = 0.1;

        this.mPoints.add(new Point(0, 1));
        this.mPoints.add(new Point((factor * 1) - factor/2 ,0.9));
        this.mPoints.add(new Point((factor * 2) - factor / 2, 0.8));
        this.mPoints.add(new Point((factor * 3) - factor / 2, 0.7));
        this.mPoints.add(new Point((factor * 4) - factor / 2, 0.6));
        this.mPoints.add(new Point((factor * 5) - factor / 2, 0.6));
        this.mPoints.add(new Point((factor * 6) - factor / 2, 0.4));
        this.mPoints.add(new Point((factor*7) - factor/2 ,0.3));
        this.mPoints.add(new Point((factor*8) - factor/2 ,0.4));
        this.mPoints.add(new Point((factor*9) - factor/2 ,0.1));
        this.mPoints.add(new Point((factor*10) - factor/2 ,0.0));

        this.invalidate();
    }
    public void addProbabilities(List<Probability> probabilities){

        mMax = 0;
        for(Probability p : probabilities){
            mMax = p.getProbability() > mMax ? p.getProbability(): mMax;
            //Log.i(TAG,p.getProbability() + " prop");
            //Log.i(TAG,mMax+ " max");
        }

        double logResult = Math.log(mMax);
        mScaleFactor = 1 + Math.abs(logResult);

        //Add new data
        clear();
        for(Probability p : probabilities){

            int index = probabilities.indexOf(p);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(p.getDate());

            double hour = 0.04166666667 * index; //calendar.get(Calendar.HOUR_OF_DAY);//1 / 24;

            //First and last entry set to 0% and 100%
            if(index == 0){
                hour = 0;
            }else if(index == probabilities.size()-1){
                hour = 1;
            }

            Point point  = new Point(hour,p.getProbability() );

          //  Log.i("point", point.percentageX + " - " + point.percentageY);

            this.addPoint(point);
        }

        invalidate();
    }

    public void onDraw(Canvas canvas) {
       if(mPoints.size() == 0){
            return;
        }
        int yOffsetForLabels = 0;

        if(mMax * mScaleFactor > 0.8){
            yOffsetForLabels = 15;
        }
        Path path = new Path();
        int height = canvas.getHeight();
        int width = canvas.getWidth();

        for(Point point : mPoints){
            point.x = point.percentageX * width + 60;
           // Log.i("mScaleFactor", " : " + mScaleFactor);
            //Log.i("percent", " : " + percent);

            point.y = (float) ((1 - point.percentageY * mScaleFactor) * height ) + yOffsetForLabels *3 ;
        }

        if(mPoints.size() > 1){
            //calcuate x/y based on size of canvas
            for(int i = 0; i < mPoints.size(); i++){
                if(i >= 0){
                    Point point = mPoints.get(i);
                  //  Log.i("dx",point.x + " - " + point.y );
                    if(i == 0){
                        Point next = mPoints.get(i + 1);
                        point.dx = ((next.x - point.x)/5);
                        point.dy = ((next.y - point.y)/5);
                    }
                    else if(i == mPoints.size() - 1){
                        Point prev = mPoints.get(i - 1);
                        point.dx = ((point.x - prev.x)/5);
                        point.dy = ((point.y - prev.y)/5);
                    }
                    else{
                        Point next = mPoints.get(i + 1);
                        Point prev = mPoints.get(i - 1);
                        point.dx = ((next.x - prev.x)/5);
                        point.dy = ((next.y - prev.y)/5);
                    }
                }
            }
        }

        if(mPoints.size()>0){
            path.moveTo(0, (float) (canvas.getHeight() * 0.8));
            path.lineTo(0, mPoints.get(0).y);

        }
        boolean first = true;

        for(int i = 0; i < mPoints.size(); i++){
            Point point = mPoints.get(i);
            if(first){
                first = false;
                path.cubicTo(point.x - point.x*2, point.y -point.y /5, point.x - point.dx , point.y - point.dy , point.x , point.y );
            }
            else{
                Point prev = mPoints.get(i - 1);
              //  Log.i("Draw", point.dx  + " " + point.dy);
                path.cubicTo(prev.x + prev.dx , prev.y + prev.dy, point.x - point.dx , point.y - point.dy , point.x , point.y );
            }
        }

        if(mPoints.size() > 0){
            path.lineTo(width,mPoints.get(mPoints.size()-1).y);
        }
        path.lineTo(width,height);
        path.lineTo(0,height);

        canvas.drawPath(path, mGraphPaint);

        int detlaY = 30;

        for(Point p : mPoints){

            int val = (int)Math.round(p.percentageY * 100);
            //if last element

            if(mPoints.indexOf(p) == mPoints.size()-1){
                //Log.i("last", p.toString());
                if(val == 0 || p.y > getHeight()){
                    canvas.drawText(val + "%",p.x-150,getHeight()-detlaY, mTextPaint);
                }else {
                    canvas.drawText(val + "%", p.x-150,p.y - detlaY  + yOffsetForLabels, mTextPaint);
                }

            }else {
                if(val == 0 || p.y > getHeight()){
                    canvas.drawText(val + "%",p.x-20,getHeight()-detlaY, mTextPaint);
                }else {
                    canvas.drawText(val + "%", p.x-20,p.y - detlaY + yOffsetForLabels, mTextPaint);
                }

            }
            //Log.i("point", p.toString());
        }
       // Log.i("Lenght", mPoints.size() + " ");

        float levelStart = (float) (canvas.getHeight() - (mNotificationLevel * canvas.getHeight()));
        float lineHeight = DpToPixelUtil.convertDpToPixel(2,getContext());

        if(mViewMode == PredictionFragment.NOTIFICATION_MODE.SET_NOTIFICATION_LEVEL) {
            mNotificationLevelPaint.setAlpha(150);
            float leftOffset = 0;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                float arcHeight = DpToPixelUtil.convertDpToPixel(30,getContext());
                leftOffset = arcHeight;
                RectF rectF = new RectF(getWidth() - arcHeight, levelStart- arcHeight/2, getWidth(), levelStart+arcHeight/2);
                canvas.drawArc (rectF, 0, 360, true, mNotificationLevelPaint);

            }
            canvas.drawRect(0, levelStart, canvas.getWidth() - leftOffset, levelStart + lineHeight, mNotificationLevelPaint);


            mNotificationLevelPaint.setAlpha(20);
            canvas.drawRect(0, levelStart + lineHeight, canvas.getWidth(), canvas.getHeight(), mNotificationLevelPaint);
            String text = Math.round(mNotificationLevel * 100) + "%";
            canvas.drawText(text, DpToPixelUtil.convertDpToPixel(10,getContext()), levelStart + DpToPixelUtil.convertDpToPixel(25,getContext()), mNotificationLevelTextPaint);
        } else {
            mNotificationLevelPaint.setAlpha(30);
            canvas.drawRect(0, levelStart, canvas.getWidth(), levelStart + lineHeight, mNotificationLevelPaint);
        }

    }



    public boolean onTouch(View view, MotionEvent event) {

        return super.onTouchEvent(event);
    }

    private void clear() {
        mPoints.clear();
    }

    private void addPoint(Point p ) {
        mPoints.add(p);
    }

    public double getNotificationLevel() {
        return mNotificationLevel;
    }

    public void setNotificationLevel(double pNotificationLevel) {
        this.mNotificationLevel = pNotificationLevel;
    }

    private class Point {
        float percentageX, percentageY;
        float x, y;
        float dx, dy;

        private Point(double x, double y){
            this.percentageY  = (float) (y);
            this.percentageX = (float) x;
        }

        @Override
        public String toString() {
            return x + ", " + y;
        }
    }

    public void setViewMode(PredictionFragment.NOTIFICATION_MODE pNewViewMode) {
        mViewMode = pNewViewMode;
    }

    public PredictionFragment.NOTIFICATION_MODE getViewMode() {
        return mViewMode;
    }
}

