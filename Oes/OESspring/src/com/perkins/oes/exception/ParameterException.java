package com.perkins.oes.exception;

import java.util.HashMap;
import java.util.Map;

public class ParameterException extends Exception {

    private static final long serialVersionUID = 3144210690894667014L;

    Map<String, String> errorfields = new HashMap<String, String>();

    public Map<String, String> getErrorfields() {
        return errorfields;
    }

    public void setErrorfields(Map<String, String> errorfields) {
        this.errorfields = errorfields;
    }

    public void addErrorfields(String name, String message) {
        errorfields.put(name, message);
    }

    public boolean isErrorField() {
        return !errorfields.isEmpty();
    }

}
