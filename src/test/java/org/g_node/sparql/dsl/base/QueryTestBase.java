package org.g_node.sparql.dsl.base;

import java.util.*;

import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.reasoner.*;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import org.g_node.sparql.FluentSparql;
import org.junit.*;

/**
 * Test with som test data in a model for testing.
 */
public class QueryTestBase {

    private static final Map<String, String> prefixes;
    private static final Model foafOnt;

    static {
        prefixes = new HashMap<>();
        prefixes.put("foaf", FOAF.NS);

        foafOnt = ModelFactory.createOntologyModel();
        foafOnt.read(QueryTestBase.class.getResource("/foaf.rdf").toString());
    }

    protected Model lotr;
    protected InfModel lotrInf;
    protected FluentSparql sparql;

    @Before
    public void setUp() {
        lotr = ModelFactory.createDefaultModel();
        lotr.read(QueryTestBase.class.getResource("/lotr.ttl").toString());

        lotrInf = ModelFactory.createInfModel(ReasonerRegistry.getOWLReasoner(), foafOnt, lotr);

        sparql = new FluentSparql(prefixes, true);
    }

}
