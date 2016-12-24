package ua.nure.easygo.help;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * Created by Oleg on 24.12.2016.
 */

public class HelpLoader {

    private static final String TEXT = "text.txt", IMAGE = "img.png";

    public static HelpItem load(Context context, Class clas) {

        String folder = "help/en/" + clas.getSimpleName() + '/';

        AssetManager assetManager = context.getResources().getAssets();

        Bitmap bmp = null;
        String text = null;

        try {
            bmp = readAsBitmap(assetManager.open(folder + IMAGE));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            text = readAsText(assetManager.open(folder + TEXT));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (bmp == null && text == null) {
            return null;
        }

        return new HelpItem(bmp, text);
    }

    private static String readAsText(InputStream is) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
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
