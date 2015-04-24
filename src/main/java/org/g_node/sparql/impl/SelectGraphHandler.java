package org.g_node.sparql.impl;

import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.rdf.model.Model;
import org.g_node.sparql.dsl.*;

public class SelectGraphHandler extends BasicHandler implements GraphHandler<SelectResult> {

    public SelectGraphHandler(Query query, Model model) {
        super(query, model);
    }

    public SelectGraphHandler(BasicHandler handler) {
        super(handler);
    }

    @Override
    public GraphHandlerEnd<SelectResult> add(Object subject, Object predicate, Node object) {
        getCurrent().addTriplePattern(mkTriple(subject, predicate, object));

        return new SelectGraphHandlerEnd(this);
    }

    @Override
    public GraphHandlerEnd<SelectResult> add(Object subject, Object predicate, String object) {
        getCurrent().addTriplePattern(mkTriple(subject, predicate, object));

        return new SelectGraphHandlerEnd(this);
    }

    @Override
    public FilterHandler<SelectResult> filter(String value) {
        return new SelectFilterHandler(this, mkNode(value));
    }

}
