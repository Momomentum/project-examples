package com.adafruit.bleuart;

/**
 * Created by regrau on 13.07.16.
 */
public interface SensorData {

    int[] getHeartRateIntensity();
    int[] getTimeBetweenHeartBeats();
    int[] getDeltaGyro();
    int[] getDeltaAccelerometer();
    int[] getAccelerometerTime();
    int[] getSkinHumidData();

}
