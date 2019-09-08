package com.sark.android;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.sark.android.Play.HomeScreen;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Notification extends AppCompatActivity {
    ListView listView;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    User user;
    TextView nettextnotif;
    CountDownTimer countDownTimer;
    int timeValue = 5;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_homenot:
                    Intent intent=new Intent(Notification.this,MainActivity.class);
                    startActivity(intent);
                    break;
                case R.id.navigation_gamenot:
                    Intent intent2=new Intent(Notification.this,HomeScreen.class);
                    startActivity(intent2);
                    break;
                case R.id.aboutnot:
                    Intent intent3=new Intent(Notification.this,AboutUs.class);
                    startActivity(intent3);
                    break;
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification2);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        listView = findViewById(R.id.lv);
        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, R.layout.user, R.id.titletxt, list);
        user = new User();

        nettextnotif = findViewById(R.id.nettxtnotif);

        ref = database.getInstance().getReference("Notifications");
        try {

            countDownTimer = new CountDownTimer(8000, 1000) {
                public void onTick(long millisUntilFinished) {
                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            list.clear();
                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                user = ds.getValue(User.class);

                                if (user.getTitle().equals(""))
                                    list.add("No Current Notifications.");

                                else
                                    list.add(user.getTitle().toString() + "\nEvent Time: "
                                            + user.getEventtime() + "\n" + user.getDescrip() + "\n\n\t\t\t\t\t\t\t\t\t\t- " + user.getDateofnotific());

                            }
                            listView.setAdapter(adapter);
                            nettextnotif.setText("Done");

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    if (nettextnotif.getText().equals(""))
                        timeValue -= 1;

                    else {
                        countDownTimer.cancel();
                    }
                }

                //Now user is out of time
                public void onFinish() {
                    //We will navigate him to the time up activity using below method
                    final AlertDialog.Builder builder = new AlertDialog.Builder(Notification.this);
                    builder.setMessage("Couldn't load. Please check your internet connectivity and try again!");
                    builder.setCancelable(true);

                    builder.setNegativeButton("Reload", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(Notification.this, Notification.class);
                            startActivity(i);
                        }
                    });

                    builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(Notification.this, MainActivity.class);
                            startActivity(i);
                        }
                    });
                    AlertDialog alertdialog = builder.create();
                    alertdialog.show();
                    alertdialog.setCancelable(false);
                }
            }.start();
        }
        catch (Exception e)
                {

final AlertDialog.Builder builder=new AlertDialog.Builder(Notification.this);
        builder.setMessage("Couldn't load. Please check your internet connectivity and try again!");
        builder.setCancelable(true);

        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
@Override
public void onClick(DialogInterface dialog, int which) {
        Intent i=new Intent(Notification.this,MainActivity.class);
        startActivity(i);
        }
        });

        builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
@Override
public void onClick(DialogInterface dialog, int which) {
        Intent i=new Intent(Notification.this,MainActivity.class);
        startActivity(i);
        }
        });
        AlertDialog alertdialog=builder.create();
        alertdialog.show();
        alertdialog.setCancelable(false);
        e.printStackTrace();
        }

        }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(Notification.this,MainActivity.class);
        startActivity(i);
        finish();
    }
}

