package ua.nure.easygo.activities;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import easygo.nure.ua.easygoclient.R;
import easygo.nure.ua.easygoclient.databinding.ActivityProfileBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ua.nure.easygo.LoginHelper;
import ua.nure.easygo.model.User;
import ua.nure.easygo.rest.ImageService;
import ua.nure.easygo.rest.RestService;
import ua.nure.easygo.utils.Logger;

public class ProfileActivity extends BaseActivity {

    private static final int REQUEST_SELECT_AVATAR = 1;

    public static void start(Context context) {
        Intent intent = new Intent(context, ProfileActivity.class);
        context.startActivity(intent);
    }

    @BindView(R.id.text_password)
    TextView textPassword;

    @BindView(R.id.layout_password)
    TextInputLayout passwordLayout;

    @BindView(R.id.image_avatar)
    ImageView imageAvatar;

    @BindView(R.id.toolbar)
    Toolbar toolbar;


    ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        ButterKnife.bind(this);

        String login = LoginHelper.getInstance().getLogin(this);
        RestService.get().getUser(login).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                binding.setUser(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Logger.toast(ProfileActivity.this, t.getMessage());
            }
        });

        setSupportActionBar(toolbar);
    }

    @OnClick(R.id.button_change_password)
    public void changePassword() {
        String password = textPassword.getText().toString();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_SELECT_AVATAR) {
            try {
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                Bitmap icon = BitmapFactory.decodeStream(inputStream);
                ImageService.getInstance().uploadBitmap(icon, binding.getUser().getAvatar());
                imageAvatar.setImageBitmap(icon);
            } catch (FileNotFoundException e) {
                Logger.toast(this, e.getMessage());
                e.printStackTrace();
            }

        }
    }

    @OnClick(R.id.avatar_container)
    public void requestImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select avatar!"), REQUEST_SELECT_AVATAR);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save) {
            RestService.get().postUser(binding.getUser()).enqueue(new Callback<Boolean>() {
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {

                    finish();
                }

                @Override
                public void onFailure(Call<Boolean> call, Throwable t) {

                }
            });
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
