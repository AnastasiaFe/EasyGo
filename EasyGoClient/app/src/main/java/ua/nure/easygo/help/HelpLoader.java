package ua.nure.easygo.help;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;


/**
 * Created by Oleg on 24.12.2016.
 */

public class HelpLoader {

    private static final String TEXT = "text.txt", IMAGE = "img.png";

    public static HelpItem load(Context context, Class clas) {

        Locale locale = context.getResources().getConfiguration().locale;
        String langFolder = "en";
        boolean rus=false;
        if (locale.getLanguage().equals("ru") || locale.getLanguage().equals("uk")) {
            langFolder="ru";
            rus=true;
        }
        String folder = "help/" + langFolder + "/" + clas.getSimpleName() + '/';

        AssetManager assetManager = context.getResources().getAssets();

        Bitmap bmp = null;
        String text = null;

        try {
            bmp = readAsBitmap(assetManager.open(folder + IMAGE));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            text = readAsText(assetManager.open(folder + TEXT), rus);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (bmp == null && text == null) {
            return null;
        }

        return new HelpItem(bmp, text);
    }

    private static String readAsText(InputStream is,boolean rus) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is, rus?"windows-1251":"UTF-8"));
        StringBuilder sb = new StringBuilder();
        while (reader.ready()) {
            sb.append(reader.readLine()).append("\n");
        }
        reader.close();
        return sb.toString();

    }

    private static Bitmap readAsBitmap(InputStream is) {
        return BitmapFactory.decodeStream(is);
    }
}
