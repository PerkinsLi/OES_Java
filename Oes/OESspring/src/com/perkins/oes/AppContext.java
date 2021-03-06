package com.perkins.oes;

import java.util.HashMap;
import java.util.Map;

import com.perkins.oes.entity.User;

public class AppContext {
    private static ThreadLocal<AppContext> appContextMap = new ThreadLocal<AppContext>();

    private Map<String, Object> objects = new HashMap<String, Object>();
    private static String contextPath;

    public static String getContextPath() {
        return contextPath;
    }

    public static void setContextPath(String path) {
        if (contextPath == null) {
            AppContext.contextPath = path;
        }
    }

    private AppContext() {
    };

    public Map<String, Object> getObjects() {
        return objects;
    }

    public void setObjects(Map<String, Object> objects) {
        this.objects = objects;
    }

    public void addObjects(String key, Object object) {
        this.objects.put(key, object);
    }

    public Object getObject(String key) {
        return objects.get(key);
    }

    public void clear() {
        AppContext context = appContextMap.get();
        if (context != null) {
            context.objects.clear();
        }
        context = null;
    }

    public void removeObject(String key) {
        objects.remove(key);

    }

    public static AppContext getAppContext() {
        AppContext appContext = appContextMap.get();
        if (appContext == null) {
            appContext = new AppContext();
            appContextMap.set(appContext);
        }
        return appContextMap.get();

    }

    public User getUser() {
        return (User) AppContext.getAppContext().getObject(Constants.APP_CONTEXT_USER);
    }

}
