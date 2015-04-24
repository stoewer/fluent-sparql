package org.g_node.sparql.dsl;

import static com.hp.hpl.jena.datatypes.xsd.XSDDatatype.XSDdouble;
import static org.assertj.core.api.Assertions.fail;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.sparql.core.Var;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import org.junit.Test;

/**
 * Simple test for checking that the query generation runs without
 * exceptions.
 */
public class SparqlSyntaxTest extends QueryTestBase {

    @Test
    public void test() {
        try {
            Var name = Var.alloc("name");
            Var age = Var.alloc("age");

            Query query = sparql
                    .prefix("foo", "http://foo.com#")
                    .prefix("foaf", FOAF.NS)

                    .select(name, age).distinct()
                    .from("http://foo.com")
                        .add(name, FOAF.name, "?b")
                        .add(age, "foo:age", 5.f, XSDdouble)
                        .add("?a", "foo:FN", "blafasel")
                        .filter("?b").lesser(3, XSDdouble)
                        .filter("?a").regexp("^[abcd].*")
                    .union()
                        .add("?a", "foo:name", "?b")
                        .add("?a", "foo:age", 6.)
                    .union()
                        .add("?a", "foo:name", "?b")
                        .add("?a", "foo:age", 7f)
                        .add("?a", "foo:age", "7.3")
                    .orderByAsc("?a")
                    .offset(0)
                    .limit(20)
                    .query();

        } catch (Exception e) {
            fail(String.format("Unexpected exception: %s", e.getMessage()));
            e.printStackTrace();
        }
    }
}
