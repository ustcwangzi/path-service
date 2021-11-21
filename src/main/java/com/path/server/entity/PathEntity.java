package com.path.server.entity;

public class PathEntity {
    private int pathId;
    private int sourceCity;
    private int destinyCity;
    private long departureTime;
    private long arriveTime;

    public int getPathId() {
        return pathId;
    }

    public void setPathId(int pathId) {
        this.pathId = pathId;
    }

    public int getSourceCity() {
        return sourceCity;
    }

    public void setSourceCity(int sourceCity) {
        this.sourceCity = sourceCity;
    }

    public int getDestinyCity() {
        return destinyCity;
    }

    public void setDestinyCity(int destinyCity) {
        this.destinyCity = destinyCity;
    }

    public long getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(long departureTime) {
        this.departureTime = departureTime;
    }

    public long getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(long arriveTime) {
        this.arriveTime = arriveTime;
    }
}
