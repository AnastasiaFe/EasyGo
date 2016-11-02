package ua.nure.easygo.adapters;

import android.app.Service;
import android.content.Context;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import easygo.nure.ua.easygoclient.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ua.nure.easygo.MockUtil;
import ua.nure.easygo.model.Map;
import ua.nure.easygo.model.Point;
import ua.nure.easygo.model.attributes.AttributeValue;
import ua.nure.easygo.model.attributes.MapAttribute;
import ua.nure.easygo.rest.EasyGoService;
import ua.nure.easygo.rest.MockService;
import ua.nure.easygo.rest.RestService;

import static ua.nure.easygo.model.attributes.AttributeType.DATE_TIME;
import static ua.nure.easygo.model.attributes.AttributeType.DOUBLE;
import static ua.nure.easygo.model.attributes.AttributeType.INTEGER;
import static ua.nure.easygo.model.attributes.AttributeType.RATING;
import static ua.nure.easygo.model.attributes.AttributeType.STRING;

/**
 * Created by Oleg on 02.11.2016.
 */

public class PointAttrAdapter extends ArrayAdapter<AttributeValue> {

    LayoutInflater layoutInflater;

    EasyGoService service;

    public PointAttrAdapter(Context context, List<AttributeValue> objects) {
        super(context, 0, objects);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        service = RestService.get();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View v;
       // if (convertView == null) {
            v = layoutInflater.inflate(R.layout.point_attr_item, parent, false);
        //} else {
          //  v = convertView;
        //}

        final AttributeValue value = getItem(position);
        service.getMap(0).enqueue(new Callback<Map>() {
            @Override
            public void onResponse(Call<Map> call, Response<Map> response) {
                Map m = response.body();
                MapAttribute mapAttribute = m.mapAttributes.attributes.get(value.attributeId);
                ((TextView)v.findViewById(R.id.text_attr_name)).setText(mapAttribute.name);
                View valueView=null;
                switch (mapAttribute.type) {
                    case INTEGER:
                        valueView = new TextView(v.getContext());
                        ((TextView)valueView).setText(value.value);
                        break;
                    case STRING:
                        valueView = new TextView(v.getContext());
                        ((TextView)valueView).setText(value.value);
                        break;
                    case DATE_TIME:
                        break;
                    case DOUBLE:
                        valueView = new TextView(v.getContext());
                        ((TextView)valueView).setText(value.value);
                        break;
                    case RATING:
                        valueView = layoutInflater.inflate(R.layout.rating_bar, (ViewGroup) v, false);
                        ((RatingBar)valueView).setRating(Integer.parseInt(value.value));
                        break;
                }
                if(valueView!=null)
                {
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.setMargins(16, 0, 16, 0);
                    ((ViewGroup)v).addView(valueView, params);
                }
            }

            @Override
            public void onFailure(Call<Map> call, Throwable t) {

            }
        });

        return v;
    }
}
