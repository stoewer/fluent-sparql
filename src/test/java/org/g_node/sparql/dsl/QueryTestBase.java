package org.g_node.sparql.dsl;

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

    public static final Map<String, String> prefixes;
    public static final Model foafOnt;

    static {
        prefixes = new HashMap<>();
        prefixes.put("foaf", FOAF.NS);

        foafOnt = ModelFactory.createOntologyModel();
        foafOnt.read(FOAF.getURI());
    }

    protected Model ring, ringInf;
    protected FluentSparql sparql;

    @Before
    public void setUp() {
        Reasoner resoner = ReasonerRegistry.getOWLReasoner();
        resoner.bindSchema(foafOnt);

        ring = ModelFactory.createDefaultModel();
        ring.read(getClass().getResource("/ring.ttl").toString());

        ringInf = ModelFactory.createInfModel(resoner, foafOnt, ring);

        sparql = new FluentSparql(prefixes, true);
    }

}
