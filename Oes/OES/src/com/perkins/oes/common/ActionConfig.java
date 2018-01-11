package com.perkins.oes.common;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ActionConfig {
    private String name;
    private String className;
    private String methodName;
    private String[] httpMethod;

    private Map<String, ResultConfig> results = new HashMap<String, ResultConfig>();

    public void addResult(String key, ResultConfig resultConfig) {
        results.put(key, resultConfig);
    }

    public Map<String, ResultConfig> getResults() {
        return results;
    }

    public ResultConfig getResult(String resultKey) {
        return results.get(resultKey);
    }

    public void setResults(Map<String, ResultConfig> results) {
        if (results == null) {
            results = new HashMap<String, ResultConfig>();
        }
        this.results = results;
    }

    public ActionConfig() {
        super();
    }

    public ActionConfig(String className, String methodName) {
        super();
        this.className = className;
        this.methodName = methodName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String[] getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String[] httpMethod) {
        this.httpMethod = httpMethod;
    }

    @Override
    public String toString() {
        return "ActionConfig [name=" + name + ", className=" + className + ", methodName=" + methodName
                + ", httpMethod=" + Arrays.toString(httpMethod) + ", results=" + results + "]";
    }

}
