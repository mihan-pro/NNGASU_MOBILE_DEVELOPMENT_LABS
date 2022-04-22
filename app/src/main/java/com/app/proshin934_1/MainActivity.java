package com.app.proshin934_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
    TextView title;
    EditText input;
    Button button;
    private int value;
    private boolean isGameFinished;
    private int target;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.gameTitle);
        input = findViewById(R.id.input);
        button = findViewById(R.id.button);
        target = getRandomNumber();
    }

    private int getRandomNumber() {
        return (int) ((Math.random() * (100 - 1)) + 1);
    }

    private void setInitialState() {
        target = getRandomNumber();
        isGameFinished = false;
        title.setText(R.string.try_to_guess);
        input.setText("");
        button.setText(R.string.input_value);
    }

    private boolean validateInput (String value)  {
        int valueAsInt;
        String currentValue = input.getText().toString();

        if(currentValue.length() == 0) {
            input.setError("Введите число!");
            return true;
        }
        valueAsInt = Integer.parseInt(currentValue);
        if(valueAsInt < 1) {
            input.setError("Введите число больше 0!");
            return true;
        }
        if(valueAsInt > 100) {
            input.setError("Введите число меньше 101!");
            return true;
        }
        return false;
    }

    private void checkResult (String value) {
        int valueAsInt = Integer.parseInt(value);
        if(valueAsInt < target) {
            title.setText(R.string.behind);
        }
        else if(valueAsInt > target) {
            title.setText(R.string.ahead);
        } else {
            isGameFinished = true;
            title.setText(R.string.hit);
            button.setText(R.string.play_more);
        }
    }

    public void onClickHandler (View view) {
        int valueAsInt;
        if(isGameFinished) {
            setInitialState();
            return;
        }

        String currentValue = input.getText().toString();
        boolean isInvalidValue = validateInput(currentValue);
        if(isInvalidValue) return;

        checkResult(currentValue);
    }

}