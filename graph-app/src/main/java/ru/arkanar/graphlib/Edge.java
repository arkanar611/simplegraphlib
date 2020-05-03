package ru.arkanar.graphlib;

/**
 * Ребро.
 */
public interface Edge {
    /**
     * id начальной вершины
     * @return id
     */
    String getFromId();

    /**
     * id конечной вершины
     * @return
     */
    String getToId();
}
