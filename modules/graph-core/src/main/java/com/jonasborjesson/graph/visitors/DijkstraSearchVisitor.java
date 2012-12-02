/**
 * 
 */
package com.jonasborjesson.graph.visitors;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import com.jonasborjesson.graph.Edge;
import com.jonasborjesson.graph.Path;
import com.jonasborjesson.graph.Vertex;
import com.jonasborjesson.graph.impl.DefaultPath;

/**
 * @author jonas@jonasborjesson.com
 */
public final class DijkstraSearchVisitor<T extends Vertex<T>> implements SearchVisitor<T> {

    /**
     * Our min-heap used for selecting the next {@link Vertex} to operate upon.
     */
    private final PriorityQueue<State<T>> heap;

    /**
     * Our starting point.
     */
    private final T start;

    /**
     * Our goal (if we can reach it)
     */
    private final T goal;

    /**
     * We store the state that we create in this list so that we can go over and
     * clear it after we are done searching.
     */
    private final List<State<T>> states = new ArrayList<DijkstraSearchVisitor.State<T>>();

    /**
     * 
     * @param start
     * @param goal
     */
    public DijkstraSearchVisitor(final T start, final T goal) {
        this.start = start;
        this.goal = goal;
        this.heap = new PriorityQueue<State<T>>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit(final T v) {

        if (v.equals(this.goal)) {
            return;
        }

        // when we are visiting a Vertex the state should have
        // already been created. Hence no null check...
        @SuppressWarnings("unchecked")
        final State<T> state = (State<T>) v.getInfo();

        if (state.visited) {
            return;
        }

        for (final Edge<T> edge : v.getEdges()) {
            edge.accept(this);
        }

        final State<T> next = this.heap.poll();
        if (next != null) {
            next.vertex.accept(this);
        }

    }

    /**
     * The purpose of visiting an edge is to calculate the cost of reaching the
     * {@link Vertex}.
     * 
     * {@inheritDoc}
     */
    @Override
    public void visit(final Edge<T> e) {
        final T v1 = e.getStartingEndpoint();
        final T v2 = e.getEndingEndpoint();

        @SuppressWarnings("unchecked")
        final State<T> s1 = (State<T>) v1.getInfo();

        @SuppressWarnings("unchecked")
        State<T> s2 = (State<T>) v2.getInfo();
        if (s2 == null) {
            s2 = createState(v2);
            s2.path = v1;
            s2.cost = s1.cost + e.getCost();
            v2.setInfo(s2);
            this.heap.add(s2);
        } else if ((s1.cost + e.getCost()) < s2.cost) {
            s2.cost = s1.cost + e.getCost();
            s2.path = v1;
        }
    }

    private State<T> createState(final T t) {
        final State<T> state = new State<T>(t);
        this.states.add(state);
        return state;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Path<T> findPath() {
        final State<T> initialState = createState(this.start);
        initialState.path = this.start;
        this.start.setInfo(initialState);
        this.start.accept(this);

        @SuppressWarnings("unchecked")
        final State<T> s = (State<T>) this.goal.getInfo();
        if (s == null) {
            return null;
        }
        final List<T> path = new ArrayList<T>();
        s.makePath(path);
        for (final State<T> state : this.states) {
            state.clear();
        }
        return new DefaultPath<T>(path);
    }

    /**
     * Simple class for keeping track of the state of a {@link Vertex}.
     * 
     * Note, we do not override equals or hashCode simply because I actually
     * really do want object reference comparison. The reason for that is simply
     * because it is possible to add vertices with the same name in this
     * implementation (perhaps we shouldn't allow that?).
     * 
     */
    private static class State<T extends Vertex<T>> implements Comparable<State<T>> {
        /**
         * Whether we have visited this Vertex in full.
         */
        public boolean visited;

        /**
         * The cost to get here.
         */
        public int cost = -1;

        /**
         * The {@link Vertex} that took us here. If the {@link #path} is the
         * same as the {@link #vertex} then this is the starting node.
         */
        public T path;

        /**
         * The {@link Vertex} for whose state we are tracking.
         */
        public final T vertex;

        /**
         * Save the previous info object associated with the {@link Vertex}. We
         * will set it back after the search is over.
         */
        private final Object previousInfo;

        public State(final T t) {
            this.previousInfo = t.getInfo();
            t.setInfo(null); // we rely on the info object being null
            this.vertex = t;
        }

        /**
         * Unwind the path we took to get here.
         * 
         * @return
         */
        public void makePath(final List<T> path) {
            if (!this.vertex.equals(this.path)) {
                @SuppressWarnings("unchecked")
                final State<T> s = (State<T>) this.path.getInfo();
                s.makePath(path);
            }
            path.add(this.vertex);

        }

        /**
         * Helper method to nullify the state stored on the {@link Vertex}
         */
        public void clear() {
            this.vertex.setInfo(this.previousInfo);
        }

        @Override
        public int compareTo(final State<T> o) {
            if (this.cost == o.cost) {
                return 0;
            }

            if (this.cost < o.cost) {
                return -1;
            }

            return 1;
        }
    }
}
