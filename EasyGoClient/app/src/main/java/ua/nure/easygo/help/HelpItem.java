package ua.nure.easygo.help;

import android.graphics.Bitmap;

import easygo.nure.ua.easygoclient.R;

/**
 * Created by Oleg on 24.12.2016.
 */

public class HelpItem {
    private String message;
    private Bitmap bmp;

    public HelpItem(Bitmap bmp, String message) {
        this.bmp = bmp;
        this.message = message;
    }

    public Bitmap getBmp() {
        return bmp;
    }

    public String getMessage() {
        return message;
    }
}
