package ua.nure.easygo.utils.binding;

import android.content.Intent;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import ua.nure.easygo.activities.MapInfoActivity;

/**
 * Created by Oleg on 19.10.2016.
 */

public class Utils {
    @BindingAdapter("bind:imageBitmap")
    public static void loadImage(ImageView iv, Bitmap bitmap) {
        if(bitmap!=null) {
            iv.setImageBitmap(bitmap);
        }

    }


    public static void showMapInfo(View v, long mapId) {
        Intent intent = new Intent(v.getContext(), MapInfoActivity.class);
        intent.putExtra(MapInfoActivity.EXTRA_MAP_ID, mapId);
        v.getContext().startActivity(intent);
    }
}
