package ru.arkanar.graphlib.impl;

import ru.arkanar.graphlib.Edge;
import ru.arkanar.graphlib.PathStrategy;
import ru.arkanar.graphlib.Vertex;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Реализация алгоритма волны.
 */
public class WaveAlgorithm implements PathStrategy {
    @Override
    public <T> List<Edge> findPath(Vertex<T> from,
                                   Vertex<T> to,
                                   Map<String, Vertex<T>> vertexMap,
                                   Map<String, List<Edge>> edgeMap
    ) {
        Set<String> visitedVertes = new HashSet<>();

        List<List<String>> wave = new ArrayList<>();
        List<String> nextWave = new ArrayList<>();
        nextWave.add(from.getId());

        wave.add(nextWave);
        visitedVertes.add(from.getId());


        boolean isFound = false;

        //проходим в ширину, пока не найдем конечную вершину или не пройдем по всем ребрам
        while (!nextWave.isEmpty() && !isFound) {
            nextWave = new ArrayList<>();
            for (String id : wave.get(wave.size() - 1)) {
                if (edgeMap.get(id) != null) {
                    nextWave.addAll(edgeMap.get(id).stream().map(Edge::getToId)
                            .filter(s -> !visitedVertes.contains(s)).collect(Collectors.toList()));
                    visitedVertes.addAll(nextWave);
                }
            }
            wave.add(nextWave);

            isFound = nextWave.stream().anyMatch(s -> s.equals(to.getId()));
        }

        //строим обратный путь, если нашли
        if (isFound) {
            return reversePath(to, edgeMap, wave);
        } else {
            return null;
        }
    }

    //Пстроение обратного пути
    private <T> List<Edge> reversePath(Vertex<T> to, Map<String, List<Edge>> edgeMap, List<List<String>> wave) {
        List<Edge> path = new ArrayList<>();

        String lastFoundId = to.getId();
        int lastFrontOfWave = wave.size() - 2;

        for (int i = lastFrontOfWave; i >= 0; i--) {
            boolean vertexFromFound = false;
            for (String id : wave.get(i)) {
                if (edgeMap.get(id) != null) {
                    for (Edge edge : edgeMap.get(id)) {
                        if (edge.getToId().equals(lastFoundId)) {
                            lastFoundId = id;
                            vertexFromFound = true;
                            path.add(edge);
                            break;
                        }
                    }
                }
                if (vertexFromFound) {
                    break;
                }
            }
        }
        return path;
    }

}

