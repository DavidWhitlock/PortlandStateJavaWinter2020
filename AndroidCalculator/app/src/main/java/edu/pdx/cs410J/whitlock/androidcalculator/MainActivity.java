package edu.pdx.cs410J.whitlock.androidcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button launchCalculator = findViewById(R.id.launch_calculator);
        View.OnClickListener listener = new TellUserIWasClicked();
        launchCalculator.setOnClickListener(listener);
    }

    private static class TellUserIWasClicked implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Snackbar.make(v, "I was clicked", 1000).show();
        }
    }
}