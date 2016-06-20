package dallepad.dallepad;

import android.app.ActionBar;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import android.content.Intent;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Set;


public class MainActivity extends Activity {

    private BluetoothAdapter BA;
    private Set<BluetoothDevice> pairedDevices;
    ListView lv;
    ArrayList list = new ArrayList();
    boolean state;
    Button connect;
    Button devices;
    TextView text;
    TextView textDevices;
    public static String EXTRA_ADDRESS = "device_address";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connect = (Button) findViewById(R.id.connect_button);
        devices = (Button) findViewById(R.id.devices_button);
        BA = BluetoothAdapter.getDefaultAdapter();
        lv = (ListView)findViewById(R.id.listView);
        text = (TextView) findViewById(R.id.textView3);
        textDevices = (TextView) findViewById(R.id.textView2);

        View decorView = getWindow().getDecorView();
// Hide both the navigation bar and the status bar.
// SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
// a general rule, you should design your app to hide the status bar whenever you
// hide the navigation bar.
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        connect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                state = BA.isEnabled();
                if (!state) {

                    Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(turnOn, 0);
                    Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                    startActivityForResult(getVisible, 0);
                }
                else
                {
                    Toast toast= Toast.makeText(getApplicationContext(),"Conectado.\n Selecione um dispositivo pareado",Toast.LENGTH_SHORT);
                    TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                    if( v != null) v.setGravity(Gravity.CENTER);
                    toast.show();
                }
            }
        });

        devices.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                getDevicesList();
            }
        });



    }

    private void getDevicesList(){
        pairedDevices = BA.getBondedDevices();
        ArrayList list = new ArrayList();
        textDevices.setText("Devices:");
        text.setText("");

        if (pairedDevices.size()>0)
        {
            for(BluetoothDevice bt : pairedDevices)
                 list.add(bt.getName() + "\n" + bt.getAddress());
            Toast.makeText(getApplicationContext(),"Mostrando dispositivos pareados",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Não foram encontrados dispositivos", Toast.LENGTH_LONG).show();
        }

        final ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,R.layout.list_color_text, list);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(myListClickListener);
    };

    private AdapterView.OnItemClickListener myListClickListener = new AdapterView.OnItemClickListener()
    {
        public void onItemClick (AdapterView<?> av, View v, int arg2, long arg3)
        {
            String info = ((TextView) v).getText().toString();
            String address = info.substring(info.length() - 17);

            Intent mainview = new Intent(MainActivity.this, MainView.class);
            mainview.putExtra(EXTRA_ADDRESS, address);
            startActivity(mainview);
        }
    };
}
