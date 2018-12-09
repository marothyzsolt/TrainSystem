package com.trainsystem.models;

import com.trainsystem.db.DbJsonObject;
import com.trainsystem.db.Query;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class Route extends BaseModel {

    private String from;
    private String to;
    private ArrayList<Time> times;


    public Route(DbJsonObject jsonObject) {
        id = jsonObject.getInt("id");
        from = jsonObject.getString("from");
        to = jsonObject.getString("to");

        times = (Time.make(jsonObject.getArray("times")));
    }

    public static Route make(JSONObject jsonObject) { return jsonObject==null?null:new Route(DbJsonObject.create(jsonObject)); }
    public static ArrayList<Route> make(Query query)
    {
        return make(query.all());
    }
    public static ArrayList<Route> make(JSONArray jsonArray)
    {
        ArrayList<Route> objs = new ArrayList<>();
        jsonArray.forEach(item-> objs.add(new Route(DbJsonObject.create(item))));

        return objs;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", times=" + times +
                '}';
    }

    @Override
    protected Map<String, String> insert(int id) {
        return null;
    }

    @Override
    protected Map<String, String> save() {
        return null;
    }
}
