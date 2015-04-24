package org.g_node.sparql.dsl;

import com.hp.hpl.jena.sparql.core.Var;
import org.g_node.sparql.impl.*;

public interface PrologueHandler {

    PrologueHandler prefix(String prefix, String uri);

    PrologueHandler prefix(String uri);

    GraphHandlerBegin<SelectResult> select(String ...variables);

    GraphHandlerBegin<SelectResult> select(Var...variables);

    GraphHandler<AskResult> ask();

}

