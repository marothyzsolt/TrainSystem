package com.trainsystem.models;

import com.jayway.jsonpath.Criteria;
import com.jayway.jsonpath.Filter;
import com.trainsystem.db.DatabaseConnection;
import com.trainsystem.db.DbJsonObject;
import com.trainsystem.db.Query;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class Route extends BaseModel {

    private String from;
    private String to;
    private int distance;
    private ArrayList<Time> times = new ArrayList<>();


    public Route(DbJsonObject jsonObject) {
        id = jsonObject.getInt("id");
        from = jsonObject.getString("from");
        to = jsonObject.getString("to");
        distance = jsonObject.getInt("distance");

        if(jsonObject.getArray("times") != null)
            times = (Time.make(jsonObject.getArray("times")));
    }

    public Route(String from, String to, int distance)
    {
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    public static Route make(JSONObject jsonObject) { return jsonObject==null?null:new Route(DbJsonObject.create(jsonObject)); }
    public static ArrayList<Route> make(Query query)
    {
        return make(query.all());
    }
    public static ArrayList<Route> make(JSONArray jsonArray)
    {
        ArrayList<Route> objs = new ArrayList<>();
        jsonArray.forEach(item-> objs.add(new Route(DbJsonObject.create(item))));

        return objs;
    }

    public static Route whereFromTo(String from, String to)
    {
        return make(Route.where("routes", Filter.filter(Criteria.where("from").is(from)).and(Criteria.where("to").is(to))).first());
    }

    public static ArrayList<Route> all() { return make(Route.all("routes").get()); }
    public void store() { store("routes"); }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", times=" + times +
                '}';
    }

    @Override
    protected Map<String, String> insert(int id) { return Map.of("id", String.valueOf(id), "from", from, "to", to, "distance", String.valueOf(distance)); }

    @Override
    protected Map<String, String> save() {
        return null;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public ArrayList<Time> getTimes() {
        return times;
    }

    public int getDistance() {
        return distance;
    }

    public int getTimeTableCount() {
        return times.size();
    }

    public int getPrice()
    {
        return distance*SystemInfo.getInstance().getKmPrice();
    }

    public void addTime(Time time) {
        if(time.getArrive() == null || time.getStart() == null)
            return;

        this.id = 3;

        /*JSONArray jsonArray = (JSONArray) DatabaseConnection.getInstance().getDatabase().get("routes");
        for (Object jsonObject : jsonArray) {
            JSONObject obj = (JSONObject) jsonObject;
            int currentId = DbJsonObject.create(obj).getInt("id");
            if(currentId == this.id)
            {
                //System.out.println(obj);
                JSONArray x = (JSONArray) obj.get("times");
                JSONObject xo = new JSONObject();
                xo.put("s", 1);
                xo.put("a", 2);
                x.add(xo);
                System.out.println(x);
                break;
            }
        }
        //insertData("routes")
        System.out.println(jsonArray);
        System.out.println(DatabaseConnection.getInstance().getTable("routes"));
        DatabaseConnection.getInstance().saveDatabase();*/
        insertData("routes[3].times", Map.of("start", "startVal", "arrival", "arrivalVal"), DatabaseConnection.getInstance().getDatabase());
        DatabaseConnection.getInstance().saveDatabase();
    }
}
