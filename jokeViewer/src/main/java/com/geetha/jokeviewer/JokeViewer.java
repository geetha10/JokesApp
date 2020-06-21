package com.geetha.jokeviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.widget.TextView;

public class JokeViewer extends AppCompatActivity {

    TextView jokeViewerTv;
    public static final String JOKE_EXTRA_STRING="getJokeExtraString";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_joke_viewer);
        jokeViewerTv=findViewById (R.id.joke_viewer_tv);
        String joke=getIntent ().getStringExtra (JOKE_EXTRA_STRING);
        jokeViewerTv.setText (joke);
    }

    public static void showJoke(String joke){
        Intent intent=new Intent ();
    }
}