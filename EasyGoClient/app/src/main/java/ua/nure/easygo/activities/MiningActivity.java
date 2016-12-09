package ua.nure.easygo.activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import easygo.nure.ua.easygoclient.R;
import ua.nure.easygo.fragments.PointsListFragment;
import ua.nure.easygo.model.Point;
import ua.nure.easygo.rest.RestService;

public class MiningActivity extends AppCompatActivity {

    public static final String EXTRA_MAP_ID = "map_id";
    List<Point> points;
    PointsListFragment pointsListFragment;

    public static void start(Context c, long mapId) {
        Intent intent = new Intent(c, MiningActivity.class);
        intent.putExtra(EXTRA_MAP_ID, mapId);
        c.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mining_layout);
        final long mapId = getIntent().getLongExtra(EXTRA_MAP_ID, 0);

        try {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... params) {
                    try {
                        points = RestService.get().getPoints(mapId).execute().body();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            }.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        tabLayout.setupWithViewPager(pager);
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(final int position) {
                return new Frag();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "Filter";
                    case 1:
                        return "Sort";
                    default:
                        return "";
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        });

    }

    public static class Frag extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            TextView v = new TextView(getActivity());
            v.setText("Frrag" + Math.random());
            return v;

        }
    }
}
