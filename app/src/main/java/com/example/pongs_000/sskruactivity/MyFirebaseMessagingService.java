package com.example.pongs_000.sskruactivity;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Pong_pk007 on 06/07/2560.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMessagingServ";

    public MyFirebaseMessagingService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        if (remoteMessage.getData().size() > 0){
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            try {
                JSONObject data = new JSONObject(remoteMessage.getData());
                String jsonMessage = data.getString("extra_information");
                Log.d(TAG, "onMessageReceived: \n" +
                        "Extra Information: " +jsonMessage);
                playNotificationSound();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (remoteMessage.getNotification() != null){
            String title = remoteMessage.getNotification().getTitle();//get title
            String message = remoteMessage.getNotification().getBody();//get message
            String click_action = remoteMessage.getNotification().getClickAction();

            Log.d(TAG, "Message Notification Title: " + title);
            Log.d(TAG, "Message Notification Body: " + message);
            Log.d(TAG, "Message Notification click_action: " + click_action);

            showNotification(message , title , click_action);
        }

    }

    @Override
    public void onDeletedMessages() {

    }

    private void showNotification(String message , String title, String click_action) {
        Intent i;
            if (click_action.equals("MAINACTIVITY")){
                i = new Intent(this, Main.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }else if (click_action.equals("PROFILE_ACTIVITY")){
                i = new Intent(this, ProfileActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }else {
                i = new Intent(this, Main.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }
            playNotificationSound();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setAutoCancel(true)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(0,builder.build());
    }

    private void handleDataMessage(JSONObject json) {

    }
    // Playing notification sound
    public void playNotificationSound() {
        try {
            Uri alarmSound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                    + "://" + this.getPackageName() + "/raw/save_and_checkout");
            Ringtone r = RingtoneManager.getRingtone(this, alarmSound);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
