package ua.nure.easygo.activities;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;




import easygo.nure.ua.easygoclient.R;


public class SettingsActivity extends PreferenceActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);

    }
}





