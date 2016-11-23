package ua.nure.easygo.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.util.DisplayMetrics;


import java.util.Locale;

import easygo.nure.ua.easygoclient.R;


public class SettingsActivity extends PreferenceActivity {
    Locale myLocale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
        Preference customPref = (Preference) findPreference("lang");
      // customPref
             //   .setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

                 /**   public boolean onPreferenceChange(Preference preference, Object newValue) {
                        switch (newValue.toString()) {
                            case "English":
                                setLocale("English");
                                break;

                            case "Russian":
                                setLocale("Russian");
                                break;
                        }
                        return true;
                    }

                });*/

    }

    public void setLocale(String lang) {
        myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, SettingsActivity.class);
        finish();
        startActivity(refresh);


    }
}
