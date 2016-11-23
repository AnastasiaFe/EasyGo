package ua.nure.easygo.activities;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.MatrixCursor;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import easygo.nure.ua.easygoclient.R;
import easygo.nure.ua.easygoclient.databinding.NavHeaderMainBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ua.nure.easygo.LoginHelper;
import ua.nure.easygo.MapsContext;
import ua.nure.easygo.model.Map;
import ua.nure.easygo.model.User;
import ua.nure.easygo.rest.EasyGoService;
import ua.nure.easygo.rest.RestService;
import ua.nure.easygo.utils.GoogleMapAdapter;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener,
        GoogleMap.OnInfoWindowClickListener, NavigationView.OnNavigationItemSelectedListener, MapsContext.MapsContextListener {

    public static final int REQUEST_MAPS = 1, REQUEST_POINT_EDITING = 2, REQUEST_MAP_FOR_ADDING_POINT = 3;
    private static final int REQUEST_LOGIN = 4;
    Intent intAddPoint;
    NavHeaderMainBinding binding;
    private GoogleMap mMap;
    private EasyGoService service;
    private MapsContext mapsContext;
    private DrawerLayout drawer;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        //TODO:stub
        service = RestService.get();

        mapsContext = new MapsContext();

        mapFragment.getMapAsync(this);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, myToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);

        binding = NavHeaderMainBinding.bind(header);

        mapsContextChanged(mapsContext);
        mapsContext.setListener(this);

        if (LoginHelper.getInstance().hasCredentials(this)) {
            onActivityResult(REQUEST_LOGIN, RESULT_OK, null);
        }


    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapLongClickListener(this);
        mMap.setOnInfoWindowClickListener(this);

        //
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            //return;
        }
        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
        if (location != null) {
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 13));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(location.getLatitude(), location.getLongitude()))      // Sets the center of the map to location user
                    .zoom(17)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }

    }


    @Override
    protected void onActivityResult(final int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_POINT_EDITING) {//need to invalidate point on map
            syncMapWithMapsContext();
        }
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_MAPS) {
                drawer.closeDrawer(Gravity.LEFT);
                final int mapId = data.getIntExtra(MapsActivity.EXTRA_MAP_ID, 0);

                new AlertDialog.Builder(this).setTitle("Map").setMessage("Overlay map with existing or replace?").setNegativeButton("Overlay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        service.getMap(mapId).enqueue(new Callback<Map>() {
                            @Override
                            public void onResponse(Call<Map> call, Response<Map> response) {
                                Map m = response.body();
                                try {
                                    mapsContext.add(m);
                                } catch (MapsContext.MapAlreadyAddedException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(Call<Map> call, Throwable t) {
                                //TODO: add error handling
                            }
                        });

                    }
                }).setPositiveButton("Replace", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        service.getMap(mapId).enqueue(new Callback<Map>() {
                            @Override
                            public void onResponse(Call<Map> call, Response<Map> response) {
                                mMap.clear();
                                Map m = response.body();
                                mapsContext.replace(m);
                                //new GoogleMapAdapter(mMap).fill(m);
                            }

                            @Override
                            public void onFailure(Call<Map> call, Throwable t) {
                                //TODO: add error handling
                            }
                        });

                    }
                }).setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create().show();


            } else if (requestCode == REQUEST_MAP_FOR_ADDING_POINT) {

                intAddPoint.putExtra(PointActivity.EXTRA_MAP_ID, data.getIntExtra(MapsActivity.EXTRA_MAP_ID, 0));
                startActivityForResult(intAddPoint, REQUEST_POINT_EDITING);
            } else if (requestCode == REQUEST_LOGIN) {


                RestService.get().getUser(LoginHelper.getInstance().getLogin(this)).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        binding.setUser(response.body());
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
            }
        }
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        MapsActivity.startWithCertainMapList(this, REQUEST_MAP_FOR_ADDING_POINT, mapsContext.getMapIds(), "Select map for point adding", false);

        intAddPoint = new Intent(this, PointActivity.class);
        intAddPoint.putExtra(PointActivity.EXTRA_LOC, latLng);

        //Toast.makeText(this, "Long click", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Intent intent = new Intent(this, PointActivity.class);
        intent.putExtra(PointActivity.EXTRA_POINT_ID, (Integer) marker.getTag());
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView =
                (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setQueryHint("Cafee");
        final String[] from = new String[]{"cityName"};
        final int[] to = new int[]{android.R.id.text1};
        final CursorAdapter cursorAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1,
                null,
                from,
                to,
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);


        searchView.setSuggestionsAdapter(cursorAdapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (mapsContext.isEmpty()) {
                    return true;
                }
                final MatrixCursor c = new MatrixCursor(new String[]{BaseColumns._ID, "cityName"});
                int i = 0;
                /*for (Point p :
                        mapsContext.getPoints()
                        ) {
                    if (p.name.toLowerCase().contains(newText.toLowerCase())) {
                        c.addRow(new Object[]{i++, p});
                    }
                }*/


                cursorAdapter.changeCursor(c);
                return false;
            }
        });
   /*     searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionSelect(int position) {

                return false;
            }

            @Override
            public boolean onSuggestionClick(int position) {

                //Point p = ((MatrixCursor)searchView.getSuggestionsAdapter().getItem(position)).getDouble();
                //mMap.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.fromLatLngZoom(new LatLng(p.getX(), p.getY()),10)));
                return false;
            }
        });*/
        return true;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_maps:
                MapsActivity.startWithFullMapList(this, REQUEST_MAPS, "Select map to view", true);

                break;
            case R.id.menu_login:
                Intent loginIntent = new Intent(this, LoginActivity.class);
                startActivityForResult(loginIntent, REQUEST_LOGIN);
                break;
        }
        return true;
    }

    private void syncMapWithMapsContext() {
        if (mMap != null) {
            mMap.clear();
        }
        new GoogleMapAdapter(mMap).fill(mapsContext);
    }

    @Override
    public void mapsContextChanged(final MapsContext mapsContext) {
        {
            //refresh points on the map
            syncMapWithMapsContext();

            //refresh context strip
            ViewGroup viewGroup = (ViewGroup) findViewById(R.id.list_map_context);
            viewGroup.removeAllViews();
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            if (!mapsContext.isEmpty()) {

                for (Map m : mapsContext.getMaps()) {
                    View v = inflater.inflate(R.layout.map_context_item, viewGroup, false);
                    viewGroup.addView(v);
                    ((ImageView) v.findViewById(R.id.image_map_icon)).setImageBitmap(m.icon);
                    ((TextView) v.findViewById(R.id.text_map_name)).setText(m.name);
                    v.setTag(m);
                    v.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            Map m = (Map) v.getTag();
                            mapsContext.remove(m);
                            return true;
                        }
                    });
                }
            } else {
                View v = inflater.inflate(R.layout.label_empty_context, viewGroup, false);
                viewGroup.addView(v);
            }
        }
    }
}
