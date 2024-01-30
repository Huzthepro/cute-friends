package com.tellstorygames.sevimliarkadaslar;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

public class SoundPlayer{
    //from here
    public static final String TAG = "DURUM";
    private AudioAttributes audioAttributes;
    final int SOUND_POOL_MAX = 2;
    private SoundPool soundPool;
    public int introses1, introses2, introses3, introses4, introses5, introses6;
    public int civcivses;
    public int civcivGood;
    public int civcivBad;
    public int bulutses;
    public int bulutGood;
    public int bulutBad;
    public int balonses;
    public int balonGood;
    public int balonBad;
    public int sesiyi;
    public int seskotu;
    public int kaybettin;
    public int lvlatlama;
    public int canses;
    public int yavasses;
    public int kalkanses;
    public int kalkanhit;
    public int bonusses;
    public int winses;
    public int zipzipses;
    public int arises;
    boolean loaded = false;

    public SoundPlayer(Context context) {

        //eski versiyonlar için
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();
            soundPool = new SoundPool.Builder()
                    .setAudioAttributes(audioAttributes)
                    .setMaxStreams(SOUND_POOL_MAX)
                    .build();
        } else {//SoundPool(int maxStreams, int streamType, int srcQuality)
            soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        }
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                loaded = true;
            }
        });

        introses1 = soundPool.load(context, R.raw.baloniyi, 1);
        introses2 = soundPool.load(context, R.raw.intr2, 1);
        introses3 = soundPool.load(context, R.raw.intr3, 1);
        introses4 = soundPool.load(context, R.raw.intr4, 1);
        introses5 = soundPool.load(context, R.raw.intr5, 1);
        introses6 = soundPool.load(context, R.raw.intr6, 1);
        civcivses = soundPool.load(context, R.raw.cikcik, 1);
        civcivGood = soundPool.load(context, R.raw.cikcikiyi, 2);
        civcivBad = soundPool.load(context, R.raw.cikcikkotu, 3);
        bulutses = soundPool.load(context, R.raw.bulut, 1);
        bulutGood = soundPool.load(context, R.raw.bulutiyi, 2);
        bulutBad = soundPool.load(context, R.raw.bulutkotu, 3);
        balonses = soundPool.load(context, R.raw.balon, 1);
        balonGood = soundPool.load(context, R.raw.baloniyi, 2);
        balonBad = soundPool.load(context, R.raw.balonkotu, 3);
        kaybettin = soundPool.load(context, R.raw.kaybettin, 1);
        lvlatlama = soundPool.load(context, R.raw.lvlatlama, 1);
        canses = soundPool.load(context, R.raw.can, 1);
        yavasses = soundPool.load(context, R.raw.yavas, 1);
        kalkanses = soundPool.load(context, R.raw.kalkan, 1);
        kalkanhit = soundPool.load(context, R.raw.kalkanhit, 1);
        bonusses = soundPool.load(context, R.raw.bonus, 1);
        winses = soundPool.load(context, R.raw.wewin, 1);
        zipzipses = soundPool.load(context, R.raw.zipzip, 1);
        arises = soundPool.load(context, R.raw.ari, 1);

    }

    public void degiskenSes(int ses){
        if (loaded){
                soundPool.play(ses, 1.0f, 1.0f, 0,0, 1f);
            }
        }


    public void release(){
        soundPool.release();
    }

}


