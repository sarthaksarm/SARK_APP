package com.example.sarthakmishra.sark;

import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ContactUs extends AppCompatActivity {
Button msgbtn,callbtn,webbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        msgbtn=(Button)findViewById(R.id.msgbtn);
        callbtn=(Button)findViewById(R.id.callbtn);
        webbtn=(Button)findViewById(R.id.webbtn);

        msgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:7004297271"));
                i.putExtra("address", "7004297271");
//                i.putExtra("sms_body", "HI, THIS IS SARTHAK MISHRA");
                startActivity(i);
            }
        });

        callbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:7004297271"));
                startActivity(i);
            }
        });

        webbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.sitsark.in/"));
                startActivity(browserIntent);

             }
        });


    }
}
