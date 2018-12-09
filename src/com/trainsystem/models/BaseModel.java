package com.trainsystem.models;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jayway.jsonpath.Criteria;
import com.jayway.jsonpath.Filter;
import com.trainsystem.db.DatabaseConnection;
import com.trainsystem.db.Query;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Map;


abstract public class BaseModel {
    protected DatabaseConnection db;


    public BaseModel() {
        this.db = DatabaseConnection.getInstance();
    }

    public static Query find(String table, int id)
    {
        return (new Query(table)).where(Criteria.where("id").is(id));
    }

    public static Query where(String table, Criteria criteria)
    {
        return (new Query(table)).where(criteria);
    }

    public static Query where(String table, Filter filter)
    {
        return (new Query(table)).where(filter);
    }

    public static Query where(String table, String key, String val) throws ParseException {
        Query query = (new Query(table)).where(Criteria.where(key).is(val));
        JSONArray jsonArray = (JSONArray) (new JSONParser()).parse(query.get().toString());

        return new Query(jsonArray);
    }

    protected void insert(String table, Map<String, String> datas)
    {
        DatabaseConnection db = DatabaseConnection.getInstance();
        String json = db.getDatabase().toString();
        Gson gson = new Gson();
        JsonObject inputObj  = gson.fromJson(json, JsonObject.class);
        JsonObject newObject = new JsonObject();
        datas.forEach((key,value) ->  newObject.addProperty(key, value));

        inputObj.get(table).getAsJsonArray().add(newObject);
        System.out.println(inputObj);
    }

}
