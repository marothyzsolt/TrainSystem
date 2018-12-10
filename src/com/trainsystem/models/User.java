package com.trainsystem.models;

import com.trainsystem.db.Query;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class User extends BaseModel {

    private int id;
    private String username;
    private String password;
    private String role;


    public User(JSONObject jsonObject) {
        id = ((Long)jsonObject.get("id")).intValue();
        username = (String) jsonObject.get("username");
        password = (String) jsonObject.get("password");
        role = (String) jsonObject.get("role");
    }

    public static User make(JSONObject jsonObject) { return jsonObject == null ? null : new User(jsonObject); }
    public static ArrayList<User> make(JSONArray jsonArray)
    {
        ArrayList<User> users = new ArrayList<>();
        jsonArray.forEach(item-> users.add(new User((JSONObject) item)));

        return users;
    }
    public static ArrayList<User> make(Query query)
    {
        return make(query.all());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role.toLowerCase();
    }

    public boolean isUser() { return getRole().equals("user"); }
    public boolean isWorker() { return getRole().equals("worker"); }
    public boolean isAdmin() { return getRole().equals("admin"); }
}
