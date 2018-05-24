package id.co.icg.lw.repositories;

import id.co.icg.lw.domain.Vehicle;
import id.co.icg.lw.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface VehicleRepository extends JpaRepository<Vehicle, String> {

    Vehicle findVehicleByVehicleNumber(String vehicleNumber);

    List<Vehicle> findAllByUser(User user);

}
