package com.trainsystem.models;

import com.trainsystem.db.Query;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Time {
    private int id;
    private Date start;
    private Date arrive;

    public Time(JSONObject jsonObject) {
        id = ((Long)jsonObject.get("id")).intValue();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");

        try {
            start = formatter.parse((String) jsonObject.get("start"));
            arrive = formatter.parse((String) jsonObject.get("arrive"));
        } catch (ParseException ignore) {}
    }

    public static Time make(JSONObject jsonObject) { return jsonObject==null?null:new Time(jsonObject); }
    public static ArrayList<Time> make(Query query)
    {
        return make(query.all());
    }
    public static ArrayList<Time> make(JSONArray jsonArray)
    {
        ArrayList<Time> objs = new ArrayList<>();
        jsonArray.forEach(item-> objs.add(new Time((JSONObject) item)));

        return objs;
    }

    public int getId() {
        return id;
    }

    public Date getStart() {
        return start;
    }

    public Date getArrive() {
        return arrive;
    }

    public String getStartFormat()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.' 'MM.' 'dd. ' 'HH:mm");
        return formatter.format(start);
    }

    public String getArriveFormat()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.' 'MM.' 'dd. ' 'HH:mm");
        return formatter.format(arrive);
    }

    @Override
    public String toString() {
        return "Time{" +
                "id=" + id +
                ", start=" + getStartFormat() +
                ", arrive=" + getArriveFormat() +
                '}';
    }
}
