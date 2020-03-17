package com.example.pethospitalmanagement.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.DoubleSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.*;

/**
 * JSON 工具类
 *
 * 阿里巴巴 fastJson 简单封装
 *
 * fastJson 版本要求：1.2.20 或以上
 */
public class JsonUtils {

    /**
     * 日期(时间)值转换字符串格式化样式
     */
    private static String JSON_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * 浮点数值转换字符串格式化样式
     */
    private static String JSON_DOUBLE_FORMAT = "#.##########";

    private static DoubleSerializer doubleSerializer = new DoubleSerializer(JSON_DOUBLE_FORMAT);

    /**
     * Java 对象转换为 JSON 字符串
     *
     * @param object
     *            - Object
     * @return JSON 字符串
     */
    public static String toJson(Object object) {
        return toJson(object, false, false);
    }

    /**
     * Java 对象转换为 JSON 字符串
     *
     * @param object
     *            - Object
     * @param formatDate
     *            - boolean
     * @param removeNullValue
     *            - boolean
     * @return JSON 字符串
     */
    public static String toJson(Object object, boolean formatDate,
                                boolean removeNullValue) {
        if (object == null || object.equals("")) {
            return "{}";
        }
        if (object instanceof String) {
            return (String) object;
        }
        SerializeConfig config = SerializeConfig.getGlobalInstance();
        config.put(Double.class, doubleSerializer);
        List<SerializerFeature> features = new ArrayList<SerializerFeature>();
        features.add(SerializerFeature.DisableCircularReferenceDetect);
        features.add(SerializerFeature.WriteBigDecimalAsPlain);
        if (!removeNullValue) {
            features.add(SerializerFeature.WriteMapNullValue);
        }
        String dateFormat = null;
        if (formatDate) {
            dateFormat = JSON_DATETIME_FORMAT;
            features.add(SerializerFeature.WriteDateUseDateFormat);
        }
        return JSON.toJSONString(object, config, null, dateFormat,
                JSON.DEFAULT_GENERATE_FEATURE,
                features.toArray(new SerializerFeature[0]));
    }

    /**
     * 构造空 JSONObject 对象
     *
     * @return JSONObject
     */
    public static JSONObject JO() {
        return new JSONObject(true);
    }

    /**
     * 构造空 JSONArray 对象
     *
     * @return JSONArray
     */
    public static JSONArray JA() {
        return new JSONArray();
    }

    /**
     * 简单判断字符串是否为 JSON 格式 (JSONObject or JSONArray)
     *
     * @param json
     *            - String
     * @return boolean
     */
    public static boolean isJSON(String json) {
        return isJO(json) || isJA(json);
    }

    /**
     * 简单判断字符串是否为 JSONObject 格式
     *
     * @param json
     *            - String
     * @return boolean
     */
    public static boolean isJO(String json) {
        if (json == null || json.equals("")) {
            return false;
        }
        return json.startsWith("{") && json.endsWith("}");
    }

    /**
     * 简单判断字符串是否为 JSONArray 格式
     *
     * @param json
     *            - String
     * @return boolean
     */
    public static boolean isJA(String json) {
        if (json == null || json.equals("")) {
            return false;
        }
        return json.startsWith("[") && json.endsWith("]");
    }

    /**
     * Java 对象转换为 JSONObject
     *
     * @param json
     *            - String
     * @return JSONObject
     */
    public static JSONObject toJO(String json) {
        if (json == null || json.equals("")) {
            return new JSONObject(true);
        }
        if (!(json.startsWith("{") && json.endsWith("}"))) {
            throw new RuntimeException("NOT JSONObject string");
        }
        LinkedHashMap<String, Object> map = JSON.parseObject(json,
                new TypeReference<LinkedHashMap<String, Object>>() {
                }, Feature.OrderedField);
        if (map != null) {
            return new JSONObject(map);
        } else {
            return new JSONObject(true);
        }
    }

