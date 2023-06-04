package com.veezean.codereview.server.utils;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * GSON工具类
 *
 * @author guiyucai
 */
public class GsonUtils {
    /**
     * 线程安全
     */
    private static final Gson GSON;
    /**
     * null的KEY不会剔除
     */
    private static final Gson GSON_NULL;

    static {
        //当Map的key为复杂对象时,需要开启该方法
        GSON = new GsonBuilder().enableComplexMapKeySerialization()
//                .serializeNulls() //当字段值为空或null时，依然对该字段进行转换
//                .excludeFieldsWithoutExposeAnnotation()//打开Export注解，但打开了这个注解,副作用，要转换和不转换都要加注解
                //序列化日期格式  "yyyy-MM-dd"
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
//                .setPrettyPrinting() //自动格式化换行
                .disableHtmlEscaping() //防止特殊字符出现乱码
                .create();
        //当Map的key为复杂对象时,需要开启该方法
        GSON_NULL = new GsonBuilder().enableComplexMapKeySerialization()
                .serializeNulls() //当字段值为空或null时，依然对该字段进行转换
//                .excludeFieldsWithoutExposeAnnotation()//打开Export注解，但打开了这个注解,副作用，要转换和不转换都要加注解
                //序列化日期格式  "yyyy-MM-dd"
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
//                .setPrettyPrinting() //自动格式化换行
                .disableHtmlEscaping() //防止特殊字符出现乱码
                .create();
    }

    public static Gson getGson() {
        return GSON;
    }

    /**
     * 有空值 解析
     */
    public static Gson getWriteNullGson() {
        return GSON_NULL;
    }

    /**
     * 将对象转成json格式
     */
    public static String gsonString(Object object) {
        String gsonString = null;
        if (GSON != null) {
            gsonString = GSON.toJson(object);
        }
        return gsonString;
    }

    /**
     * 将json转成特定的cls的对象
     */
    public static <T> T gsonToBean(String gsonString, Class<T> cls) {
        T t = null;
        if (GSON != null) {
            //传入json对象和对象类型,将json转成对象
            t = GSON.fromJson(gsonString, cls);
        }
        return t;
    }

    /**
     * 将jsonString转成JsonObject
     */
    public static JsonObject stringToJsonObject(String gsonString) {
        return JsonParser.parseString(gsonString).getAsJsonObject();
    }

    /**
     * json字符串转成list
     */
    public static <T> List<T> gsonToList(String gsonString) {
        List<T> list = null;
        if (GSON != null) {
            //根据泛型返回解析指定的类型,TypeToken<List<T>>{}.getType()获取返回类型
            list = GSON.fromJson(gsonString, new TypeToken<List<T>>() {
            }.getType());
        }
        return list;
    }

    /**
     * json字符串转成list
     */
    public static <T> List<T> jsonToList(String json, Class<T> cls) {
        ArrayList<T> mList = new ArrayList<>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for (final JsonElement elem : array) {
            mList.add(GSON.fromJson(elem, cls));
        }
        return mList;
    }

    /**
     * json字符串转成list中有map的
     */
    public static <T> List<Map<String, T>> gsonToListMaps(String gsonString) {
        List<Map<String, T>> list = null;
        if (GSON != null) {
            list = GSON.fromJson(gsonString,
                    new TypeToken<List<Map<String, T>>>() {
                    }.getType());
        }
        return list;
    }

    /**
     * json字符串转成map的
     */
    public static <T> Map<String, T> gsonToMaps(String gsonString) {
        Map<String, T> map = null;
        if (GSON != null) {
            map = GSON.fromJson(gsonString, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }


    /**
     * json对象转成map的
     */
    public static Map<String, String> gsonToMaps(JsonObject jsonObject) {
        return GSON.fromJson(jsonObject, new TypeToken<Map<String, String>>() {
        }.getType());
    }

    /**
     * 字符串格式化输出
     *
     * @param request
     * @return
     */
    public static String prettyJsonString(String request) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement parse = JsonParser.parseString(request);
        return gson.toJson(parse);
    }


}