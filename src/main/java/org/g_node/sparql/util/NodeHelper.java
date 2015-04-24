package org.g_node.sparql.util;


import java.util.Map.Entry;

import com.hp.hpl.jena.graph.*;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.sparql.util.NodeFactoryExtra;
import org.apache.jena.riot.RiotException;
import org.apache.jena.riot.system.PrefixMapFactory;

public abstract class NodeHelper {

    protected abstract Query getQuery();

    public final Node mkNode(Object obj) {

        if (obj instanceof Node) {
            return (Node) obj;
        } else if (obj instanceof FrontsNode) {
            return ((FrontsNode) obj).asNode();
        } else {
            return mkNode(obj.toString());
        }
    }


    public final Node mkNode(String str) {
        Node n = Node.ANY;

        str = str.trim();

        for (Entry<String, String> entry: getQuery().getPrefixMapping().getNsPrefixMap().entrySet()) {
            String prefix = entry.getKey();
            String uri = entry.getValue();

            if (str.startsWith(uri)) {
                str = str.replace(uri, String.format("%s:", prefix));
                break;
            }
        }

        try {
            n = NodeFactoryExtra.parseNode(str, PrefixMapFactory.createForInput(getQuery().getPrefixMapping()));
        } catch (RiotException e) {
            n = NodeFactoryExtra.createLiteralNode(str, null, null);
        }

        return n;
    }

    public final Triple mkTriple(Object s, Object p, String o) {
        return new Triple(mkNode(s), mkNode(p), mkNode(o));
    }

    public final Triple mkTriple(Object s, Object p, Node o) {
        return new Triple(mkNode(s), mkNode(p), o);
    }

}
