package id.co.icg.lw.repositories;

import id.co.icg.lw.domain.CurrentOfferImage;
import id.co.icg.lw.domain.user.Member;
import id.co.icg.lw.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface CurrentOfferImageRepository extends JpaRepository<CurrentOfferImage, String> {

}
