/**
 * 
 */
package com.jonasborjesson.graph;

/**
 * @author jonas@jonasborjesson.com
 */
public interface Visitor<T extends Vertex<T>> {

    /**
     * Visit the {@link Vertex}
     * 
     * @param t
     */
    void visit(T t);

    /**
     * Visit the {@link Edge}
     * 
     * @param e
     */
    void visit(Edge<T> e);

}
