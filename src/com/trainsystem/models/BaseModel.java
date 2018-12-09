package com.trainsystem.models;

import com.jayway.jsonpath.Criteria;
import com.jayway.jsonpath.Filter;
import com.trainsystem.db.DatabaseConnection;
import com.trainsystem.db.Query;
import com.trainsystem.helpers.JsonHelper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Map;


abstract public class BaseModel {
    protected int id;
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

    public static Query all(String table) { return new Query(table); }

    @SuppressWarnings("unchecked")
    protected JSONObject insertData(String table, Map<String, String> datas)
    {
        JSONArray arr = (JSONArray) DatabaseConnection.getInstance().getDatabase().get(table);
        JSONObject jsonObject = new JSONObject();

        datas.forEach(jsonObject::put);
        ((JSONArray) DatabaseConnection.getInstance().getDatabase().get(table)).add(jsonObject);
        DatabaseConnection.getInstance().getDatabase().put(table, arr);

        return DatabaseConnection.getInstance().getDatabase();
    }

    abstract protected Map<String, String> insert(int id);
    abstract protected Map<String, String> save();

    public void store(String table)
    {
        if(this.id == 0)
            insertData(table, insert(getNextId(table)));
        else
            save();

        DatabaseConnection.getInstance().saveDatabase();
    }

    private int getNextId(String table)
    {
        int maxid = 0;
        for (Object jsonObject : (JSONArray) DatabaseConnection.getInstance().getDatabase().get(table))
            if (jsonObject != null && (id = Integer.parseInt(String.valueOf(((JSONObject) jsonObject).get("id")))) > maxid)
                maxid = id;
        return ++maxid;
    }

    public int getId() {
        return id;
    }

    @SuppressWarnings("unchecked")
    public void delete(String table, int id)
    {
        DatabaseConnection.getInstance().getDatabase().replace(
                table,
                DatabaseConnection.getInstance().getTable(table),
                JsonHelper.removeByKey("id", id, DatabaseConnection.getInstance().getTable(table))
        );

        DatabaseConnection.getInstance().saveDatabase();
    }


}
