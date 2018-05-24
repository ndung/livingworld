package id.co.icg.lw.services.vehicle;

import id.co.icg.lw.domain.Vehicle;
import id.co.icg.lw.domain.user.User;

import java.util.List;

public interface VehicleService {
    Vehicle create(CreateVehicleRequest request) throws Exception;

    Vehicle update(UpdateVehicleRequest request) throws Exception;

    boolean delete(String vehicleId) throws Exception;

    List<Vehicle> getAll() throws Exception;

    List<Vehicle> findByUser(String userId) throws Exception;
}
