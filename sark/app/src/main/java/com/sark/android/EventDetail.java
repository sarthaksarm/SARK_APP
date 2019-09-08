package com.sark.android;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class EventDetail extends AppCompatActivity {

    private DatabaseReference imref,imtle,imExdec,lref;
    TextView tilt,desc,nettextevent;
    ImageView upcomimg;
    Button register;
    CountDownTimer countDownTimer;

    char ch;
    int timeValue = 5;

    private static FirebaseDatabase mDatabase;

    public static FirebaseDatabase getDatabase() {
        if (mDatabase == null) {
            mDatabase = FirebaseDatabase.getInstance();
            mDatabase.setPersistenceEnabled(true);
        }
        return mDatabase;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        tilt=(TextView)findViewById(R.id.Tex1);
        desc=(TextView)findViewById(R.id.Desc);
        upcomimg=(ImageView)findViewById(R.id.upcom);
        register=(Button)findViewById(R.id.Register);

      nettextevent=findViewById(R.id.nettxtevent);


        try{

            FileInputStream fis=EventDetail.this.openFileInput("file");
            InputStreamReader isr=new InputStreamReader(fis);
            BufferedReader bufferedReader=new BufferedReader(isr);

            String line;
            while((line=bufferedReader.readLine())!=null)
            {
//                Toast.makeText(EventDetail.this, ""+line, Toast.LENGTH_SHORT).show();

                ch=line.charAt(line.length()-1);

            }

            sendKey(ch);

        }
        catch (IOException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(EventDetail.this,MainActivity.class);
        startActivity(i);
    }

    public void sendKey(char  key)
    {

        if(key!='0')
        {
            register.setEnabled(false);
        }
        else {
            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(EventDetail.this,Register.class);
                    startActivity(intent);

                }
            });
        }

        imref= FirebaseDatabase.getInstance().getReference().child("Upcoming").child(key+"").child("image");
        imtle=FirebaseDatabase.getInstance().getReference().child("Upcoming").child(key+"").child("title");
        imExdec=FirebaseDatabase.getInstance().getReference().child("Upcoming").child(key+"").child("detail");


        imtle.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String dap1=dataSnapshot.getValue().toString();
                tilt.setText(dap1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        imExdec.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String dap3=dataSnapshot.getValue().toString();
                desc.setText(dap3);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        imref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String dap=dataSnapshot.getValue().toString();
                Picasso.get().load(dap).placeholder(R.drawable.back).into(upcomimg);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
