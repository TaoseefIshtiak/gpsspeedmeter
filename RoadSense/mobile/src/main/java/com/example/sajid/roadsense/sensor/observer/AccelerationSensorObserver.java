package com.example.sajid.roadsense.sensor.observer;

/**
 * Created by Sajid on 4/11/2019.
 */

public interface AccelerationSensorObserver
{
    /**
     * Notify observers when new acceleration measurements are available.
     * @param acceleration the acceleration values (x, y, z)
     * @param timeStamp the time of the sensor update.
     */
    public void onAccelerationSensorChanged(float[] acceleration,
                                            long timeStamp);
}
