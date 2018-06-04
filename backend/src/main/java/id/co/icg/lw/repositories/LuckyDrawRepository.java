package id.co.icg.lw.repositories;

import id.co.icg.lw.domain.transaction.LuckyDraw;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface LuckyDrawRepository extends JpaRepository<LuckyDraw, Long> {
}
