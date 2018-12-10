package com.trainsystem.models;

import com.trainsystem.db.Query;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class Route extends BaseModel {

    private int id;
    private String from;
    private String to;
    private int distance;
    private ArrayList<Time> times;


    public Route(JSONObject jsonObject) {
        id = ((Long)jsonObject.get("id")).intValue();
        from = (String) jsonObject.get("from");
        to = (String) jsonObject.get("to");
        distance = (int) jsonObject.get("distance");
        times = (Time.make((JSONArray) jsonObject.get("times")));
    }

    public static Route make(JSONObject jsonObject) { return jsonObject==null?null:new Route(jsonObject); }
    public static ArrayList<Route> make(Query query)
    {
        return make(query.all());
    }
    public static ArrayList<Route> make(JSONArray jsonArray)
    {
        ArrayList<Route> objs = new ArrayList<>();
        jsonArray.forEach(item-> objs.add(new Route((JSONObject) item)));

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
    protected Map<String, String> insert(int id) { return Map.of("id", String.valueOf(id), "from", from, "to", to, "distance", String.valueOf(distance)); }

    @Override
    protected Map<String, String> save() {
        return null;
    }
}
