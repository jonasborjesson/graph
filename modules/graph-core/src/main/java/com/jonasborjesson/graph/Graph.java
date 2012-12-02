package com.jonasborjesson.graph;

import java.util.Iterator;

/**
 * 
 * @author jonas@jonasborjesson.com
 */
public interface Graph<T extends Vertex<T>> {

    /**
     * The name of the graph.
     * 
     * @return
     */
    String getName();

    /**
     * Get all the vertices for this graph.
     * 
     * @return
     */
    Iterator<T> getVertices();

    /**
     * Add a new vertex to this graph.
     * 
     * @param name
     *            the name of the {@link Vertex}
     * @return the newly created {@link Vertex} T
     */
    T addVertex(String name);

    /**
     * Create a directional {@link Edge} between the two vertices v1 and v2. If
     * you wish the edge to go between v2 and v1 as well then you need to call
     * this method again with the vertices swapped around.
     * 
     * @param v1
     *            vertex one
     * @param v2
     *            vertex two
     * @return the new {@link Edge} representing the connection between the two
     *         vertices.
     */
    Edge<T> createEdge(T v1, T v2);

    /**
     * Find the shortest path between the two vertices. If there is no path
     * between the two vertices then null will be returned.
     * 
     * @param start
     *            the start {@link Vertex}
     * @param goal
     *            the goal {@link Vertex}
     * @return the single shortest path between the start and the goal or null
     *         if there is no such path.
     */
    Path<T> findPath(T start, T goal);

}
