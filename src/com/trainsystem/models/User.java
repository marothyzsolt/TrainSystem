package com.trainsystem.models;

import com.trainsystem.db.DbJsonObject;
import com.trainsystem.db.Query;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class User extends BaseModel {

    private String username;
    private String password;
    private String role;


    public User(DbJsonObject dbJsonObject)
    {
        id = dbJsonObject.getInt("id");
        username = dbJsonObject.getString("username");
        password = dbJsonObject.getString("password");
        role = dbJsonObject.getString("role");
    }

    public User(String username, String password, String role)
    {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public static User make(JSONObject jsonObject) { return jsonObject==null?null:new User(DbJsonObject.create(jsonObject)); }
    public static ArrayList<User> make(Query query) { return make(query.all()); }
    public static ArrayList<User> make(JSONArray jsonArray)
    {
        ArrayList<User> users = new ArrayList<>();
        jsonArray.forEach(item-> users.add(new User(new DbJsonObject((JSONObject) item))));

        return users;
    }

    @Override
    protected Map<String, String> insert(int id) { return Map.of("id", String.valueOf(id), "username", username, "password", password, "role", role); }
    @Override
    protected Map<String, String> save() { return null; }
    public void store() { store("users"); }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
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
