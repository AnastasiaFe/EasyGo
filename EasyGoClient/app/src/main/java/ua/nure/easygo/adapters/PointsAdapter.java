package ua.nure.easygo.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import easygo.nure.ua.easygoclient.BR;
import easygo.nure.ua.easygoclient.R;
import ua.nure.easygo.model.Map;
import ua.nure.easygo.model.Point;
import ua.nure.easygo.rest.RestService;

/**
 * Created by Oleg on 01.12.2016.
 */

public class PointsAdapter extends BaseBindableAdapter<Point> {
    private final Context context;
    private HashMap<Long, Map> cache;

    public PointsAdapter(Context context, final List<Point> items) {
        super(context, items, R.layout.point_item, BR.point);
        this.context = context;
        cache = new HashMap<>();
       AsyncTask<Void, Void, Void> t =  new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                for (Point p : items) {
                    if (!cache.containsKey(p.mapId)) {
                        try {
                            cache.put(p.mapId, RestService.get().getMap(p.mapId).execute().body());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return null;
            }
        };
        try {
            t.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View v = super.getView(position, convertView, parent);

        final TableLayout table = (TableLayout) v.findViewById(R.id.table);
        table.setEnabled(false);

        //table.removeAllViews();
        final Point p = getItem(position);
        Map m = cache.get(p.mapId);
        new PointAttrAdapter(context, m.mapAttributes.attributes, p.attributeValues, table, true);
        DataBindingUtil.getBinding(v).setVariable(BR.map, m);


        return v;
    }
}
