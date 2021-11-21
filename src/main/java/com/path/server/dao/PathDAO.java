package com.path.server.dao;

import com.path.server.entity.PathEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Repository
public class PathDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(PathDAO.class);

    private static final String SQL_INSERT = "INSERT INTO t_path(source_city, destiny_city, departure_time, arrive_time) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE t_path SET source_city = ?, destiny_city = ?, departure_time = ?, arrive_time = ? WHERE path_id = ";
    private static final String SQL_SELECT_ID = "SELECT path_id FROM t_path WHERE source_city = ";
    private static final String SQL_SELECT_ALL = "SELECT path_id, source_city, destiny_city, departure_time, arrive_time FROM t_path WHERE source_city = ";
    @Resource
    private JdbcTemplate jdbcTemplate;

    public boolean add(PathEntity entity) {
        if (entity.getPathId() > 0) {
            return update(entity);
        }
        try {
            jdbcTemplate.update(SQL_INSERT, entity.getSourceCity(), entity.getDestinyCity(), entity.getDepartureTime(), entity.getArriveTime());
            return true;
        } catch (Exception e) {
            LOGGER.warn("update ex", e);
            return false;
        }
    }

    public boolean update(PathEntity entity) {
        try {
            jdbcTemplate.update(SQL_UPDATE + entity.getPathId(), entity.getSourceCity(), entity.getDestinyCity(), entity.getDepartureTime(), entity.getArriveTime());
            return true;
        } catch (Exception e) {
            LOGGER.warn("update ex", e);
            return false;
        }
    }

    public Integer query(int sourceCity, int destinyCity) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ID + sourceCity + " AND destiny_city = " + destinyCity, Integer.class);
        } catch (Exception e) {
            return null;
        }
    }

    public List<PathEntity> query(int sourceCity) {
        try {
            return jdbcTemplate.query(SQL_SELECT_ALL + sourceCity, new PathEntityMapper());
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    private static class PathEntityMapper implements RowMapper<PathEntity> {

        @Override
        public PathEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            PathEntity entity = new PathEntity();
            entity.setPathId(rs.getInt("path_id"));
            entity.setSourceCity(rs.getInt("source_city"));
            entity.setDestinyCity(rs.getInt("destiny_city"));
            entity.setDepartureTime(rs.getLong("departure_time"));
            entity.setArriveTime(rs.getLong("arrive_time"));
            return entity;
        }
    }
}
