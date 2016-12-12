package ua.nure.easygo;

import android.app.Application;

import ua.nure.easygo.rest.RestService;

/**
 * Created by Oleg on 01.12.2016.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RestService.init(this);
    }
}
