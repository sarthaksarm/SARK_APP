package com.sark.android;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    private TextView mTextMessage;
    EditText nameet,emailet,phoneet,usnet;
    String names,email,usn,phoneno;
    Button submit;
    DatabaseReference rootRef,demoRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        rootRef = FirebaseDatabase.getInstance().getReference();

        demoRef = rootRef.child("Registrations");

        nameet=(EditText)findViewById(R.id.nameet);
        emailet=(EditText)findViewById(R.id.emailet);
        phoneet=(EditText)findViewById(R.id.phoneet);
        usnet=(EditText)findViewById(R.id.usnet);
        submit=(Button)findViewById(R.id.submitbtn);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                names=nameet.getText().toString();
                email=emailet.getText().toString();
                usn=usnet.getText().toString();
                phoneno=phoneet.getText().toString();

                if(names.equals("")||email.equals("")||usn.equals("")||phoneno.equals(""))
                {
                    Toast.makeText(Register.this,"All fields are mandatory to fill!", Toast.LENGTH_LONG).show();

                }
                else
                {
                    String id="us"+ System.currentTimeMillis();
                    if(phoneno.length()==10)
                    {
                        nameet.setText("");
                        emailet.setText("");
                        phoneet.setText("");
                        usnet.setText("");

                        Intent i=new Intent(Register.this,PhoneLogin.class);

                        i.putExtra("Name",names);
                        i.putExtra("Mobile",phoneno);
                        i.putExtra("email",email);
                        i.putExtra("USN",usn);
                        i.putExtra("id",id);

                        startActivity(i);

                    }

                    else
                    {
                        Toast.makeText(Register.this,"Please check phone number!", Toast.LENGTH_LONG).show();

                    }
                } }
        });


    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(Register.this,MainActivity.class);
        startActivity(i);
        finish();
    }
}
