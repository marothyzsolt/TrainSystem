package com.trainsystem.db;

import com.jayway.jsonpath.Criteria;

public class Where {
    private String key;
    private String value;

    private String operator;

    public Where(String key, String operator, String value) {
        this.key = key;
        this.value = value;
        this.operator = operator;
    }

    public Criteria getCriteria()
    {
        return Criteria.where(key).is(value);
    }
}
