package com.trainsystem.db;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DbJsonObject {
    private JSONObject jsonObject;

    public DbJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public JSONObject getObject(String key)
    {
        return (JSONObject) jsonObject.get(key);
    }

    public JSONArray getArray(String key)
    {
        return (JSONArray) jsonObject.get(key);
    }

    public Object get(String key)
    {
        return jsonObject.get(key);
    }

    public int getInt(String key)
    {
        if(!jsonObject.containsKey(key))
            return 0;
        if(jsonObject.get(key) instanceof Long)
            return  ((Long)jsonObject.get(key)).intValue();
        else if(jsonObject.get(key) instanceof String)
            return Integer.parseInt((String) jsonObject.get(key));
        else
            return (int) jsonObject.get(key);
    }

    public String getString(String key)
    {
        if(!jsonObject.containsKey(key))
            return null;
        if(jsonObject.get(key) instanceof Long)
            return ((Long)jsonObject.get(key)).toString();
        else
            return jsonObject.get(key).toString();
    }

    public double getDouble(String key)
    {
        if(!jsonObject.containsKey(key))
            return 0;
        if(jsonObject.get(key) instanceof Long)
            return Double.valueOf((Long)jsonObject.get(key));
        else if(jsonObject.get(key) instanceof String)
            return Double.valueOf(jsonObject.get(key).toString());
        else
            return (double) jsonObject.get(key);
    }

    public Date getDate(String key)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");

        try {
            return formatter.parse((String) jsonObject.get(key));
        } catch (ParseException ignore) {}
        return null;
    }

    public static DbJsonObject create(JSONObject jsonObject)
    {
        return new DbJsonObject(jsonObject);
    }

    public static DbJsonObject create(Object jsonObject)
    {
        return new DbJsonObject((JSONObject) jsonObject);
    }

    @Override
    public String toString() {
        return jsonObject.toString();
    }
}

