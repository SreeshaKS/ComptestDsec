package com.compass.sreesha.comptest;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;


public class MainActivity extends ActionBarActivity implements SensorEventListener{



    private float currentDegree = 0f;
    private float degree=0;
    private float startOrientation;
    int swing=0;
    int flag=0;

    private SensorManager mSensorManager;
    int rotationCount=0;
    int subCount=0;

    TextView tvHeading;
    TextView countDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvHeading = (TextView) findViewById(R.id.tvHeading);
        countDisplay = (TextView)findViewById(R.id.count);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    public void onButtonCLick(View v)
    {
        startOrientation=degree;
        swing = 3;
        flag=1;
        rotationCount=0;



    }




    @Override
    protected void onResume() {
        super.onResume();

        // for the system's orientation sensor registered listeners
        mSensorManager.registerListener((android.hardware.SensorEventListener) this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // to stop the listener and save battery
        mSensorManager.unregisterListener((android.hardware.SensorEventListener) this);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {

        //float[] orientSwing=new float[40];


        // get the angle around the z-axis rotated
        degree = Math.round(event.values[0]);


        tvHeading.setText( Float.toString(degree) + " degrees");


        if(degree>=(startOrientation-swing)&&degree<=(startOrientation+swing)&&flag==1)
        {
           // onPause();
           /* try {
                //TimeUnit.NANOSECONDS.sleep(100);
                //TimeUnit.MICROSECONDS.sleep(100);
                //TimeUnit.MILLISECONDS.sleep(100);
                TimeUnit.SECONDS.sleep(2);
                //TimeUnit.MINUTES.sleep(100);
                //TimeUnit.HOURS.sleep(100);
                // TimeUnit.DAYS.sleep(100);
            } catch (InterruptedException e) {
                //Handle exception
            }*/
            subCount++;
            if(subCount==7) {

                rotationCount++;
                if(rotationCount==1)rotationCount--;
                countDisplay.setText("       Count = " + rotationCount);
                subCount=0;
            }
            //onResume();
        }

         currentDegree = -degree;

    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // not in use
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
