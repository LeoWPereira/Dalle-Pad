package dallepad.dallepad;

/**
 * Created by luis on 10/04/2016.
 */

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;


public class MainView extends Activity {

    String address = null;
    private ProgressDialog progress;
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    private boolean isBtConnected = false;
    //SPP UUID. Look for it
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private static final String TAG = "MyActivity";
    TextView side;
    ImageButton effect_button1;
    ImageButton effect_button2;
    ImageButton effect_button3;
    ImageButton effect_button4;
    ImageButton effect_button5;
    ImageButton effect_button6;
    ImageButton effect_button7;
    ImageButton effect_button8;
    ImageButton effect_button9;
    ImageButton effect_button10;
    ImageButton effect_button11;
    ImageButton effect_button12;
    ImageButton effect_button13;
    ImageButton effect_button14;
    ImageButton effect_button15;
    ImageButton effect_button16;
    String convertInt;
    MidiPlayer midiPlayer;
    String[] separate = new String[2];
    boolean isRight = false;


    final int RECEIVE = 1;
    private StringBuilder sb = new StringBuilder();
    public MyHandler h;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent newint = getIntent();
        address = newint.getStringExtra(MainActivity.EXTRA_ADDRESS);

        setContentView(R.layout.main_view);


        midiPlayer = new MidiPlayer(this);
        midiPlayer.loadSounds();

        Button leftside = (Button) findViewById(R.id.left_button);
        Button rightside = (Button) findViewById(R.id.right_button);
        effect_button1 = (ImageButton) findViewById(R.id.button1);
        effect_button2 = (ImageButton) findViewById(R.id.button2);
        effect_button3 = (ImageButton) findViewById(R.id.button3);
        effect_button4 = (ImageButton) findViewById(R.id.button4);
        effect_button5 = (ImageButton) findViewById(R.id.button5);
        effect_button6 = (ImageButton) findViewById(R.id.button6);
        effect_button7 = (ImageButton) findViewById(R.id.button7);
        effect_button8 = (ImageButton) findViewById(R.id.button8);
        effect_button9 = (ImageButton) findViewById(R.id.button9);
        effect_button10 = (ImageButton) findViewById(R.id.button10);
        effect_button11 = (ImageButton) findViewById(R.id.button11);
        effect_button12 = (ImageButton) findViewById(R.id.button12);
        effect_button13 = (ImageButton) findViewById(R.id.button13);
        effect_button14 = (ImageButton) findViewById(R.id.button14);
        effect_button15 = (ImageButton) findViewById(R.id.button15);
        effect_button16 = (ImageButton) findViewById(R.id.button16);
        side = (TextView) findViewById(R.id.side);

        new ConnectBT().execute();

