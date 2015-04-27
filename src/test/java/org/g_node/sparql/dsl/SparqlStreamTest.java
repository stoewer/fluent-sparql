package org.g_node.sparql.dsl;

import java.util.List;
import java.util.stream.*;

import static org.assertj.core.api.Assertions.*;

import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.sparql.core.Var;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.vocabulary.RDF;
import org.g_node.sparql.dsl.base.QueryTestBase;
import org.junit.Test;

public class SparqlStreamTest extends QueryTestBase {

    @Test
    public void testSimpleQuery() {
        // get the family name of a person with given name 'Frodo'
        Var familyName = Var.alloc("familyName");
        List<String> names = sparql
                .select(familyName)
                .from(lotrInf)
                    .add("?a", RDF.type, FOAF.Person)
                    .add("?a", FOAF.givenname, "Frodo")
                    .add("?a", FOAF.family_name, familyName)
                .execute().stream()
                .map((qs) -> qs.getLiteral(familyName.toString()).getString())
                .collect(Collectors.toList());

        assertThat(names.size()).isEqualTo(1);
        assertThat(names.get(0)).isEqualTo("Baggins");
    }

}
