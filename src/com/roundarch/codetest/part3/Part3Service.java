package com.roundarch.codetest.part3;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.List;
import java.util.Map;

import api.TestApiRestAdapter;
import constants.Constants;

public class Part3Service extends Service {

    private final String TAG = this.getClass().getSimpleName();

    // TODO - we can use this as the broadcast intent to filter for in our Part3Fragment
    public static final String ACTION_SERVICE_DATA_UPDATED = "com.roundarch.codetest.ACTION_SERVICE_DATA_UPDATED";

    private IBinder binder;
    private List<Map<String,String>> data = null;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: ");
        updateData();
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ");
        binder = new Part3ServiceBinder();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind: ");
        // TODO - this interface needs to be implemented to allow consumers
        // TODO - access to the data we plan to download
//        return new Part3ServiceBinder();
        return binder;
    }

    private void updateData() {
        // TODO - start the update process for our data
        TestApiRestAdapter restAdapter = new TestApiRestAdapter(getApplicationContext());
        restAdapter.getDataApi();
    }

    private void broadcastDataUpdated() {
        // TODO - send the broadcast -->*********** i sent the broadcast from the callback in the rest adapter**********

    }

    public final class Part3ServiceBinder extends Binder {
        // TODO - we need to expose our public IBinder API to clients

        public Part3Service getService() {
            return Part3Service.this;
        }
    }

    // TODO - eventually we plan to request JSON from the network, so we need
    // TODO - to implement a way to perform that off the main thread.  Then, once we
    // TODO - have the data we can parse it as JSON (using standard Android APIs is fine)
    // TODO - before finally returning to the main thread to store our data on the service.
    // TODO - Keep in mind that the service will keep a local copy and will need an interface
    // TODO - to allow clients to access it.

    // TODO - if you need a simple JSON endpoint, you can obtain the ZIP codes for the state
    // TODO - of Illinois by using this URL:
    //
    // TODO - http://gomashup.com/json.php?fds=geo/usa/zipcode/state/IL

}
