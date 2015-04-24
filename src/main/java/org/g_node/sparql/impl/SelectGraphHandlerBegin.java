package org.g_node.sparql.impl;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.rdf.model.Model;
import org.g_node.sparql.dsl.*;


public class SelectGraphHandlerBegin extends SelectGraphHandlerFrom implements GraphHandlerBegin<SelectResult> {

    public SelectGraphHandlerBegin(Query query, Model model) {
        super(query, model);
    }

    public SelectGraphHandlerBegin(BasicHandler handler) {
        super(handler);
    }

    @Override
    public GraphHandlerFrom<SelectResult> distinct() {
        getQuery().setDistinct(true);

        return new SelectGraphHandlerFrom(this);
    }

}
