package id.co.icg.lw.repositories;

import id.co.icg.lw.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends JpaRepository<User, String> {
    @Query("select u from User u where u.id = :id")
    User findOne(@Param("id") String id);

    User findByCardNumber(String cardNumber);

    @Query("select u from User u where u.id = :id and u.password = :password")
    User findOne(@Param("id") String id, @Param("password") String password);
}
