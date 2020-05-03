package ru.arkanar.graphlib.impl;

import ru.arkanar.graphlib.Edge;

public class EdgeImpl implements Edge {

    private final String fromId;
    private final String toId;

    public EdgeImpl(String fromId, String toId)
    {
        this.fromId = fromId;
        this.toId = toId;
    }

    @Override
    public String getFromId() {
        return fromId;
    }

    @Override
    public String getToId() {
        return toId;
    }

    @Override
    public String toString() {
        return String.format("From: %s - To: %s", fromId, toId);
    }
}
