package org.g_node.sparql.dsl;


import com.hp.hpl.jena.rdf.model.Model;

public interface FromHandler<T extends Result> {

    GraphHandler<T> from(String uri);

    GraphHandler<T> from(Model model);

}
