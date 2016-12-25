package ua.nure.easygo.activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

import ua.nure.easygo.help.HelpDialogManager;

/**
 * Created by Oleg on 24.12.2016.
 */

public class BaseActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;

    /**
     * Count of currently running background tasks
     */
    private int fetchesCount;

    @Override
    protected void onStart() {
        super.onStart();

        HelpDialogManager.showHelp(getClass(), this);

    }

    protected synchronized final void endFetching() {
        fetchesCount--;
        if (fetchesCount == 0) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    protected synchronized final void startFetching() {
        fetchesCount++;
        if (fetchesCount == 1) {
            progressDialog = ProgressDialog.show(this, "Loading", "Please wait", true, false);
        }

    }
}
