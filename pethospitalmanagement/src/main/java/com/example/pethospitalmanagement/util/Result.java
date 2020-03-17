package com.example.pethospitalmanagement.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

public abstract class Result {
    public static JSONObject done() {
        JSONObject json = JsonUtils.JO();
        json.put("ID", "S");
        return json;
    }

    public static JSONObject done(int rows) {
        JSONObject json = JsonUtils.JO();
        json.put("ID", "S");
        json.put("ROWS", rows);
        return json;
    }

    public static JSONObject done(String message) {
        JSONObject json = JsonUtils.JO();
        json.put("ID", "S");
        json.put("MSG", message);
        return json;
    }

    public static JSONObject done(int rows, String message) {
        JSONObject json = JsonUtils.JO();
        json.put("ID", "S");
        json.put("ROWS", rows);
        json.put("MSG", message);
        return json;
    }

    public static JSONObject error(String errorMessage) {
        JSONObject json = JsonUtils.JO();
        json.put("ID", "E");
        json.put("MSG", errorMessage);
        return json;
    }

    public static JSONObject error(int errorId, String errorMessage) {
        JSONObject json = JsonUtils.JO();
        json.put("ID", "E");
        json.put("EID", errorId);
        json.put("MSG", errorMessage);
        return json;
    }

    public static JSONObject object(Object object) {
        JSONObject json = JsonUtils.JO();
        json.put("ID", "S");
        json.put("TYPE", "OBJECT");
        json.put("DATA", object);
        return json;
    }

    public static JSONObject map(Map<String, Object> map) {
        JSONObject json = JsonUtils.JO();
        json.put("ID", "S");
        json.put("TYPE", "MAP");
        json.put("DATA", map);
        return json;
    }

    public static JSONObject list(List<Object> list) {
        JSONObject json = JsonUtils.JO();
        json.put("ID", "S");
        json.put("TYPE", "LIST");
        json.put("DATA", list);
        return json;
    }

    public static boolean isSuccess(JSONObject result) {
        if (result == null) {
            return false;
        }
        String id = result.getString("ID");
        return "S".equals(id);
    }

    public static boolean isFailure(JSONObject result) {
        return !isSuccess(result);
    }

    public static String getID(JSONObject result) {
        if (result == null) {
            return "E";
        }
        String id = result.getString("ID");
        if (id == null) {
            return "E";
        }
        id = id.trim();
        if (id.isEmpty()) {
            return "E";
        }
        return id;
    }

    public static int getEID(JSONObject result) {
        if (result == null) {
            return 0;
        }
        return result.getIntValue("EID");
    }

    public static int getROWS(JSONObject result) {
        if (result == null) {
            return 0;
        }
        return result.getIntValue("ROWS");
    }

    public static String getMSG(JSONObject result, String defaultMSG) {
        if (result == null) {
            return defaultMSG;
        }
        if (!result.containsKey("MSG")) {
            return defaultMSG;
        }
        String msg = result.getString("MSG");
        if (msg == null) {
            return defaultMSG;
        }
        msg = msg.trim();
        if (msg.isEmpty()) {
            return defaultMSG;
        }
        return msg;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getObject(JSONObject result, T defaultObject) {
        if (result == null) {
            return defaultObject;
        }
        if (!result.containsKey("DATA")) {
            return defaultObject;
        }
        return (T) result.get("DATA");
    }

    public static JSONObject getJO(JSONObject result, JSONObject defaultMap) {
        if (result == null) {
            return defaultMap;
        }
        if (!result.containsKey("DATA")) {
            return defaultMap;
        }
        String type = result.getString("TYPE");
        if (!"MAP".equals(type)) {
            return defaultMap;
        }
        return result.getJSONObject("DATA");
    }

    public static JSONArray getJA(JSONObject result, JSONArray defaultList) {
        if (result == null) {
            return defaultList;
        }
        if (!result.containsKey("DATA")) {
            return defaultList;
        }
        String type = result.getString("TYPE");
        if (!"LIST".equals(type)) {
            return defaultList;
        }
        return result.getJSONArray("DATA");
    }

    private Result() {
        throw new IllegalArgumentException("Constructor is not permitted");
    }
}
