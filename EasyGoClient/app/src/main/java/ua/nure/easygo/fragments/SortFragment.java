package ua.nure.easygo.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnItemSelected;
import butterknife.Unbinder;
import easygo.nure.ua.easygoclient.R;
import ua.nure.easygo.mining.SortingInfo;
import ua.nure.easygo.model.attributes.MapAttribute;
import ua.nure.easygo.model.attributes.MapAttributes;

/**
 * A simple {@link Fragment} subclass.
 */
public class SortFragment extends Fragment {

    public static SortFragment get(MapAttributes mapAttributes) {
        SortFragment fragment = new SortFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_ATTRS, mapAttributes);
        fragment.setArguments(args);
        return fragment;
    }

    public SortFragment() {
        // Required empty public constructor
    }

    private static final String ARG_ATTRS = "attrs";


    Unbinder unbinder;

    @BindView(R.id.spinner_sort_attr)
    Spinner sortSpinner;

    @BindView(R.id.switchAscending)
    Switch ascendingSwitch;

    OnSortChangeListener listener;

    SortingInfo sortingInfo = new SortingInfo();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = getActivity();
        if (!(activity instanceof OnSortChangeListener)) {
            throw new RuntimeException("Implement OnSortChangeListener");
        }
        listener = (OnSortChangeListener) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sort, container, false);
        unbinder = ButterKnife.bind(this, v);

        MapAttributes mapAttributes = (MapAttributes) getArguments().getSerializable(ARG_ATTRS);

        sortSpinner.setAdapter(new ArrayAdapter<MapAttribute>(getActivity(), R.layout.item_spinner_text, R.id.text, mapAttributes.attributes));


        return v;
    }

    @OnCheckedChanged(R.id.switchAscending)
    public void ascChanged() {
        sortingInfo.setAscending(ascendingSwitch.isChecked());
        listener.sortChanged(sortingInfo);
    }

    @OnItemSelected(R.id.spinner_sort_attr)
    public void selected(int position) {
        sortingInfo.setAttributeIndex(position);
        listener.sortChanged(sortingInfo);
    }

    public interface OnSortChangeListener {
        void sortChanged(SortingInfo sortingInfo);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        unbinder.unbind();
    }
}
