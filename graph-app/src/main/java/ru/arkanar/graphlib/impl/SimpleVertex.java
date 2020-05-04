package ru.arkanar.graphlib.impl;

import ru.arkanar.graphlib.Vertex;

public class SimpleVertex<T> implements Vertex<T> {

    private final String id;
    private final T object;

    public SimpleVertex(String id, T object) {
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
