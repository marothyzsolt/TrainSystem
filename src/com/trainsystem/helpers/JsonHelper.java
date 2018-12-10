package com.trainsystem.helpers;

import com.trainsystem.db.DbJsonObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonHelper {

    public static JSONArray removeByKey(String key, int value, JSONArray from) { return removeByKey(key, String.valueOf(value), from); }
    public static JSONArray removeByKey(String key, String value, JSONArray from)
    {
        int i=0;
        for (Object jsonObject: from)
        {
            if((DbJsonObject.create(jsonObject)).getString(key).equals(value))
                return remove(i, from);
            i++;
        }
        return from;
    }

    public static JSONArray remove(int idx, JSONArray from) {
        List<JSONObject> objs = asList(from);
        objs.remove(idx);

        JSONArray ja = new JSONArray();
        for (JSONObject obj : objs) {
            ja.add(obj);
        }

        return ja;
    }

    public static List<JSONObject> asList(JSONArray ja) {
        int len = ja.size();
        ArrayList<JSONObject> result = new ArrayList<>(len);
        for (int i = 0; i < len; i++) {
            JSONObject obj = (JSONObject) ja.get(i);
            if (obj != null) {
                result.add(obj);
            }
        }
        return result;
    }
}