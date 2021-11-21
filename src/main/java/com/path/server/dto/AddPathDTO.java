package com.path.server.dto;

public class AddPathDTO {
    private Integer sourceCity;
    private Integer destinyCity;
    private Long departureTime;
    private Long arriveTime;

    public Integer getSourceCity() {
        return sourceCity;
    }

    public void setSourceCity(Integer sourceCity) {
        this.sourceCity = sourceCity;
    }

    public Integer getDestinyCity() {
        return destinyCity;
    }

    public void setDestinyCity(Integer destinyCity) {
        this.destinyCity = destinyCity;
    }

    public Long getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Long departureTime) {
        this.departureTime = departureTime;
    }

    public Long getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Long arriveTime) {
        this.arriveTime = arriveTime;
    }

    @Override
    public String toString() {
        return "AddPathDTO{" +
                "sourceCity=" + sourceCity +
                ", destinyCity=" + destinyCity +
                ", departureTime=" + departureTime +
                ", arriveTime=" + arriveTime +
                '}';
    }
}
