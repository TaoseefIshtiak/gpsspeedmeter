package com.example.sajid.roadsense.sensor.observer;

/**
 * Created by Sajid on 4/11/2019.
 */

public interface AngularVelocitySensorObserver
{
    /**
     * Notify observers when new angular velocity measurements are available.
     * @param angularVelocity the angular velocity of the device (x,y,z).
     * @param timeStamp the time stamp of the measurement.
     */
    public void onAngularVelocitySensorChanged(float[] angularVelocity, long timeStamp);
}
