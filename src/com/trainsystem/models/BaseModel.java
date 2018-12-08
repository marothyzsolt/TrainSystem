package com.trainsystem.models;

import com.jayway.jsonpath.Criteria;
import com.jayway.jsonpath.Filter;
import com.trainsystem.db.DatabaseConnection;
import com.trainsystem.db.Query;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


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

}
