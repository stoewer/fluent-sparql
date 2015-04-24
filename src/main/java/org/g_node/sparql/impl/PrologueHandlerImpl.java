package org.g_node.sparql.impl;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.sparql.core.Var;
import org.g_node.sparql.dsl.*;


public class PrologueHandlerImpl implements PrologueHandler {

    private final Query query;


    public PrologueHandlerImpl(Query query) {
        this.query = query;
    }

    @Override
    public PrologueHandler prefix(String prefix, String uri) {
        query.setPrefix(prefix, uri);

        return this;
    }

    @Override
    public PrologueHandler prefix(String uri) {
        query.setPrefix(":", uri);

        return this;
    }

    @Override
    public GraphHandlerBegin<SelectResult> select(String... variables) {
        query.setQuerySelectType();

        for (String var : variables)
            query.addResultVar(var);

        return new SelectGraphHandlerBegin(query, null);
    }

    @Override
    public GraphHandlerBegin<SelectResult> select(Var... variables) {
        query.setQuerySelectType();

        for (Var var : variables)
            query.addResultVar(var);

        return new SelectGraphHandlerBegin(query, null);
    }

    @Override
    public GraphHandler<AskResult> ask() {
        // TODO implement ask
        return null;
    }
}
