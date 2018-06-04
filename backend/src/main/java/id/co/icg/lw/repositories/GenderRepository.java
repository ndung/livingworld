package id.co.icg.lw.repositories;

import id.co.icg.lw.domain.master.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface GenderRepository extends JpaRepository<Gender, String> {
}
