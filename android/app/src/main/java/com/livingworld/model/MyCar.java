package com.livingworld.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dizzay on 1/9/2018.
 */

public class MyCar {

    @SerializedName("vehicleId")
    private String vehicleId;

    @SerializedName("vehicleType")
    private String name;
//    @SerializedName("vehicleId")
    private String plat;
    @SerializedName("vehicleColor")
    private String color;

    public String getId() {
        return vehicleId;
    }

    public void setId(String id) {
        this.vehicleId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlat() {
        return plat;
    }

    public void setPlat(String plat) {
        this.plat = plat;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
