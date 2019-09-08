package com.sark.android;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

public class Splash extends AwesomeSplash {
    private TextView tv;
    private ImageView iv;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);
//      //  tv=(TextView)findViewById(R.id.txtsplash);
//        iv=(ImageView)findViewById(R.id.imgsplash);
//
//        Animation myanim= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
//     // tv.startAnimation(myanim);
//      iv.startAnimation(myanim);
//
//
//        Thread timer = new Thread(){
//            public void run()
//            {
//
//                try{
//                    Intent i= new Intent(Splash.this,MainActivity.class);
//                       sleep(4000);
//                       startActivity(i);
//
//
//                }
//                catch(InterruptedException e){
//                    e.printStackTrace();
//                }
//            }
//        };
//        timer.start();
//    }

    @Override
    public void initSplash(ConfigSplash configSplash) {


        configSplash.setBackgroundColor(R.color.white); //any color you want form colors.xml
        configSplash.setAnimCircularRevealDuration(2000); //int ms
        configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);  //or Flags.REVEAL_LEFT
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM); //or Flags.REVEAL_TOP

        //Choose LOGO OR PATH; if you don't provide String value for path it's logo by default

        //Customize Logo
        configSplash.setLogoSplash(R.drawable.sarkcap); //or any other drawable
        configSplash.setAnimLogoSplashDuration(2000); //int ms
        configSplash.setAnimLogoSplashTechnique(Techniques.FadeIn); //choose one form Techniques (ref: https://github.com/daimajia/AndroidViewAnimations)


        //Customize Path
        // configSplash.setPathSplash(SyncStateContract.Constants.DROID_LOGO); //set path String
        configSplash.setOriginalHeight(400); //in relation to your svg (path) resource
        configSplash.setOriginalWidth(400); //in relation to your svg (path) resource
        configSplash.setAnimPathStrokeDrawingDuration(3000);
        configSplash.setPathSplashStrokeSize(3); //I advise value be <5
        configSplash.setPathSplashStrokeColor(R.color.colorAccent); //any color you want form colors.xml
        configSplash.setAnimPathFillingDuration(3000);
        configSplash.setPathSplashFillColor(R.color.Wheat); //path object filling color


        //Customize Title
        configSplash.setTitleSplash("\"Where imagination transforms to innovation\""); //change your app name here
        configSplash.setTitleTextColor(R.color.splashtxt);
        configSplash.setTitleTextSize(17f); //float value
        configSplash.setAnimTitleDuration(3000);
        configSplash.setAnimTitleTechnique(Techniques.FadeInLeft);
       // configSplash.setTitleFont("fonts/pacifico.ttf");

    }

    @Override
    public void animationsFinished() {

        Intent intent=new Intent(Splash.this,MainActivity.class);
        startActivity(intent);

    }
}