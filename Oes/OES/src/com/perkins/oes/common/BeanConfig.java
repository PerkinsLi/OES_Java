package com.perkins.oes.common;

import java.util.ArrayList;
import java.util.List;

public class BeanConfig {
    private String id;
    private String className;
    private String scope;

    private List<BeanProperty> properties = new ArrayList<BeanProperty>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public List<BeanProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<BeanProperty> properties) {
        this.properties = properties;
    }

    public void addProperty(BeanProperty property) {
        properties.add(property);
    }

}
