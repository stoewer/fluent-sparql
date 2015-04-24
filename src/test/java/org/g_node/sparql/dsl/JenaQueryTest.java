package org.g_node.sparql.dsl;

import com.hp.hpl.jena.graph.NodeFactory;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.sparql.expr.*;
import com.hp.hpl.jena.sparql.syntax.*;
import com.hp.hpl.jena.sparql.util.NodeFactoryExtra;
import org.g_node.sparql.util.NodeHelper;
import org.junit.*;


public class JenaQueryTest extends NodeHelper {

    private Query query;

    @Override
    public Query getQuery() {
        return query;
    }

    @Before
    public void setUp() {
        query = new Query();
    }

    @Test
    public void testManualQuery() {
        Query query = new Query();

        query.setPrefix("foo", "http://foo.com#");
        query.setPrefix("xsd", "http://www.w3.org/2001/XMLSchema#");

        query.setQuerySelectType();

        query.addResultVar("a");
        query.addResultVar("b");

        Element topGroup = new ElementGroup();
        ElementGroup currGroup = (ElementGroup) topGroup;

        currGroup.addTriplePattern(mkTriple("?a", "foo:name", NodeFactoryExtra.floatToNode(7.0f)));

        ElementUnion tmpGroup = new ElementUnion();
        tmpGroup.addElement(currGroup);
        topGroup = tmpGroup;

        currGroup = new ElementGroup();

        currGroup.addTriplePattern(mkTriple("?a", "foo:name", NodeFactoryExtra.intToNode(6)));

        ElementFilter filter = new ElementFilter(
                new E_LessThan(
                        NodeValue.makeNode(mkNode("?a")),
                        NodeValue.makeNode(mkNode("4.8"))
                )
        );
        currGroup.addElement(filter);

        if (topGroup instanceof ElementGroup) {
            ((ElementGroup) topGroup).addElement(currGroup);
        } else if (topGroup instanceof ElementUnion) {
            ((ElementUnion) topGroup).addElement(currGroup);
        }

        query.setQueryPattern(topGroup);

        System.out.println(query.toString());
    }
}
