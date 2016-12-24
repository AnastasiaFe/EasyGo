package ua.nure.easygo.activities;

import android.support.v7.app.AppCompatActivity;

import ua.nure.easygo.help.HelpDialogManager;

/**
 * Created by Oleg on 24.12.2016.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onStart() {
        super.onStart();

        HelpDialogManager.showHelp(getClass(), this);

    }
}
