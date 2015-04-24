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
            String name = row.getLiteral(familyName.toString()).getString();
            assertThat(name).isEqualTo("Baggins");

            assertThat(result.hasNext()).isFalse();
        } catch (Exception e) {
            fail(String.format("Unexpected exception: %s", e.getMessage()));
            e.printStackTrace();
        }
    }

    @Test
    public void testFilterQueryLesser() {
        // get nick name and given name from persons under 40
        Query lesser40 = sparql
                .select("nick", "given")
                    .add("?p", FOAF.givenname, "?given")
                    .add("?p", FOAF.nick, "?nick")
                    .add("?p", "foaf:age", "?age")
                .filter("?age").lesser(40)
                .query();

        try (QueryExecution exec = QueryExecutionFactory.create(lesser40, lotrInf)) {
            ResultSet result = exec.execSelect();

            assertThat(result.hasNext()).isTrue();

            QuerySolution row = result.next();
            String given = row.getLiteral("given").getString();
            String nick  = row.getLiteral("nick").getString();
            assertThat(given).isEqualTo("Samwise");
            assertThat(nick).isEqualTo("Sam");

            assertThat(result.hasNext()).isFalse();
        } catch (Exception e) {
            fail(String.format("Unexpected exception: %s", e.getMessage()));
            e.printStackTrace();
        }
    }

    @Test
    public void testFilterQueryGreater() {
        // get nick name and given name from persons over 40
        Query greater40 = sparql.select("nick", "given")
                .add("?p", FOAF.givenname, "?given")
                .add("?p", FOAF.nick, "?nick")
                .add("?p", "foaf:age", "?age")
                .filter("?age").greater(40)
                .query();

        try (QueryExecution exec = QueryExecutionFactory.create(greater40, lotrInf)) {
            ResultSet result = exec.execSelect();

            assertThat(result.hasNext()).isTrue();

            QuerySolution row = result.next();
            String given = row.getLiteral("given").getString();
            String nick  = row.getLiteral("nick").getString();
            assertThat(given).isEqualTo("Frodo");
            assertThat(nick).isEqualTo("Tiny Hobbit");

            assertThat(result.hasNext()).isFalse();
        } catch (Exception e) {
            fail(String.format("Unexpected exception: %s", e.getMessage()));
            e.printStackTrace();
        }
    }

    @Test
    public void testFilterQueryLesserEqual() {
        // get nick name and given name from persons of the age of 50 or younger
        Query lesserEqual50 = sparql
                .select("nick", "given")
                    .add("?p", FOAF.givenname, "?given")
                    .add("?p", FOAF.nick, "?nick")
                    .add("?p", "foaf:age", "?age")
                .filter("?age").lesserEqual(50)
                .orderByAsc("nick")
                .query();

        try (QueryExecution exec = QueryExecutionFactory.create(lesserEqual50, lotrInf)) {
            ResultSet result = exec.execSelect();

            assertThat(result.hasNext()).isTrue();

            QuerySolution row = result.next();
            String given = row.getLiteral("given").getString();
            String nick  = row.getLiteral("nick").getString();
            assertThat(given).isEqualTo("Samwise");
            assertThat(nick).isEqualTo("Sam");

            assertThat(result.hasNext()).isTrue();

            row = result.next();
            given = row.getLiteral("given").getString();
            nick  = row.getLiteral("nick").getString();
            assertThat(given).isEqualTo("Frodo");
            assertThat(nick).isEqualTo("Tiny Hobbit");

            assertThat(result.hasNext()).isFalse();
        } catch (Exception e) {
            fail(String.format("Unexpected exception: %s", e.getMessage()));
            e.printStackTrace();
        }
    }

    @Test
    public void testFilterQueryGreaterEqual() {
        // get nick name and given name from persons of the age of 38 or older
        Query greaterEqual38 = sparql
                .select("nick", "given")
                    .add("?p", FOAF.givenname, "?given")
                    .add("?p", FOAF.nick, "?nick")
                    .add("?p", "foaf:age", "?age")
                .filter("?age").greaterEqual(38)
                .orderByDesc("nick")
                .query();

        try (QueryExecution exec = QueryExecutionFactory.create(greaterEqual38, lotrInf)) {
            ResultSet result = exec.execSelect();

            assertThat(result.hasNext()).isTrue();

            QuerySolution row = result.next();
            String given = row.getLiteral("given").getString();
            String nick  = row.getLiteral("nick").getString();
            assertThat(given).isEqualTo("Frodo");
            assertThat(nick).isEqualTo("Tiny Hobbit");

            assertThat(result.hasNext()).isTrue();

            row = result.next();
            given = row.getLiteral("given").getString();
            nick  = row.getLiteral("nick").getString();
            assertThat(given).isEqualTo("Samwise");
            assertThat(nick).isEqualTo("Sam");

            assertThat(result.hasNext()).isFalse();
        } catch (Exception e) {
            fail(String.format("Unexpected exception: %s", e.getMessage()));
            e.printStackTrace();
        }
    }

}