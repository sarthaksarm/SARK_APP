package com.example.sarthakmishra.sark;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AboutUs extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference mFirebaseDatabase;
    TextView abttxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

       final WebView webView = (WebView) findViewById(R.id.webview1);
       final WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        abttxt = findViewById(R.id.abouttxt);
        // t1=(TextView)findViewById(R.id.t1);
        // t1.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference().child("about");

       final String htmlText = " %s ";

        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String data = "";
                String appTitle = dataSnapshot.getValue().toString();
                abttxt.setText(appTitle);

                data=abttxt.getText().toString()+"";

                Toast.makeText(AboutUs.this,"Data: "+data,Toast.LENGTH_SHORT).show();

                String myData = "<html><body style=\"text-align:justify\">" + data + "<br></body></html>";

                // myData.replace("\\r\\n", "<br>").replace("\\n", "<br>");
                webView.loadDataWithBaseURL(null, String.format(htmlText, myData), "text/html", "utf-8", "utf-8");
                webSettings.setDefaultFontSize(19);
                webView.setBackgroundColor(000000);
                webView.setPaddingRelative(2, 2, 2, 2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//
//
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu7,menu);
        return super.onCreateOptionsMenu(menu);
    }*/

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        int id=item.getItemId();
//
//        if(id==android.R.id.home)
//            onBackPressed();
//
//        return super.onOptionsItemSelected(item);
//    }
}