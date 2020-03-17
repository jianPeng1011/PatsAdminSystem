package com.example.pethospitalmanagement.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormTools {
    /**
     * 获取系统时间
     * @return
     */
    public static String getSysTime(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        String date = format.format(calendar.getTime());
        return date;
    }
    /**
     * 获取系统日期
     * @return
     */
    public static String getSysDate(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();

        String date = format.format(calendar.getTime());
        //calendar.getTime().compareTo(calendar.getTime());
        return date;
    }
    /**
     * 检查是否为空
     * @param obj
     * @return true|false
     */
    public static boolean isNull(Object obj){
        if(obj == null || obj.equals(null) || obj.toString().equals("")){
            return true;
        }else{
            return false;
        }
    }
    /**
     * 检查是否为中文
     * @return true|false
     */
    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    /**
     * @检查是否为正小数
     * @param obj
     * @return
     */
    public static boolean isPositiveDecimal(Object obj){
        if(isNull(obj)){
            return false;
        }else{
            Pattern pattern = Pattern.compile("\\+{0,1}[0]\\.[1-9]*|\\+{0,1}[1-9]\\d*\\.\\d*");
            Matcher isNum = pattern.matcher(obj.toString());
            if( !isNum.matches() ){
                return false;
            } else {
                return true;
            }
        }
    }

    /**
     * 检查是否为数字
     * @param obj
     * @return true|false
     */
    public static boolean isNumber(Object obj){
        if(isNull(obj)){
            return false;
        }else{
            Pattern pattern = Pattern.compile("[0-9]*");
            Matcher isNum = pattern.matcher(obj.toString());
            if( !isNum.matches() ){
                return false;
            } else {
                return true;
            }
        }
    }

    /**
     * @todo 判断为小数或者整数
     * @param obj
     * @return
     */
    public static boolean isWholeNumber(Object obj){
        return isNumber(obj) || isPositiveDecimal(obj);
    }

    /**
     * 检查为空 返回空字符串
     * @return true|false
     */
    public static String checkNull(String param){
        return isNull(param)? "": param;
    }

    /**
     * 检查是否为空
     * @return
     */
    public static void isNullException(Object obj,String str) throws Exception{
        if(obj == null || obj.toString().equals("")){
            throw new RuntimeException(str);
        }
    }
    /**
     * 检查是否为空
     * @return
     */
    public static void isJsonNullException(Object obj,String str) throws Exception{
        JSONObject jo = JsonUtils.toJO(obj);
        if(jo.get("key") == null || jo.getString("key").equals("")){
            throw new RuntimeException(str);
        }
    }
    /**
     * 检查是否为空
     * @return
     */
    public static void isArrayNullException(Object obj,String str) throws Exception{
        if(obj == null || "".equals(obj)){
            throw new Exception(str);
        }
        JSONArray ja = JsonUtils.toJA(obj);
        if(ja.size() == 0){
            throw new RuntimeException(str);
        }
    }

    @SuppressWarnings("rawtypes")
    public static boolean isArrayNull(Object obj) throws Exception{
        if(obj == null || "".equals(obj)){
            return true;
        }
        List list = (List) obj;
        if(list.size() == 0){
            return true;
        }
        return false;
    }

    /**
     * 检查数组是否为空
     * @return
     */
    public static void isArrayKeyNullException(Object obj,String key,String str) throws Exception{
        JSONArray ja = JsonUtils.toJA(obj);
        for(int i=0; i<ja.size(); i++){
            JSONObject jo = ja.getJSONObject(i);
            isNullException(jo.get(key), str);
        }
    }
    /**
     * 替换null为""
     * @return
     */
    @SuppressWarnings({ "rawtypes" })
    public static Map replaceJsonNull(Map map) throws Exception {
        String json = JsonUtils.toJson(map);
        if (json != null) {
            json.replace(":null", ":\"\"");
        }
        return JsonUtils.toJO(json);
    }

    /**
     * 替换null为""
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Map replaceMapNull(Map map) throws Exception{
        for(Iterator it = map.keySet().iterator(); it.hasNext();){
            String key = it.next().toString();
            if(isNull(map.get(key))){
                map.put(key, "");
            }
        }
        return map;
    }

    /**
     * 替换null为""
     * @return
     */
    @SuppressWarnings({ "rawtypes" })
    public static List replaceListNull(List<Map> list) throws Exception{
        if (list != null) {
            for(int i=0;i<list.size();i++){
                list.set(i, replaceMapNull(list.get(i)));
            }
        }
        return list;
    }

    /**
     * 将String读取成Map
     * @return
     */
    @SuppressWarnings({ "rawtypes" })
    public static Map mapperToMap(Object object) throws Exception{
        if (!isNull(object)) {
            return (Map) JSON.parse(object.toString());
        }
        return null;
    }

    /**
     * 将String读取成Map
     * @return
     */
    @SuppressWarnings({ "rawtypes" })
    public static Map toMap(Object object) throws Exception{
        if (!isNull(object)) {
            return (Map)JSON.parse(object.toString());
        }
        return new HashMap();
    }

    /**
     * 将String读取成Map
     * @return
     */
    @SuppressWarnings({ "rawtypes" })
    public static List mapperToList(Object object) throws Exception{
        if (!isNull(object)) {
            return JsonUtils.toList(object.toString());
        }
        return null;
    }

    /**
     * 将String读取成ListMap
     * @return
     */
    @SuppressWarnings({ "rawtypes" })
    public static List<Map> mapperToListMap(Object object) throws Exception{
        if(!isNull(object)){
            return JsonUtils.toListMap(object.toString());
        }
        return null;
    }

    /**
     * 将String读取成Map
     * @return
     */
    @SuppressWarnings({ "rawtypes" })
    public static Map mapperToJSONObject(String XmlData) throws Exception{
        return JsonUtils.toJO(XmlData);
    }

}
