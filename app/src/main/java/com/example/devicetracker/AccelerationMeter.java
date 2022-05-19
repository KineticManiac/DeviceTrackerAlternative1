package com.example.devicetracker;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

//Evet, doğru kelimenin "accelerometer" olduğunu biliyorum.
//TYPE_LINEAR_ACCELERATION ile çalışamadım. Manuel olarak halledeceğim.
public final class LinearAccelerationMeter extends Meter<Vector3>{

    private final SensorManager sensorManager;
    private final Sensor sensor;
    private final AccelerationSensorEventListener listener;
    private Vector3 rawValue, gravityValue;

    public LinearAccelerationMeter(SensorManager sensorManager){
        this.sensorManager = sensorManager;
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        listener = new AccelerationSensorEventListener();
        rawValue = gravityValue = Vector3.ZERO;
    }

    @Override
    public void pause() {
        sensorManager.unregisterListener(listener);
    }

    @Override
    public void resume() {
        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void calibrate() {
        gravityValue = rawValue;
    }

    private class AccelerationSensorEventListener implements SensorEventListener{

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            rawValue = new Vector3(
                    sensorEvent.values[0],
                    sensorEvent.values[1],
                    sensorEvent.values[2]
            );

            setValue(Vector3.add(rawValue, biasValue), sensorEvent.timestamp);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) { }
    }
}
