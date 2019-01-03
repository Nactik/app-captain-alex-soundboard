package com.alex.nathancapiaux.myapplication;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<String> songList = new ArrayList<>();
    private Button buttonMen;
    private Button buttonNo;
    private Button buttonCows;
    private Button buttonGood;
    private Button buttonCrazy;
    private Button buttonGoat;
    private MediaPlayer monSon;

    /** It fills the song list with the name of the song file **/
    public void initSongList(List songList){
        songList.add("i_prefer_men");
        songList.add("noooo");
        songList.add("beautiful_cows");
        songList.add("very_good");
        songList.add("are_you_crazy");
        songList.add("goat");
    }

    public void stop(){
        if (monSon != null) {
            monSon.release();
            monSon = null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initSongList(songList);

        /** Getting the buttons **/
        buttonMen = (Button) findViewById(R.id.sound_button_men);
        buttonNo = (Button) findViewById(R.id.sound_button_no);
        buttonCows = (Button) findViewById(R.id.sound_button_beautiful_cows);
        buttonGood = (Button) findViewById(R.id.sound_button_very_good);
        buttonCrazy = (Button) findViewById(R.id.sound_button_crazy);
        buttonGoat = (Button) findViewById(R.id.sound_button_goat);


        buttonMen.setTag(0);
        buttonNo.setTag(1);
        buttonCows.setTag(2);
        buttonGood.setTag(3);
        buttonCrazy.setTag(4);
        buttonGoat.setTag(5);

        /** Adding Listener on the buttons **/
        buttonMen.setOnClickListener(this);
        buttonNo.setOnClickListener(this);
        buttonCows.setOnClickListener(this);
        buttonGood.setOnClickListener(this);
        buttonCrazy.setOnClickListener(this);
        buttonGoat.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int clickedButton = (int) v.getTag();
        stop();
        monSon = MediaPlayer.create(this, Uri.parse("android.resource://"+this.getPackageName()+ "/raw/" +songList.get(clickedButton)));
        monSon.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stop();
            }
        });

        monSon.start();
    }
}
