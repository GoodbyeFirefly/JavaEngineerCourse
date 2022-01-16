package com.xxy.util;

import com.google.gson.Gson;

public class JsonUtil {
    private static Gson g = new Gson();
    public static String toJson (Object o) {
        return g.toJson(o);
    }
}
