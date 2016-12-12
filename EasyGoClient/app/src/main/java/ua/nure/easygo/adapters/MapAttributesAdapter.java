package ua.nure.easygo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Arrays;
import java.util.List;

import easygo.nure.ua.easygoclient.R;
import easygo.nure.ua.easygoclient.databinding.MapAttrItemBinding;
import ua.nure.easygo.model.attributes.AttributeType;
import ua.nure.easygo.model.attributes.MapAttribute;


/**
 * Created by Oleg on 28.10.2016.
 */

public class MapAttributesAdapter {

    private ViewGroup container;
    private List<MapAttribute> items;

    private LayoutInflater layoutInflater;

    public MapAttributesAdapter(ViewGroup container, List<MapAttribute> items) {
        this.container = container;
        this.items = items;
        layoutInflater = (LayoutInflater) container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        container.removeAllViews();
        for (int i = 0; i < items.size(); i++) {
            container.addView(getView(i));
        }
    }


    public View getView(int position) {
        MapAttrItemBinding binding = MapAttrItemBinding.inflate(layoutInflater);
        final MapAttribute mapAttribute = items.get(position);
        binding.setAttr(mapAttribute);

        View v = binding.getRoot();
        Spinner attrType = (Spinner) v.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(v.getContext(),
                R.array.attr_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        attrType.setAdapter(adapter);


        attrType.setSelection(Arrays.asList(v.getContext().getResources().getStringArray(R.array.attr_types)).indexOf(mapAttribute.type.toString()));
        attrType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                mapAttribute.type = AttributeType.valueOf(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return v;
    }
}