    /**
     * Java 对象转换为 JSONArray
     *
     * @param json
     *            - String
     * @return JSONArray
     */
    public static JSONArray toJA(String json) {
        if (json == null || json.equals("")) {
            return new JSONArray();
        }
        if (!(json.startsWith("[") && json.endsWith("]"))) {
            throw new RuntimeException("NOT JSONArray string");
        }
        return toJsonObject(json);
    }

    /**
     * JSON 字符串转换为 JSONObject 或者 JSONArray
     *
     * @param json
     *            - String
     * @return JSONObject 或者 JSONArray
     */
    public static <T> T toJsonObject(String json) {
        return JSON.parseObject(json, new TypeReference<T>() {
        });
    }

    /**
     * Java 对象转换为 JSONObject
     *
     * @param object
     *            - Object
     * @return JSONObject
     */
    public static JSONObject toJO(Object object) {
        if (object == null || object.equals("")) {
            return new JSONObject(true);
        }
        if (object instanceof JSONObject) {
            return (JSONObject) object;
        }
        return toJsonObject(object);
    }

    /**
     * Java 对象转换为 JSONArray
     *
     * @param object
     *            - Object
     * @return JSONArray
     */
    public static JSONArray toJA(Object object) {
        if (object == null || object.equals("")) {
            return new JSONArray();
        }
        if (object instanceof JSONArray) {
            return (JSONArray) object;
        }
        Object obj = toJsonObject(object);
        if (!(obj instanceof JSONArray)) {
            obj = (new JSONArray()).fluentAdd(object);
        }
        return (JSONArray) obj;
    }

    /**
     * Java 对象转换为 JSONObject 或者 JSONArray
     *
     * @param object
     *            - Object
     * @return JSONObject 或者 JSONArray
     */
    @SuppressWarnings("unchecked")
    public static <T> T toJsonObject(Object object) {
        if (object instanceof String) {
            return JSON.parseObject((String) object, new TypeReference<T>() {
            });
        } else {
            return (T) JSON.toJSON(object);
        }
    }

    /**
     * JSON 字符串转换为 JavaBean
     *
     * @param json
     *            - String
     * @param clazz
     *            - Class
     * @return Java Object
     */
    public static <T> T toBean(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }

    /**
     * JSON 字符串转换为 HashMap
     *
     * @param json
     *            - String
     * @return Map
     */
    @SuppressWarnings("rawtypes")
    public static Map toMap(String json) {
        if (json == null || json.equals("")) {
            return new HashMap();
        }
        return JSON.parseObject(json, HashMap.class);
    }

    /**
     * JSON 字符串转换为 LinkedHashMap
     *
     * @param json
     *            - String
     * @return Map
     */
    @SuppressWarnings("rawtypes")
    public static Map toLinkedMap(String json) {
        if (json == null || json.equals("")) {
            return new LinkedHashMap();
        }
        return JSON.parseObject(json, LinkedHashMap.class, Feature.OrderedField);
    }

    /**
     * JSON 字符串转换为 List
     *
     * @param json
     *            - String
     * @return List
     */
    @SuppressWarnings("rawtypes")
    public static List toList(String json) {
        if (json == null || json.equals("")) {
            return new ArrayList();
        }
        return JSON.parseObject(json, List.class);
    }

    /**
     * JSON 字符串转换为 List{@code <Map>}
     *
     * @param json
     *            - String
     * @return List{@code <Map>}
     */
    @SuppressWarnings("rawtypes")
    public static List<Map> toListMap(String json) {
        if (json == null || json.equals("")) {
            return new ArrayList<Map>();
        }
        return JSON.parseArray(json, Map.class);
    }

