package com.perkins.oes.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {

    private static Properties p = null;
    static {
        InputStream in = null;
        try {
            in = PropertyUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
            p = new Properties();
            p.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getProperties(String key) {
        return p.getProperty(key);
    }
}
