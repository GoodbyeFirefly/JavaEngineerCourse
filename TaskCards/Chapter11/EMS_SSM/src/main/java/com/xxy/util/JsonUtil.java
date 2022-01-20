package com.xxy.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {
    private static Gson g = new GsonBuilder().disableHtmlEscaping().create();
    public static String toJson (Object o) {
        return g.toJson(o);
    }
}
