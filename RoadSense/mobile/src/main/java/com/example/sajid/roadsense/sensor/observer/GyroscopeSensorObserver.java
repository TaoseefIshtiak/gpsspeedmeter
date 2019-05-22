package com.example.sajid.roadsense.sensor.observer;

/**
 * Created by Sajid on 4/11/2019.
 */

public interface GyroscopeSensorObserver
{
    /**
     * Notify observers when new gyroscope measurements are available.
     * @param gyroscope the rotation values (x, y, z)
     * @param timeStamp the time of the sensor update.
     */
    public void onGyroscopeSensorChanged(float[] gyroscope, long timeStamp);
}
