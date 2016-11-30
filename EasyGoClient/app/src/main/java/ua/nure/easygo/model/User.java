package ua.nure.easygo.model;

/**
 * Created by Oleg on 18.11.2016.
 */

public class User {
    public String name;
    public String login;
    public String password;



    public User(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public User() {
    }

    public String getAvatar() {
        return "user" + login;
    }


}
