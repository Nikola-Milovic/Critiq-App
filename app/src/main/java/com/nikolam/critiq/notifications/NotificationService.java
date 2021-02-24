package com.nikolam.critiq.notifications;

import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.nikolam.critiq.R;

import timber.log.Timber;

public class NotificationService extends FirebaseMessagingService {

    public NotificationService() {
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
    }

    //most people just stick a last_notification_id in shared preferences or something if they want to cancel specific notification (e.g. if you visit a specific
    // fragment but not via the notification from the system tray), keeping it as the same will make sure previous notifications are overwritten with new content
    private void showNotification(String comment, String user, String postID){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "messages")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("New comment from " + user)
                .setContentText(comment)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManagerCompat managerCompat  = NotificationManagerCompat.from(this);
        managerCompat.notify(123, builder.build());
    }

    @Override
    public void onNewToken(@NonNull String s) {
        SharedPreferences prefs = getSharedPreferences("Firebase", MODE_PRIVATE);

        prefs.edit().putString("fcm_token", s).apply();

        Timber.d("New token is %s", s);

        super.onNewToken(s);
    }
}
