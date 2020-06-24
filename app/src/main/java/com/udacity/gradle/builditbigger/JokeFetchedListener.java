package com.udacity.gradle.builditbigger;

public interface JokeFetchedListener {
    void onJokeFetched(String joke);
    void updateProgressBar(boolean show);
}
