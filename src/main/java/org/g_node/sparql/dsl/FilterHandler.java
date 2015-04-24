package org.g_node.sparql.dsl;

import com.hp.hpl.jena.datatypes.RDFDatatype;
import com.hp.hpl.jena.graph.*;
import com.hp.hpl.jena.sparql.util.NodeFactoryExtra;

public interface FilterHandler<T extends Result> {

    GraphHandlerEnd<T> greater(Node value);

    GraphHandlerEnd<T> greater(String value);

    default GraphHandlerEnd<T> greater(long value) {
        return greater(NodeFactoryExtra.intToNode(value));
    }

    default GraphHandlerEnd<T> greater(float value) {
        return greater(NodeFactoryExtra.floatToNode(value));
    }

    default GraphHandlerEnd<T> greater(double value) {
        return greater(NodeFactoryExtra.doubleToNode(value));
    }

    default GraphHandlerEnd<T> greater(Number value, RDFDatatype type) {
        return greater(NodeFactory.createLiteral(value.toString(), type));
    }


    GraphHandlerEnd<T> greaterEqual(Node value);

    GraphHandlerEnd<T> greaterEqual(String value);

    default GraphHandlerEnd<T> greaterEqual(long value) {
        return greaterEqual(NodeFactoryExtra.intToNode(value));
    }

    default GraphHandlerEnd<T> greaterEqual(float value) {
        return greaterEqual(NodeFactoryExtra.floatToNode(value));
    }

    default GraphHandlerEnd<T> greaterEqual(double value) {
        return greaterEqual(NodeFactoryExtra.doubleToNode(value));
    }

    default GraphHandlerEnd<T> greaterEqual(Number value, RDFDatatype type) {
        return greaterEqual(NodeFactory.createLiteral(value.toString(), type));
    }


    GraphHandlerEnd<T> lesser(Node value);

    GraphHandlerEnd<T> lesser(String value);

    default GraphHandlerEnd<T> lesser(long value) {
        return lesser(NodeFactoryExtra.intToNode(value));
    }

    default GraphHandlerEnd<T> lesser(float value) {
        return lesser(NodeFactoryExtra.floatToNode(value));
    }

    default GraphHandlerEnd<T> lesser(double value) {
        return lesser(NodeFactoryExtra.doubleToNode(value));
    }

    default GraphHandlerEnd<T> lesser(Number value, RDFDatatype type) {
        return lesser(NodeFactory.createLiteral(value.toString(), type));
    }



    GraphHandlerEnd<T> lesserEqual(Node value);

    GraphHandlerEnd<T> lesserEqual(String value);

    default GraphHandlerEnd<T> lesserEqual(long value) {
        return lesserEqual(NodeFactoryExtra.intToNode(value));
    }

    default GraphHandlerEnd<T> lesserEqual(float value) {
        return lesserEqual(NodeFactoryExtra.floatToNode(value));
    }

    default GraphHandlerEnd<T> lesserEqual(double value) {
        return lesserEqual(NodeFactoryExtra.doubleToNode(value));
    }

    default GraphHandlerEnd<T> lesserEqual(Number value, RDFDatatype type) {
        return lesserEqual(NodeFactory.createLiteral(value.toString(), type));
    }


    GraphHandlerEnd<T> regexp(String regexp);

}
