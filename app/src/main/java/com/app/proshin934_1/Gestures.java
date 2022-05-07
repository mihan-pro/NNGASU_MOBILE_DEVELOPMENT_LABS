package com.app.proshin934_1;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.view.Menu;
import android.widget.TextView;

public class Gestures extends Activity implements OnGesturePerformedListener{

    GestureLibrary gLib;
    GestureOverlayView gestures;
    TextView tvOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestures);
        tvOut=findViewById(R.id.textView1);
        //Загрузка жестов (gestures) из res/raw/gestures
        gLib = GestureLibraries.fromRawResource(this, R.raw.gesture);
        if (!gLib.load()) {
            //Если жесты не загружены, то выход из приложения
            finish();
        }

        gestures = (GestureOverlayView) findViewById(R.id.gestureOverlayView1);
        gestures.addOnGesturePerformedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.gestures, menu);
        return true;
    }

    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        //Создаёт ArrayList c загруженными из gestures жестами
        ArrayList<Prediction> predictions = gLib.recognize(gesture);
        if (predictions.size() > 0) {
            //если загружен хотя бы один жест из gestures
            Prediction prediction = predictions.get(0);
            if (prediction.score > 1.0) {
                if (prediction.name.equals("one"))
                    tvOut.setText("1");
                else if (prediction.name.equals("two"))
                    tvOut.setText("2");
                else if (prediction.name.equals("three"))
                    tvOut.setText("3");
                else if (prediction.name.equals("four"))
                    tvOut.setText("4");
                else if (prediction.name.equals("five"))
                    tvOut.setText("5");
                else if (prediction.name.equals("six"))
                    tvOut.setText("6");
                else if (prediction.name.equals("seven"))
                    tvOut.setText("7");
                else if (prediction.name.equals("eight"))
                    tvOut.setText("8");
                else if (prediction.name.equals("nine"))
                    tvOut.setText("9");
            }else{
                tvOut.setText("Жест неизвестен");
            }
        }
    }

}
