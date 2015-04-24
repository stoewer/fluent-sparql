package org.g_node.sparql.dsl;

import com.hp.hpl.jena.datatypes.RDFDatatype;
import com.hp.hpl.jena.graph.*;
import com.hp.hpl.jena.sparql.util.NodeFactoryExtra;

public interface GraphHandler<T extends Result> {

    GraphHandlerEnd<T> add(Object subject, Object predicate, Node object);

    GraphHandlerEnd<T> add(Object subject, Object predicate, String object);

    default GraphHandlerEnd<T> add(Object subject, Object predicate, FrontsNode object) {
        return add(subject, predicate, object.asNode());
    }

    default GraphHandlerEnd<T> add(Object subject, Object predicate, String object, RDFDatatype type) {
        return add(subject, predicate, NodeFactory.createLiteral(object, type));
    }

    default GraphHandlerEnd<T> add(Object subject, Object predicate, long object) {
        return add(subject, predicate, NodeFactoryExtra.intToNode(object));
    }

    default GraphHandlerEnd<T> add(Object subject, Object predicate, long object, RDFDatatype type) {
        return add(subject, predicate, NodeFactory.createLiteral(Long.toString(object), type));
    }

    default GraphHandlerEnd<T> add(Object subject, Object predicate, float object) {
        return add(subject, predicate, NodeFactoryExtra.floatToNode(object));
    }

    default GraphHandlerEnd<T> add(Object subject, Object predicate, float object, RDFDatatype type) {
        return add(subject, predicate, NodeFactory.createLiteral(Float.toString(object), type));
    }

    default GraphHandlerEnd<T> add(Object subject, Object predicate, double object) {
        return add(subject, predicate, NodeFactoryExtra.doubleToNode(object));
    }

    default GraphHandlerEnd<T> add(Object subject, Object predicate, double object, RDFDatatype type) {
        return add(subject, predicate, NodeFactory.createLiteral(Double.toString(object), type));
    }

    FilterHandler<T> filter(String value);

}
