package ua.nure.easygo.model;

import android.graphics.Bitmap;

/**
 * Created by Oleg on 18.11.2016.
 */

public class User {
    public String name;
    public String login;
    public String password;

    public Bitmap avatar;

    /**
     * @param name
     * @param login
     * @param password
     * @param avatar   can be null
     */
    public User(String name, String login, String password, Bitmap avatar) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.avatar = avatar;
    }

    public User() {
    }

}
