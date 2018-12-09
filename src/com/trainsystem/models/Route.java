package com.trainsystem.models;

import com.trainsystem.db.Query;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class Route extends BaseModel {

    private int id;
    private String from;
    private String to;
    private ArrayList<Time> times;


    public Route(JSONObject jsonObject) {
        id = ((Long)jsonObject.get("id")).intValue();
        from = (String) jsonObject.get("from");
        to = (String) jsonObject.get("to");

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
}
