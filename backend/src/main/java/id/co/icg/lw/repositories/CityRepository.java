package id.co.icg.lw.repositories;

import id.co.icg.lw.domain.master.City;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface CityRepository extends JpaRepository<City, String> {
}
