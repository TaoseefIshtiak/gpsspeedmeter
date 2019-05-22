package com.example.sajid.roadsense.sensor.observer;

/**
 * Created by Sajid on 4/11/2019.
 */

public interface MagneticSensorObserver
{
    /**
     * Notify observers when new magnetic measurements are available.
     *
     * @param magnetic
     *            the magnetic measurements (x, y, z).
     * @param timeStamp
     *            the time stamp of the measurement.
     */
    public void onMagneticSensorChanged(float[] magnetic, long timeStamp);
}
