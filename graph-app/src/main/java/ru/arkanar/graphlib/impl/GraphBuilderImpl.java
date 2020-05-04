package ru.arkanar.graphlib.impl;

import ru.arkanar.graphlib.Graph;
import ru.arkanar.graphlib.GraphBuilder;
import ru.arkanar.graphlib.GraphType;
import ru.arkanar.graphlib.PathStrategy;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class GraphBuilderImpl<T> implements GraphBuilder<T> {

    /**
     * Список графов, которые можно построить.
     */
    private final Map<String, BiFunction<Function<T, String>, PathStrategy, Graph<T>>> GRAPH_SUPPLIER;

    /**
     * Если не задали, то строим направленный граф
     */
    private String graphType = GraphType.DIRECTED;

    /**
     * По умолчанию для уникального обозначения вершины берем hashCode
     */
    private Function<T, String> idFunction = t -> String.valueOf(t.hashCode());

    /**
     * Алгоритм по которому будет искаться кратчайший путь
     */
    private PathStrategy pathStrategy = new WaveAlgorithm();

    /**
     * Конструктор.
     */
    public GraphBuilderImpl() {
        final Map<String, BiFunction<Function<T, String>, PathStrategy, Graph<T>>> graphs = new HashMap<>();
        graphs.put(GraphType.DIRECTED, DirectedGraph::new);
        graphs.put(GraphType.UNDIRECTED, UndirectedGraph::new);
        GRAPH_SUPPLIER = Collections.unmodifiableMap(graphs);
    }

    @Override
    public GraphBuilder<T> setGraphType(String graphType) {
        this.graphType = graphType;
        return this;
    }

    @Override
    public GraphBuilder<T> setIdFunction(Function<T, String> idFunction) {
        this.idFunction = idFunction;
        return this;
    }

    @Override
    public GraphBuilder<T> setFindPathStrategy(PathStrategy pathStrategy) {
        this.pathStrategy = pathStrategy;
        return this;
    }

    @Override
    public Graph<T> build() {
        return GRAPH_SUPPLIER.getOrDefault(graphType, (function, strategy) -> {
            throw new RuntimeException("Unexpected graph type:" + graphType);
        }).apply(idFunction, pathStrategy);
    }
}
