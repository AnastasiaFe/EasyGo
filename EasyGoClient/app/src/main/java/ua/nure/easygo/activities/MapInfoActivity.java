package ua.nure.easygo.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.FileNotFoundException;
import java.io.InputStream;

import easygo.nure.ua.easygoclient.R;
import easygo.nure.ua.easygoclient.databinding.ActivityMapInfoBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ua.nure.easygo.Constants;
import ua.nure.easygo.LoginHelper;
import ua.nure.easygo.adapters.MapAttributesAdapter;
import ua.nure.easygo.model.Map;
import ua.nure.easygo.model.User;
import ua.nure.easygo.model.attributes.AttributeType;
import ua.nure.easygo.model.attributes.MapAttribute;
import ua.nure.easygo.rest.EasyGoService;
import ua.nure.easygo.rest.ImageService;
import ua.nure.easygo.rest.RestService;
import ua.nure.easygo.utils.Logger;

public class MapInfoActivity extends AppCompatActivity {

    public static final String EXTRA_MAP_ID = "map_id";
    private static final int REQUEST_SELECT_ICON = 1;
    EasyGoService service;


    MapAttributesAdapter adapter;

    ActivityMapInfoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_map_info);
        binding.imageIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select icon"), REQUEST_SELECT_ICON);
            }
        });

        service = RestService.get();
        final long mapId = getIntent().getLongExtra(EXTRA_MAP_ID, Constants.ID_NONE);
        if (mapId != Constants.ID_NONE) {

            service.getMap(mapId).enqueue(new Callback<Map>() {
                @Override
                public void onResponse(Call<Map> call, Response<Map> response) {
                    binding.setMap(response.body());

                    adapter = new MapAttributesAdapter(binding.mapAttributes, response.body().mapAttributes.attributes);

                }

                @Override
                public void onFailure(Call<Map> call, Throwable t) {

                }
            });

        } else {
            binding.setMap(new Map());
            binding.getMap().ownerLogin = LoginHelper.getInstance().getLogin(this);
            adapter = new MapAttributesAdapter(binding.mapAttributes, binding.getMap().mapAttributes.attributes);

        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        binding.buttonAddAttribute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.getMap().mapAttributes.attributes.add(new MapAttribute("new", AttributeType.values()[(int) (Math.random() * AttributeType.values().length)]));
                adapter = new MapAttributesAdapter(binding.mapAttributes, binding.getMap().mapAttributes.attributes);
                binding.attrsScroll.fullScroll(View.FOCUS_DOWN);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUEST_SELECT_ICON) {
            try {
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                Bitmap icon = BitmapFactory.decodeStream(inputStream);
                ImageService.getInstance().uploadBitmap(icon, binding.getMap().getIcon());
                binding.imageIcon.setImageBitmap(icon);
            } catch (FileNotFoundException e) {
                Logger.toast(this, e.getMessage());
                e.printStackTrace();
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        RestService.get().getUser(LoginHelper.getInstance().getLogin(this)).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body() != null && response.body().isAdmin()) {
                    menu.add("Delete").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            RestService.get().deleteMap(binding.getMap().mapId).enqueue(new Callback<Boolean>() {
                                @Override
                                public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                    finish();
                                }

                                @Override
                                public void onFailure(Call<Boolean> call, Throwable t) {

                                }
                            });
                            return false;
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save) {
            service.postMap(binding.getMap()).enqueue(new Callback<Boolean>() {
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                    finish();
                }

                @Override
                public void onFailure(Call<Boolean> call, Throwable t) {
                    Logger.toast(MapInfoActivity.this, t.getMessage());
                }
            });

        }
        return super.onOptionsItemSelected(item);

    }
}