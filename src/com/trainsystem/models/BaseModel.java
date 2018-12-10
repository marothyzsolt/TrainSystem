package com.trainsystem.models;

import com.google.gson.JsonObject;
import com.jayway.jsonpath.Criteria;
import com.jayway.jsonpath.Filter;
import com.trainsystem.db.DatabaseConnection;
import com.trainsystem.db.DbJsonArray;
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

    protected DbJsonArray findData(String table, JSONObject db)
    {
        System.out.println(table);
        String[] split = table.split("[.]");
        if(table.length() == 0)
        {
            if(db != null) {
                if (db instanceof JSONObject) {
                    //JSONArray arr = new JSONArray();
                    //arr.add(db);
                    return DbJsonArray.create(db);
                }
            }
            return null;
        }
        if(split.length == 1 && (!split[0].contains("[") && !split[0].contains("]")))
        {
            /*JSONArray x = (JSONArray) db.get(split[0]);
            JSONObject xo = new JSONObject();
            datas.forEach(xo::put);
            x.add(xo);*/
            return DbJsonArray.create(db.get(split[0]));
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
                        return findData(onlyTableName, (JSONObject) currDb.get(i));
                    }
                    i++;
                }
            } else {
                Object currDb = db.get(s);

                if (currDb instanceof JSONObject) {
                    return findData(onlyTableName, (JSONObject) currDb);
                }

            }
        }
        return null;
    }


    protected JSONObject insertData(Map<String, String> datas, DbJsonArray database)
    {
        JSONObject jsonObject = new JSONObject();
        datas.forEach(jsonObject::put);
        database.get().add(datas);

        return DatabaseConnection.getInstance().getDatabase();
    }

    protected JSONObject insertData(Map<String, String> datas, JSONArray database)
    {
        JSONObject jsonObject = new JSONObject();
        datas.forEach(jsonObject::put);
        database.add(datas);

        return DatabaseConnection.getInstance().getDatabase();
    }

    protected JSONObject insertData(String table, Map<String, String> datas)
    {
<<<<<<< HEAD
        System.out.println("insertData");
        //if(table.split(".").length)
        if(DatabaseConnection.getInstance().getDatabase().get(table) instanceof JSONArray) {
            System.out.println("insertData__Array");
            JSONArray arr = (JSONArray) DatabaseConnection.getInstance().getDatabase().get(table);
=======
        return insertData(table, datas, DatabaseConnection.getInstance().getDatabase());
    }

    @SuppressWarnings("unchecked")
    protected JSONObject insertData(String table, Map<String, String> datas, JSONObject database)
    {

        //if(table.split(".").length)
        if(database.get(table) instanceof JSONArray) {
            JSONArray arr = (JSONArray) database.get(table);
>>>>>>> 446df890cd12830cdabb56b2065fcbe447fb29ac
            JSONObject jsonObject = new JSONObject();

            datas.forEach(jsonObject::put);
            ((JSONArray) database.get(table)).add(jsonObject);
            database.put(table, arr);
        } else if(database.get(table) instanceof JSONObject)
        {
            System.out.println("insertData__Object");
            JSONObject jsonObject = new JSONObject();
            datas.forEach(jsonObject::put);
            database.putAll(datas);
        }

        return DatabaseConnection.getInstance().getDatabase();
<<<<<<< HEAD
=======
       // return null;
>>>>>>> 446df890cd12830cdabb56b2065fcbe447fb29ac
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
        return getNextId(DatabaseConnection.getInstance().getTable(table));
    }

    protected int getNextId(JSONArray jsonArray)
    {
        int maxid = 0;
        for (Object jsonObject : jsonArray)
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
