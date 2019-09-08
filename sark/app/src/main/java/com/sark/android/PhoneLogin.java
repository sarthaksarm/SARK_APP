package com.sark.android;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class PhoneLogin extends AppCompatActivity {
    private static final String TAG = "PhoneLogin";
    private boolean mVerificationInProgress = false;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private FirebaseAuth mAuth;
    TextView t1,t2;
    ImageView i1;
    EditText e1,e2;
    Button b1,b2;
    DatabaseReference rootRef,demoRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_login);

        rootRef = FirebaseDatabase.getInstance().getReference();

        demoRef = rootRef.child("Registrations");

        e1 = (EditText) findViewById(R.id.Phonenoedittext);
        b1 = (Button) findViewById(R.id.PhoneVerify);
        t1 = (TextView) findViewById(R.id.textView2Phone);
        i1 = (ImageView) findViewById(R.id.imageView2Phone);
        e2 = (EditText) findViewById(R.id.OTPeditText);
        b2 = (Button) findViewById(R.id.OTPVERIFY);
        t2 = (TextView) findViewById(R.id.textViewVerified);

        String id= getIntent().getStringExtra("id");
        String names= getIntent().getStringExtra("Name");
        String phoneno= getIntent().getStringExtra("Mobile");
        String usn= getIntent().getStringExtra("USN");
        String email= getIntent().getStringExtra("email");
        e1.setText(phoneno);

        mAuth = FirebaseAuth.getInstance();

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                // Log.d(TAG, "onVerificationCompleted:" + credential);
                mVerificationInProgress = false;
                Toast.makeText(PhoneLogin.this, "Verification Complete", Toast.LENGTH_SHORT).show();
                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Log.w(TAG, "onVerificationFailed", e);
                Toast.makeText(PhoneLogin.this, "Verification Failed", Toast.LENGTH_SHORT).show();
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    Toast.makeText(PhoneLogin.this, "Invalid Phone Number!", Toast.LENGTH_SHORT).show();
                    // ...
                } else if (e instanceof FirebaseTooManyRequestsException) {
                }

            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                // Log.d(TAG, "onCodeSent:" + verificationId);
                Toast.makeText(PhoneLogin.this, "Verification code has been sent on your number", Toast.LENGTH_SHORT).show();
                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;
                e1.setVisibility(View.GONE);
                b1.setVisibility(View.GONE);
                t1.setVisibility(View.GONE);
                i1.setVisibility(View.GONE);
                t2.setVisibility(View.VISIBLE);
                e2.setVisibility(View.VISIBLE);
                b2.setVisibility(View.VISIBLE);
                // ...
            }
        };

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (e1.getText().toString().equals(""))
                    Toast.makeText(PhoneLogin.this, "Please enter no.", Toast.LENGTH_SHORT).show();

                else
                {
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            "+91"+e1.getText().toString(),
                            60,
                            java.util.concurrent.TimeUnit.SECONDS,
                            PhoneLogin.this,
                            mCallbacks);
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (e2.getText().toString().equals(""))
                    Toast.makeText(PhoneLogin.this, "Please enter Code. It can not be left empty!", Toast.LENGTH_SHORT).show();

                else {
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, e2.getText().toString());
                    // [END verify_with_code]
                    signInWithPhoneAuthCredential(credential);
                }
            }
        });

    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Log.d(TAG, "signInWithCredential:success");
                            //startActivity(new Intent(PhoneLogin.this,Home.class));
                            Toast.makeText(PhoneLogin.this,"Verification Done", Toast.LENGTH_SHORT).show();

                            String id= getIntent().getStringExtra("id");
                            String names= getIntent().getStringExtra("Name");
                            String phoneno= getIntent().getStringExtra("Mobile");
                            String usn= getIntent().getStringExtra("USN");
                            String email= getIntent().getStringExtra("email");

                            demoRef.child(id).child("Name").setValue(names);
                            demoRef.child(id).child("Mobile").setValue(phoneno);
                            demoRef.child(id).child("email").setValue(email);
                            demoRef.child(id).child("USN").setValue(usn);
                            demoRef.child(id).child("id").setValue(id);


                            final AlertDialog.Builder builder=new AlertDialog.Builder(PhoneLogin.this);
                            builder.setMessage("Congratulations! You have successfully registered in the event!");
                            builder.setCancelable(true);

                            builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent i=new Intent(PhoneLogin.this,MainActivity.class);
                                    startActivity(i);
                                }
                            });

                            builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent i=new Intent(PhoneLogin.this,MainActivity.class);
                                    startActivity(i);
                                }
                            });
                            AlertDialog alertdialog=builder.create();
                            alertdialog.setCancelable(false);
                            alertdialog.show();

                            // ...
                        } else {
                            // Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(PhoneLogin.this,"Invalid Verification", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(PhoneLogin.this,MainActivity.class);
        startActivity(i);
        finish();
    }
}
