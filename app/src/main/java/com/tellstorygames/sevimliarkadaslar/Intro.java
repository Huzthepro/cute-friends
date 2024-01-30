package com.tellstorygames.sevimliarkadaslar;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class Intro extends AppCompatActivity {

    private ImageView intro;
    private int zaman;
    private Handler handler = new Handler();
    private Timer timer = new Timer();
    private SoundPlayer soundIntro;
    private ImageView skpIntro;
    private AnimationDrawable skpIntAnm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        intro = findViewById(R.id.introImage);
        skpIntro = findViewById(R.id.skipintro);

        soundIntro = new SoundPlayer(this);
        skpIntro.setBackgroundResource(R.drawable.skipintanim);
        skpIntAnm = (AnimationDrawable) skpIntro.getBackground();
        skpIntAnm.start();

            this.timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            introAction();
                        }
                    });
                }
            }, 0, 20);
    }

    public void introAction(){
        zaman ++;
        if (zaman == 30) {//elma elinde
            intro.setImageResource(R.drawable.intro2);
            soundIntro.degiskenSes(soundIntro.introses1);
        }
        if (zaman == 70) {//elma düştü
            intro.setImageResource(R.drawable.intro3);
        }
        if (zaman == 120) {//yeni elmaya bakıyor
            intro.setImageResource(R.drawable.intro4);
        }
        if (zaman == 160) {//uzandı 280
            intro.setImageResource(R.drawable.intro5);
            soundIntro.degiskenSes(soundIntro.introses2);
        }
        if (zaman == 200) {//maymun girdi
            intro.setImageResource(R.drawable.intro6);
            soundIntro.degiskenSes(soundIntro.introses3);
        }
        if (zaman == 250) {//maymun sırıtma
            intro.setImageResource(R.drawable.intro7);
        }
        if (zaman == 280) {//maymun uzanma
            intro.setImageResource(R.drawable.intro8);
            soundIntro.degiskenSes(soundIntro.introses4);
        }
        if (zaman == 320) {//sepet yok
            intro.setImageResource(R.drawable.intro9);
            soundIntro.degiskenSes(soundIntro.introses5);
        }
        if (zaman == 340) {//elma koparıldı
            intro.setImageResource(R.drawable.intro10);
        }
        if (zaman == 390) {//sepete baktı
            intro.setImageResource(R.drawable.intro11);
            soundIntro.degiskenSes(soundIntro.introses6);
        }
        if (zaman == 430) {//üzüldü
            intro.setImageResource(R.drawable.intro12);
            soundIntro.degiskenSes(soundIntro.introses1);
        }
        if (zaman == 460) {//elma düştü
            intro.setImageResource(R.drawable.intro13);
            soundIntro.degiskenSes(soundIntro.introses6);
        }
        if (zaman == 500) {//ağlıyor
            intro.setImageResource(R.drawable.intro14);
            soundIntro.degiskenSes(soundIntro.introses6);
        }
        if (zaman == 540) { //ağlıyor2
            timer.cancel();
            timer = null;
            skpIntAnm.stop();
            Intent i=new Intent(Intro.this, Oyun.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            finish();
        }
    }

    public void skipIntro (View view) {
        timer.cancel();
        timer = null;
        skpIntAnm.stop();
        Intent i=new Intent(Intro.this, Oyun.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
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
        soundIntro.release();
        super.onDestroy();
    }

}
