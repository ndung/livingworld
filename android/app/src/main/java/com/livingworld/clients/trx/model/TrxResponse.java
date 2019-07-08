package com.livingworld.clients.trx.model;

public class TrxResponse {
    private String id;
    private int points;
    private String[] luckyDraws;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String[] getLuckyDraws() {
        return luckyDraws;
    }

    public void setLuckyDraws(String[] luckyDraws) {
        this.luckyDraws = luckyDraws;
    }
}
