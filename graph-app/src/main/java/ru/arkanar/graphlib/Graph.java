package ru.arkanar.graphlib;

import java.util.List;

/**
 * Graph interface.
 *
 * @param <T> user object type
 */
public interface Graph<T> {
    /**
     * Добавление вершины в граф.
     * @param vertex вершина
     */
    void addVertex(T vertex);

    /**
     * Добавление ребра в граф.
     * @param firstVertex начальная вершина
     * @param secondVertex конечная вершина
     */
    void addEdge(T firstVertex, T secondVertex);

    /**
     * Поиск пути.
     * @param fromVertex вершина из которой ищется путь
     * @param toVertex вершина до которой ищется путь
     */
    List<Edge> getPath(T fromVertex, T toVertex);

}
