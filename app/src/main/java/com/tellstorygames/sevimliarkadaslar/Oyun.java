package com.tellstorygames.sevimliarkadaslar;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;

public class Oyun extends AppCompatActivity {

    private int karakter = 0;
    //ses
    private SoundPlayer soundPlayer;

    //LEVELLER
    private TextView level;

    //KARAKTERLER
    private ImageView iyi;
    private int iyiX = -200;
    private int iyiY;
    private ImageView kotu;
    private int kotuX = -200;
    private int kotuY;
    private ImageView yarasa;
    private int yarasaX = -200;
    private boolean yarasaTetik = false;
    private ImageView bulutefekt;
    private boolean bulutTetik = false;
    private int bulutefektX = -500;
    private int bulutefektY;
    private ImageView yol;
    private int yolX;
    private ImageView yol2;
    private int yol2X;
    private ImageView ari;
    private int ariX = -200;
    private int ariY;
    private boolean ariTetik;
    private ImageView ariGosterge;
    private boolean ariOnline;
    private int ariSuresi;
    private ImageView kalkanGosterge;
    private boolean kalkanOnline = false;
    private ImageView bonus10;
    private int bonus10X = -200;
    private int bonus10Y;
    private boolean bonus10Tetik = false;
    private ImageView bonus5;
    private int bonus5X = -200;
    private int bonus5Y;
    private boolean bonus5Tetik = false;
    private ImageView hizlanRsm;
    private boolean hizOnline = false;
    private ImageView life;
    private int lifeX = -200;
    private int lifeY;
    private boolean lifeTetik = false;
    private boolean yavasOnline = false;



    //ANİMASYONLAR
    private AnimationDrawable kumAnm;
    private AnimationDrawable zipAnm;
    private AnimationDrawable kalkanAnm;
    private AnimationDrawable bulavailAnm;
    private AnimationDrawable cikavailAnm;
    private AnimationDrawable balavailAnm;
    private ImageView ozlgcaktif;
    private AnimationDrawable ariAnm;
    private AnimationDrawable ariGstrgAnm;
    private AnimationDrawable yrsAnm;
    private AnimationDrawable krtlAnm;
    private AnimationDrawable mymnWalkAnm;
    private AnimationDrawable mymnDrnAnm;
    private AnimationDrawable eceWinAnm;
    private AnimationDrawable cikcikFlyAnm;
    private AnimationDrawable cikMenuAnm;
    private ImageView cikchar;
    private AnimationDrawable bulutFlyAnm;
    private AnimationDrawable bulMenuAnm;
    private ImageView bulchar;
    private AnimationDrawable balonFlyAnm;
    private AnimationDrawable balMenuAnm;
    private ImageView balchar;

    //player
    private ImageView player;
    private int playerBoy;
    private int playerY;
    private int playerHiz;
    private Bitmap playerTik;

    //Oyun alanı
    private int sahneHeight; //frame
    private RelativeLayout menuSayfasi;
    private RelativeLayout durumSayfasi;
    private int sahneEni; //ekran
    private int sahneBoyu; //ekran

    //Ortam Görselleri
    private ImageView eceWinOyun;
    private ImageView kopekOyun;
    private ImageView maymunDayak;
    private ImageView toplancak;
    private ImageView maymun;
    private ImageView can, can2, can3;
    private TextView skorTabelasi;
    //Aksiyon zamanlayıcı
    private Handler handler = new Handler();
    private Timer timer = new Timer();

    //OZEL GUC
    private ImageView ozelNo;
    private ProgressBar ozelNoBar;
    private int ozelnosay;
    private boolean ozelTetik=false;
    private ImageView ozelYes;
    private ProgressBar ozelYesBar;
    private int ozelYesSay=800;
    private boolean tiklandi=false;
    private int surec;


