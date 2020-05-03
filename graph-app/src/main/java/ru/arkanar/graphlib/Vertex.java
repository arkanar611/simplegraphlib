package ru.arkanar.graphlib;

/**
 * Вершина графа.
 * @param <T> пользовательский тип.
 */
public interface Vertex<T> {
    /**
     * идентификатор вершины.
     * @return id
     */
    String getId();

    /**
     * Объект пользователя.
     * @return объект.
     */
    T getObject();
}
