package ua.nure.easygo.activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import easygo.nure.ua.easygoclient.R;
import ua.nure.easygo.fragments.FilterFragment;
import ua.nure.easygo.fragments.PointsListFragment;
import ua.nure.easygo.fragments.SortFragment;
import ua.nure.easygo.mining.FilterParam;
import ua.nure.easygo.mining.Filterer;
import ua.nure.easygo.mining.Sorterer;
import ua.nure.easygo.mining.SortingInfo;
import ua.nure.easygo.model.Map;
import ua.nure.easygo.model.Point;
import ua.nure.easygo.rest.RestService;

public class MiningActivity extends BaseActivity implements FilterFragment.OnFilterChangedListener, SortFragment.OnSortChangeListener {

    public static final String EXTRA_MAP_ID = "map_id";
    List<Point> points;
    Map map;
    private List<FilterParam> filters;
    private SortingInfo sortingInfo;


    public static void start(Context c, long mapId) {
        Intent intent = new Intent(c, MiningActivity.class);
        intent.putExtra(EXTRA_MAP_ID, mapId);
        c.startActivity(intent);
    }

    PointsListFragment list;

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
                        map = RestService.get().getMap(mapId).execute().body();
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
                switch (position) {
                    case 0:
                        return FilterFragment.get(map.mapAttributes);
                    case 1:
                        return SortFragment.get(map.mapAttributes);
                }
                return null;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return getString(R.string.filter);
                    case 1:
                        return getString(R.string.sort);
                    default:
                        return "";
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        });

        list = new PointsListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, list).commit();


    }

    @Override
    protected void onResume() {
        super.onResume();
        list.setPoints(points);
    }

    private void processMining() {
        List<Point> copy = new LinkedList<>(points);
        Filterer.filter(copy, filters, map.mapAttributes);
        Sorterer.sort(copy, map.mapAttributes, sortingInfo);
        list.setPoints(copy);
    }

    @Override
    public void onFilterChanged(List<FilterParam> filters) {
        this.filters = filters;
        //Logger.toast(this, filters.toString());
        processMining();
    }


    @Override
    public void sortChanged(SortingInfo sortingInfo) {
        this.sortingInfo = sortingInfo;
        processMining();
        //Logger.toast(this, sortingInfo.toString());
    }
}
