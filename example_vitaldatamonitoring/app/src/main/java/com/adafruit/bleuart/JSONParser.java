package com.adafruit.bleuart;

import android.bluetooth.BluetoothGattCharacteristic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class JSONParser implements SensorData {

    JSONObject jsonObject;

    public JSONParser(BluetoothGattCharacteristic rx) {
        //String is here for testing purposes, is going to be removed when everything is done.
//        String result = "{\"PulseSensor\":[ {\"HeartRateIntensity\":180, \"TimeFromLastBeat\":100}, {\"HeartRateIntensity\":10, \"TimeFromLastBeat\":10}, {\"HeartRateIntensity\":200, \"TimeFromLastBeat\":130}],\"GyroSensor\":[{\"DeltaGyro\":60,\"DeltaAccelerometer\":20},{\"DeltaGyro\":50,\"DeltaAccelerometer\":330}]}";
        String result = rx.getStringValue(0);
        try {
            jsonObject = new JSONObject(result);
        } catch (JSONException e) {
            System.err.println("Error while creating the JSONParser object");
        }
    }

    @Override
    public int[] getHeartRateIntensity() {
        return getDataSet("ps", "HRI");
    }
    @Override
    public int[] getTimeBetweenHeartBeats() {
        return getDataSet("ps", "t");

    }
    @Override
    public int[] getDeltaGyro() {
        return getDataSet("gs","g");
    }
    @Override
    public int[] getDeltaAccelerometer() {
        return getDataSet("gs", "a");
    }
    @Override
    public int[]getAccelerometerTime(){
        return getDataSet("gs","t");
    }

    @Override
    public int[] getSkinHumidData() {
        return getDataSet("hs", "a");
    }

    /**
     * Gets the data from a JSON object.
     *
     * @param sensor   Flag to determine from which sensor we get the data.
     * @param dataName Flag to determine which dataset we want.
     * @return Array that contains all values from the JSON for a specific data type.
     */
    private int[] getDataSet(String sensor, String dataName) {
        int dataset[] = new int[0];
        try {
            JSONArray sensorData = jsonObject.getJSONArray(sensor);
            dataset = new int[sensorData.length()];
            for (int i = 0; i < sensorData.length(); i++) {
                dataset[i] = getSpecificSensorValue(dataName, sensorData, i);
            }
        } catch (Exception e) {
            System.err.println("Error while retrieving data from JSON");
        }
        return dataset;
    }

    /**
     * Gets a specific value from a JSON array.
     *
     * @param sensorValueName Name for the key which contains the value we need.
     * @param sensorData      JSON array which contains the data.
     * @param index           index for the array, which determines which dataset to get
     * @return A specific value from the sensor.
     */
    private int getSpecificSensorValue(String sensorValueName, JSONArray sensorData, int index) {
        int specificValue = 0;
        try {
            JSONObject dataPair = sensorData.getJSONObject(index);
            specificValue = dataPair.getInt(sensorValueName);
        } catch (JSONException e) {
            System.err.println("Error while getting specific data.");
        }
        return specificValue;
    }
}
