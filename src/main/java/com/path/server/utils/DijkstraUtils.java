package com.path.server.utils;

import javafx.util.Pair;

import java.util.*;

public class DijkstraUtils {
    public static Map<Integer, Long> dijkstra(Map<Integer, Map<Integer, Long>> graph, int start) {
        Map<Integer, Long> distMap = new HashMap<>();
        distMap.put(start, 0L);
        Queue<Pair<Integer, Long>> queue = new PriorityQueue<>(Comparator.comparingLong(Pair::getValue));
        queue.offer(new Pair<>(start, 0L));
        while (!queue.isEmpty()) {
            Pair<Integer, Long> edge = queue.poll();
            Map<Integer, Long> neighbors = graph.get(edge.getKey());
            distMap.putIfAbsent(edge.getKey(), Long.MAX_VALUE);
            if (distMap.get(edge.getKey()) < edge.getValue() || neighbors == null) {
                continue;
            }

            for (Map.Entry<Integer, Long> entry : neighbors.entrySet()) {
                distMap.putIfAbsent(entry.getKey(), Long.MAX_VALUE);
                if (distMap.get(entry.getKey()) > distMap.get(edge.getKey()) + entry.getValue()) {
                    distMap.put(entry.getKey(), distMap.get(edge.getKey()) + entry.getValue());
                    queue.offer(new Pair<>(entry.getKey(), distMap.get(entry.getKey())));
                }
            }
        }
        distMap.remove(start);
        return distMap;
    }
}
