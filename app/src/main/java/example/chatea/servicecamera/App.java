package example.chatea.servicecamera;

import android.app.Application;

/**
 * Created by jaspreet on 02-08-2017.
 */

public class App extends Application {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        SharedPreferenceHelper.init(this);
    }

    public static App getInstance () {
        return instance;
    }
}
