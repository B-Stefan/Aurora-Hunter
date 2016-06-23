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
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.hs_bremen.aurora_hunter.R;
import de.hs_bremen.aurora_hunter.commons.prediction.models.Probability;

public class KpIndexChartView extends View implements OnTouchListener {
    public static final String TAG = KpIndexChartView.class.getSimpleName();

    private static final float STROKE_WIDTH = 5f;

    private List<Point> points = new ArrayList<Point>();

    private Paint mBackgroundPaint = new Paint();
    private Paint mGraphPaint = new Paint();
    private Paint paintTxt = new Paint();
    private Paint paintG1Line = new Paint();
    private double scaleFactor = 1;

    public KpIndexChartView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setFocusable(true);
        setFocusableInTouchMode(true);

        setOnTouchListener(this);

        int colorBg = ContextCompat.getColor(getContext(), R.color.colorBackgroundChart);
        int colorFg = ContextCompat.getColor(getContext(), R.color.colorKPChart);

        mGraphPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //mGraphPaint.setStyle(Paint.Style.STROKE);
        mGraphPaint.setStrokeWidth(STROKE_WIDTH);
        mGraphPaint.setColor(colorBg);
        mGraphPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        //mGraphPaint.setShader(new LinearGradient(0, 0, 0, getHeight(), colorFg, colorBg, Shader.TileMode.MIRROR));

        mGraphPaint.setDither(true);                    // set the dither to true
        mGraphPaint.setStrokeJoin(Paint.Join.ROUND);    // set the join to round you want
        mGraphPaint.setStrokeCap(Paint.Cap.ROUND);      // set the mGraphPaint cap to round too
        mGraphPaint.setAntiAlias(true);
        mGraphPaint.setAlpha(100);

        paintG1Line.setStrokeWidth(1f);
        paintG1Line.setColor(Color.BLACK);
        paintG1Line.setStyle(Paint.Style.STROKE);
        paintG1Line.setDither(true);                    // set the dither to true
        paintG1Line.setAntiAlias(true);
        paintG1Line.setAlpha(100);

        paintTxt.setStrokeWidth(10f);
        paintTxt.setDither(true);                    // set the dither to true
        paintTxt.setStrokeJoin(Paint.Join.ROUND);    // set the join to round you want
        paintTxt.setStrokeCap(Paint.Cap.ROUND);      // set the mGraphPaint cap to round too
        paintTxt.setPathEffect(new CornerPathEffect(10));   // set the path effect when they join.
        paintTxt.setAntiAlias(true);
        paintTxt.setColor(Color.BLACK);
        paintTxt.setTextSize(20);
        paintTxt.setFakeBoldText(true);
        paintTxt.setAlpha(150);

    }



    public void addProbabilities(final List<Probability> probabilities){

        double max = 0;

        for(Probability p: probabilities){
            if(p.getKpInformation() != null ){

                max = p.getKpInformation().getKpValue()/9 > max ? p.getKpInformation().getKpValue()/9: max;
            }
        }
      //  Log.i("max", "d" + max);
        scaleFactor = 1 + Math.log(max)/Math.log(1/4);
      //  Log.i("max", "d" + factor);

        //Add new data
        clear();
        for(Probability p: probabilities){

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

            double kpVale = 0;
            if(p.getKpInformation() != null ){
                kpVale = p.getKpInformation().getKpValue() / 9;
            }
            Point point  = new Point(hour,kpVale);

          //  Log.i("point", point.percentageX + " - " + point.percentageY);

            this.addPoint(point);
        }

        invalidate();
    }

    public void onDraw(Canvas canvas) {

        if(points.size() == 0 ){
            return;
        }
        Path path = new Path();
        int height = canvas.getHeight();
        int width = canvas.getWidth();

        for(Point point : points){
            point.x = point.percentageX * width + 60;
           // Log.i("scaleFactor", " : " + scaleFactor);
            //Log.i("percent", " : " + percent);

            point.y = (float) ((1 - point.percentageY * scaleFactor) * height * 0.7  + 30  );
        }

        if(points.size() > 1){
            //calcuate x/y based on size of canvas
            for(int i = 0; i < points.size(); i++){
                if(i >= 0){
                    Point point = points.get(i);
                  //  Log.i("dx",point.x + " - " + point.y );
                    if(i == 0){
                        Point next = points.get(i + 1);
                        point.dx = ((next.x - point.x)/5);
                        point.dy = ((next.y - point.y)/5);
                    }
                    else if(i == points.size() - 1){
                        Point prev = points.get(i - 1);
                        point.dx = ((point.x - prev.x)/5);
                        point.dy = ((point.y - prev.y)/5);
                    }
                    else{
                        Point next = points.get(i + 1);
                        Point prev = points.get(i - 1);
                        point.dx = ((next.x - prev.x)/5);
                        point.dy = ((next.y - prev.y)/5);
                    }
                }
            }
        }

        if(points.size()>0){
            path.moveTo(0, (float) (canvas.getHeight() * 0.8));
            path.lineTo(0, points.get(0).y);

        }
        boolean first = true;

        for(int i = 0; i < points.size(); i++){
            Point point = points.get(i);
            if(first){
                first = false;
                path.cubicTo(point.x - point.x*2, point.y -point.y /5, point.x - point.dx , point.y - point.dy , point.x , point.y );
            }
            else{
                Point prev = points.get(i - 1);
              //  Log.i("Draw", point.dx  + " " + point.dy);
                path.cubicTo(prev.x + prev.dx , prev.y + prev.dy, point.x - point.dx , point.y - point.dy , point.x , point.y );
            }
        }
        path.lineTo(width, (float)(height * 0.8));

        path.lineTo(width, (float)(height * 0.9));
        path.lineTo(0, (float) (height * 0.9));

        canvas.drawPath(path, mGraphPaint);
        canvas.drawRect(0, (float) (height * 0.9), width, height, mGraphPaint);

        double Kp1Height  = (1 - 0.6666666) * height;//points.get(points.size()-1).y;
        canvas.drawRect(0,(float) Kp1Height,width,(float) Kp1Height+1,paintG1Line);
        canvas.drawText("G1",2,(float) Kp1Height,paintTxt);
        int detlaY = 15;

        for(Point p : points){
            int val = (int)Math.round(9 * p.percentageY);
            //if last element
            if(points.indexOf(p) == points.size()-1){
                //Log.i("last", p.toString());
             //   canvas.drawText(val + "", p.x-150,p.y - detlaY, paintTxt);
            }
            else {
                canvas.drawText(val + "", p.x-10,p.y - detlaY, paintTxt);
            }
            //Log.i("point", p.toString());
        }
       // Log.i("Lenght", points.size() + " ");

    }

    public boolean onTouch(View view, MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_UP) {
            for (int i = 0; i < event.getHistorySize(); i++) {
                Point point = new Point();
                point.x = event.getHistoricalX(i);
                point.y = event.getHistoricalY(i);
               // points.add(point);
            }
            invalidate();
            return true;
        }
        return super.onTouchEvent(event);
    }

    private void clear() {
        points.clear();
    }

    private void addPoint(Point p ) {
        points.add(p);
    }

    private class Point {
        float percentageX,percentageY;
        float x, y;
        float dx, dy;

        private Point(){}

        private Point(double x, double y){
            this.percentageY  = (float) (y);
          //  Log.i("point", x + " - " + y);
            this.percentageX = (float)x;
        }


        @Override
        public String toString() {
            return x + ", " + y;
        }
    }
}

