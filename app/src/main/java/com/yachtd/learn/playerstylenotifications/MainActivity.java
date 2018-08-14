package com.yachtd.learn.playerstylenotifications;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static final String CHANNEL_ID = "com.yachtd.learn.playerstylenotifications.channel";
    public static final int NOTIFICATION_ID = 412;

    private static final int REQUEST_CODE = 501;

    private static final String ACTION_MEDIA_PLAY = "com.yachtd.learn.playerstylenotifications.MEDIA_PLAY";
    private static final String ACTION_MEDIA_PAUSE = "com.yachtd.learn.playerstylenotifications.MEDIA_PAUSE";
    private static final String ACTION_MEDIA_STOP = "com.yachtd.learn.playerstylenotifications.MEDIA_STOP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(TAG, "onCreate: ");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "onStart: ");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: ");
        super.onPause();
    }

    @Override
    protected void onStop() {

        Log.d(TAG, "onStop: ");

        super.onStop();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, String.format("onNewIntent: action: %s.", intent.getAction()));
    }

    public void onCreateNotification(View view) {

        Log.d(TAG, "onCreateNotification: ");

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, MainActivity.CHANNEL_ID);

        Intent openUI = new Intent(this, MainActivity.class);
        openUI.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent contentIntent = PendingIntent.getActivity(this, MainActivity.REQUEST_CODE, openUI, PendingIntent.FLAG_CANCEL_CURRENT);

        // Play.
        Intent intentPlayAction = new Intent(this, MainActivity.class)
            .setAction(ACTION_MEDIA_PLAY)
            .setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        NotificationCompat.Action playAction =
                new NotificationCompat.Action(
                        R.drawable.ic_play_action,
                        "Play",
                        PendingIntent.getActivity(this, MainActivity.REQUEST_CODE, intentPlayAction, PendingIntent.FLAG_CANCEL_CURRENT));

        // Pause.
        Intent intentPauseAction = new Intent(this, MainActivity.class)
                .setAction(ACTION_MEDIA_PAUSE)
                .setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        NotificationCompat.Action pauseAction =
                new NotificationCompat.Action(
                        R.drawable.ic_pause,
                        "Pause",
                        PendingIntent.getActivity(this, MainActivity.REQUEST_CODE, intentPauseAction, PendingIntent.FLAG_CANCEL_CURRENT));

        // Stop.
        Intent intentStopAction = new Intent(this, MediaBroadcastReceiver.class)
                .setAction(Intent.ACTION_MEDIA_BUTTON);

        PendingIntent pendingStopAction = PendingIntent.getBroadcast(this, MainActivity.REQUEST_CODE, intentStopAction, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Action stopAction =
                new NotificationCompat.Action(
                        R.drawable.ic_stop,
                        "Stop",
                        pendingStopAction);


        builder
                .setSmallIcon(R.drawable.ic_play)
                .setContentTitle("LRN Player 1")
                .setContentIntent(contentIntent)
                .addAction(playAction)
                .addAction(pauseAction)
                .addAction(stopAction)
                .setStyle(
                        new android.support.v4.media.app.NotificationCompat.MediaStyle()
                                .setShowActionsInCompactView(0, 1, 2)
                                .setShowCancelButton(true));

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(MainActivity.NOTIFICATION_ID, builder.build());
    }

    @Override
    protected void onDestroy() {

        Log.d(TAG, "onDestroy: ");

        super.onDestroy();
    }
}