        rightside.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                function1();
            }
        });

        leftside.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                function2();
            }
        });

        effect_button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if(isRight == true) {
                    midiPlayer.changeEffect(0);
                }
                else{
                    midiPlayer.changeEffect(28);
                }
            }
        });
        effect_button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if(isRight == true) {
                    midiPlayer.changeEffect(1);
                }
                else{
                    midiPlayer.changeEffect(29);
                }
            }
        });
        effect_button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if(isRight == true) {
                    midiPlayer.changeEffect(2);
                }
                else{
                    midiPlayer.changeEffect(30);
                }
            }
        });
        effect_button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if(isRight == true) {
                    midiPlayer.changeEffect(3);
                }
                else{
                    midiPlayer.changeEffect(31);
                }
            }
        });
        effect_button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if(isRight == true) {
                    midiPlayer.changeEffect(4);
                }
                else{
                    midiPlayer.changeEffect(24);
                }
            }
        });
        effect_button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if(isRight == true) {
                    midiPlayer.changeEffect(5);
                }
                else{
                    midiPlayer.changeEffect(25);
                }
            }
        });
        effect_button7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if(isRight == true) {
                    midiPlayer.changeEffect(6);
                }
                else{
                    midiPlayer.changeEffect(26);
                }
            }
        });
        effect_button8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if(isRight == true) {
                    midiPlayer.changeEffect(7);
                }
                else{
                    midiPlayer.changeEffect(27);
                }
            }
        });
        effect_button9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if(isRight == true) {
                    midiPlayer.changeEffect(8);
                }
                else{
                    midiPlayer.changeEffect(20);
                }
            }
        });
        effect_button10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if(isRight == true) {
                    midiPlayer.changeEffect(9);
                }
                else{
                    midiPlayer.changeEffect(21);
                }
            }
        });
        effect_button11.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if(isRight == true) {
                    midiPlayer.changeEffect(10);
                }
                else{
                    midiPlayer.changeEffect(22);
                }
            }
        });
        effect_button12.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if(isRight == true) {
                    midiPlayer.changeEffect(11);
                }
                else{
                    midiPlayer.changeEffect(23);
                }
            }
        });
        effect_button13.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if(isRight == true) {
                    midiPlayer.changeEffect(12);
                }
                else{
                    midiPlayer.changeEffect(16);
                }
            }
        });
        effect_button14.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if(isRight == true) {
                    midiPlayer.changeEffect(13);
                }
                else{
                    midiPlayer.changeEffect(17);
                }
            }
        });
        effect_button15.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if(isRight == true) {
                    midiPlayer.changeEffect(14);
                }
                else{
                    midiPlayer.changeEffect(18);
                }
            }
        });
        effect_button16.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if(isRight == true) {
                    midiPlayer.changeEffect(15);
                }
                else{
                    midiPlayer.changeEffect(19);
                }
            }
        });

        h = new MyHandler();
    }

    public void onDestroy() {
        super.onDestroy();

        midiPlayer.release();
        Log.i(TAG, "MidiPlayer Released");
        try{
            btSocket.close();
            Log.i(TAG, "Socket closed");
        }
        catch (IOException e){}
    }

    private void msg(String s)
    {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
    }

    private void function1(){

        side.setText("Lado direito");
        isRight = true;

    }

    private void function2(){

        side.setText("Lado esquerdo");
        isRight = false;
    }

    private class ConnectBT extends AsyncTask<Void, Void, Void>
    {
        private boolean ConnectSuccess = true;

        @Override
        protected void onPreExecute()
        {
            progress = ProgressDialog.show(MainView.this, "Conectando", "Por favor espere!");
        }

        @Override
        protected Void doInBackground(Void... devices)
        {
            try
            {
                if (btSocket == null || !isBtConnected)
                {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();
                    BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);
                    btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    btSocket.connect();
                }
            }
            catch (IOException e)
            {
                ConnectSuccess = false;
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result)
        {
            super.onPostExecute(result);

            if (!ConnectSuccess)
            {
                msg("Conexão falhou");
                finish();
            }
            else
            {
                msg("Conectado");
                isBtConnected = true;
            }
            progress.dismiss();
            ConnectedThread bsl = new ConnectedThread(btSocket);
            Thread messageListener = new Thread(bsl);
            messageListener.start();
        }
    }

    private class CheckMessage implements Runnable {
        private String message;

        public CheckMessage(String message) {
            this.message = message;
        }

        public void run() {

            try {
                Log.i(TAG, message);
                separate = message.split(":");
                Log.i(TAG, separate[0]);
                Log.i(TAG, separate[1]);
                for(int i=0; i<32;i++) {
                    convertInt = Integer.toString(i);
                    if (separate[1].equals(convertInt)) {
                        Log.i(TAG, "eh "+ convertInt);
                        midiPlayer.playSound(i, separate[0]);
                    }
                }
            } catch(Exception e) { e.printStackTrace();}
        }
    }

    private class ConnectedThread extends Thread {
        private final InputStream InStream;

        public ConnectedThread(BluetoothSocket socket) {
            InputStream in = null;

            try {
                in = socket.getInputStream();
            } catch (IOException e) {
            }

            InStream = in;
        }

        public void run() {
            byte[] buffer = new byte[1024];
            int bytes;

            while (true) {
                try {
                    bytes = InStream.read(buffer);
                    h.obtainMessage(RECEIVE, bytes, -1, buffer).sendToTarget();
                } catch (IOException e) {
                    break;
                }
            }
        }

    }
    class MyHandler extends Handler{
        @Override
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case RECEIVE:
                    byte[] readBuf = (byte[]) msg.obj;
                    String strIncom = new String(readBuf, 0, msg.arg1);
                    sb.append(strIncom);
                    int endOfLineIndex = sb.indexOf("\r\n");
                    if (endOfLineIndex > 0) {
                        String sbprint = sb.substring(0, endOfLineIndex);
                        sb.delete(0, sb.length());
                        h.post(new CheckMessage(sbprint));
                    }
                    break;
            }
        }
    }

}
