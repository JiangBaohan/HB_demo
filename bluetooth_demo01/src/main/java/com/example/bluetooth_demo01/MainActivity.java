package com.example.bluetooth_demo01;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import java.util.Set;

/**
 * 蓝牙：
 * 蓝牙必须添加的两个权限
 <uses-permission android:name="android.permission.BLUETOOTH"/>
 <uses-permission android:name="android.permission.BLUETOOTH_ADMIN"/>
 * 开启
 * 关闭
 * 搜索
 * 停止
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1= (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);
        Button button2= (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);
        Button button3= (Button) findViewById(R.id.button3);
        button3.setOnClickListener(this);
        //获取蓝牙适配器
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button1:
                //打开
                Intent intent=new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,300);
                startActivity(intent);
                break;
            case R.id.button2:
                //关闭
                bluetoothAdapter.disable();

                break;
            case R.id.button3:
                //搜索：进行其他设备的扫描
                //     获取到扫描的其他蓝牙设备，返回结果集（获取到已经配对的设备）
                Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();
                for (BluetoothDevice n: bondedDevices) {
                    //设备名称
                    String name = n.getName();
                    //设备地址
                    String address = n.getAddress();
                    Log.d("HB","设备名称:"+name+"\n设备地址:"+address);
                }
                break;
        }
        //停止蓝牙搜索
        bluetoothAdapter.cancelDiscovery();
    }
}
