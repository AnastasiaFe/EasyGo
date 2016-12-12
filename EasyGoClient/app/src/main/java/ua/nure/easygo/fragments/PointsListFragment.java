package ua.nure.easygo.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

import easygo.nure.ua.easygoclient.R;
import ua.nure.easygo.adapters.PointsAdapter;
import ua.nure.easygo.model.Point;


public class PointsListFragment extends Fragment {


    public static final String EXTRA_POINTS_IDS = "points_ids";
    ListAdapter adapter;
    ListView ls;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PointsListFragment() {
    }

    public static PointsListFragment newInstance(Context context, long... pointsIds) {
        PointsListFragment fragment = new PointsListFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void setPoints(List<Point> points) {

        adapter = new PointsAdapter(getActivity(), points);
        ls.setAdapter(adapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_point_list, container, false);


        ls = (ListView) view.findViewById(R.id.list);


        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
      /*  if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
