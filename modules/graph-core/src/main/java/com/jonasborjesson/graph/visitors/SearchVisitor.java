package com.jonasborjesson.graph.visitors;

import com.jonasborjesson.graph.Path;
import com.jonasborjesson.graph.Vertex;
import com.jonasborjesson.graph.Visitor;

/**
 * Visitor interface for search algorithms.
 * 
 * @author jonas@jonasborjesson.com
 */
public interface SearchVisitor<T extends Vertex<T>> extends Visitor<T> {

    /**
     * Find the most optimal path.
     * 
     * @param start
     * @param goal
     * @return
     */
    Path<T> findPath();
}
