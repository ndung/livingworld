package id.co.icg.lw.services.vehicle;

import id.co.icg.lw.domain.Vehicle;

public class VehicleResponse {

    public VehicleResponse(Vehicle vehicle) {
        this.vehicleId = vehicle.getVehicleId();
        this.vehicleColor = vehicle.getVehicleColor();
        this.vehicleType = vehicle.getVehicleType();
        this.vehicleNumber = vehicle.getVehicleNumber();
    }
    private String vehicleId;
    private String vehicleColor;
    private String vehicleType;
    private String vehicleNumber;

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleColor() {
        return vehicleColor;
    }

    public void setVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
}
