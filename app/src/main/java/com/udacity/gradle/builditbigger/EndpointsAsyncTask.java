package com.udacity.gradle.builditbigger;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.ProgressBar;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

class EndpointsAsyncTask extends AsyncTask <Void, Boolean, String> {
    private static MyApi myApiService = null;
    private JokeFetchedListener listener;
    public EndpointsAsyncTask(JokeFetchedListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute ();
        listener.updateProgressBar (true);
    }

    @Override
    protected String doInBackground(Void... voids) {
        if (myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder (AndroidHttp.newCompatibleTransport (),
                    new AndroidJsonFactory (), null)
                    .setRootUrl ("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer (new GoogleClientRequestInitializer () {
                        @Override
                        public void initialize(AbstractGoogleClientRequest <?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent (true);
                        }
                    });
            myApiService = builder.build ();
        }

        try {
            return myApiService.sayHi ("name").execute ().getData ();
        } catch (IOException e) {
            return e.getMessage ();
        }
    }
    @Override
    protected void onPostExecute(String result) {
        listener.updateProgressBar (false);
        listener.onJokeFetched (result);
    }
}
