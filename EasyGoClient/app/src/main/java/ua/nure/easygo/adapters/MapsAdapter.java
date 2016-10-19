package ua.nure.easygo.adapters;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import easygo.nure.ua.easygoclient.databinding.MapItemBinding;
import ua.nure.easygo.model.Map;

/**
 * Created by Oleg on 19.10.2016.
 */

public class MapsAdapter extends BaseBindableAdapter<Map> {


    public MapsAdapter(Context context, List<Map> items, @LayoutRes int layoutId, int bindVariableId) {
        super(context, items, layoutId, bindVariableId);
    }
}