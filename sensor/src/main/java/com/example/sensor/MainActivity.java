package com.example.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import static android.os.Build.VERSION_CODES.M;

public class MainActivity extends AppCompatActivity {

    private SensorManager manager;
    private Sensor defaultSensor;
    private SensorEventListener sensorEventListener;
    private MySensorEventListener mySensorEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        defaultSensor = manager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        mySensorEventListener = new MySensorEventListener();
        manager.registerListener(mySensorEventListener, defaultSensor,SensorManager.SENSOR_DELAY_NORMAL);





    }
            private  class  MySensorEventListener implements SensorEventListener{
                @Override
                public void onSensorChanged(SensorEvent sensorEvent) {
                    float value = sensorEvent.values[0];
                    if(value > 0 && value < 90){
                        Log.d("方向","东北");
                    }else if(value > 90 && value < 180){
                        Log.d("方向","东南");
                    }else if(value > 180 && value < 270){
                        Log.d("方向","西南");
                    }else if(value > 270 && value < 360){
                        Log.d("方向","西北");
                    }else if(value == 0){
                        Log.d("方向","北");
                    }else if(value == 90){
                        Log.d("方向","东");
                    }else if(value == 180){
                        Log.d("方向","南");
                    }else if(value == 270){
                        Log.d("方向","西");
                    }
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int i) {

                }
            }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        manager.unregisterListener(mySensorEventListener);
        mySensorEventListener=null;
    }
}
