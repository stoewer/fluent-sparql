package org.g_node.sparql.impl;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import static java.util.Spliterator.*;


import java.util.stream.Stream;

import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.Model;
import org.g_node.sparql.dsl.Result;

public class SelectResult implements Result {

    private final Query query;
    private final Model model;

    public SelectResult(Query query, Model model) {
        this.query = query;
        this.model = model;
    }

    public Query getQuery() {
        return query;
    }

    public Model getModel() {
        return model;
    }

    public ResultSet result() {
        try (QueryExecution exec = createExec()) {
            ResultSet internal = exec.execSelect();
            return ResultSetFactory.copyResults(internal);
        }
    }

    public Stream<QuerySolution> stream() {
        int characteristics = DISTINCT | NONNULL;
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(result(), characteristics), false);
    }

    public <T> Stream<T> map(Function<QuerySolution, T> function) {
        return null;
    }

    public <T> Stream<T> map(Class<T> bean) {
        return null;
    }

    private QueryExecution createExec() {
        if (model == null)
            return QueryExecutionFactory.create(query);
        else
            return QueryExecutionFactory.create(query, model);
    }

}
