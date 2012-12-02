/**
 * 
 */
package com.jonasborjesson.graph;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.jonasborjesson.graph.impl.DefaultGraph;
import com.jonasborjesson.graph.impl.DefaultVertex;
import com.jonasborjesson.graph.visitors.DotVisitor;

/**
 * @author jonas@jonasborjesson.com
 */
public class DotVisitorTest extends GraphTestBase {

    /**
     * @throws java.lang.Exception
     */
    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void testPrintDefaultGraph() {
        final DotVisitor<DefaultVertex> visitor = new DotVisitor<DefaultVertex>();
        final String dot = visitor.toDotNotation(this.graph);
        System.out.println(dot);
        assertThat(dot.contains("one -> two"), is(true));
        assertThat(dot.contains("two -> one"), is(true));
    }

    /**
     * Make sure that we can print a very simple little graph.
     */
    @Test
    public void testSimpleGraph() {
        final DefaultGraph graph = new DefaultGraph("Test");
        final DefaultVertex v1 = graph.addVertex("one");
        final DefaultVertex v2 = graph.addVertex("two");
        graph.createEdge(v1, v2).setCost(3);
        graph.createEdge(v2, v1);

        final DotVisitor<DefaultVertex> visitor = new DotVisitor<DefaultVertex>();
        final String dot = visitor.toDotNotation(graph);
        System.out.println(dot);
        assertThat(dot.contains("one -> two"), is(true));
        assertThat(dot.contains("two -> one"), is(true));
    }

}
