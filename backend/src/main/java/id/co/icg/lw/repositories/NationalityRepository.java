package id.co.icg.lw.repositories;

import id.co.icg.lw.domain.master.Nationality;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface NationalityRepository extends JpaRepository<Nationality, String> {
}
