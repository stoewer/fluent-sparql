package org.g_node.sparql.dsl;


public interface GraphHandlerEnd<T extends Result> extends GraphHandler<T>, SolutionHandler<T> {

    GraphHandler<T> union();

}
