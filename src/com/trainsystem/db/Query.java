package com.trainsystem.db;

import com.jayway.jsonpath.Criteria;
import com.jayway.jsonpath.Filter;
import com.jayway.jsonpath.JsonPath;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Query {
    private List<String> tables = new ArrayList<>();
    private Filter filter;
    private JSONArray database = null;

    /*public Query(List<String> tables) {
        this.tables = tables;
    }*/

    public Query(String table) {
        this.tables.add(table);
    }


    public Query(JSONArray jsonArray) {
        database = jsonArray;
    }

    public JSONArray get()
    {

        String tableStructure = "$.";
        if(tables.size() > 0)
            tableStructure += StringUtils.join(this.tables, '.');
        if(this.filter != null)
            tableStructure += "[?]";

        if(tables.size() == 0)
            tableStructure += "[*]";

        if (this.filter == null)
            return JsonPath.read((database==null?DatabaseConnection.getInstance().getDatabase():database), tableStructure);
        else
        {
            try {
                String x = JsonPath.parse((database==null?DatabaseConnection.getInstance().getDatabase():database)).read(tableStructure, this.filter).toString();
                database = (JSONArray)new JSONParser().parse(x);
                return database;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Query where(Criteria criteria)
    {
        filter = Filter.filter(criteria);
        return this;
    }
    public Query where(Filter filter)
    {
        this.filter = filter;
        return this;
    }

    public JSONObject first()
    {
        if(database == null)
            this.get();

        if(database.size() > 0)
            return (JSONObject) database.get(0);
        return null;
    }

    public JSONArray all()
    {
        if(database == null)
            return this.get();
        else
            return database;
    }

}
