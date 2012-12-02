package com.jonasborjesson.graph;


/**
 * Represents a path between two vertices in a {@link Graph}.
 * 
 * @author jonas@jonasborjesson.com
 */
public interface Path<T extends Vertex<T>> {

    /**
     * The total cost of traversing this {@link Path}.
     * 
     * @return
     */
    int getTotalCost();

    /**
     * Get the number of vertices in this path, which includes the start and
     * goal vertices. Hence, if there is a path between two vertices this method
     * will always return at least two.
     * 
     * @return
     */
    int getNoOfVertices();

    /**
     * Return the {@link Vertex} at the given index within the path.
     * 
     * @param index
     *            the index (zero based)
     * @return
     * @throws IndexOutOfBoundsException
     *             in case index is less than zero or greater or equal to
     *             {@link #getNoOfVertices()}
     */
    T getVertex(int index) throws IndexOutOfBoundsException;

    /**
     * Get all the vertices that makes up this {@link Path}.
     * 
     * @return
     */
    Iterable<T> getVertices();

}
