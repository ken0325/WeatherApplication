package com.example.weatherapplication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class weatherDisasterWarningActivity extends OptionsMenuActivity {
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_disaster_warning);
        btn = findViewById(R.id.button_DefaultNotification);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAPI();
//                String title = ModelWeatherDisasterWarning.code;
//                showNotification(title, "message");
                showNotification();
            }
        });
    }
    public void startAPI() {
        ApiWeatherDisasterWarning apiController = new ApiWeatherDisasterWarning();
        apiController.start();
        try {
            apiController.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//    void showNotification(String title, String message) {
void showNotification(){
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("YOUR_CHANNEL_ID",
                    "YOUR_CHANNEL_NAME",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("YOUR_NOTIFICATION_CHANNEL_DESCRIPTION");
            mNotificationManager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), "YOUR_CHANNEL_ID");
        if (ModelWeatherDisasterWarning.warning.size() != 0) {
            mBuilder.setSmallIcon(R.mipmap.ic_launcher) // notification icon
                    .setContentTitle(ModelWeatherDisasterWarning.warning.get(0).title) // title for notification
                    .setContentText(ModelWeatherDisasterWarning.warning.get(0).text)// message for notification
                    .setAutoCancel(true); // clear notification after click
        } else {
            mBuilder.setSmallIcon(R.mipmap.ic_launcher) // notification icon
                    .setContentTitle("Weather Disaster Warning") // title for notification
                    .setContentText("No any Disaster Warning in now")// message for notification
                    .setAutoCancel(true); // clear notification after click
        }
        Intent intent = new Intent(getApplicationContext(), weatherDisasterWarningActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pi);
        mNotificationManager.notify(0, mBuilder.build());
    }
}
