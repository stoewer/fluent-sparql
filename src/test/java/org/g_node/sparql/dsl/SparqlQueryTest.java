package org.g_node.sparql.dsl;

import static org.assertj.core.api.Assertions.*;

import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.sparql.core.Var;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.vocabulary.RDF;
import org.g_node.sparql.dsl.base.QueryTestBase;
import org.junit.Test;

public class SparqlQueryTest extends QueryTestBase {

    @Test
    public void testSimpleQuery() {

        // get the family name of a person with given name 'Frodo'
        Var familyName = Var.alloc("familyName");
        Query q = sparql
                .select(familyName)
                    .add("?a", RDF.type, FOAF.Person)
                    .add("?a", FOAF.givenname, "Frodo")
                    .add("?a", FOAF.family_name, familyName)
                .query();

        try (QueryExecution exec = QueryExecutionFactory.create(q, lotrInf)) {
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