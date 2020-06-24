package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.geetha.jokeviewer.JokeViewer;


public class MainActivity extends AppCompatActivity implements JokeFetchedListener {
    AsyncTask <Void, Boolean, String> task;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        pb = findViewById (R.id.status_indicator);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater ().inflate (R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId ();
        if (id == R.id.action_settings) {
            return true;
        }

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

    @Override
    public void updateProgressBar(boolean show) {
        pb.setVisibility (show ? View.VISIBLE : View.INVISIBLE);
    }
}
