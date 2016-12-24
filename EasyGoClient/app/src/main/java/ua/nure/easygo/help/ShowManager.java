package ua.nure.easygo.help;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Manages state of help (whether help should be shown or not)
 */

public class ShowManager {

    private static final String PREFS = "help";


    /**
     * Changes help as already shown
     *
     * @param context
     * @param clas
     * @return true if help should be shown
     */
    public static boolean show(Context context, Class clas) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        boolean shown = preferences.getBoolean(clas.getSimpleName(), false);

        if (!shown) {
            SharedPreferences.Editor e = preferences.edit();
            e.putBoolean(clas.getSimpleName(), true);
            e.apply();
            e.commit();
        }

        return !shown;

    }

    public static void showAgainLater(Context context, Class clas) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);

        SharedPreferences.Editor e = preferences.edit();
        e.putBoolean(clas.getSimpleName(), false);
        e.apply();
        e.commit();


    }
}
