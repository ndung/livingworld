package id.co.icg.lw.services.vehicle;

import id.co.icg.lw.domain.Vehicle;
import id.co.icg.lw.repositories.VehicleRepository;
import id.co.icg.lw.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserService userService;

    @Override
    public Vehicle create(CreateVehicleRequest request) throws Exception {
        if (vehicleRepository.findVehicleByVehicleNumber(request.getVehicleNumber()) != null) {
            throw new Exception("Plat number is already registered");
        }

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(UUID.randomUUID().toString());
        vehicle.setUser(userService.findOne(request.getUserId()));
        vehicle.setVehicleColor(request.getVehicleColor());
        vehicle.setVehicleNumber(request.getVehicleNumber());
        vehicle.setVehicleType(request.getVehicleType());
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle update(UpdateVehicleRequest request) throws Exception {
        Vehicle vehicle = findOne(request.getVehicleId());
        vehicle.setVehicleColor(request.getVehicleColor());
        vehicle.setVehicleType(request.getVehicleType());
        return vehicleRepository.save(vehicle);
    }

    @Override
    public boolean delete(String vehicleId) throws Exception {
        Vehicle vehicle = findOne(vehicleId);
        vehicle.setUser(null);
        vehicleRepository.delete(vehicle);
        return true;
    }

    @Override
    public List<Vehicle> getAll() throws Exception {
        return vehicleRepository.findAll();
    }

    @Override
    public List<Vehicle> findByUser(String userId) throws Exception {
        return vehicleRepository.findAllByUser(userService.findOne(userId));
    }

    private Vehicle findOne(String vehicleId) throws Exception{
        Vehicle vehicle = vehicleRepository.findOne(vehicleId);
        if (vehicle == null) {
            throw new Exception("Vehicle is not found");
        }

        return vehicle;
    }
}
