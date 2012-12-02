/**
 * 
 */
package com.jonasborjesson.graph.impl;

import java.util.ArrayList;
import java.util.List;

import com.jonasborjesson.graph.Edge;
import com.jonasborjesson.graph.Vertex;
import com.jonasborjesson.graph.Visitor;

/**
 * @author jonas@jonasborjesson.com
 * 
 */
public final class DefaultVertex implements Vertex<DefaultVertex> {

    /**
     * The name of this {@link Vertex}
     */
    private final String name;

    /**
     * The edges going out from this {@link Vertex}.
     */
    private final List<Edge<DefaultVertex>> edges = new ArrayList<Edge<DefaultVertex>>();

    /**
     * Users can push any arbitrary info onto a {@link Vertex}.
     */
    private Object info;

    /**
     * Simple constructor.
     * 
     * @param name
     *            the name of this node.
     */
    protected DefaultVertex(final String name) {
        assert (name != null) && !name.isEmpty();
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<DefaultVertex> getNeighbors() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Iterable<Edge<DefaultVertex>> getEdges() {
        return this.edges;
    }

    /**
     * 
     * @param edge
     */
    protected void addEdge(final DefaultEdge edge) {
        this.edges.add(edge);
    }

    @Override
    public void accept(final Visitor<DefaultVertex> visitor) {
        visitor.visit(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInfo(final Object info) {
        this.info = info;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getInfo() {
        return this.info;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
