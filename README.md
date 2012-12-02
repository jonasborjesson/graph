# Graphs

I needed a basic graph library for some personal projects but simply didn't find one written in Java that I liked so I decided to write my own. This library is very basic and provides an interface for dealing with graphs in general and uses the visitor pattern to do various graph operations such as traversal, printing etc.

## Basic Structure and Usage

The structure of this library is very straight forward. There are a set of interfaces that defines the various graph elements such as a Vertex, Edge, Path and so on. At the top level you have the Graph interface through which you create and obtain vertices and edges. You can view the Graph as a factory for the other graph components. Each Edge can be associated with a cost, which is typically something the grap traversal algorithms will take into consideration when trying to find an optimal path trough the graph.

Apart from all the interfaces there is a default implementation and using it is fairly straight forward. The best way to get up and running is to check out the unit tests starting with com.jonasborjesson.graph.impl.GraphTest, which has been copied below for easy reference.

```java

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
        final DefaultVertex v2 = g.addVertex("v1");

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

```

## Add a new search algorithm

This library follows the [visitor pattern](http://en.wikipedia.org/wiki/Visitor_pattern) so in order to add a new anything you will need to understand this software pattern. 

## Contribute

This library is Maven based so you will need to have maven installed. Once installed, simply build it like so:

```
cd modules
mvn clean install
```

and that's it.

Contributions are more than welcome and if you do decide to contribute please make sure to add unit tests and appropriate documentation.


## Other Graph Libraries

There are off course other libraries out there that you should check out. 
* [Stackframe](http://www.stackframe.com/software/PathFinder)
