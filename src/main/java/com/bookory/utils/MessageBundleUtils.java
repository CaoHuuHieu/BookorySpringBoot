package com.bookory.utils;

import java.util.ResourceBundle;

public class MessageBundleUtils {
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

    public static String getMessage(String key){
        return resourceBundle.getString(key);
    }

    public static String getMessage(String key, Object... params){
        return resourceBundle.getString(key).formatted(params);
    }

}
