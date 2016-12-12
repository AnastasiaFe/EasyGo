package ua.nure.easygo.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import easygo.nure.ua.easygoclient.R;
import ua.nure.easygo.LoginHelper;

public class ProfileActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, ProfileActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        String login = LoginHelper.getInstance().getLogin(this);
    }
}
