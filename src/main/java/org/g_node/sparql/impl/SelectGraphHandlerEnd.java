package org.g_node.sparql.impl;

import java.util.Optional;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.sparql.syntax.*;
import org.g_node.sparql.dsl.*;


public final class SelectGraphHandlerEnd extends SelectGraphHandler implements GraphHandlerEnd<SelectResult> {

    public SelectGraphHandlerEnd(Query query, Model model) {
        super(query, model);
    }

    public SelectGraphHandlerEnd(BasicHandler handler) {
        super(handler);
    }

    @Override
    public GraphHandler<SelectResult> union() {
        Query query = getQuery();

        if (query.getQueryPattern() == getCurrent()) {
            ElementUnion union = new ElementUnion();
            union.addElement(getCurrent());

            ElementGroup newCurrent = new ElementGroup();
            union.addElement(newCurrent);

            query.setQueryPattern(union);

            return new SelectGraphHandler(this);
        } else {
            Optional<ElementUnion> union = findUnion(query.getQueryPattern());

            if (union.isPresent()) {
                union.get().addElement(new ElementGroup());
            } else {
                throw new RuntimeException("Unable to find union syntax element");
            }

            return new SelectGraphHandler(this);
        }
    }

    @Override
    public SolutionHandler<SelectResult> orderByAsc(String... vars) {
        Query query = getQuery();

        for (String var : vars)
            query.addOrderBy(var, Query.ORDER_ASCENDING);

        return new SelectGraphHandlerEnd(this);
    }

    @Override
    public SolutionHandler<SelectResult> orderByDesc(String... vars) {
        Query query = getQuery();

        for (String var : vars)
            query.addOrderBy(var, Query.ORDER_DESCENDING);

        return new SelectGraphHandlerEnd(this);
    }

    @Override
    public SolutionHandler<SelectResult> offset(long offset) {
        getQuery().setOffset(offset);

        return new SelectGraphHandlerEnd(this);
    }

    @Override
    public SolutionHandler<SelectResult> limit(long limit) {
        getQuery().setLimit(limit);

        return new SelectGraphHandlerEnd(this);
    }

    @Override
    public Query query() {
        return getQuery().cloneQuery();
    }

    @Override
    public SelectResult execute() {
        return new SelectResult(getQuery(), getModel());
    }
}
