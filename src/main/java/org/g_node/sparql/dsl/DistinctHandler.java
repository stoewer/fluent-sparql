package org.g_node.sparql.dsl;


public interface DistinctHandler<T extends Result> {

    GraphHandlerFrom<T> distinct();

}
