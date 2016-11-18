package ua.nure.easygo.adapters;

import android.app.Service;
import android.content.Context;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RatingBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

import easygo.nure.ua.easygoclient.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ua.nure.easygo.MockUtil;
import ua.nure.easygo.model.Map;
import ua.nure.easygo.model.MapList;
import ua.nure.easygo.model.Point;
import ua.nure.easygo.model.attributes.AttributeValue;
import ua.nure.easygo.model.attributes.AttributeValues;
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

public class PointAttrAdapter {


    LayoutInflater layoutInflater;

    EasyGoService service;

    AttributeValues values;

    public PointAttrAdapter(Context context, AttributeValues values, TableLayout root) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.values = values;
        service = RestService.get();

        for (int i = 0; i < values.values.size(); i++) {
            root.addView(getView(i, root));
        }
    }


    public View getView(int position, ViewGroup parent) {
        final View v;

        v = layoutInflater.inflate(R.layout.point_attr_item, parent, false);


        final AttributeValue value = values.values.get(position);
        service.getMaps().enqueue(new Callback<MapList>() {
            @Override
            public void onResponse(Call<MapList> call, Response<MapList> response) {
                RestService.mapList = response.body();
                Map m = response.body().maps.get(0);
                MapAttribute mapAttribute = m.mapAttributes.attributes.get(value.attributeId);
                ((TextView) v.findViewById(R.id.text_attr_name)).setText(mapAttribute.name);
                View valueView = null;
                switch (mapAttribute.type) {
                    case INTEGER:
                        valueView = new EditText(v.getContext());
                        ((TextView) valueView).setInputType(InputType.TYPE_CLASS_NUMBER);
                        ((TextView) valueView).setText(value.value);
                        break;
                    case STRING:
                        valueView = new EditText(v.getContext());
                        ((TextView) valueView).setText(value.value);
                        break;
                    case DATE_TIME:
                        break;
                    case DOUBLE:
                        valueView = new EditText(v.getContext());
                        ((TextView) valueView).setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                        ((TextView) valueView).setText(value.value);
                        break;
                    case RATING:
                        valueView = layoutInflater.inflate(R.layout.rating_bar, (ViewGroup) v, false);
                        ((RatingBar) valueView).setRating(Integer.parseInt(value.value));
                        break;
                }
                if (valueView != null) {
                    TableRow.LayoutParams params = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.setMargins(16, 0, 16, 0);
                  //  params.weight=0;

                    ((ViewGroup) v).addView(valueView, params);
                }
            }

            @Override
            public void onFailure(Call<MapList> call, Throwable t) {

            }
        });

        return v;
    }
}