    /**
     * 按指定 Key 名获取 JSONObject 对象
     *
     * @param jo
     *            - 一个非空的 JSONObject 对象，如果对象不存在或者无法转换为 JSONObject，则返回一个 Empty 的 JSONObject 对象
     * @param key
     *            - jo 中的某个 Key 的名称
     * @return 获取到的 JSONObject 对象
     */
    @SuppressWarnings({ "unchecked" })
    public static JSONObject getJO(JSONObject jo, String key) {
        if (jo == null) {
            return JsonUtils.JO();
        }
        Object object = jo.get(key);
        if (object instanceof JSONObject) {
            return (JSONObject) object;
        } else if (object instanceof Map) {
            return new JSONObject((Map<String, Object>) object);
        } else {
            JSONObject json = toJO(object);
            if (json == null) {
                return JsonUtils.JO();
            } else {
                return json;
            }
        }
    }

    /**
     * 按指定 Key 名获取 JSONArray 对象
     *
     * @param jo
     *            - 一个非空的 JSONObject 对象，如果对象不存在或者无法转换为 JSONArray，则返回一个 Empty 的 JSONArray 对象
     * @param key
     *            - jo 中的某个 Key 的名称
     * @return 获取到的 JSONArray 对象
     */
    @SuppressWarnings({ "unchecked" })
    public static JSONArray getJA(JSONObject jo, String key) {
        if (jo == null) {
            return JsonUtils.JA();
        }
        Object object = jo.get(key);
        if (object instanceof JSONArray) {
            return (JSONArray) object;
        } else if (object instanceof List) {
            return new JSONArray((List<Object>) object);
        } else {
            JSONArray json = toJA(object);
            if (json == null) {
                return JsonUtils.JA();
            } else {
                return json;
            }
        }
    }

    /**
     * 克隆 JSONObject 对象，确保新对象与原对象不是引用关系。
     *
     * @param jo
     *            - JSONObject
     * @return JSONObject
     */
    public static JSONObject cloneJO(JSONObject jo) {
        if (jo != null) {
            return toJO(toJson(jo));
        } else {
            return null;
        }
    }

    /**
     * 克隆 JSONArray 对象，确保新对象与原对象不是引用关系。
     *
     * @param ja
     *            - JSONArray
     * @return JSONArray
     */
    public static JSONArray cloneJA(JSONArray ja) {
        if (ja != null) {
            return toJA(toJson(ja));
        } else {
            return null;
        }
    }

    /**
     * 修改  JSONObject 对象 json 中的某个指定 Key 名称，保持其值不变，如果 src Key 不存在
     * 或 dest Key 已经存在，则不做任何修改直接返回原 json
     * @param jo - 待修改的 JSONObject 对象
     * @param src - 修改前的源 Key 名称
     * @param dest - 修改后的目标 Key 名称
     * @return
     */
    public static void renameKey(JSONObject jo, String src, String dest) {
        if (jo == null) {
            return;
        }
        if (!jo.containsKey(src)) {
            return;
        }
        if (jo.containsKey(dest)) {
            return;
        }
        Object o = jo.get(src);
        jo.remove(src);
        jo.put(dest, o);
    }

    public static void renameKey(Map<String, Object> map, String src, String dest) {
        if (map == null) {
            return;
        }
        if (!map.containsKey(src)) {
            return;
        }
        if (map.containsKey(dest)) {
            return;
        }
        Object o = map.get(src);
        map.remove(src);
        map.put(dest, o);
    }

    /**
     * 判断 JSONObject 对象中 Key 是否存在，不存在则赋值为 value。此方法多用于赋默认值。
     * @param jo - 待判断的 JSONObject 对象
     * @param key - Key 名称
     * @param value - Key 对应的默认值，仅当该 Key 不存在时使用，否则不会覆盖原值。
     */
    public static void setDefaultValue(JSONObject jo, String key, Object value) {
        if (jo == null) {
            jo = JO();
            jo.put(key, value);
        } else {
            if (!jo.containsKey(key)) {
                jo.put(key, value);
            }
        }
    }
}
