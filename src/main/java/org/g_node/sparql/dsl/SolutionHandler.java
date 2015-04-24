package org.g_node.sparql.dsl;

import com.hp.hpl.jena.query.Query;

public interface SolutionHandler<T extends Result> {

    SolutionHandler<T> orderByAsc(String ...vars);

    SolutionHandler<T> orderByDesc(String ...vars);

    SolutionHandler<T> offset(long offset);

    SolutionHandler<T> limit(long limit);

    Query query();

    T execute();

}
