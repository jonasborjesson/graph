/**
 * 
 */
package com.jonasborjesson.graph.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.jonasborjesson.graph.Edge;
import com.jonasborjesson.graph.Graph;
import com.jonasborjesson.graph.Path;
import com.jonasborjesson.graph.visitors.DijkstraSearchVisitor;
import com.jonasborjesson.graph.visitors.SearchVisitor;

/**
 * @author jonas@jonasborjesson.com
 */
public class DefaultGraph implements Graph<DefaultVertex> {

    /**
     * The name of this graph. Only meant for human consumption
     */
    private final String name;

    /**
     * All the vertices in this graph.
     */
    private final List<DefaultVertex> graph = new ArrayList<DefaultVertex>();

    /**
     * Constructor.
     * 
     * @param name
     *            the name of this graph, such as "My simple graph" or whatever.
     */
    public DefaultGraph(final String name) {
        this.name = name;
    }

    /**
     * Default empty constructor.
     */
    public DefaultGraph() {
        this("N/A");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final DefaultVertex addVertex(final String name) {
        final DefaultVertex v = new DefaultVertex(name);
        this.graph.add(v);
        return v;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Edge<DefaultVertex> createEdge(final DefaultVertex v1, final DefaultVertex v2) {
        if (!(this.graph.contains(v1) && this.graph.contains(v2))) {
            throw new IllegalArgumentException("This graphs does not contain both vertices");
        }

        return new DefaultEdge(v1, v2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Path<DefaultVertex> findPath(final DefaultVertex start, final DefaultVertex goal) {
        final SearchVisitor<DefaultVertex> search = new DijkstraSearchVisitor<DefaultVertex>(start, goal);
        return search.findPath();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<DefaultVertex> getVertices() {
        return this.graph.iterator();
    }
}
