package org.g_node.sparql.dsl;

import static com.hp.hpl.jena.datatypes.xsd.XSDDatatype.XSDdouble;
import static org.g_node.sparql.FluentSparql.sparql;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.sparql.core.Var;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import org.junit.Test;

public class SparqlTest {



//    @Test
//    public void testOutput() {
//        FluentSparql sparql = new FluentSparql();
//
//        try {
//            Stream<String> o = sparql
//                    .prefix("foo", "http://foo.com#")
//                    .prefix("bar", "http://bar.org#")
//                    .prefix("xsd", "http://www.w3.org/2001/XMLSchema#")
//                    .select("a").distinct()
//                        .add("?a", "foo:name", "?b")
//                        .add("?a", "bar:FN", "5")
//                        .filter("?a").greater(4.2)
//                        .filter("?a").regexp("^[abc]$")
//                    .union()
//                        .add("?a", "foo:name", "?b")
//                        .add("?b", "bar:FN", "bla")
//                        .filter("?a").greater(5)
//                    .offset(6)
//                    .limit(10)
//                    .orderByAsc("?a", "?b")
//                    .execute().map(String.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(sparql.query.toString());
//    }

    @Test
    public void testSimple() {
        Var name = Var.alloc("name");
        Var age = Var.alloc("age");

        Query query = sparql
                .prefix("foo", "http://foo.com#")
                .prefix("foaf", FOAF.NS)
                .select(name, age)
                .distinct()
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

        System.out.println(query.toString());
    }

}