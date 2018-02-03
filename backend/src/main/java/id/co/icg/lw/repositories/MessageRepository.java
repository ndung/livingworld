package id.co.icg.lw.repositories;

import id.co.icg.lw.domain.Message;
import id.co.icg.lw.domain.user.User;
import id.co.icg.lw.services.message.MessageResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("select new id.co.icg.lw.services.message.MessageResponse(m) from Message m where m.receiver is null or m.receiver = :receiver order by m.createAt desc ")
    List<MessageResponse> findAllByReceiverOrderByCreateAtDesc(@Param("receiver") User receiver, Pageable pageable);

    @Query("select new id.co.icg.lw.services.message.MessageResponse(m) from Message m where m.receiver is null order by m.createAt desc ")
    List<MessageResponse> findAllByReceiver(Pageable pageable);

}
