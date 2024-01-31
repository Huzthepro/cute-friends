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

    private static final int TIME_ELMA_ELINDE = 30;
    private static final int TIME_ELMA_DUSTU = 70;
    private static final int TIME_YENI_ELMA = 120;
    private static final int TIME_UZANDI = 160;
    private static final int TIME_MAYMUN_GIRDI = 200;
    private static final int TIME_MAYMUN_SIRITMA = 250;
    private static final int TIME_MAYMUN_UZANMA = 280;
    private static final int TIME_SEPET_YOK = 320;
    private static final int TIME_ELMA_KOPARILDI = 340;
    private static final int TIME_SEPETE_BAKTI = 390;
    private static final int TIME_UZULDU = 430;
    private static final int TIME_ELMA_DUSTU_AGAIN = 460;
    private static final int TIME_AGLIYOR = 500;
    private static final int TIME_AGLIYOR_2 = 540;

    private ImageView intro;
    private int elapsedTime;
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

    public void introAction() {
        elapsedTime++;
        switch (elapsedTime) {
            case TIME_ELMA_ELINDE:
                setIntroImage(R.drawable.intro2);
                playSound(soundIntro.introses1);
                break;
            case TIME_ELMA_DUSTU:
                setIntroImage(R.drawable.intro3);
                break;
            case TIME_YENI_ELMA:
                setIntroImage(R.drawable.intro4);
                break;
            case TIME_UZANDI:
                setIntroImage(R.drawable.intro5);
                playSound(soundIntro.introses2);
                break;
            case TIME_MAYMUN_GIRDI:
                setIntroImage(R.drawable.intro6);
                playSound(soundIntro.introses3);
                break;
            case TIME_MAYMUN_SIRITMA:
                setIntroImage(R.drawable.intro7);
                break;
            case TIME_MAYMUN_UZANMA:
                setIntroImage(R.drawable.intro8);
                playSound(soundIntro.introses4);
                break;
            case TIME_SEPET_YOK:
                setIntroImage(R.drawable.intro9);
                playSound(soundIntro.introses5);
                break;
            case TIME_ELMA_KOPARILDI:
                setIntroImage(R.drawable.intro10);
                break;
            case TIME_SEPETE_BAKTI:
                setIntroImage(R.drawable.intro11);
                playSound(soundIntro.introses6);
                break;
            case TIME_UZULDU:
                setIntroImage(R.drawable.intro12);
                playSound(soundIntro.introses1);
                break;
            case TIME_ELMA_DUSTU_AGAIN:
                setIntroImage(R.drawable.intro13);
                playSound(soundIntro.introses6);
                break;
            case TIME_AGLIYOR:
                setIntroImage(R.drawable.intro14);
                playSound(soundIntro.introses6);
                break;
            case TIME_AGLIYOR_2:
                handleIntroCompletion();
                break;
        }
    }

    private void setIntroImage(int resourceId) {
        intro.setImageResource(resourceId);
    }

    private void playSound(int soundId) {
        soundIntro.degiskenSes(soundId);
    }

    private void handleIntroCompletion() {
        timer.cancel();
        timer = null;
        skpIntAnm.stop();
        Intent i = new Intent(Intro.this, Oyun.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }

    public void skipIntro(View view) {
        handleIntroCompletion();
    }

    // Back button handling
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (event.getKeyCode()) {
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

