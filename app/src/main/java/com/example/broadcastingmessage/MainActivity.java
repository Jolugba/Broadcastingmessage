package com.example.broadcastingmessage;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class MainActivity extends AppCompatActivity {

    private CustomReceiver mReceiver = new CustomReceiver();
    private static final String ACTION_CUSTOM_BROADCAST =
            BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";

    // Define the IntentFilter.
    IntentFilter filter = new IntentFilter();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Add system broadcast actions sent by the system when the power is
        // connected and disconnected.
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        filter.addAction(Intent.ACTION_POWER_CONNECTED);

        // Register the receiver using the activity context, passing in the
        // IntentFilter
        this.registerReceiver(mReceiver,filter);

        // Register the receiver to receive custom broadcast.
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver,new IntentFilter(ACTION_CUSTOM_BROADCAST));


    }


    public void sendCustomBroadcast(View view) {
        Intent customBroadcastIntent = new Intent(ACTION_CUSTOM_BROADCAST);
        LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent);
    }

    /**
     * Unregisters the broadcast receivers when the app is destroyed.
     */

    @Override
    protected void onDestroy() {
        this.unregisterReceiver(mReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
        super.onDestroy();

    }


}