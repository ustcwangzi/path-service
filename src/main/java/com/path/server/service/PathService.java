package com.path.server.service;

import com.path.server.dao.PathDAO;
import com.path.server.dto.AddPathDTO;
import com.path.server.entity.PathEntity;
import com.path.server.utils.DijkstraUtils;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

@Service
public class PathService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PathService.class);
    @Resource
    private PathDAO pathDAO;

    public boolean addPath(AddPathDTO request) {
        try {
            PathEntity entity = build(request);
            Integer pathId = pathDAO.query(request.getSourceCity(), request.getDestinyCity());
            if (pathId != null && pathId > 0) {
                entity.setPathId(pathId);
            }
            return pathDAO.add(entity);
        } catch (Exception e) {
            LOGGER.error("addPath ex, request:{}", request, e);
        }
        return false;
    }

    public Map<String, List<String>> query(int sourceCity) {
        try {
            List<PathEntity> entityList = pathDAO.query(sourceCity);
            if (CollectionUtils.isEmpty(entityList)) {
                return Collections.emptyMap();
            }
            Map<String, List<String>> result = new HashMap<>();
            result.put("sortByTime", convert(DijkstraUtils.dijkstra(buildWithTime(entityList), sourceCity)));
            result.put("sortByConnection", convert(DijkstraUtils.dijkstra(buildWithoutTime(entityList), sourceCity)));
            return result;
        } catch (Exception e) {
            LOGGER.error("query ex, sourceCity:{}", sourceCity, e);
        }
        return Collections.emptyMap();
    }

    private static Map<Integer, Map<Integer, Long>> buildWithTime(List<PathEntity> entityList) {
        Map<Integer, Map<Integer, Long>> map = new HashMap<>();
        entityList.forEach(entity -> {
            map.putIfAbsent(entity.getSourceCity(), new HashMap<>());
            map.get(entity.getSourceCity()).put(entity.getDestinyCity(), entity.getArriveTime() - entity.getDepartureTime());
        });
        return map;
    }

    private static Map<Integer, Map<Integer, Long>> buildWithoutTime(List<PathEntity> entityList) {
        Map<Integer, Map<Integer, Long>> map = new HashMap<>();
        entityList.forEach(entity -> {
            map.putIfAbsent(entity.getSourceCity(), new HashMap<>());
            map.get(entity.getSourceCity()).put(entity.getDestinyCity(), 1L);
        });
        return map;
    }

    private static List<String> convert(Map<Integer, Long> map) {
        List<Pair<Integer, Long>> sortList = new ArrayList<>(map.size());
        map.forEach((integer, aLong) -> sortList.add(new Pair<>(integer, aLong)));
        sortList.sort(Comparator.comparingLong(Pair::getValue));
        List<String> result = new ArrayList<>(sortList.size());
        sortList.forEach(pair -> result.add(pair.getKey() + " : " + pair.getValue()));
        return result;
    }

    private static PathEntity build(AddPathDTO request) {
        PathEntity entity = new PathEntity();
        entity.setSourceCity(request.getSourceCity());
        entity.setDestinyCity(request.getDestinyCity());
        entity.setDepartureTime(request.getDepartureTime());
        entity.setArriveTime(request.getArriveTime());
        return entity;
    }
}
