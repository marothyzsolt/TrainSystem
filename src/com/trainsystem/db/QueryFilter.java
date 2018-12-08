package com.trainsystem.db;

import com.jayway.jsonpath.Filter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QueryFilter extends Filter {

    List<Where> whereList = new ArrayList<>();

    public QueryFilter(List<Where> whereList) {
        this.whereList = whereList;
    }

    @Override
    public boolean apply(PredicateContext predicateContext) {

        return false;
    }
}
