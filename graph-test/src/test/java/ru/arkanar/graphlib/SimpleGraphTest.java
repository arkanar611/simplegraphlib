package ru.arkanar.graphlib;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.arkanar.graphlib.impl.GraphBuilderImpl;
import ru.arkanar.graphlib.impl.WaveAlgorithm;

import java.util.List;

public class SimpleGraphTest {
    private TestObjectVertex first;
    private TestObjectVertex second;
    private TestObjectVertex third;
    private TestObjectVertex fourth;

    @BeforeEach
    public void initBeforeEach(){
        first = new TestObjectVertex("Vertex 1");
        second = new TestObjectVertex("Vertex 2");
        third = new TestObjectVertex("Vertex 3");
        fourth = new TestObjectVertex("Vertex 4");
    }

    @Test
    public void checkDirectedGraph(){
        GraphBuilder<TestObjectVertex> graphBuilder = new GraphBuilderImpl<>();
        Graph<TestObjectVertex> directedGraph = graphBuilder
                .setGraphType(GraphType.DIRECTED)
                .setFindPathStrategy(new WaveAlgorithm())
                .setIdFunction(TestObjectVertex::getName)
                .build();

        directedGraph.addVertex(first);
        directedGraph.addVertex(second);
        directedGraph.addVertex(third);
        directedGraph.addVertex(fourth);

        directedGraph.addEdge(first, second);
        directedGraph.addEdge(second, third);
        directedGraph.addEdge(third, fourth);
        directedGraph.addEdge(fourth, second);

        List<Edge> list = directedGraph.getPath(first, fourth);

        Assertions.assertNotNull(list);
        Assertions.assertEquals("Vertex 3", list.get(0).getFromId());
        Assertions.assertEquals("Vertex 2", list.get(1).getFromId());
        Assertions.assertEquals("Vertex 1", list.get(2).getFromId());

    }

    @Test
    public void checkUnDirectedGraph(){
        GraphBuilder<TestObjectVertex> graphBuilder = new GraphBuilderImpl<>();
        Graph<TestObjectVertex> unDirectedGraph = graphBuilder
                .setGraphType(GraphType.UNDIRECTED)
                .setFindPathStrategy(new WaveAlgorithm())
                .setIdFunction(TestObjectVertex::getName)
                .build();

        unDirectedGraph.addVertex(first);
        unDirectedGraph.addVertex(second);
        unDirectedGraph.addVertex(third);
        unDirectedGraph.addVertex(fourth);

        unDirectedGraph.addEdge(first, second);
        unDirectedGraph.addEdge(first, third);
        unDirectedGraph.addEdge(third, fourth);
        unDirectedGraph.addEdge(fourth, second);

        List<Edge> list = unDirectedGraph.getPath(first, fourth);

        Assertions.assertNotNull(list);
        Assertions.assertEquals(2, list.size());
    }
}
