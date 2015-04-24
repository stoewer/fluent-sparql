package org.g_node.sparql;

import java.util.Map.Entry;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.shared.PrefixMapping;
import com.hp.hpl.jena.shared.impl.PrefixMappingImpl;
import com.hp.hpl.jena.sparql.core.Var;
import com.hp.hpl.jena.sparql.syntax.ElementGroup;
import org.g_node.sparql.dsl.*;
import org.g_node.sparql.impl.*;


public class FluentSparql implements PrologueHandler {

    public static final FluentSparql sparql = new FluentSparql(true);

    private PrefixMapping prefixMapping;

    public FluentSparql() {
        this(false);
    }

    public FluentSparql(boolean useStandardPrefixes) {
        this(new PrefixMappingImpl(), useStandardPrefixes);
    }

    public FluentSparql(PrefixMapping prefixMapping, boolean useStandardPrefixes) {
        this.prefixMapping = prefixMapping;
        if (useStandardPrefixes) 
            addStandardPrefixes();
    }

    public void addStandardPrefixes() {
        for (Entry<String, String> entry: PrefixMapping.Standard.getNsPrefixMap().entrySet())
            this.prefixMapping.setNsPrefix(entry.getKey(), entry.getValue());
    }

    @Override
    public PrologueHandler prefix(String name, String uri) {
        Query query = new Query();

        query.setPrefixMapping(prefixMapping);
        query.setPrefix(name, uri);

        ElementGroup element = new ElementGroup();
        query.setQueryPattern(element);

        return new PrologueHandlerImpl(query);
    }

    @Override
    public PrologueHandler prefix(String uri) {
        Query query = new Query();

        query.setPrefixMapping(prefixMapping);
        query.setPrefix(":", uri);

        ElementGroup element = new ElementGroup();
        query.setQueryPattern(element);

        return new PrologueHandlerImpl(query);
    }

    @Override
    public GraphHandlerBegin<SelectResult> select(String... variables) {
        Query query = new Query();

        query.setQuerySelectType();
        query.setPrefixMapping(prefixMapping);

        for (String var : variables)
            query.addResultVar(var);

        ElementGroup element = new ElementGroup();
        query.setQueryPattern(element);
        return new SelectGraphHandlerBegin(query, null);
    }

    @Override
    public GraphHandlerBegin<SelectResult> select(Var... variables) {
        Query query = new Query();

        query.setQuerySelectType();
        query.setPrefixMapping(prefixMapping);

        for (Var var : variables)
            query.addResultVar(var);

        ElementGroup element = new ElementGroup();
        query.setQueryPattern(element);
        return new SelectGraphHandlerBegin(query, null);
    }

    @Override
    public GraphHandler<AskResult> ask() {
        // TODO implement ask

        return null;
    }

}
