package com.hhu.ts.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;


import java.io.IOException;
import java.util.*;

@Slf4j
public class JsonUtil<T> {


    private static final ObjectMapper MAPPER = new ObjectMapper();


    public static Map<String,Object> getMapFromJson(String jsonStr){

        if(jsonStr == null){
            return null;
        }

        try {
          return   MAPPER.readValue(jsonStr,Map.class);
        }catch (Exception e){
            log.error("jsonStr 转化异常");
        }
       return null;
    }

    public static String getJsonStr(Object obj){

        if(obj ==null){
            return null;
        }

        try {
            return   MAPPER.writeValueAsString(obj);
        }catch (Exception e){
            log.error("jsonStr 转化异常");
        }
        return null;
    }

    public static List<Map<String,Object>> getListFromJson(String jsonStr)  {

        if(jsonStr == null){
            return null;
        }

        List<Map<String,Object>> list = new ArrayList<>();
        String[] split = jsonStr.split("\\n");
        try {
            for(String str: split){
                Map<String,Object> map = MAPPER.readValue(str, Map.class);
                list.add(map);
            }
            return list;
        }catch (Exception e){
            log.error("jsonStr 转化异常");
        }
        return null;
    }

    public static Map<String,Map<String,Object>> getMapListFromJson(String jsonStr,String key)  {

        if(jsonStr == null){
            return null;
        }

        Map<String,Map<String,Object>> mapList = new HashMap<>();
        String[] split = jsonStr.split("\\n");
        try {
            for(String str: split){
                Map<String,Object> map = MAPPER.readValue(str, Map.class);
                String k = String.valueOf( map.get(key));
                mapList.put(k,map);

            }
            return mapList;
        }catch (Exception e){
            log.error("jsonStr 转化异常");
        }
        return null;
    }

    public  List<T> getListFromJson(String jsonStr,Class<T> t) {

        if(jsonStr == null){
            return null;
        }

        List<T> list = new ArrayList<>();
        String[] split = jsonStr.split("\\n");
        try {
            for(String str: split){
                T t1 = MAPPER.readValue(str, t);
                list.add(t1);
            }
            return list;
        }catch (Exception e){
            log.error("jsonStr 转化异常");
        }
        return null;
    }

    public static Map<String,Object> getListMapFromJson(String jsonStr,String k1) {

        if(jsonStr == null){
            return null;
        }

        Map<String,Object> map = new HashMap<>();
        String[] split = jsonStr.split("\\n");
        try {
            for(String str: split){
                Map<String,Object> t1 = MAPPER.readValue(str, Map.class);
                map.put((String) t1.get(k1),t1);
            }
            return map;
        }catch (Exception e){
            log.error("jsonStr 转化异常");
        }
        return null;
    }


    public static Set<String> getSetFromJsonStr(String jsonStr,String key){
        if(jsonStr == null){
            return null;
        }

        Set<String> set = new HashSet<>();
        String[] split = jsonStr.split("\\n");
        try {
            for(String str: split){
                Map<String,Object> map = MAPPER.readValue(str, Map.class);
                set.add(String.valueOf( map.get(key)));
            }
            return set;
        }catch (Exception e){
            log.error("jsonStr 转化异常");
        }
        return null;

    }

    public static <T> T toObject(String json, Class<T> clazz) {
        try {
            // 将转译符和前后引号去除
            return MAPPER.readValue(json.replaceAll("^\"|\\\\|\"$", ""), clazz);
        } catch (IOException e) {
        }
        return null;
    }
}
