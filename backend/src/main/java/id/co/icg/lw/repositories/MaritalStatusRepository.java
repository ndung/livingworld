package id.co.icg.lw.repositories;

import id.co.icg.lw.domain.master.MaritalStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface MaritalStatusRepository extends JpaRepository<MaritalStatus, String> {
}
