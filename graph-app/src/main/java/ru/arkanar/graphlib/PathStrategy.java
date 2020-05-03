package ru.arkanar.graphlib;

import java.util.List;
import java.util.Map;

/**
 * Интерфейс обходов графа.
 */
public interface PathStrategy {
    /**
     * Метод поиска пути в графе.
     * @param from откуда
     * @param to куда
     * @param map вершины графа.
     * @param edgeMap ребра графа
     * @param <T> Пользовательский тип вершины.
     * @return список ребер.
     */
    <T> List<Edge> findPath(Vertex<T> from, Vertex<T> to, Map<String, Vertex<T>> map, Map<String, List<Edge>> edgeMap);
}
