package org.g_node.sparql.impl;


import java.util.*;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.sparql.syntax.*;
import org.g_node.sparql.util.NodeHelper;

public abstract class BasicHandler extends NodeHelper {

    private final Query query;
    private final Model model;

    // internal
    private final ElementGroup current;

    /**
     * Makes a copy of the query which is further modified by the handler.
     *
     * @param query The query used by the handler.
     * @param model The model to execute the query on.
     */
    public BasicHandler(Query query, Model model) {
        this.query = query.cloneQuery();
        this.model = model;
        current = findCurrent();
    }

    /**
     * Creates a new handler based on a copy of the query from the original handler.
     *
     * @param handler The handler to copy.
     */
    public BasicHandler(BasicHandler handler) {
        this(handler.query, handler.model);
    }

    /**
     * Returns the query used by the handler.
     * The content of the query may change subsequent changes are
     * made by the handler.
     *
     * @return The query used by the handler.
     */
    public Query getQuery() {
        return query;
    }

    /**
     * Returns the model the query will be executed on.
     *
     * @return Model for the query.
     */
    public Model getModel() {
        return model;
    }

    /**
     * Get the syntax element which is modified by the handler.
     *
     * @return The current syntax element.
     */
    public ElementGroup getCurrent() {
        return current;
    }

    private ElementGroup findCurrent() {
        Optional<ElementUnion> union = findUnion(query.getQueryPattern());

        if (union.isPresent()) {
            List<Element> elements = union.get().getElements();
            Element lastElement = elements.get(elements.size() - 1);

            if (lastElement instanceof ElementGroup) {
                return (ElementGroup) lastElement;
            } else {
                throw new RuntimeException("Unable to find current syntax element");
            }
        } else {
            Element topElement = query.getQueryPattern();

            if (topElement instanceof ElementGroup) {
                return (ElementGroup) topElement;
            } else {
                throw new RuntimeException("Unable to find current syntax element");
            }
        }
    }

    protected Optional<ElementUnion> findUnion(Element element) {
        if (element instanceof ElementUnion) {
            return Optional.of((ElementUnion) element);
        }

        if (element instanceof ElementGroup) {
            for (Element child : ((ElementGroup) element).getElements()) {
                Optional<ElementUnion> union = findUnion(child);

                if (union.isPresent())
                    return union;
            }

            return Optional.empty();
        }

        return Optional.empty();
    }
}
