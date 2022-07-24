package client.model;

import com.google.gson.Gson;

import java.util.HashMap;

public class Response {
    private int status;
    private HashMap<String, Object> data;

    public int getStatus() {
        return status;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void addData(String key, Object value) {
        this.data.put(key, value);
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static Response fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Response.class);
    }
}
