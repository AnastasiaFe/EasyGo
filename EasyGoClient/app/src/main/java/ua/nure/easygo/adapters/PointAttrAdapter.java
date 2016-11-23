package ua.nure.easygo.adapters;

import android.content.Context;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

import easygo.nure.ua.easygoclient.R;
import ua.nure.easygo.model.attributes.AttributeValue;
import ua.nure.easygo.model.attributes.AttributeValues;
import ua.nure.easygo.model.attributes.MapAttribute;
import ua.nure.easygo.rest.EasyGoService;
import ua.nure.easygo.rest.RestService;

/**
 * Created by Oleg on 02.11.2016.
 */

public class PointAttrAdapter {


    private final List<MapAttribute> mapAttributes;
    LayoutInflater layoutInflater;
    EasyGoService service;
    AttributeValues values;

    public PointAttrAdapter(Context context, List<MapAttribute> mapAttributes, AttributeValues values, TableLayout root) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mapAttributes = mapAttributes;
        this.values = values;
        service = RestService.get();

        for (int i = 0; i < mapAttributes.size(); i++) {
            root.addView(getView(i, root));
        }
    }


    public View getView(final int position, ViewGroup parent) {
        final View v;

        v = layoutInflater.inflate(R.layout.point_attr_item, parent, false);


        final MapAttribute mapAttribute = mapAttributes.get(position);


        AttributeValue value = null;
        for (AttributeValue val : values.values) {
            if (val.attributeId == position) {
                value = val;
            }
        }


        ((TextView) v.findViewById(R.id.text_attr_name)).setText(mapAttribute.name);
        View valueView = null;
        switch (mapAttribute.type) {
            case INTEGER:
                valueView = new EditText(v.getContext());
                ((TextView) valueView).setSingleLine();
                ((TextView) valueView).setInputType(InputType.TYPE_CLASS_NUMBER);
                if (value != null)
                    ((TextView) valueView).setText(value.value);
                ((TextView) valueView).setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        saveValue(v, position);
                        return false;
                    }
                });
                break;
            case STRING:
                valueView = new EditText(v.getContext());
                ((TextView) valueView).setSingleLine();
                if (value != null)
                    ((TextView) valueView).setText(value.value);
                ((TextView) valueView).setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        saveValue(v, position);
                        return false;
                    }
                });
                break;
            case DATE_TIME:
                break;
            case DOUBLE:
                valueView = new EditText(v.getContext());
                ((TextView) valueView).setSingleLine();
                ((TextView) valueView).setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                if (value != null)
                    ((TextView) valueView).setText(value.value);
                ((TextView) valueView).setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        saveValue(v, position);
                        return false;
                    }
                });
                break;
            case RATING:
                valueView = layoutInflater.inflate(R.layout.rating_bar, (ViewGroup) v, false);
                if (value != null)
                    ((RatingBar) valueView).setRating(Float.parseFloat(value.value));
                ((RatingBar) valueView).setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        values.setValue(position, String.valueOf(ratingBar.getRating()));
                    }
                });
                break;
        }
        if (valueView != null) {
            TableRow.LayoutParams params = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(16, 0, 16, 0);
            //  params.weight=0;

            ((ViewGroup) v).addView(valueView, params);
        }


        return v;
    }

    private void saveValue(TextView textView, int attrId) {
        values.setValue(attrId, textView.getText().toString());
    }
}
