package com.developer.nguyenngocbaothy.android_ptit;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnMap, btnEmail;
    FloatingActionButton fabSharing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnMap = (Button) findViewById(R.id.btnMap);
        btnEmail = (Button) findViewById(R.id.btnEmail);
        fabSharing = (FloatingActionButton) findViewById(R.id.fabSharing);

        // animation float button sharing
        Animation animFloatButtonSharing = AnimationUtils.loadAnimation(this, R.anim.anim_floatbuttonsharing);
        fabSharing.startAnimation(animFloatButtonSharing);

        //pushNotification();

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MapActivity.class);
                startActivity(i);
            }
        });

        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, EmailActivity.class);
                startActivity(i);
            }
        });

        fabSharing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String shareBody = "your body here";
                String shareSub = "your subject here";
                intent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                intent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(intent, "Sharing via:"));
            }
        });
    }

    public void pushNotification() {
        NotificationManager notif=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notify= null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            notify = new Notification.Builder
                    (getApplicationContext()).setContentTitle("Message").setContentText("body").
                    setContentTitle("Has new food").setSmallIcon(R.drawable.places_ic_search).build();
        }

//        notify.flags |= Notification.FLAG_AUTO_CANCEL;
        notif.notify(0, notify);
    }


}
