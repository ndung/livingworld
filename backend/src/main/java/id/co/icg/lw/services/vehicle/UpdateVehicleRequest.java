package id.co.icg.lw.services.vehicle;

public class UpdateVehicleRequest extends CreateVehicleRequest {
    private String vehicleId;

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }
}
