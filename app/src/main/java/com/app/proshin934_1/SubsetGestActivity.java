package com.app.proshin934_1;

import android.os.Bundle;
import android.app.Activity;
import android.view.*;
import android.widget.*;

import androidx.core.view.GestureDetectorCompat;

public class SubsetGestActivity extends Activity {

    private GestureDetectorCompat mDetector;
    private TextView tvOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.);
        mDetector = new GestureDetectorCompat(this, new MyGestListener());
        tvOut =  findViewById(R.id.title);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class MyGestListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            tvOut.setText("onFling: " + event1.toString()+event2.toString());
            return true;
        }
    }

}

