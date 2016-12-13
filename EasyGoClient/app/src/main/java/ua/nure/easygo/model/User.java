package ua.nure.easygo.model;

/**
 * Created by Oleg on 18.11.2016.
 */

public class User {
    public String name;
    public String login;
    public String password;
    public String role;


    public User(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
        role = "user";
    }

    public User() {
    }

    public boolean isAdmin(){
        return "admin".equals(role);
    }

    public String getAvatar() {
        return "user" + login;
    }


}
