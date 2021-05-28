package com.mygdx.game;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;



import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Set;
import java.util.UUID;

public class BluetoothActivity {
    public static final UUID MY_UUID = UUID.fromString("6160a667-a1b7-497f-8189-f4e327aff4c5");

    private static final int REQUEST_ENABLE_BT = 1;
    BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private BluetoothDevice mmDevice;
    private UUID deviceUUID;
    ConnectedThread mConnectedThread;

    String TAG = "MainActivity";
    EditText send_data;
    TextView view_data;

    public void pairDevice(View v) {

        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
        Log.e(TAG, " " + pairedDevices.size());
        if (pairedDevices.size() > 0) {
            Object[] devices = pairedDevices.toArray();
            BluetoothDevice device = (BluetoothDevice) devices[0];
            Log.e(TAG, " " + device);

            bluetoothAdapter.cancelDiscovery();

            ConnectThread connect = new ConnectThread(device, MY_UUID);
            connect.start();
        }
    }

    private class ConnectThread extends Thread {
        private BluetoothSocket mmSocket;

        public ConnectThread(BluetoothDevice device, UUID uuid) {
            Log.d(TAG, "ConnectThread: started");
            mmDevice = device;
            deviceUUID = uuid;
        }

        @Override
        public void run() {
            BluetoothSocket tmp = null;
            Log.i(TAG, "RUN mConnectThread ");

            try {
                Log.d(TAG, "ConnectThread: Trying to create Socket using UUID");
                tmp = mmDevice.createRfcommSocketToServiceRecord(MY_UUID);
            } catch (IOException e) {
                Log.e(TAG, "ConnectThread : Could not create Socket using UUID");
            }

            mmSocket = tmp;

            try {
                mmSocket.connect();
            } catch (IOException e) {
                try {
                    mmSocket.close();
                    Log.d(TAG, "run: Closed Socket.");
                } catch (IOException ioException) {
                    Log.e(TAG, "mConnectThread: run: Unable to close connection in socket ");
                }
                Log.d(TAG, "run: ConnectThread: Could not connect to UUID: " + MY_UUID);
            }

            connected(mmSocket);
        }

        public void cancel() {
            try {
                Log.d(TAG, "cancel: Closing client socket ");
                mmSocket.close();
            } catch (IOException e) {
                Log.e(TAG, "cancel: close() of mmSocket in ConnectThread failed " + e.getMessage());
            }
        }
    }

    private void connected(BluetoothSocket mmSocket){
        Log.d(TAG, "connected: Starting ");

        mConnectedThread = new ConnectedThread(mmSocket);
        mConnectedThread.start();
    }

    private class ConnectedThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket) {
            Log.d(TAG, "ConnectedThread: starting ");

            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            try {
                tmpIn = mmSocket.getInputStream();
                tmpOut = mmSocket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        @Override
        public void run() {
            byte[] buffer = new byte[1024];

            int bytes;

            while (true) {
                try {
                    bytes = mmInStream.read(buffer);
                    final String incomingMessage = new String(buffer, 0, bytes);
                    Log.d(TAG, "InputStream: " + incomingMessage);
                } catch (IOException e) {
                   Log.e(TAG, "write: Error reading InputStream. " + e.getMessage());
                   break;
                }
            }
        }

        public void write(byte[] bytes) {
            String text = new String(bytes, Charset.defaultCharset());
            Log.d(TAG, "write: writing to OutputStream " + text);
            try {
                mmOutStream.write(bytes);
            } catch (IOException e) {
                Log.e(TAG, "write: Error writing");
            }
        }

        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void SendMessage(View v) {
        byte[] bytes = send_data.getText().toString().getBytes(Charset.defaultCharset());
        mConnectedThread.write(bytes);
    }

    public void Start_Server(View view) {
        AcceptThread accept = new AcceptThread();
        accept.start();
    }

    private class AcceptThread extends Thread {
        private final BluetoothServerSocket mmServerSocket;

        public AcceptThread() {
            BluetoothServerSocket tmp = null;

            try {
                tmp = bluetoothAdapter.listenUsingInsecureRfcommWithServiceRecord("Bluetooth", MY_UUID);
                Log.d(TAG, "AcceptThread: Setting up Server using: " + MY_UUID);
            } catch (IOException e) {
                Log.e(TAG, "AcceptThread: IOException: " + e.getMessage());
            }

            mmServerSocket = tmp;
        }

        @Override
        public void run() {
            Log.d(TAG, "run: AcceptThread Running");

            BluetoothSocket socket = null;

            bluetoothAdapter.cancelDiscovery();

            try {
                Log.d(TAG, "run: RFCOM server socket start...");
                socket = mmServerSocket.accept();
                Log.d(TAG, "AcceptThread: RFCOM server socket accepted connection" );
            } catch (IOException e) {
                Log.e(TAG, " AcceptThread: IOException: " + e.getMessage());
            }

            if (socket != null) {
                connected(socket);
            }

            Log.i(TAG, "END mAcceptThread");
        }

        public void cancel() {
            try {
                mmServerSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}