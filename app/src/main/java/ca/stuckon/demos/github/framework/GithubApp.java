package ca.stuckon.demos.github.framework;

import android.app.Application;

import ca.stuckon.demos.github.network.GithubRequestFactory;

public class GithubApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // init Crashlytics
        // init Fonts
        GithubRequestFactory.init("https://api.github.com/");
    }
}
