package com.trainsystem.models;

import com.jayway.jsonpath.Criteria;
import com.jayway.jsonpath.Filter;
import com.trainsystem.db.DatabaseConnection;
import com.trainsystem.db.DbJsonObject;
import com.trainsystem.db.Query;
import com.trainsystem.helpers.JsonHelper;
import org.apache.commons.lang3.StringUtils;
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

    protected JSONObject insertData(String table, Map<String, String> datas, JSONObject db)
    {
        System.out.println(table);
        String[] split = table.split("[.]");
        if(split.length == 1)
        {
            JSONArray x = (JSONArray) db.get(split[0]);
            JSONObject xo = new JSONObject();
            datas.forEach(xo::put);
            x.add(xo);
        } else {
            String s = split[0];
            split[0] = "";
            String onlyTableName = StringUtils.join(split, '.');
            if (onlyTableName.length() > 1)
                onlyTableName = onlyTableName.substring(1);
            if (s.contains("[") && s.contains("]")) {
                int getId = Integer.parseInt(s.replaceAll("\\D+", ""));
                String getDb = s.replaceAll("[^A-Za-z]+", "");
                JSONArray currDb = (JSONArray) db.get(getDb);

                int i = 0;
                for (Object jsonObject : currDb) {
                    JSONObject obj = (JSONObject) jsonObject;
                    int currentId = DbJsonObject.create(obj).getInt("id");
                    if (currentId == getId) {
                        insertData(onlyTableName, datas, (JSONObject) currDb.get(i));
                        break;
                    }
                    i++;
                }
            } else {
                Object currDb = db.get(s);

                if (currDb instanceof JSONObject) {
                    if (split.length > 1)
                        insertData(onlyTableName, datas, (JSONObject) currDb);
                }

            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    protected JSONObject insertData(String table, Map<String, String> datas)
    {

        //if(table.split(".").length)
        /*if(DatabaseConnection.getInstance().getDatabase().get(table) instanceof JSONArray) {
            JSONArray arr = (JSONArray) DatabaseConnection.getInstance().getDatabase().get(table);
            JSONObject jsonObject = new JSONObject();

            datas.forEach(jsonObject::put);
            ((JSONArray) DatabaseConnection.getInstance().getDatabase().get(table)).add(jsonObject);
            DatabaseConnection.getInstance().getDatabase().put(table, arr);
        } else if(DatabaseConnection.getInstance().getDatabase().get(table) instanceof JSONObject)
        {
            JSONObject jsonObject = new JSONObject();
            datas.forEach(jsonObject::put);
            ((JSONObject)DatabaseConnection.getInstance().getDatabase().get(table)).putAll(datas);
        }

        return DatabaseConnection.getInstance().getDatabase();*/
        return null;
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

    protected void storeSingleObject(String table, Map<String, String> datas)
    {
        insertData(table, datas);
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
