package id.co.icg.lw.repositories;

import id.co.icg.lw.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("select e from Event e where e.active = 'Y' and e.startDate < current_date() and e.endDate > current_date() order by e.startDate desc")
    List<Event> findAllActive();
}
