package org.g_node.sparql.dsl;

import static org.assertj.core.api.Assertions.*;

import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.sparql.core.Var;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.vocabulary.RDF;
import org.junit.Test;

public class SparqlQueryTest extends QueryTestBase {

    @Test
    public void testSimpleQuery() {

        Var familyName = Var.alloc("familyName");
        Query q = sparql
                .select(familyName)
                    .add("?a", RDF.type, FOAF.Person)
                    .add("?a", FOAF.givenname, "Frodo")
                    .add("?a", FOAF.family_name, familyName)
                .query();

        Query q2 = QueryFactory.create("SELECT ?c WHERE { ?a ?b ?c . }");

        try (QueryExecution exec = QueryExecutionFactory.create(q, ringInf)) {
            ResultSet result = exec.execSelect();

            assertThat(result.hasNext()).isTrue();

            QuerySolution row = result.next();

            assertThat(result.hasNext()).isFalse();

            String name = row.getLiteral(familyName.toString()).getString();
            assertThat(name).isEqualTo("Baggins");
        } catch (Exception e) {
            fail(String.format("Unexpected exception: %s", e.getMessage()));
            e.printStackTrace();
        }
    }

}