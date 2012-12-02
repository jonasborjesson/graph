package com.jonasborjesson.graph.impl;

import com.jonasborjesson.graph.Edge;
import com.jonasborjesson.graph.Visitor;

/**
 * 
 * @author jonas@jonasborjesson.com
 */
public final class DefaultEdge implements Edge<DefaultVertex> {

    private int cost;
    private final DefaultVertex endpointA;
    private final DefaultVertex endpointB;

    protected DefaultEdge(final DefaultVertex endpointA, final DefaultVertex endpointB) {
        this.endpointA = endpointA;
        this.endpointB = endpointB;
        endpointA.addEdge(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int setCost(final int weight) {
        final int tmp = this.cost;
        this.cost = weight;
        return tmp;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DefaultVertex getStartingEndpoint() {
        return this.endpointA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DefaultVertex getEndingEndpoint() {
        return this.endpointB;
    }

    @Override
    public int getCost() {
        return this.cost;
    }

    @Override
    public void accept(final Visitor<DefaultVertex> visitor) {
        visitor.visit(this);
    }
}
