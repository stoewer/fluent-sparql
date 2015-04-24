package org.g_node.sparql.impl;

import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.sparql.expr.*;
import com.hp.hpl.jena.sparql.syntax.ElementFilter;
import org.g_node.sparql.dsl.*;

public class SelectFilterHandler extends BasicHandler implements FilterHandler<SelectResult> {

    private Node otherValue;

    public SelectFilterHandler(Query query, Model model, Node otherValue) {
        super(query, model);
        this.otherValue = otherValue;
    }

    public SelectFilterHandler(BasicHandler handler, Node otherValue) {
        super(handler);
        this.otherValue = otherValue;
    }

    @Override
    public GraphHandlerEnd<SelectResult> greater(Node value) {
        getCurrent().addElement(new ElementFilter(new E_GreaterThan(
                NodeValue.makeNode(otherValue),
                NodeValue.makeNode(value)
        )));

        return new SelectGraphHandlerEnd(this);
    }

    @Override
    public GraphHandlerEnd<SelectResult> greater(String value) {
        return greater(mkNode(value));
    }

    @Override
    public GraphHandlerEnd<SelectResult> greaterEqual(Node value) {
        getCurrent().addElement(new ElementFilter(new E_GreaterThanOrEqual(
                NodeValue.makeNode(otherValue),
                NodeValue.makeNode(value)
        )));

        return new SelectGraphHandlerEnd(this);
    }

    @Override
    public GraphHandlerEnd<SelectResult> greaterEqual(String value) {
        return greaterEqual(mkNode(value));
    }

    @Override
    public GraphHandlerEnd<SelectResult> lesser(Node value) {
        getCurrent().addElement(new ElementFilter(new E_LessThan(
                NodeValue.makeNode(otherValue),
                NodeValue.makeNode(value)
        )));

        return new SelectGraphHandlerEnd(this);
    }

    @Override
    public GraphHandlerEnd<SelectResult> lesser(String value) {
        return lesser(mkNode(value));
    }

    @Override
    public GraphHandlerEnd<SelectResult> lesserEqual(Node value) {
        getCurrent().addElement(new ElementFilter(new E_LessThanOrEqual(
                NodeValue.makeNode(otherValue),
                NodeValue.makeNode(value)
        )));

        return new SelectGraphHandlerEnd(this);
    }

    @Override
    public GraphHandlerEnd<SelectResult> lesserEqual(String value) {
        return lesserEqual(mkNode(value));
    }

    @Override
    public GraphHandlerEnd<SelectResult> regexp(String regexp) {
        getCurrent().addElement(new ElementFilter(new E_Regex(
                NodeValue.makeNode(otherValue),
                regexp,
                ""
        )));

        return new SelectGraphHandlerEnd(this);
    }
}
