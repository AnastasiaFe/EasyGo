package ua.nure.easygo.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import easygo.nure.ua.easygoclient.R;
import ua.nure.easygo.mining.FilterParam;
import ua.nure.easygo.model.attributes.AttributeType;
import ua.nure.easygo.model.attributes.MapAttribute;
import ua.nure.easygo.model.attributes.MapAttributes;
import ua.nure.easygo.utils.TextViewMaster;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilterFragment extends Fragment {

    private static final String ARG_MAP_ATTRS = "map_attrs";

    public static FilterFragment get(MapAttributes attributes) {
        FilterFragment filterFragment = new FilterFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_MAP_ATTRS, attributes);
        filterFragment.setArguments(args);
        return filterFragment;
    }

    public FilterFragment() {

    }

    MapAttributes mapAttributes;
    List<FilterParam> filterParams;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!getArguments().containsKey(ARG_MAP_ATTRS)) {
            throw new RuntimeException("Provide map attributes for Filter fragment");
        }

        mapAttributes = (MapAttributes) getArguments().getSerializable(ARG_MAP_ATTRS);
        filterParams = new ArrayList<>();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(context instanceof OnFilterChangedListener)) {
            throw new RuntimeException("Container activity must implement filter changed listener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_filter, container, false);
        ViewGroup filtersContainer = (ViewGroup) v.findViewById(R.id.filters);
        for (MapAttribute a : mapAttributes.attributes) {
            if (!a.type.filterable) {
                continue;
            }
            FilterParam param = new FilterParam(mapAttributes.getId(a));
            filterParams.add(param);

            ViewGroup view = (ViewGroup) inflater.inflate(R.layout.item_filter, filtersContainer, false);
            ((TextView) view.findViewById(R.id.textFilterName)).setText(a.name);
            filtersContainer.addView(view);

            View filter = null;
            switch (a.type) {

                case INTEGER:
                case DOUBLE:
                    filter = inflater.inflate(R.layout.filter_text, view, false);
                    TextViewMaster.setInputFormat((TextView) filter.findViewById(R.id.min), TextViewMaster.TextFormat.INT);
                    TextViewMaster.setInputFormat((TextView) filter.findViewById(R.id.max), TextViewMaster.TextFormat.INT);

                    break;

                case RATING:
                    filter = inflater.inflate(R.layout.filter_rating, view, false);
                {
                    RatingBar min = (RatingBar) filter.findViewById(R.id.min), max = (RatingBar) filter.findViewById(R.id.max);
                }
                break;
            }
            if (filter != null) {
                view.addView(filter);
            }
        }

        return v;
    }

    public interface OnFilterChangedListener {
        void onFilterChanged(List<FilterParam> filters);
    }

}
