package com.example.devicetracker;

import android.hardware.SensorManager;

public final class VelocityMeter<TAccMeter extends Meter<TValue>, TValue>  extends Meter<TValue>{

    private static final double NS2S = 1.0 / 1000000000.0;

    private final TAccMeter accelerationMeter;
    private long lastTimestamp = -1;

    public VelocityMeter(SensorManager sensorManager){
        accelerationMeter = new TAccMeter(sensorManager);
        accelerationMeter.setOnValueChangedListener(new OnAccelarationValueChangedListener());
        value = Vector3.ZERO;
    }

    @Override
    public void pause() {
        accelerationMeter.pause();
    }

    @Override
    public void resume() {
        accelerationMeter.resume();
    }

    public void calibrate(){
        value = Vector3.ZERO;
        lastTimestamp = -1;
        accelerationMeter.calibrate();
    }

    private class OnAccelarationValueChangedListener implements Meter.OnValueChangedListener<Vector3>{

        @Override
        public void onValueChanged(Vector3 value, long timestamp) {
            if(lastTimestamp != -1) {
                long lTime = timestamp - lastTimestamp;
                double dTime = lTime * NS2S;

                Vector3 velocity = VelocityMeter.this.value;
                Vector3 change = Vector3.scale(value, dTime);

                velocity = Vector3.add(velocity, change);

                setValue(velocity, timestamp);
            }
            lastTimestamp = timestamp;
        }
    }
}
