package com.tellstorygames.sevimliarkadaslar;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import java.util.concurrent.TimeUnit;

public class AnaMenu extends AppCompatActivity {
    private SoundPlayer soundAna;
    static int karakter;
    private ImageView cikcikisk;
    private ImageView bulutisk;
    private ImageView balonisk;
    private ImageView ece;
    private ImageView eceWin;
    private ImageView maymon;
    private ImageView muteTus;

    private AnimationDrawable cikcikMenuAnimation;
    private AnimationDrawable balonMenuAnimation;
    private AnimationDrawable bulutMenuAnimation;
    private AnimationDrawable mymnMenuAnm;
    private AnimationDrawable eceUzgunAnm;
    private AnimationDrawable eceWinAnm;

    public static boolean mut = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana_menu);

        soundAna = new SoundPlayer(this);

        cikcikisk = (ImageView) findViewById(R.id.cikcikmenu);
        bulutisk = (ImageView) findViewById(R.id.bulutmenu);
        balonisk = (ImageView) findViewById(R.id.balonmenu);
        ece = findViewById(R.id.ece);
        eceWin = findViewById(R.id.eceWin);
        maymon =findViewById(R.id.maymon);
        muteTus = findViewById(R.id.muteTus);

        if (mut){
            muteTus.setImageResource(R.drawable.mute);
        } else {
            muteTus.setImageResource(R.drawable.unmute);
        }

        eceWin.setBackgroundResource(R.drawable.ecewin);
        eceWinAnm = (AnimationDrawable) eceWin.getBackground();

        ece.setBackgroundResource(R.drawable.eceuzgun);
        eceUzgunAnm = (AnimationDrawable) ece.getBackground();

        cikcikisk.setBackgroundResource(R.drawable.cikcikmenuanimation);
        cikcikMenuAnimation = (AnimationDrawable) cikcikisk.getBackground();

        bulutisk.setBackgroundResource(R.drawable.bltmenuanm);
        bulutMenuAnimation = (AnimationDrawable) bulutisk.getBackground();

        balonisk.setBackgroundResource(R.drawable.blnmenuanm);
        balonMenuAnimation = (AnimationDrawable) balonisk.getBackground();

        maymon.setBackgroundResource(R.drawable.mymnduran);
        mymnMenuAnm = (AnimationDrawable) maymon.getBackground();


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        cikcikMenuAnimation.start();
        bulutMenuAnimation.start();
        balonMenuAnimation.start();
        mymnMenuAnm.start();
        eceUzgunAnm.start();
        super.onWindowFocusChanged(hasFocus);
    }

    public void cikcikgo (View view) {
        karakter = 0;

        try {TimeUnit.MILLISECONDS.sleep(600);}
        catch (InterruptedException e){e.printStackTrace();}
        soundAna.degiskenSes(soundAna.civcivses);
        Intent i=new Intent(AnaMenu.this,Oyun.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }

    public void bulutgo (View view) {
        karakter = 1;

        try {TimeUnit.MILLISECONDS.sleep(600);}
        catch (InterruptedException e){e.printStackTrace();}
        soundAna.degiskenSes(soundAna.bulutses);
        try {TimeUnit.MILLISECONDS.sleep(400);}
        catch (InterruptedException e){e.printStackTrace();}
        Intent i=new Intent(AnaMenu.this,Oyun.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }

    public void balongo (View view) {
        karakter = 2;

        try {TimeUnit.MILLISECONDS.sleep(700);}
        catch (InterruptedException e){e.printStackTrace();}
        soundAna.degiskenSes(soundAna.balonses);
        try {TimeUnit.MILLISECONDS.sleep(500);}
        catch (InterruptedException e){e.printStackTrace();}
        Intent i=new Intent(AnaMenu.this,Oyun.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }

    public void kapat (View view){
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);}

    public void mute (View view){
        if (!mut){
            mut = true;
            muteTus.setImageResource(R.drawable.mute);}
        else {
            mut = false;
            muteTus.setImageResource(R.drawable.unmute);}
    }

    //geri tuşu iptal etme
    @Override
    public boolean dispatchKeyEvent(KeyEvent event){
        if (event.getAction() == KeyEvent.ACTION_DOWN){
            switch (event.getKeyCode()){
                case KeyEvent.KEYCODE_BACK:
                    return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }
    @Override
    protected void onDestroy() {
        soundAna.release();
        super.onDestroy();
    }
}
