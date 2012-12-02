package com.jonasborjesson.graph;

/**
 * 
 * @author jonas@jonasborjesson.com
 * 
 */
public interface Edge<T extends Vertex<T>> {

    /**
     * Set the cost of this edge.
     * 
     * @param cost
     *            the new cost.
     * @return the old cost.
     */
    int setCost(int cost);

    /**
     * Get the cost of traversing this {@link Edge}
     * 
     * @return
     */
    int getCost();

    /**
     * Get the starting endpoint at of this {@link Edge}.
     * 
     * @return endpoint A
     */
    T getStartingEndpoint();

    /**
     * Get the ending endpoint at the start of this {@link Edge}.
     * 
     * @return endpoint A
     */
    T getEndingEndpoint();

    /**
     * Visitor method.
     * 
     * @param visitor
     */
    void accept(Visitor<T> visitor);

}
