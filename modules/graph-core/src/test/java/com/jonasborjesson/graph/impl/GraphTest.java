/**
 * 
 */
package com.jonasborjesson.graph.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.jonasborjesson.graph.Edge;
import com.jonasborjesson.graph.Path;

/**
 * @author jonas@jonasborjesson.com
 */
public class GraphTest extends GraphTestBase {


    /**
     * @throws java.lang.Exception
     */
    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }


    /**
     * The most basic graph we can test. If you are new to this library, check
     * out this unit test first. It has extra verbose commenting to get you up
     * to speed.
     */
    @Test
    public void testVeryBasicGraph() {

        // Create a new instance of the default implementation
        // of the graph interface
        final DefaultGraph g = new DefaultGraph();

        // Once we have obtained a reference to a new graph
        // we can use it to create and add new vertices.
        // You can think of the Graph as a factory for creating
        // other graph elements such as vertices and edges.
        final DefaultVertex v1 = g.addVertex("v1");
        final DefaultVertex v2 = g.addVertex("v2");

        // We just created to vertices, v1 and v2, but we have not connected
        // them just yet. Hence, if we try and find a path between vertex v1
        // and vertex v2 we should not find one. If there is no path between
        // two vertices the Graph.findPath will return null.
        Path<DefaultVertex> path = g.findPath(v1, v2);
        assertThat(path, is((Path<DefaultVertex>) null));

        // In order to connect two vertices we have to create an edge
        // between them. This is done by using the createEdge method
        // on the graph interface. Once we have created an edge between
        // two vertices we can also associate a cost with traversing
        // that edge. By default the cost is set to 1 but we can change
        // it to anything...
        final Edge<DefaultVertex> edge = g.createEdge(v1, v2);
        edge.setCost(3);

        // since we just connected v1 and v2 there should actually
        // be away to get from v1 to v2, which is what we test below.
        path = g.findPath(v1, v2);

        // the assertPath is a utility function to verify we
        // got back what we expected.
        assertPath(path, v1, v2);

        // Every edge in this library only goes one way so if you
        // try and in the other direction (i.e. from v2 to v1 in this
        // particular example) then there will not be any path.
        // Let's verify that...
        path = g.findPath(v2, v1);
        assertThat(path, is((Path<DefaultVertex>) null));

        // Ok, let's create an edge in the other direction as well.
        // Simply use the graph to create a new edge, this time
        // from v2 to v1 (we did the opposite way last time)
        g.createEdge(v2, v1);

        // when we now try and go between v2 and v1 we should
        // be able to do it.
        path = g.findPath(v2, v1);
        assertPath(path, v2, v1);

        // That's it!
    }

    /**
     * Test the default graph and the various paths through it.
     */
    @Test
    public void testGraph() {
        Path<DefaultVertex> path = this.graph.findPath(this.one, this.seven);
        assertPath(path, this.one, this.four, this.seven);

        path = this.graph.findPath(this.one, this.three);
        assertPath(path, this.one, this.four, this.three);

        path = this.graph.findPath(this.one, this.four);
        assertPath(path, this.one, this.four);

        path = this.graph.findPath(this.two, this.one);
        assertPath(path, this.two, this.four, this.three, this.one);

        path = this.graph.findPath(this.two, this.five);
        assertPath(path, this.two, this.four, this.five);

        path = this.graph.findPath(this.four, this.six);
        assertPath(path, this.four, this.seven, this.six);

        path = this.graph.findPath(this.four, this.five);
        assertPath(path, this.four, this.five);

        path = this.graph.findPath(this.seven, this.six);
        assertPath(path, this.seven, this.six);

        path = this.graph.findPath(this.three, this.one);
        assertPath(path, this.three, this.one);

        // change some weights around. Make all internal paths really
        // expensive and the "external" paths cheap.
        this.oneTwo.setCost(1);
        this.twoFive.setCost(1);
        this.fiveSeven.setCost(1);
        this.sevenSix.setCost(1);
        this.threeSiz.setCost(1);
        this.threeOne.setCost(1);

        this.oneFour.setCost(10);
        this.twoFour.setCost(10);
        this.fourFive.setCost(10);
        this.fourSeven.setCost(10);
        this.fourSix.setCost(10);

        // Going 1 to seven will not take the external path
        path = this.graph.findPath(this.one, this.seven);
        assertPath(path, this.one, this.two, this.five, this.seven);

        path = this.graph.findPath(this.one, this.six);
        assertPath(path, this.one, this.two, this.five, this.seven, this.six);

        // three to seven will loop all the way around
        path = this.graph.findPath(this.three, this.seven);
        assertPath(path, this.three, this.one, this.two, this.five, this.seven);

        path = this.graph.findPath(this.three, this.six);
        assertPath(path, this.three, this.six);

        // one to three, however, doesn't have a choice and
        // has to go an expensive internal route...
        path = this.graph.findPath(this.one, this.three);
        assertPath(path, this.one, this.four, this.three);
    }

    /**
     * Make sure that if there is no path between two vertices that we are doing
     * the correct thing (returning null and of course don't end up in a loop or
     * whatever)
     */
    @Test
    public void testNoPathToGoal() {
        assertThat(this.graph.findPath(this.seven, this.one), is((Path<DefaultVertex>) null));
        assertThat(this.graph.findPath(this.six, this.one), is((Path<DefaultVertex>) null));
        assertThat(this.graph.findPath(this.five, this.four), is((Path<DefaultVertex>) null));
    }

    /**
     * Helper method for asserting that the path we find is what we expect.
     * 
     * @param path
     * @param expected
     */
    private void assertPath(final Path<DefaultVertex> path, final DefaultVertex... expected) {
        assertThat(path.getNoOfVertices(), is(expected.length));
        for (int i = 0; i < expected.length; ++i) {
            assertThat(path.getVertex(i), is(expected[i]));
        }

    }

}
