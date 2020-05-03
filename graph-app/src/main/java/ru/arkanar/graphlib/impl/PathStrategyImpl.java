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

public class PathStrategyImpl implements PathStrategy {
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


        boolean find = false;

        //проходим в ширину, пока не найдем конечную вершину или не пройдем по всем ребрам
        while (!nextWave.isEmpty() && !find) {
            nextWave = new ArrayList<>();
            for (String id : wave.get(wave.size() - 1)) {
                if (edgeMap.get(id) != null) {
                    nextWave.addAll(edgeMap.get(id).stream().map(Edge::getToId)
                            .filter(s -> !visitedVertes.contains(s)).collect(Collectors.toList()));
                    visitedVertes.addAll(nextWave);
                }
            }
            wave.add(nextWave);

            find = nextWave.stream().anyMatch(s -> s.equals(to.getId()));
        }

        //строим обратный путь, если нашли
        if (find) {
            List<Edge> path = new ArrayList<>();
            String lastFoundId = to.getId();
            for (int i = wave.size() - 2; i >= 0; i--) {
                boolean nextFounded = false;
                for (String id : wave.get(i)) {
                    if (edgeMap.get(id) != null) {
                        for (Edge edge : edgeMap.get(id)) {
                            if (edge.getToId().equals(lastFoundId)) {
                                lastFoundId = id;
                                nextFounded = true;
                                path.add(edge);
                                break;
                            }
                        }
                    }
                    if (nextFounded) {
                        break;
                    }
                }
            }
            return path;
        } else {
            return null;
        }
    }

}

