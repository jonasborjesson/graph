/**
 * 
 */
package com.jonasborjesson.graph.visitors;

import java.util.Iterator;

import com.jonasborjesson.graph.Edge;
import com.jonasborjesson.graph.Graph;
import com.jonasborjesson.graph.Vertex;
import com.jonasborjesson.graph.Visitor;

/**
 * Simple visitor for printing out a graph in dot notation so we can visualize
 * it.
 * 
 * Note, currently this will only work for connected graphs.
 * 
 * @author jonas@jonasborjesson.com
 */
public class DotVisitor<T extends Vertex<T>> implements Visitor<T> {

    private StringBuilder sb;

    public DotVisitor() {
    }

    /**
     * Dump a graph to dot notation so it can be printed nicely
     * 
     * @param graph
     * @return
     */
    public String toDotNotation(final Graph<T> graph) {
        this.sb = new StringBuilder("digraph g {\n");
        final Iterator<T> it = graph.getVertices();
        if (it.hasNext()) {
            final T v = it.next();
            v.accept(this);

            // clean out the state again
            // probably should store previous state
            // and reset it.
            v.setInfo(null);
            while (it.hasNext()) {
                it.next().setInfo(null);
            }
        }
        this.sb.append("}");
        return this.sb.toString();
    }

    @Override
    public void visit(final T v) {

        // means that we have been here before.
        if (v.getInfo() != null) {
            return;
        }

        // really doesn't matter what it is...
        v.setInfo(true);

        for (final Edge<T> edge : v.getEdges()) {
            edge.accept(this);
        }
    }

    @Override
    public void visit(final Edge<T> e) {
        final T v = e.getStartingEndpoint();
        final T w = e.getEndingEndpoint();
        this.sb.append("   ");
        this.sb.append(v.getName());
        this.sb.append(" -> ");
        this.sb.append(w.getName());
        this.sb.append("[label=\"c=" + e.getCost() + "\"]\n");
        w.accept(this);
    }


}
