package ru.arkanar.graphlib.impl;

import ru.arkanar.graphlib.Edge;
import ru.arkanar.graphlib.Vertex;

import java.util.List;

public class VertexImpl<T> implements Vertex<T> {

    private final String id;
    private final T object;

    public VertexImpl(String id, T object) {
        this.id = id;
        this.object = object;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public T getObject() {
        return object;
    }

}
