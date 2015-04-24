package org.g_node.sparql.impl;

import java.util.function.Function;
import java.util.stream.Stream;

import com.hp.hpl.jena.query.*;
import org.g_node.sparql.dsl.Result;

public class SelectResult implements Result {

    public ResultSet resultSet() {
        return null;
    }

    public Stream<QuerySolution> stream() {
        return null;
    }

    public <T> Stream<T> map(Function<QuerySolution, T> function) {
        return null;
    }

    public <T> Stream<T> map(Class<T> bean) {
        return null;
    }

}
