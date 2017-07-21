package com.example.sensorlight;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import static android.content.Context.SENSOR_SERVICE;

public class MainActivity extends AppCompatActivity {

    private SensorManager manager;
    private Sensor defaultSensor;
    private MySensorEventListener mySensorEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//通过静态方法服务拿到传感器的经理,使用经理来获取对应的传感器对象
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
/* 同过经理得到所有的传感器
   List<Sensor> sensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL);
   高级For循环
   for(Sensor sensor: sensorList ){
   //打印传感器名称
   Log.d("light================","传感器的名称"+sensor.getName());
   }*/
        //获取光传感器
        defaultSensor = manager.getDefaultSensor(Sensor.TYPE_LIGHT);
        //创建自己定义的传感器监听对象
        mySensorEventListener = new MySensorEventListener();
        manager.registerListener(mySensorEventListener,defaultSensor,SensorManager.SENSOR_DELAY_FASTEST);

    }
    private  class MySensorEventListener implements SensorEventListener{
        //当传感器数据变化的回调方法
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float value = sensorEvent.values[0];
            Log.d("XX","脸大的程度改变光线值"+value);
        }
        //当传感器精度发生变化的时候调用的方法
        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除光传器监听
       manager.unregisterListener(mySensorEventListener);
        //把自己的传感器监听对象置为null
        mySensorEventListener=null;

    }
}
