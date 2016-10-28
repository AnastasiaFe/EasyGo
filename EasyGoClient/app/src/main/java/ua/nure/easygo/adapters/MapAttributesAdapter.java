package ua.nure.easygo.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.Arrays;
import java.util.List;

import easygo.nure.ua.easygoclient.BR;
import easygo.nure.ua.easygoclient.R;
import easygo.nure.ua.easygoclient.databinding.MapAttrItemBinding;
import ua.nure.easygo.model.attributes.MapAttribute;



/**
 * Created by Oleg on 28.10.2016.
 */

public class MapAttributesAdapter extends BaseBindableAdapter<MapAttribute> {
    public MapAttributesAdapter(Context context, List<MapAttribute> items) {
        super(context, items, R.layout.map_attr_item, BR.attr);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = super.getView(position, convertView, parent);
        Spinner attrType = (Spinner)v.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(v.getContext(),
                R.array.attr_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        attrType.setAdapter(adapter);
        MapAttribute mapAttribute = getItem(position);

        attrType.setSelection(Arrays.asList(v.getContext().getResources().getStringArray(R.array.attr_types)).indexOf(mapAttribute.type.toString()));
        return v;
    }
}
