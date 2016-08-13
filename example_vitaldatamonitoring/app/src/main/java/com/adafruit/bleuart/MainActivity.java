package com.adafruit.bleuart;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


public class MainActivity extends Activity implements BluetoothLeUart.Callback {
    // UI elements
    private TextView messages;
    private TextView deviceStatus;
    private LineGraphSeries<DataPoint> heartRateSeries;
    private LineGraphSeries<DataPoint> accelerometerSeries;
    private LineGraphSeries<DataPoint> gyroSeries;
    private LineGraphSeries<DataPoint> skinHumidSeries;
    private int lastX = 0;
    private int AccX = 0;
    private int skinHumidityX = 0;
    private SensorData sensorData;


    //  Bluetooth LE UART instance.  This is defined in BluetoothLeUart.java.
    private BluetoothLeUart uart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeGraphs();




        // Grab references to UI elements.
        messages = (TextView) findViewById(R.id.messages);
        deviceStatus = (TextView) findViewById(R.id.deviceStatus);

//        addEntry();
        // Initialize UART.
        uart = new BluetoothLeUart(getApplicationContext());

        uart.registerCallback(this);
        uart.connectFirstAvailable();
    }

    // Write some text to the messages text view.
    // Care is taken to do this on the main UI thread so writeLine can be called from any thread
    // (like the BTLE callback).
    private void writeConnectionInfo(final CharSequence text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                messages.append(text);
                messages.append("\n");
            }
        });
    }

    // OnCreate, called once to initialize the activity.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // OnResume, called right before UI is displayed.  Connect to the bluetooth device.
    @Override
    protected void onResume() {
        super.onResume();
        deviceStatus.setTextColor(Color.BLUE);
        deviceStatus.setText("Scanning...");

        uart.registerCallback(this);
        uart.connectFirstAvailable();
    }

    // OnStop, called right before the activity loses foreground focus.  Close the BTLE connection.
    @Override
    protected void onStop() {
        super.onStop();
        deviceStatus.setTextColor(Color.RED);
        deviceStatus.setText("Disconnected");

        uart.unregisterCallback(this);
        uart.disconnect();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // UART Callback event handlers.
    @Override
    public void onConnected(BluetoothLeUart uart) {
        // Called when UART device is connected and ready to send/receive data.
        deviceStatus.setTextColor(Color.GREEN);
        deviceStatus.setText("Connected");
    }

    @Override
    public void onConnectFailed(BluetoothLeUart uart) {
//      Called when some error occured which prevented UART connection from completing.
        deviceStatus.setTextColor(Color.RED);
        deviceStatus.setText("Failed to Connect");
    }

    @Override
    public void onDisconnected(BluetoothLeUart uart) {
        // Called when the UART device disconnected.
        deviceStatus.setTextColor(Color.RED);
        deviceStatus.setText("Disconnected");
    }

    @Override
    public void onReceive(BluetoothLeUart uart, BluetoothGattCharacteristic rx) {
        sensorData = new JSONParser(rx);
        addEntry();
    }

    @Override
    public void onDeviceFound(BluetoothDevice device) {
        deviceStatus.setTextColor(Color.MAGENTA);
        deviceStatus.setText("" + device.getName());
    }

    @Override
    public void onDeviceInfoAvailable() {
        writeConnectionInfo(uart.getDeviceInfo());
    }

    public void onClick(View view) {
        if (view.getId() == R.id.btn_stop_connection) {
            onStop();
            onDisconnected(uart);
        } else if (view.getId() == R.id.btn_start_connection) {
            deviceStatus.setTextColor(Color.BLUE);
            deviceStatus.setText("Scanning...");
            uart.registerCallback(this);
            uart.connectFirstAvailable();
        }
    }

    // Wrapper function so we do not bloat our onCreate
    private void initializeGraphs() {
        initializeHeartRateGraph();
        initializeAccelerometerGraph();
        initializeSkinHumidGraph();
    }

    private void initializeHeartRateGraph(){
        GraphView heartRateGraph = (GraphView) findViewById(R.id.HeartRateGraph);

        heartRateSeries = new LineGraphSeries<DataPoint>();
        heartRateSeries.setTitle("Heart Rate Data");

        heartRateGraph.addSeries(heartRateSeries);
        heartRateGraph.getGridLabelRenderer().setHorizontalAxisTitle("Zeit");
        heartRateGraph.getGridLabelRenderer().setVerticalAxisTitle("Herzschlag");
        heartRateGraph.getLegendRenderer().setVisible(true);
    }

    private void initializeAccelerometerGraph() {
        GraphView accelerometerGraph = (GraphView) findViewById(R.id.AccelerometerGraph);

        accelerometerSeries = new LineGraphSeries<DataPoint>();
        accelerometerSeries.setColor(Color.GREEN);
        gyroSeries = new LineGraphSeries<DataPoint>();
        accelerometerSeries.setTitle("Accelerometer Data");

        accelerometerGraph.addSeries(accelerometerSeries);
        accelerometerGraph.addSeries(gyroSeries);
        accelerometerGraph.getGridLabelRenderer().setHorizontalAxisTitle("Zeit");
        accelerometerGraph.getGridLabelRenderer().setVerticalAxisTitle("DeltaGyro");
        accelerometerGraph.getLegendRenderer().setVisible(true);
    }

    private void initializeSkinHumidGraph() {
        GraphView skinHumidGraph = (GraphView) findViewById(R.id.SkinHumidGraph);

        skinHumidSeries = new LineGraphSeries<DataPoint>();
        skinHumidSeries.setTitle("Skin Resistance");

        skinHumidGraph.addSeries(skinHumidSeries);
        skinHumidGraph.getGridLabelRenderer().setHorizontalAxisTitle("Zeit");
        skinHumidGraph.getGridLabelRenderer().setVerticalAxisTitle("Hautfeuchtigkeit");
        skinHumidGraph.getLegendRenderer().setVisible(true);
    }

    private void addEntry() {
        addHeartRateEntry();
        addGyroSensorData();
        addHumiditySensorData();
    }

    private void addHeartRateEntry() {
        int[] heartRateIntensity = sensorData.getHeartRateIntensity();
        int[] timeBetweenBeats = sensorData.getTimeBetweenHeartBeats();
        for (int i = 0; i < heartRateIntensity.length; i++) {
            lastX += timeBetweenBeats[i];
            heartRateSeries.appendData(new DataPoint(lastX, heartRateIntensity[i]), true, 10);
        }
    }

    private void addGyroSensorData() {
        int [] gyroDelta = sensorData.getDeltaGyro();
        int [] deltaAccelerometer = sensorData.getDeltaAccelerometer();
        for (int i = 0; i < gyroDelta.length; i++) {
            accelerometerSeries.appendData(new DataPoint(AccX, gyroDelta[i]), true, 10);
            gyroSeries.appendData(new DataPoint(AccX, deltaAccelerometer[i]), true, 10);
            AccX++;
        }
    }
    private void addHumiditySensorData(){
       int [] skinHumidity = sensorData.getSkinHumidData();
        for (int i = 0; i < skinHumidity.length; i++) {
            skinHumidSeries.appendData(new DataPoint(skinHumidityX++, skinHumidity[i]), true, 10);
        }
    }
}