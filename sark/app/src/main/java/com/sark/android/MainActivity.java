package com.sark.android;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sark.android.Play.HomeScreen;
import com.sark.android.TeamDevAchi.AchievementActivity;
import com.sark.android.TeamDevAchi.DeveloperActivity;
import com.sark.android.TeamDevAchi.TeamActivity;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.RandomTransitionGenerator;
import com.flaviofaria.kenburnsview.Transition;
import com.google.android.gms.appinvite.AppInvite;
import com.google.android.gms.appinvite.AppInviteInvitation;
import com.google.android.gms.appinvite.AppInviteInvitationResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;

public class MainActivity extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int REQUEST_INVITE = 0;

    private GoogleApiClient mGoogleApiClient;

    private DrawerLayout drawer;
    private NavigationView nv;
    CardView learn, Play, Chat, Noti;
    ImageView upcomimg;
    KenBurnsView kenBurnsView;
    private Boolean moving = true;
    String applink;
    DatabaseReference mFirebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(AppInvite.API)
                .enableAutoManage(this, this)
                .build();

        boolean autoLaunchDeepLink = true;
        AppInvite.AppInviteApi.getInvitation(mGoogleApiClient, this, autoLaunchDeepLink)
                .setResultCallback(
                        new ResultCallback<AppInviteInvitationResult>() {
                            @Override
                            public void onResult(AppInviteInvitationResult result) {
                                Log.d(TAG, "getInvitation:onResult:" + result.getStatus());
                                // Because autoLaunchDeepLink = true we don't have to do anything
                                // here, but we could set that to false and manually choose
                                // an Activity to launch to handle the deep link here.
                            }
                        });

        learn = (CardView) findViewById(R.id.learncard);
        Play = (CardView) findViewById(R.id.playcard);
        Chat = (CardView) findViewById(R.id.chatcard);
        Noti = (CardView) findViewById(R.id.botcard);
        upcomimg = (ImageView) findViewById(R.id.upeventimg);


