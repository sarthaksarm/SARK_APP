package com.sark.android.Play;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.sark.android.MainActivity;
import com.sark.android.R;

import info.hoang8f.widget.FButton;

public class HomeScreen extends AppCompatActivity {
    FButton playGame,quit;
    TextView tQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        playGame =(FButton)findViewById(R.id.playGame);
        quit = (FButton) findViewById(R.id.quit);
        tQ = (TextView)findViewById(R.id.tQ);
        //PlayGame button - it will take you to the MainGameActivity
        playGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this,GameMain.class);
                startActivity(intent);
                finish();
            }
        });
        //Quit button - This will quit the game
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Typeface - this is for fonts style
//        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/shablagooital.ttf");
//        playGame.setTypeface(typeface);
//        quit.setTypeface(typeface);
//        tQ.setTypeface(typeface);
    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(HomeScreen.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
