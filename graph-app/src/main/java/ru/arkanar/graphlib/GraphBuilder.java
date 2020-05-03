package ru.arkanar.graphlib;

import java.util.function.Function;

/**
 * Создатель графа.
 * @param <T> - тип вершины
 */
public interface GraphBuilder<T> {
    /**
     * Устанавливает тип графа
     */
    GraphBuilder<T> setGraphType(String graphType);

    /**
     * Устанавливает функцию для вычисления идентификатора вершины
     */
    GraphBuilder<T> setIdFunction(Function<T, String> idFunction);

    /**
     * Алгоритм вычисления пути в графе
     */
    GraphBuilder<T> setFindPathStrategy(PathStrategy pathStrategy);

    /**
     * Создание графа.
     */
    Graph<T> build();



}
