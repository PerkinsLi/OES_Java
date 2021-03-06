package com.perkins.oes.util;

import java.lang.reflect.Method;

import com.perkins.oes.AppContext;
import com.perkins.oes.Constants;

public class SessionUtil {

    private static Object getSessionInThread() {
        Object session = AppContext.getAppContext().getObject(Constants.APP_CONTEXT_SESSION);
        return session;
    }

    public static void addSession(String key, Object object) {
        Object session = getSessionInThread();
        if (session == null) {
            return;
        }
        try {
            Class<?>[] param = new Class[2];
            param[0] = String.class;
            param[1] = Object.class;

            Method method = session.getClass().getMethod("setAttribute", param);

            Object[] objects = new Object[2];
            objects[0] = key;
            objects[1] = object;

            method.invoke(session, objects);
        } catch (Exception ex) {
            new RuntimeException(ex);
        }
    }

    public static Object getSession(String key) {
        Object session = getSessionInThread();
        if (session == null) {
            return null;
        }
        try {
            Class<?> param = String.class;
            Method method = session.getClass().getMethod("getAttribute", param);

            String str = key;
            Object returnValue = method.invoke(session, str);

            return returnValue;
        } catch (Exception ex) {
            new RuntimeException(ex);
        }
        return null;
    }

    public static void removeSession(String key) {
        Object session = getSessionInThread();
        if (session == null) {
            return;
        }
        try {
            Class<?> param = String.class;
            Method method = session.getClass().getMethod("removeAttribute", param);

            String str = key;
            method.invoke(session, str);
        } catch (Exception ex) {
            new RuntimeException(ex);
        }
    }

    public static void invalidate() {
        Object session = getSessionInThread();
        if (session == null) {
            return;
        }
        try {
            Method method = session.getClass().getMethod("invalidate");
            method.invoke(session);
        } catch (Exception ex) {
            new RuntimeException(ex);
        }
    }
}
