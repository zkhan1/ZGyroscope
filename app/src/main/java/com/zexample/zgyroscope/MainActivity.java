package com.zexample.zgyroscope;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private String TAG = "Gyroscope";
    SensorManager sensorManager;
    Sensor mGyroscope;
    TextView xTxtView, yTxtView, zTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        xTxtView = (TextView) findViewById(R.id.xtxtview);
        yTxtView = (TextView) findViewById(R.id.ytxtview);
        zTxtView = (TextView) findViewById(R.id.ztxtview);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mGyroscope = sensorManager.getDefaultSensor(mGyroscope.TYPE_GYROSCOPE);

        if (mGyroscope != null) {
            Log.d(TAG, "Gyroscope is available");
        } else {
            Log.d(TAG, "Gyroscope is not supported");
        }
    }



    public SensorEventListener gyroListener = new SensorEventListener() {
        public void onAccuracyChanged(Sensor sensor, int acc) {
        }

        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            xTxtView.setText("X-axis: " + (int) x + " rad/s");
            yTxtView.setText("Y-axis: " + (int) y + " rad/s");
            zTxtView.setText("Z-axis: " + (int) z + " rad/s");
        }
    };


    public void onResume() {
        super.onResume();
        sensorManager.registerListener(gyroListener, mGyroscope, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onStop() {
        super.onStop();
        sensorManager.unregisterListener(gyroListener);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}