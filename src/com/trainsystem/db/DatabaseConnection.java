package com.trainsystem.db;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

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


}
