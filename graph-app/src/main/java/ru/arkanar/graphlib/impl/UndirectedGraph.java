package ru.arkanar.graphlib.impl;

import ru.arkanar.graphlib.Edge;
import ru.arkanar.graphlib.Graph;
import ru.arkanar.graphlib.PathStrategy;
import ru.arkanar.graphlib.Vertex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class UndirectedGraph<T> implements Graph<T> {

    /**
     * Вершины графа
     */
    private Map<String, Vertex<T>> vertexMap = new HashMap<>();

    /**
     * Ребра
     */
    private Map<String, List<Edge>> edgeMap = new HashMap<>();

    private final Function<T, String> idFunction;

    private final PathStrategy pathStrategy;

    public UndirectedGraph(Function<T, String> idFunction, PathStrategy pathStrategy) {
        this.pathStrategy = pathStrategy;
        this.idFunction = idFunction;
    }

    @Override
    public void addVertex(T vertex) {
        vertexMap.put(idFunction.apply(vertex), new VertexImpl<>(idFunction.apply(vertex), vertex));
    }

    @Override
    public void addEdge(T firstVertex, T secondVertex) {
        String first = idFunction.apply(firstVertex);
        String second = idFunction.apply(secondVertex);

        edgeMap.putIfAbsent(first, new ArrayList<>());
        edgeMap.putIfAbsent(second, new ArrayList<>());

        edgeMap.get(first).add(new EdgeImpl(first, second));
        edgeMap.get(second).add(new EdgeImpl(second, first));
    }

    @Override
    public List<Edge> getPath(T fromVertex, T toVertex) {
        return pathStrategy.findPath(
                vertexMap.get(idFunction.apply(fromVertex)),
                vertexMap.get(idFunction.apply(toVertex)),
                vertexMap,
                edgeMap
        );
    }
}
