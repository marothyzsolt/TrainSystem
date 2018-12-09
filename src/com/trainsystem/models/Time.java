package com.trainsystem.models;

import com.trainsystem.db.DbJsonObject;
import com.trainsystem.db.Query;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class Time extends BaseModel {
    private Date start;
    private Date arrive;

    public Time(DbJsonObject jsonObject)
    {
        id = jsonObject.getInt("id");
        start = jsonObject.getDate("start");
        arrive = jsonObject.getDate("arrive");
    }

    public static Time make(JSONObject jsonObject) { return jsonObject==null?null:new Time(DbJsonObject.create(jsonObject)); }
    public static ArrayList<Time> make(Query query)
    {
        return make(query.all());
    }
    public static ArrayList<Time> make(JSONArray jsonArray)
    {
        ArrayList<Time> objs = new ArrayList<>();
        jsonArray.forEach(item-> objs.add(new Time(DbJsonObject.create(item))));

        return objs;
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



    @Override
    protected Map<String, String> insert(int id) {
        return null;
    }

    @Override
    protected Map<String, String> save() {
        return null;
    }
}
