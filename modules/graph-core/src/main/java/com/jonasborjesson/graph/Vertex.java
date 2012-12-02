package com.jonasborjesson.graph;

import com.jonasborjesson.graph.visitors.SearchVisitor;

/**
 * 
 * @author jonas@jonasborjesson.com
 * 
 */
public interface Vertex<T extends Vertex<T>> {

    /**
     * 
     * @return
     */
    Iterable<T> getNeighbors();

    /**
     * 
     * @return
     */
    Iterable<Edge<T>> getEdges();

    /**
     * Get the name of this {@link Vertex}
     * 
     * @return
     */
    String getName();

    /**
     * Visitor method. Used by various types of visitors, which in our case is
     * mainly the {@link SearchVisitor}s.
     * 
     * @param visitor
     */
    void accept(Visitor<T> visitor);

    /**
     * Anyone (mainly our {@link SearchVisitor}s can push some arbitrary
     * information onto a {@link Vertex}. This is very useful when e.g.
     * implementing a search algorithm.
     * 
     * @param info
     */
    void setInfo(Object info);

    /**
     * Retrieve the information previously pushed onto this {@link Vertex}.
     * 
     * @return the info object or null if none has been set.
     */
    Object getInfo();

}
