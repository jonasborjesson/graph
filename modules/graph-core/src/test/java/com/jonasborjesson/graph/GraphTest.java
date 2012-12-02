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


    @Test
    public void testVeryBasicGraph() {
        final DefaultGraph g = new DefaultGraph();
        final DefaultVertex v1 = g.addVertex("v1");
        final DefaultVertex v2 = g.addVertex("v1");

        // they are not connected so no path between them
        // just yet...
        assertThat(g.findPath(v1, v2), is((Path<DefaultVertex>) null));

        // connect v1 to v2...
        g.createEdge(v1, v2).setCost(40);
        assertPath(g.findPath(v1, v2), v1, v2);

        // no path from v2 to v1 just yet though...
        assertThat(g.findPath(v2, v1), is((Path<DefaultVertex>) null));

        // connect the other way
        g.createEdge(v2, v1);
        assertPath(g.findPath(v2, v1), v2, v1);
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
