package org.g_node.sparql.impl;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.rdf.model.Model;
import org.g_node.sparql.dsl.*;


public class SelectGraphHandlerFrom extends SelectGraphHandler implements GraphHandlerFrom<SelectResult> {

    public SelectGraphHandlerFrom(Query query, Model model) {
        super(query, model);
    }

    public SelectGraphHandlerFrom(BasicHandler handler) {
        super(handler);
    }

    @Override
    public GraphHandler<SelectResult> from(String uri) {
        getQuery().addGraphURI(uri);

        return new SelectGraphHandler(this);
    }

    @Override
    public GraphHandler<SelectResult> from(Model model) {
        return new SelectGraphHandler(getQuery(), model);
    }

}
