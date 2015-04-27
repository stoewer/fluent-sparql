package org.g_node.sparql.impl;


import com.hp.hpl.jena.query.ResultSet;
import org.g_node.sparql.dsl.Result;

public class AskResult implements Result {

    public boolean isTrue() {
        return true;
    }

    @Override
    public ResultSet result() {
        return null;
    }
}
