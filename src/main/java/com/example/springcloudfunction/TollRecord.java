package com.example.springcloudfunction;

public class TollRecord {

    private String stationId;
    private String licensePlate;
    private String timeStamp;

    public TollRecord(String stationId, String licensePlate, String timeStamp) {
        this.stationId = stationId;
        this.licensePlate = licensePlate;
        this.timeStamp = timeStamp;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