    //Durum kontrolü
    private int dskHiz, normalHiz, yuksekHiz;
    private TextView lvlGosterge;
    private TextView menuSkor;
    private TextView highScore;
    private int lifeCounter = 3;
    private int lvl;
    private int zamanlayici;
    private int kalan;
    private int skor = 0;
    private boolean lvlUp = false;
    private boolean opener = false;
    private boolean yenileme= false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oyun);

        MediaPlayer music = MediaPlayer.create(this, R.raw.back);
                music.setLooping(true);
        music.setVolume(0.3f , 0.3f);
        music.start();

        soundPlayer = new SoundPlayer(this);

        //KARAKTERLER
        cikchar = findViewById(R.id.cikchar);
        balchar = findViewById(R.id.balchar);
        bulchar = findViewById(R.id.bulchar);
        ozelNoBar = findViewById(R.id.progbarred);
        ozelNo = findViewById(R.id.ozelgucno);
        ozelYesBar = findViewById(R.id.progbargr);
        ozelYes = findViewById(R.id.ozelgucyes);
        ozlgcaktif = findViewById(R.id.ozelgucaktf);

        bulutefekt = findViewById(R.id.bulutarka);
        bulutefekt.setX(-500);
        yol = findViewById(R.id.yolcizgi);
        yol2 = findViewById(R.id.yolcizgi2);
        ari = findViewById(R.id.ari);
        ari.setX(-200);
        ariGosterge = findViewById(R.id.arigosterge);
        kalkanGosterge = findViewById(R.id.kalkanGosterge);
        bonus5 = findViewById(R.id.bonus5);
        bonus5.setX(-200);
        bonus10 = findViewById(R.id.bonus10);
        bonus10.setX(-200);
        hizlanRsm = findViewById(R.id.hizlanRsm);
        iyi = findViewById(R.id.iyi);
        iyi.setX(-200);
        kotu = findViewById(R.id.kotu);
        kotu.setX(-200);
        yarasa = findViewById(R.id.yarasa);
        yarasa.setX(-200);

        toplancak = findViewById(R.id.toplanacak);
        skorTabelasi = (TextView) findViewById(R.id.skorTabelasi);
        menuSkor = (TextView) findViewById(R.id.skorunuz);
        lvlGosterge = (TextView) findViewById(R.id.lvlGosterge);

        highScore = (TextView) findViewById(R.id.highScore);
        menuSayfasi = findViewById(R.id.menuSayfasi);
        durumSayfasi = findViewById(R.id.durumLayout);

        level = findViewById(R.id.lvl);

        eceWinOyun = findViewById(R.id.eceWinOyun);
        kopekOyun = findViewById(R.id.kopekOyun);
        maymunDayak = findViewById(R.id.maymunDayak);
        maymun = findViewById(R.id.maymun);
        can = findViewById(R.id.can);
        can2 = findViewById(R.id.can2);
        can3 = findViewById(R.id.can3);

        //BOŞ KALIPLAR
        life = findViewById(R.id.life);
        player = findViewById(R.id.player);

        //ANMİASYON AYARLAMA
        cikchar.setBackgroundResource(R.drawable.cikcikmenuanimation);
        cikMenuAnm = (AnimationDrawable) cikchar.getBackground();
        cikMenuAnm.start();

        balchar.setBackgroundResource(R.drawable.blnmenuanm);
        balMenuAnm = (AnimationDrawable) balchar.getBackground();
        balMenuAnm.start();

        bulchar.setBackgroundResource(R.drawable.bltmenuanm);
        bulMenuAnm = (AnimationDrawable) bulchar.getBackground();
        bulMenuAnm.start();

        ari.setBackgroundResource(R.drawable.arianm);
        ariAnm = (AnimationDrawable) ari.getBackground();

        ariGosterge.setBackgroundResource(R.drawable.arigstrganm);
        ariGstrgAnm = (AnimationDrawable) ariGosterge.getBackground();

        yarasa.setBackgroundResource(R.drawable.yrsanm);
        yrsAnm = (AnimationDrawable) yarasa.getBackground();

        kotu.setBackgroundResource(R.drawable.krtlanm);
        krtlAnm = (AnimationDrawable) kotu.getBackground();

        maymun.setBackgroundResource(R.drawable.mymnduran);
        mymnDrnAnm = (AnimationDrawable) maymun.getBackground();

        eceWinOyun.setBackgroundResource(R.drawable.ecewin);
        eceWinAnm = (AnimationDrawable) eceWinOyun.getBackground();

        //Sahne özelliklerini alma
        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        sahneEni = size.x;
        sahneBoyu = size.y;

        if (sahneEni < 900) {dskHiz = Math.round(sahneEni / 72F); normalHiz = Math.round(sahneEni / 62F); yuksekHiz = Math.round(sahneEni / 59F);}
        if (sahneEni >= 900 && sahneEni <= 1300) {dskHiz = Math.round(sahneEni / 86F); normalHiz = Math.round(sahneEni / 72F); yuksekHiz = Math.round(sahneEni / 68F);}
        if (sahneEni > 1300) {dskHiz = Math.round(sahneEni / 122F); normalHiz = Math.round(sahneEni / 102F); yuksekHiz = Math.round(sahneEni / 96F);}
    }



    public void hareket(){
        if (karakter == 0){
            player.setBackgroundResource(R.drawable.cikcikfly);
            cikcikFlyAnm = (AnimationDrawable) player.getBackground();
            cikcikFlyAnm.start();
            soundPlayer.sesiyi = soundPlayer.civcivGood;
            soundPlayer.seskotu = soundPlayer.civcivBad;
            playerTik = BitmapFactory.decodeResource(getResources(),R.drawable.cikciksag);
            ozelNo.setBackgroundResource(R.drawable.cikozel);
            ozelYes.setBackgroundResource(R.drawable.cikavailanm);
            cikavailAnm = (AnimationDrawable) ozelYes.getBackground();
            cikavailAnm.start();
        }
        if (karakter == 1){
            player.setBackgroundResource(R.drawable.bulutfly);
            bulutFlyAnm = (AnimationDrawable) player.getBackground();
            bulutFlyAnm.start();
            soundPlayer.sesiyi = soundPlayer.bulutGood;
            soundPlayer.seskotu = soundPlayer.bulutBad;
            playerTik = BitmapFactory.decodeResource(getResources(),R.drawable.bulut2);
            ozelNo.setBackgroundResource(R.drawable.bulozel);
            ozelYes.setBackgroundResource(R.drawable.bulavailanm);
            bulavailAnm = (AnimationDrawable) ozelYes.getBackground();
            bulavailAnm.start();
        }
        if (karakter == 2){
            player.setBackgroundResource(R.drawable.balonfly);
            balonFlyAnm = (AnimationDrawable) player.getBackground();
            balonFlyAnm.start();
            soundPlayer.sesiyi = soundPlayer.balonGood;
            soundPlayer.seskotu = soundPlayer.balonBad;
            playerTik = BitmapFactory.decodeResource(getResources(),R.drawable.balongoz);
            ozelNo.setBackgroundResource(R.drawable.balozel);
            ozelYes.setBackgroundResource(R.drawable.balavailanm);
            balavailAnm = (AnimationDrawable) ozelYes.getBackground();
            balavailAnm.start();
        }
        zamanlayici +=1;
        if (zamanlayici > 10000){ zamanlayici = 0;}

        //OZEL GUCLER

        if (!ozelTetik){
            ozelnosay += 1;
            ozelNoBar.setProgress(ozelnosay/8);
            ozelNo.setVisibility(View.VISIBLE);
            ozelNoBar.setVisibility(View.VISIBLE);
        }
        if (ozelnosay == 800){

            ozelNo.setVisibility(View.INVISIBLE);
            ozelNoBar.setVisibility(View.INVISIBLE);
            ozelYes.setVisibility(View.VISIBLE);
            ozelTetik = true;
            ozelnosay =0;
        }

        if (tiklandi){
            if(surec == 1){
                ozelYesSay -= 2.5;
                ozelYesBar.setProgress(ozelYesSay/8);
            }
            if(surec == 2){
                ozelYesSay -= 1.5;
                ozelYesBar.setProgress(ozelYesSay/8);
            }
            if(surec == 3){
                ozelYesSay -= 1;
                ozelYesBar.setProgress(ozelYesSay/8);
            }
        }

        if(ozelYesSay <= 0){
            ozlgcaktif.setVisibility(View.INVISIBLE);
            kalkanOnline = false;
            kalkanGosterge.setVisibility(View.INVISIBLE);
            hizOnline = false;
            hizlanRsm.setVisibility(View.INVISIBLE);
            yavasOnline = false;
            ozelYesSay = 800;
            ozelYesBar.setProgress(ozelYesSay/8);
            tiklandi = false;
            ozelTetik = false;
            ozelYes.setVisibility(View.INVISIBLE);
            ozelYesBar.setVisibility(View.INVISIBLE);
        }
        if (skor <= 2) {
            lvl = 1;
            lvlUp = false;
            kalan = 3-skor;
            iyi.setImageResource(R.drawable.kiraz);
            maymun.setX(Math.round(sahneEni * 7.5 / 10));}
        if (skor > 2 && skor <= 10) {
            lvl = 2;
            if (!lvlUp) {lvlUp = true; lvlUp();}
            kalan = 11-skor;
            iyi.setImageResource(R.drawable.uzum);
            maymun.setX(Math.round(sahneEni * 7 / 10));}
        if (skor > 10 && skor <= 20) {
            lvl = 3;
            if (lvlUp) {lvlUp = false; lvlUp();}
            kalan = 21-skor;
            iyi.setImageResource(R.drawable.elma);
            maymun.setX(Math.round(sahneEni * 6 / 10));}
        if (skor > 20 && skor <= 34) {
            lvl = 4;
            if (!lvlUp){ lvlUp = true; lvlUp();}
            kalan = 35-skor;
            iyi.setImageResource(R.drawable.portakal);
            maymun.setX(Math.round(sahneEni * 5.2 / 10));}
        if (skor > 34 && skor <= 54) {
            lvl = 5;
            if (lvlUp){ lvlUp = false; lvlUp();}
            kalan = 55-skor;
            iyi.setImageResource(R.drawable.cilek);
            maymun.setX(Math.round(sahneEni * 4.4 / 10));}
        if (skor > 54 && skor <= 84) {
            lvl = 6;
            if (!lvlUp){ lvlUp = true; lvlUp();}
            kalan = 85-skor;
            iyi.setImageResource(R.drawable.muz);
            maymun.setX(Math.round(sahneEni * 3.6 / 10));}
        if (skor > 84 && skor <= 124) {
            lvl = 7;
            if (lvlUp){ lvlUp = false; lvlUp();}
            kalan = 125-skor;
            iyi.setImageResource(R.drawable.ananas);
            maymun.setX(Math.round(sahneEni * 2.8 / 10));}
        if (skor > 124 && skor <= 199) {
            lvl = 8;
            if (!lvlUp){ lvlUp = true; lvlUp();}
            kalan = 200-skor;
            iyi.setImageResource(R.drawable.havuc);
            maymun.setX(Math.round(sahneEni * 2 / 10));}
        if (skor > 199) {
            lvl = 9;
            if (lvlUp){lvlUp = false; lvlUp();}
            skorTabelasi.setText(skor + " PUAN");
            toplancak.setVisibility(View.INVISIBLE);
            lvlGosterge.setText("BONUS");
            level.setText("KAZANDIN");
            iyi.setImageResource(R.drawable.coin);}
        else {
            lvlGosterge.setText("LVL "+ lvl);
            level.setText(lvl + ". LEVEL");
            skorTabelasi.setText(""+kalan);
            toplancak.setImageDrawable(iyi.getDrawable());}

            //PLAYER
        playerY = (int)player.getY();
        playerBoy = player.getHeight();

        //PLAYER HIZ
        playerY = playerY + playerHiz;
        if (ariOnline){playerHiz = playerHiz +3;}
        else { playerHiz = playerHiz + 1;}
        //PLAYER SINIR
        if (playerY < 0) playerY = 0;
        if (playerY > sahneHeight-playerBoy) playerY = sahneHeight - playerBoy;
        player.setY(playerY);
        hitCheck();
        ////////////////
        //İYİ hareket
        iyiX -= normalHiz;
        if (iyiX <= -iyi.getWidth()){
            iyiX = sahneEni + (int) Math.floor(Math.random() * sahneEni / 2);
            iyiY = (int) Math.floor(Math.random() * (sahneHeight - iyi.getHeight()));
        }
        iyi.setX(iyiX);
        iyi.setY(iyiY);
        /////////////////
        //KÖTÜ Hareket
        if(yavasOnline){ kotuX -= 1; }
        else{kotuX -= yuksekHiz;}
        if (kotuX <= -kotu.getWidth()){
            kotuX = sahneEni + (int) Math.floor(Math.random() * sahneEni / 2);
            kotuY = (int) Math.floor(Math.random() * (sahneHeight - kotu.getHeight()));
        }
        kotu.setX(kotuX);
        kotu.setY(kotuY);
        /////////////////
        //LIFE hareket
        life.setImageBitmap(playerTik);
        if (zamanlayici % 552 == 0){ lifeTetik = true;}
        if (lifeTetik){
            lifeX -= normalHiz;
            if (lifeX <= -life.getWidth()) {
                lifeX = sahneEni + (int) Math.floor(Math.random() * sahneEni);
                lifeY = (int) Math.floor(Math.random() * (sahneHeight - life.getHeight()));
                lifeTetik = false;
            }
        }
        life.setX(lifeX);
        life.setY(lifeY);
        //////////////////
        //BULUT Hareket
        if (zamanlayici % 437 == 0){ bulutTetik = true;}
        if (bulutTetik){
            bulutefektX -= normalHiz;
            if (bulutefektX <= -bulutefekt.getWidth()){
                bulutTetik = false;
                bulutefektX = sahneEni + (int) Math.floor(Math.random() * sahneEni);
                bulutefektY = (int) Math.floor(Math.random() * (sahneHeight - bulutefekt.getHeight()));
            }
        }
        bulutefekt.setX(bulutefektX);
        bulutefekt.setY(bulutefektY);
        ///////////////
        //YOL hareket
        yolX -= 2;
        if (yolX < -sahneEni / 5){ yolX = 0;}
        yol.setX(yolX);
        yol2X -= 2;
        if (yol2X < 0) {yol2X = sahneEni / 5;}
        yol2.setX(yol2X);
        ///////////////////////
        /////////////////
        //ARİ hareket
        if (lvl > 2 && zamanlayici % 287 == 0){ariTetik = true;}
        if (ariTetik){
            ariAnm.start();
            if(yavasOnline){ ariX -= 1;}
            else { ariX -= dskHiz;}
            if (ariX <= -ari.getWidth()){
                ariTetik = false;
                ariX = sahneEni + (int) Math.floor(Math.random() * sahneEni);
                ariY = (int) Math.floor(Math.random() * (sahneHeight - ari.getHeight()));
            }
        }
        ari.setX(ariX);
        ari.setY(ariY);
        //////////////////
        //////////////////
        //BONUS 5 hareket
        if (lvl > 4 && zamanlayici % 308 == 0){ bonus5Tetik = true;}
        if (bonus5Tetik){
            bonus5X -= normalHiz;
            if (bonus5X <= -bonus5.getWidth()){
                bonus5Tetik = false;
                bonus5X = sahneEni + (int) Math.floor(Math.random() * sahneEni);
                bonus5Y = (int) Math.floor(Math.random() * (sahneHeight - bonus5.getHeight()));
            }
        }
        bonus5.setX(bonus5X);
        bonus5.setY(bonus5Y);
        //////////////////
        //YARASA Hareket
        if(lvl>5 && zamanlayici % 300 == 0){ yarasaTetik = true; }
        if (yarasaTetik){
            yrsAnm.start();
            if(yavasOnline){ yarasaX -= 1; }
            else{ yarasaX -= dskHiz;}

            if (yarasaX <= -yarasa.getWidth()){
                yarasaTetik = false;
                yarasaX = sahneEni + (int) Math.floor(Math.random() * sahneEni);
            }
        }
        yarasa.setX(yarasaX);

        ////////////////////
        //////////////////
        //BONUS 10 hareket
        if (lvl > 7 && zamanlayici % 483 == 0){ bonus10Tetik = true;}
        if (bonus10Tetik){
            bonus10X -= yuksekHiz;
            if (bonus10X <= -bonus10.getWidth()){
                bonus10Tetik = false;
                bonus10X = sahneEni + (int) Math.floor(Math.random() * sahneEni);
                bonus10Y = (int) Math.floor(Math.random() * (sahneHeight - bonus10.getHeight()));
            }
        }
        bonus10.setX(bonus10X);
        bonus10.setY(bonus10Y);

        //ARI GÖSTERGE
        if (ariOnline && ariSuresi <= 230){
            ariGstrgAnm.start();
            ariSuresi += 1;
            ariGosterge.setY(playerY);
            ariGosterge.setVisibility(View.VISIBLE);
        } else { ariOnline = false;
            ariSuresi = 0;
            ariGosterge.setVisibility(View.INVISIBLE);}
        //HIZ GÖSTERGE
        if (hizOnline){
            hizlanRsm.setY(playerY);
            hizlanRsm.setVisibility(View.VISIBLE);
        }
        //KALKAN GÖSTERGE
        if (kalkanOnline){
            kalkanGosterge.setY(playerY);
            kalkanGosterge.setVisibility(View.VISIBLE);
        }


        //CANLAR
        if (lifeCounter == 3) { can.setImageResource(R.drawable.can); can2.setImageResource(R.drawable.can);
        can3.setImageResource(R.drawable.can); }
        if (lifeCounter == 2) { can.setImageResource(R.drawable.caneksi); can2.setImageResource(R.drawable.can);
        can3.setImageResource(R.drawable.can); }
        if (lifeCounter == 1) { can2.setImageResource(R.drawable.caneksi); can3.setImageResource(R.drawable.can);}
        if (lifeCounter == 0) {
            can3.setImageResource(R.drawable.caneksi);
            menuSkor.setText("" + skor);
            SharedPreferences settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
            int highScoretutucu = settings.getInt("HIGH_SCORE", 0);
            if (skor > highScoretutucu){
                highScore.setText("En Yüksek Puan: " + skor);
                //kayıt
                SharedPreferences.Editor editor = settings.edit();
                editor.putInt("HIGH_SCORE", skor);
                editor.commit();
            }else{
                highScore.setText("En Yüksek Puan: " + highScoretutucu);
            }
            yenileme();

        }

    }
    /////////////////////////////
    ////////////////////////////////////////////////////
    //////////////////////////////
    public void hitCheck(){
        //İYİ ortalayıcı ve dokunma
        int iyiCenterX = iyiX + iyi.getWidth() / 2;
        int iyiCenterY = iyiY + iyi.getHeight() / 2;
        if (0 <= iyiCenterX && iyiCenterX <= playerBoy &&
                playerY <= iyiCenterY && iyiCenterY <= playerY + playerBoy) {
            iyiX = -iyi.getWidth();
            skor += 1;
            soundPlayer.degiskenSes(soundPlayer.sesiyi);}

        //KÖTÜ ortalayıcı ve dokunma
        int kotuCenterX = kotuX + kotu.getWidth() / 2;
        int kotuCenterY = kotuY + kotu.getHeight() / 2;
        if (0 <= kotuCenterX && kotuCenterX <= playerBoy &&
                playerY <= kotuCenterY && kotuCenterY <= playerY + playerBoy) {
            if (kalkanOnline){kotuX = -kotu.getWidth();
                soundPlayer.degiskenSes(soundPlayer.kalkanhit);}
            else {kotuX = -kotu.getWidth();
                lifeCounter -= 1;
                soundPlayer.degiskenSes(soundPlayer.seskotu);
            }
        }

        //LIFE ortalayıcı ve dokunma
        int lifeCenterX = lifeX + life.getWidth() / 2;
        int lifeCenterY = lifeY + life.getHeight() / 2;
        if (0 <= lifeCenterX && lifeCenterX <= playerBoy &&
                playerY <= lifeCenterY && lifeCenterY <= playerY + playerBoy) {
            lifeX = -life.getWidth();
            lifeTetik = false;
            if (lifeCounter < 3 ){ lifeCounter += 1;
                soundPlayer.degiskenSes(soundPlayer.canses);}
        }

        //ARI ortalayıcı ve dokunma
        int ariCenterX = ariX + ari.getWidth() / 2;
        int ariCenterY = ariY + ari.getHeight() / 2;
        if (0 <= ariCenterX && ariCenterX <= playerBoy &&
                playerY <= ariCenterY && ariCenterY <= playerY + playerBoy){
            ariTetik = false;
            ariX = -ari.getWidth();
            ariOnline = true;
            soundPlayer.degiskenSes(soundPlayer.arises);
        }

        //BONUS5 ortalayıcı ve dokunma
        int bonus5CenterX = bonus5X + bonus5.getWidth() / 2;
        int bonus5CenterY = bonus5Y + bonus5.getHeight() / 2;
        if (0 <= bonus5CenterX && bonus5CenterX <= playerBoy &&
                playerY <= bonus5CenterY && bonus5CenterY <= playerY + playerBoy){
            skor += 5;
            bonus5X = -bonus5.getWidth();
            bonus5Tetik = false;
            soundPlayer.degiskenSes(soundPlayer.bonusses);
        }

        //YARASA ortalayıcı ve dokunma
        int yarasaCenterX = yarasaX + yarasa.getWidth() / 2;
        int yarasaCenterY = (int)yarasa.getY() + yarasa.getHeight() / 2;
        if (0 <= yarasaCenterX && yarasaCenterX <= playerBoy &&
                playerY <= yarasaCenterY && yarasaCenterY <= playerY + playerBoy) {
            yarasaX = -yarasa.getWidth();
            yarasaTetik = false;
            if (!kalkanOnline){lifeCounter -= 1;
                soundPlayer.degiskenSes(soundPlayer.bulutBad);}
                else{ soundPlayer.degiskenSes(soundPlayer.kalkanhit);}
        }

        //BONUS10 ortalayıcı ve dokunma
        int bonus10CenterX = bonus10X + bonus10.getWidth() / 2;
        int bonus10CenterY = bonus10Y + bonus10.getHeight() / 2;
        if (0 <= bonus10CenterX && bonus10CenterX <= playerBoy &&
                playerY <= bonus10CenterY && bonus10CenterY <= playerY + playerBoy){
            skor += 10;
            bonus10X = -bonus10.getWidth();
            bonus10Tetik = false;
            soundPlayer.degiskenSes(soundPlayer.bonusses);
        }


    }
    /////////////////
    //////////////////////////////////////////////////////
    /////////////////////////////////////////////////////
    /////////////////
    //OZEL GUC BASILDI
    public void ozelbasildi (View view) {
        if (!tiklandi){
            tiklandi = true;
            ozelYes.setVisibility(View.INVISIBLE);
            ozelYesBar.setVisibility(View.VISIBLE);
            //cikcik
            if (karakter == 0){
                ozlgcaktif.setVisibility(View.VISIBLE);
                ozlgcaktif.setBackgroundResource(R.drawable.kalkananm);
                kalkanAnm = (AnimationDrawable) ozlgcaktif.getBackground();
                kalkanAnm.start();
                surec = 1;
                soundPlayer.degiskenSes(soundPlayer.kalkanses);
                kalkanOnline = true;
            }
            //bulut
            if (karakter == 1){
                ozlgcaktif.setVisibility(View.VISIBLE);
                ozlgcaktif.setBackgroundResource(R.drawable.zipanm);
                zipAnm = (AnimationDrawable) ozlgcaktif.getBackground();
                zipAnm.start();
                surec = 2;
                soundPlayer.degiskenSes(soundPlayer.zipzipses);
                hizOnline = true;
            }
            //balon
            if (karakter == 2){
                ozlgcaktif.setVisibility(View.VISIBLE);
                ozlgcaktif.setBackgroundResource(R.drawable.kumanm);
                kumAnm = (AnimationDrawable) ozlgcaktif.getBackground();
                kumAnm.start();
                surec = 3;
                yavasOnline = true;
                soundPlayer.degiskenSes(soundPlayer.yavasses);
            }

        }

    }

    public void yenileme(){
        timer.cancel();
        timer = null;
        playerHiz=0;
        if (lvl < 9){soundPlayer.degiskenSes(soundPlayer.kaybettin);}
        maymun.setBackgroundResource(R.drawable.mymnduran);
        mymnDrnAnm = (AnimationDrawable) maymun.getBackground();
        mymnDrnAnm.start();
        zamanlayici = 0;
        skor = 0;
        lvl = 1;
        lifeCounter = 3;
        lvlGosterge.setText("LVL "+ lvl);
        level.setText(lvl + ". LEVEL");
        skorTabelasi.setText(""+kalan);
        toplancak.setVisibility(View.VISIBLE);
        ozelYes.setVisibility(View.INVISIBLE);
        ozelYesBar.setVisibility(View.INVISIBLE);
        kalkanGosterge.setVisibility(View.INVISIBLE);
        hizlanRsm.setVisibility(View.INVISIBLE);
        eceWinOyun.setVisibility(View.INVISIBLE);
        kopekOyun.setVisibility(View.INVISIBLE);
        maymunDayak.setVisibility(View.INVISIBLE);

        lifeTetik = false;
        yavasOnline = false;
        ariSuresi = 0;
        ariGosterge.setVisibility(View.INVISIBLE);
        ariOnline = false;
        ariTetik= false;
        kotuX = -200;
        kotu.setX(kotuX);
        yarasaX = -200;
        yarasa.setX(yarasaX);
        ariX= -200;
        ari.setX(ariX);
        iyiX= -200;
        iyi.setX(iyiX);
        bonus5X= -200;
        bonus5.setX(bonus5X);
        bonus10X= -200;
        bonus10.setX(bonus10X);
        lifeX= -200;
        life.setX(lifeX);
        ozelYesSay =800;
        ozelYesBar.setProgress(ozelYesSay/8);
        ozelnosay = 0;
        ozelYesBar.setProgress(ozelYesSay/8);
        ozlgcaktif.setVisibility(View.INVISIBLE);
        kalkanOnline = false;
        hizOnline = false;
        tiklandi = false;
        ozelTetik = false;

        opener = false;
        menuSayfasi.setVisibility(View.VISIBLE);
    }

    //TEKRAR
    /////////////////
    /////////////////
    public void tekrar (View view) {
        menuSayfasi.setVisibility(View.INVISIBLE);
        durumSayfasi.setVisibility(View.VISIBLE);
    }

    //BALON SECILINCE
    public void balplay (View view) {
        FrameLayout frame = (FrameLayout) findViewById(R.id.oyunAlani);
        sahneHeight = frame.getHeight();
        karakter = 2;
        opener = true;
        maymun.setBackgroundResource(R.drawable.mymnwalk);
        mymnWalkAnm = (AnimationDrawable) maymun.getBackground();
        mymnWalkAnm.start();
        krtlAnm.start();
        durumSayfasi.setVisibility(View.INVISIBLE);

        //20 milisaniyede 1 pozisyon değişimi
        this.timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        hareket();
                    }
                });
            }
        }, 0, 20);
    }
    //CIKCIK SECILINCE
    public void cikplay (View view) {
        FrameLayout frame = (FrameLayout) findViewById(R.id.oyunAlani);
        sahneHeight = frame.getHeight();
        karakter = 0;
        opener = true;
        maymun.setBackgroundResource(R.drawable.mymnwalk);
        mymnWalkAnm = (AnimationDrawable) maymun.getBackground();
        mymnWalkAnm.start();
        krtlAnm.start();
        durumSayfasi.setVisibility(View.INVISIBLE);
        //20 milisaniyede 1 pozisyon değişimi
        this.timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        hareket();
                    }
                });
            }
        }, 0, 20);
    }

    //BULUT SECILINCE
    public void bulplay (View view) {
        FrameLayout frame = (FrameLayout) findViewById(R.id.oyunAlani);
        sahneHeight = frame.getHeight();
        karakter = 1;
        opener = true;
        maymun.setBackgroundResource(R.drawable.mymnwalk);
        mymnWalkAnm = (AnimationDrawable) maymun.getBackground();
        mymnWalkAnm.start();
        krtlAnm.start();
        durumSayfasi.setVisibility(View.INVISIBLE);
        //20 milisaniyede 1 pozisyon değişimi
        this.timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        hareket();
                    }
                });
            }
        }, 0, 20);
    }

    //TIKLANDIĞINDA
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN)
        {
            if (hizOnline) {if (sahneBoyu < 1600){ playerHiz = -Math.round(sahneBoyu / 33F);}
                            if (sahneBoyu > 1600 && sahneBoyu < 2240){ playerHiz = -Math.round(sahneBoyu / 48F);}
                            if (sahneBoyu > 2240){ playerHiz = -Math.round(sahneBoyu / 64F);}
            }else {if (sahneBoyu < 1600){ playerHiz = -Math.round(sahneBoyu / 48F);}
                if (sahneBoyu >= 1600 && sahneBoyu < 2240){ playerHiz = -Math.round(sahneBoyu / 70F);}
                if (sahneBoyu >= 2240){ playerHiz = -Math.round(sahneBoyu / 100F);}
            }
        }
        return true;
    }

    //GERİYE BASINCA
    public void menuOpener(){
        opener = false;
        timer.cancel();
        timer = null;
        durumSayfasi.setVisibility(View.VISIBLE);
        if (lvl == 9){
            eceWinOyun.setVisibility(View.VISIBLE);
            eceWinAnm.start();
            kopekOyun.setVisibility(View.VISIBLE);
            maymunDayak.setVisibility(View.VISIBLE);
            maymun.setVisibility(View.INVISIBLE);
        } else {
            maymun.setBackgroundResource(R.drawable.mymnduran);
            mymnDrnAnm = (AnimationDrawable) maymun.getBackground();
            mymnDrnAnm.start();
        }
    }
    // ALT TAB
    @Override
    protected void onPause() {
        menuOpener();
        super.onPause();
    }

    @Override
    protected void onStop() {
        menuOpener();
        super.onStop();
    }

    //LVL ATLAMA
    public void lvlUp(){
        if (lvl == 9){
            durumSayfasi.setVisibility(View.VISIBLE);
            eceWinOyun.setVisibility(View.VISIBLE);
            eceWinAnm.start();
            kopekOyun.setVisibility(View.VISIBLE);
            maymunDayak.setVisibility(View.VISIBLE);
            maymun.setVisibility(View.INVISIBLE);
            soundPlayer.degiskenSes(soundPlayer.winses);
            opener = false;
            timer.cancel();
            timer = null;
        } else {
            opener = false;
            timer.cancel();
            timer = null;
            maymun.setBackgroundResource(R.drawable.mymnduran);
            mymnDrnAnm = (AnimationDrawable) maymun.getBackground();
            mymnDrnAnm.start();
            durumSayfasi.setVisibility(View.VISIBLE);
            soundPlayer.degiskenSes(soundPlayer.lvlatlama);
        }
    }

    //geri tuşu iptal etme
    @Override
    public boolean dispatchKeyEvent(KeyEvent event){
        if (event.getAction() == KeyEvent.ACTION_DOWN){
            if (opener){menuOpener();} // false olursa menu tekrar açılmaz.
            switch (event.getKeyCode()){
                case KeyEvent.KEYCODE_BACK:
                    return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }
}


