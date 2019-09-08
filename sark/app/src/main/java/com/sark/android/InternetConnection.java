package com.sark.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class InternetConnection extends BroadcastReceiver{

    @Override
    public void onReceive(final Context context, Intent intent) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        boolean isConnected = wifi != null && wifi.isConnectedOrConnecting() || mobile != null && mobile.isConnectedOrConnecting();

        if (!isConnected) {
            Toast.makeText(context,"NO Internet Connection!",Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(context,"Back to Online!",Toast.LENGTH_SHORT).show();

    }
}
