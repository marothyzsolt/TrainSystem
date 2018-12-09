package com.trainsystem.db;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;

public class DatabaseConnection {
    private static final String filePath = "src/com/trainsystem/db/db.json";

    private JSONObject database = null;

    private static DatabaseConnection instance;
    static {
        instance = new DatabaseConnection();
    }

    private DatabaseConnection() {
        JSONParser parser = new JSONParser();

        try {
            database = (JSONObject) parser.parse( new FileReader(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DatabaseConnection getInstance() {
        if(instance == null)
            instance = new DatabaseConnection();
        return instance;
    }

    public JSONObject getDatabase()
    {
        return database;
    }

    public JSONArray getTable(String table)
    {
        return (JSONArray) database.get(table);
    }

    public void saveDatabase()
    {
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(database.toJSONString());
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}
