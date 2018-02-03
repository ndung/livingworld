package id.co.icg.lw.repositories;

import id.co.icg.lw.domain.user.Member;
import id.co.icg.lw.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByUser(User user);
}
