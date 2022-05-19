package com.example.devicetracker;

import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public final class MainActivity extends AppCompatActivity {

    LightMeter lightMeter;
    TextView luxValue;

    VelocityMeter velocityMeter;
    TextView speedValue;
    Button calibrationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        lightMeter = new LightMeter(sensorManager);
        lightMeter.setOnValueChangedListener((value, timestamp) -> onLightValueChanged(value));

        luxValue = findViewById(R.id.luxValue);

        velocityMeter = new VelocityMeter(sensorManager);
        velocityMeter.setOnValueChangedListener((value, timestamp) -> onVelocityValueChanged(value));

        speedValue = findViewById(R.id.speedValue);

        calibrationButton = findViewById(R.id.calibrationButton);
        calibrationButton.setOnClickListener(view -> onCalibrationButtonClicked());
    }

    private void onCalibrationButtonClicked() {
        velocityMeter.calibrate();
    }

    private void onVelocityValueChanged(Vector3 value) {
        speedValue.setText(value.toString());
    }

    private void onLightValueChanged(Float value) {
        luxValue.setText(value.toString());
    }

    @Override
    protected void onPause() {
        lightMeter.pause();
        velocityMeter.pause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        lightMeter.resume();
        velocityMeter.resume();
    }
}