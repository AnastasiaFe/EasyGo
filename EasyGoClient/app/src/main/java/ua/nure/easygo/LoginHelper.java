package ua.nure.easygo;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Oleg on 18.11.2016.
 */

public class LoginHelper {

    public static final String LOGIN_PREFERENCES = "login_prefs", LOGIN = "login", PASSWORD = "password";
    private static LoginHelper instance;
    private String login, password;

    private LoginHelper() {
    }

    public synchronized static LoginHelper getInstance() {
        if (instance == null) {
            instance = new LoginHelper();
        }
        return instance;
    }

    private SharedPreferences getPrefs(Context c) {
        return c.getSharedPreferences(LOGIN_PREFERENCES, Context.MODE_PRIVATE);
    }

    private void  loadUser(Context context) {
        SharedPreferences preferences = getPrefs(context);
        if (hasCredentials(context)) {
            login = preferences.getString(LOGIN, "");
            password = preferences.getString(PASSWORD, "");
        }

    }

    private void saveUser(Context context) {
        SharedPreferences.Editor e = getPrefs(context).edit();
        e.putString(LOGIN, login);
        e.putString(PASSWORD, password);
        e.commit();
        e.apply();
    }

    public boolean hasCredentials(Context c) {
        SharedPreferences preferences = getPrefs(c);
        return preferences.contains(LOGIN) && preferences.contains(PASSWORD);
    }

    public String getLogin(Context context) {
        loadUser(context);
        return login;
    }

    public void setCurrentUser(Context context, String mEmail, String mPassword) {
        this.login = mEmail;
        this.password = mPassword;
        saveUser(context);
    }
}
