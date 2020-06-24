package com.udacity.gradle.builditbigger;

import android.text.TextUtils;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class TestEndpointAsyncTask {

    private String result = null;
    @Test
    public void loadJoke(){
        final CountDownLatch countDowLatch=new CountDownLatch (1);

        new EndpointsAsyncTask (new JokeFetchedListener () {
            @Override
            public void onJokeFetched(String joke) {
                result=joke;
                countDowLatch.countDown ();
            }
        }).execute ();

        try {
            countDowLatch.await();
            assertNotNull ("joke is null", result);
            assertFalse ("joke is empty", TextUtils.isEmpty(result));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
