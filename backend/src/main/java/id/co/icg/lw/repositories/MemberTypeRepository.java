package id.co.icg.lw.repositories;

import id.co.icg.lw.domain.master.MemberType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberTypeRepository extends JpaRepository<MemberType, String> {
}
