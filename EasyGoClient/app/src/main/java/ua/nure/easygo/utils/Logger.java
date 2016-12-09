package ua.nure.easygo.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Oleg on 27.11.2016.
 */

public class Logger {
    public static void toast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}
