package dallepad.dallepad;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by luis on 10/04/2016.
 */
public class ConnectionSettings {

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

    public BluetoothAdapter getBluetoothAdapter(){
        return BA;
    };




}
