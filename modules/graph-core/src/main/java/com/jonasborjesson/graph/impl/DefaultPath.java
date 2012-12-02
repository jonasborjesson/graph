/**
 * 
 */
package com.jonasborjesson.graph.impl;

import java.util.List;

import com.jonasborjesson.graph.Path;
import com.jonasborjesson.graph.Vertex;

/**
 * @author jonas@jonasborjesson.com
 */
public final class DefaultPath<T extends Vertex<T>> implements Path<T> {

    /**
     * The list of vertices we have to go through to get to our goal.
     */
    private final List<T> path;

    /**
     * 
     */
    public DefaultPath(final List<T> path) {
        this.path = path;
    }

    @Override
    public int getTotalCost() {
        final int cost = 0;
        for (final T v : this.path) {
        }

        return cost;
    }

    @Override
    public Iterable<T> getVertices() {
        return this.path;
    }

    @Override
    public int getNoOfVertices() {
        return this.path.size();
    }

    @Override
    public T getVertex(final int index) throws IndexOutOfBoundsException {
        return this.path.get(index);
    }

}
