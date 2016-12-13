package ua.nure.easygo.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import easygo.nure.ua.easygoclient.R;
import ua.nure.easygo.mining.FilterParam;
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
    OnFilterChangedListener listener;

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
        Activity activity = getActivity();
        if (!(activity instanceof OnFilterChangedListener)) {
            throw new RuntimeException("Container activity must implement filter changed listener");
        }
        listener = (OnFilterChangedListener) activity;
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
            final FilterParam param = new FilterParam(mapAttributes.getId(a));
            filterParams.add(param);

            ViewGroup view = (ViewGroup) inflater.inflate(R.layout.item_filter, filtersContainer, false);
            ((TextView) view.findViewById(R.id.textFilterName)).setText(a.name);
            filtersContainer.addView(view);

            View filter = null;
            switch (a.type) {

                case INTEGER: {
                    filter = inflater.inflate(R.layout.filter_text, view, false);
                    TextView min = (TextView) filter.findViewById(R.id.min), max = (TextView) filter.findViewById(R.id.max);
                    TextViewMaster.setInputFormat(min, TextViewMaster.TextFormat.INT);
                    TextViewMaster.setInputFormat(max, TextViewMaster.TextFormat.INT);
                    min.addTextChangedListener(new FilterParamListener(min, TextViewMaster.TextFormat.INT) {
                        @Override
                        public void acceptParam(Object paramValue) {
                            param.setMin(paramValue);
                            listener.onFilterChanged(filterParams);
                        }
                    });
                    max.addTextChangedListener(new FilterParamListener(max, TextViewMaster.TextFormat.INT) {
                        @Override
                        public void acceptParam(Object paramValue) {
                            param.setMax(paramValue);
                            listener.onFilterChanged(filterParams);
                        }
                    });

                }
                case DOUBLE: {
                    filter = inflater.inflate(R.layout.filter_text, view, false);
                    TextView min = (TextView) filter.findViewById(R.id.min), max = (TextView) filter.findViewById(R.id.max);
                    TextViewMaster.setInputFormat(min, TextViewMaster.TextFormat.DOUBLE);
                    TextViewMaster.setInputFormat(max, TextViewMaster.TextFormat.DOUBLE);
                    min.addTextChangedListener(new FilterParamListener(min, TextViewMaster.TextFormat.DOUBLE) {
                        @Override
                        public void acceptParam(Object paramValue) {
                            param.setMin(paramValue);
                            listener.onFilterChanged(filterParams);
                        }
                    });
                    max.addTextChangedListener(new FilterParamListener(max, TextViewMaster.TextFormat.DOUBLE) {
                        @Override
                        public void acceptParam(Object paramValue) {
                            param.setMax(paramValue);
                            listener.onFilterChanged(filterParams);
                        }
                    });
                }
                break;

                case RATING:
                    filter = inflater.inflate(R.layout.filter_rating, view, false);
                {
                    RatingBar min = (RatingBar) filter.findViewById(R.id.min), max = (RatingBar) filter.findViewById(R.id.max);
                    min.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                        @Override
                        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                            if (fromUser) {
                                param.setMin((double)rating);
                                listener.onFilterChanged(filterParams);
                            }
                        }
                    });
                    max.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                        @Override
                        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                            if (fromUser) {
                                param.setMax((double)rating);
                                listener.onFilterChanged(filterParams);
                            }
                        }
                    });
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

    public static abstract class FilterParamListener implements TextWatcher {

        private TextView target;
        private TextViewMaster.TextFormat textFormat;

        public FilterParamListener(TextView target, TextViewMaster.TextFormat textFormat) {
            this.target = target;
            this.textFormat = textFormat;
        }

        public abstract void acceptParam(Object paramValue);

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String text = target.getText().toString();
            Object res = null;
            try {
                switch (textFormat) {
                    case STRING:
                        res = text;
                        break;
                    case INT:
                        res = Integer.parseInt(text);
                        break;
                    case DOUBLE:
                        res = Double.parseDouble(text);
                        break;
                }
            } catch (NumberFormatException e) {

            }
            acceptParam(res);
        }
    }

}
