package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.geetha.jokeviewer.JokeViewer;


public class MainActivity extends AppCompatActivity implements JokeFetchedListener {
    AsyncTask <Void, Void, String> task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater ().inflate (R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId ();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        //Jokes.getJoke ();
        return super.onOptionsItemSelected (item);
    }

    public void tellJoke(View view) {
        task = new EndpointsAsyncTask (this);
        task.execute ();
    }

    @Override
    protected void onStop() {
        super.onStop ();
        if (task != null) {
            task.cancel (true);
        }
    }

    @Override
    public void onJokeFetched(String joke) {
        Toast.makeText (this, joke, Toast.LENGTH_LONG).show ();
        Intent intent = new Intent (this, JokeViewer.class);
        intent.putExtra (JokeViewer.JOKE_EXTRA_STRING, joke);
        startActivity (intent);
    }
}
