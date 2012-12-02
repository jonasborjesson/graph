/**
 * 
 */
package com.jonasborjesson.graph;

import org.junit.After;
import org.junit.Before;

import com.jonasborjesson.graph.impl.DefaultGraph;
import com.jonasborjesson.graph.impl.DefaultVertex;

/**
 * @author jonas
 *
 */
public class GraphTestBase {
    protected DefaultGraph graph;

    protected DefaultVertex one;
    protected DefaultVertex two;
    protected DefaultVertex three;
    protected DefaultVertex four;
    protected DefaultVertex five;
    protected DefaultVertex six;
    protected DefaultVertex seven;

    // save the edges so we can change the
    // values without having to rebuild the graph
    protected Edge<DefaultVertex> oneTwo;
    protected Edge<DefaultVertex> oneFour;
    protected Edge<DefaultVertex> twoFour;
    protected Edge<DefaultVertex> twoFive;
    protected Edge<DefaultVertex> fiveSeven;
    protected Edge<DefaultVertex> fourFive;
    protected Edge<DefaultVertex> fourSeven;
    protected Edge<DefaultVertex> fourSix;
    protected Edge<DefaultVertex> fourThree;
    protected Edge<DefaultVertex> sevenSix;
    protected Edge<DefaultVertex> threeOne;
    protected Edge<DefaultVertex> threeSiz;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        initializeDefaultGraph();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Setup the default graph we will be using for most of our unit tests,
     * which looks like this
     * 
     * <pre>
     *          1 -------> 2
     *         ^ \       / \
     *        /   \     /   \
     *       /     \   /     \
     *      /       v v       v
     *     3 <------ 4 -----> 5
     *      \       / \       /
     *       \     /   \     /
     *        \   /     \   /
     *         v v       v v
     *         6 <------- 7
     * 
     * </pre>
     * 
     */
    protected void initializeDefaultGraph() {
        this.graph = new DefaultGraph();
        this.one = this.graph.addVertex("one");
        this.two = this.graph.addVertex("two");
        this.three = this.graph.addVertex("three");
        this.four = this.graph.addVertex("four");
        this.five = this.graph.addVertex("five");
        this.six = this.graph.addVertex("six");
        this.seven = this.graph.addVertex("seven");

        this.oneTwo = this.graph.createEdge(this.one, this.two);
        this.oneFour = this.graph.createEdge(this.one, this.four);
        this.twoFour = this.graph.createEdge(this.two, this.four);
        this.twoFive = this.graph.createEdge(this.two, this.five);
        this.fiveSeven = this.graph.createEdge(this.five, this.seven);
        this.fourFive = this.graph.createEdge(this.four, this.five);
        this.fourSeven = this.graph.createEdge(this.four, this.seven);
        this.fourSix = this.graph.createEdge(this.four, this.six);
        this.fourThree = this.graph.createEdge(this.four, this.three);
        this.sevenSix = this.graph.createEdge(this.seven, this.six);
        this.threeOne = this.graph.createEdge(this.three, this.one);
        this.threeSiz = this.graph.createEdge(this.three, this.six);

        this.oneTwo.setCost(2);
        this.oneFour.setCost(1);
        this.twoFour.setCost(3);
        this.twoFive.setCost(10);
        this.fiveSeven.setCost(6);
        this.fourFive.setCost(2);
        this.fourSeven.setCost(4);
        this.fourSix.setCost(8);
        this.fourThree.setCost(2);
        this.sevenSix.setCost(1);
        this.threeOne.setCost(4);
        this.threeSiz.setCost(5);
    }

}