//        kenBurnsView = (KenBurnsView) findViewById(R.id.imagemain);
//        AccelerateDecelerateInterpolator ACCELERATE_DECELERATE = new AccelerateDecelerateInterpolator();
//        RandomTransitionGenerator generator = new RandomTransitionGenerator(10000, ACCELERATE_DECELERATE);
//        kenBurnsView.setTransitionGenerator(generator);
//
//        kenBurnsView.setTransitionListener(onTransittionListener());
//
//        kenBurnsView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (moving) {
//                    kenBurnsView.pause();
//                    moving = false;
//                } else {
//                    kenBurnsView.resume();
//                    ;
//                    moving = true;
//                }
//            }
//        });

        upcomimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k = new Intent(MainActivity.this, UpcomingEvent.class);
                startActivity(k);
            }
        });
        learn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Loading...", Toast.LENGTH_LONG).show();
                Intent intent3 = new Intent(MainActivity.this, LearnActivity.class);
                startActivity(intent3);
            }
        });
        Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //             Toast.makeText(MainActivity.this, "Loading...", Toast.LENGTH_LONG).show();
                Intent intent4 = new Intent(MainActivity.this, HomeScreen.class);
                startActivity(intent4);
            }
        });

        Chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //             Toast.makeText(MainActivity.this, "Loading...", Toast.LENGTH_LONG).show();
                Intent intent5 = new Intent(MainActivity.this, ChatMain.class);
                startActivity(intent5);
            }
        });
        Noti.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Loading...", Toast.LENGTH_LONG).show();
                Intent intent5 = new Intent(MainActivity.this, Notification.class);
                startActivity(intent5);
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        nv = (NavigationView) findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                switch (id) {
                    case R.id.event:
//                        Toast.makeText(MainActivity.this, "Events Page!", Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this, "Loading...", Toast.LENGTH_LONG).show();
                        Intent intent9 = new Intent(MainActivity.this, OurEventActivity.class);
                        startActivity(intent9);
                        break;
                    case R.id.achievement:
                        //Toast.makeText(MainActivity.this, "Achievements Page", Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this, "Loading...", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this, AchievementActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.about:
                        //Toast.makeText(MainActivity.this, "About Page", Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this, "Loading...", Toast.LENGTH_LONG).show();
                        Intent intent5 = new Intent(MainActivity.this, AboutUs.class);
                        startActivity(intent5);
                        //return true;
                        break;
                    case R.id.team:
                        //Toast.makeText(MainActivity.this, "Our Team Page", Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this, "Loading...", Toast.LENGTH_LONG).show();
                        Intent intent1 = new Intent(MainActivity.this, TeamActivity.class);
                        startActivity(intent1);
                        //return true;
                        break;
                    case R.id.developers:
                        //Toast.makeText(MainActivity.this, "Developers Page", Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this, "Loading...", Toast.LENGTH_LONG).show();
                        Intent intent2 = new Intent(MainActivity.this, DeveloperActivity.class);
                        startActivity(intent2);
                        //return true;
                        break;
                    case R.id.share:

                        try {
                            mFirebaseDatabase = FirebaseDatabase.getInstance().getReference().child("PlayStore").child("Link");
                            View parentlayout1 = findViewById(android.R.id.content);
                            Snackbar snackbar1 = Snackbar.make(parentlayout1,"Spread it wider!",Snackbar.LENGTH_LONG);
                            snackbar1.show();
                            mFirebaseDatabase = FirebaseDatabase.getInstance().getReference().child("PlayStore").child("Link");

                            mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    applink=dataSnapshot.getValue().toString();

                                    Intent intent = new Intent(Intent.ACTION_SEND);
                                    intent.setType("text/plain");
                                    intent.putExtra(Intent.EXTRA_SUBJECT, "SARK");
                                    intent.putExtra(Intent.EXTRA_TEXT, "Install *SARK* App now! I recommend you, to use it. Join me there!"+applink); //give article's or app's link here
                                    startActivity(Intent.createChooser(intent, "Share!"));
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }
                        catch (Exception e)
                        {
                            View parentlayout = findViewById(android.R.id.content);
                            Snackbar snackbar = Snackbar.make(parentlayout,"Necessary packages, not available on your device! \" +\n" +
                                    "                            \"Kindly contact us directly at \\\"sitsark@gmail.com\\\"",Snackbar.LENGTH_LONG);
                            snackbar.show();
                        }

                        break;
                    case R.id.contact:
                        Intent intent8 = new Intent(MainActivity.this, ContactUs.class);
                        startActivity(intent8);
                        break;

                    case R.id.exit:
                        finishAffinity();
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
        showMessage(getString(R.string.google_play_services_error));
    }

    private void onCustomInviteClicked() {
        // When using the setEmailHtmlContent method, you must also set a subject using the
        // setEmailSubject message and you may not use either setCustomImage or setCallToActionText
        // in conjunction with the setEmailHtmlContent method.
        //
        // The "%%APPINVITE_LINK_PLACEHOLDER%%" token is replaced by the invitation server
        // with the custom invitation deep link based on the other parameters you provide.
        Intent intent = new AppInviteInvitation.IntentBuilder(getString(R.string.invitation_title))
                .setMessage(getString(R.string.invitation_message))
                .setDeepLink(Uri.parse(getString(R.string.invitation_deep_link)))
                .setEmailHtmlContent("<html><body>" +
                        "<h1>App Invites</h1>" +
                        "<a href=\"%%APPINVITE_LINK_PLACEHOLDER%%\">Install Now!</a>" +
                        "<body></html>")
                .setEmailSubject(getString(R.string.invitation_subject))
                .build();
        startActivityForResult(intent, REQUEST_INVITE);
    }
    // [END on_custom_invite_clicked]

    // [START on_activity_result]
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: requestCode=" + requestCode + ", resultCode=" + resultCode);

        if (requestCode == REQUEST_INVITE) {
            if (resultCode == RESULT_OK) {
                // Check how many invitations were sent and log a message
                // The ids array contains the unique invitation ids for each invitation sent
                // (one for each contact select by the user). You can use these for analytics
                // as the ID will be consistent on the sending and receiving devices.
                String[] ids = AppInviteInvitation.getInvitationIds(resultCode, data);
                Log.d(TAG, getString(R.string.sent_invitations_fmt, ids.length));
            } else {
                // Sending failed or it was canceled, show failure message to the user
                showMessage(getString(R.string.send_failed));
            }
        }
    }
    // [END on_activity_result]

    private void showMessage(String msg) {
        ViewGroup container = (ViewGroup) findViewById(R.id.snackbar_layout);
        Snackbar.make(container, msg, Snackbar.LENGTH_SHORT).show();
    }

    private void onInviteClicked() {
        Intent intent = new AppInviteInvitation.IntentBuilder(getString(R.string.invitation_title))
                .setMessage(getString(R.string.invitation_message))
                .setDeepLink(Uri.parse(getString(R.string.invitation_deep_link)))
                .setCustomImage(Uri.parse(getString(R.string.invitation_custom_image)))
                .setCallToActionText(getString(R.string.invitation_cta))
                .build();
        startActivityForResult(intent, REQUEST_INVITE);
    }

//    private KenBurnsView.TransitionListener onTransittionListener() {
//        return new KenBurnsView.TransitionListener() {
//
//            @Override
//            public void onTransitionStart(Transition transition) {
//
//                // Toast.makeText(MainActivity.this, "start", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onTransitionEnd(Transition transition) {
//                // Toast.makeText(MainActivity.this, "end", Toast.LENGTH_SHORT).show();
//            }
//        };
//    }

    @Override
    public void onBackPressed() {
       try{
           if (drawer.isDrawerOpen(GravityCompat.START)) {
               drawer.closeDrawer(GravityCompat.START);
           } else {
               final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
               builder.setMessage("Are you sure, want to exit?");
               builder.setCancelable(true);

               builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       finishAffinity();
                   }
               });

               builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       dialog.cancel();
                   }
               });
               AlertDialog alertdialog = builder.create();
               alertdialog.show();
           }
       }
      catch (Exception e)
      {
          Intent i=new Intent(MainActivity.this,MainActivity.class);
          startActivity(i);
      }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.invite_button:
                onInviteClicked();
                break;
        }
    }
}
