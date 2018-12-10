package com.trainsystem.db;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DbJsonArray {
    private JSONArray jsonArray;

    public DbJsonArray(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    public DbJsonArray(Object obj) {
        jsonArray = new JSONArray();
        ((JSONArray) jsonArray).add(obj);
    }

    public Object get(int id)
    {
        return jsonArray.get(id);
    }
    //public



    public static DbJsonArray create(JSONArray jsonArray)
    {
        return new DbJsonArray(jsonArray);
    }
    public static DbJsonArray create(JSONObject jsonObject)
    {
        JSONArray arr = new JSONArray();
        arr.add(jsonObject);
        return new DbJsonArray(arr);
    }
    public static DbJsonArray create(Object jsonArray)
    {
        if(jsonArray instanceof JSONArray)
            return new DbJsonArray((JSONArray) jsonArray);
        else if(jsonArray instanceof Object)
            return new DbJsonArray(jsonArray);
        return null;
    }

    @Override
    public String toString() {
        if(jsonArray == null)
            return null;
        return jsonArray.toString();
    }

    public JSONArray get() {
        return jsonArray;
    }

    public DbJsonObject first() {
        return DbJsonObject.create(jsonArray.get(0));
    }

    public String firstString() { return (String) jsonArray.get(0); }
    public int firstInt() { return Integer.parseInt(String.valueOf(jsonArray.get(0))); }
    public double firstDouble() { return Double.parseDouble(String.valueOf(jsonArray.get(0))); }
}
