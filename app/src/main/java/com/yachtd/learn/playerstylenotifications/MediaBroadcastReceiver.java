package com.yachtd.learn.playerstylenotifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MediaBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "MediaBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, String.format("onReceive: Action: %s.", intent.getAction()));
    }
}
