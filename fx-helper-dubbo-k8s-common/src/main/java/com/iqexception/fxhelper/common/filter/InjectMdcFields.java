package com.iqexception.fxhelper.common.filter;

import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class InjectMdcFields {
    /**
     *  injectFields Pair <fieldName, mdcKey>
     */
    private List<Pair<String, String>>  injectFields;

    public InjectMdcFields() {
    }

    public InjectMdcFields(List<Pair<String, String>> injectFields) {
        this.injectFields = injectFields;
    }

    public List<Pair<String, String>> getInjectFields() {
        return injectFields;
    }

    public void setInjectFields(List<Pair<String, String>> injectFields) {
        this.injectFields = injectFields;
    }
}
