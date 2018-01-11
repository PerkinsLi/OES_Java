package com.perkins.oes.util;

public class StringUtil {

    public static Boolean IsEmpty(String data) {
        if (data == null || data.equals("")) {
            return true;
        }

        return false;
    }

}
