package id.co.icg.lw.controllers.api;

import id.co.icg.lw.domain.Response;
import id.co.icg.lw.parking.Transaction;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ParkingController extends BaseController {

    Logger logger = Logger.getLogger(ParkingController.class);

    @Autowired
    JdbcTemplate parkingJdbcTemplate;

    @RequestMapping(value = "/parking/inquiry", method = RequestMethod.POST)
    public ResponseEntity<Response> checkTicket(@RequestBody String ticketNumber) {
        String sql = "select * from tbl_trans where notran = ? order by datetimein desc limit 1";
        Object[] param = new Object[]{ticketNumber};
        try {
            Transaction transaction = parkingJdbcTemplate.queryForObject(sql, param, new RowMapper<Transaction>() {
                @Override
                public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Transaction trx = new Transaction();

                    return trx;
                }
            });

        }catch(Exception ex){
            logger.error("error", ex);
        }
        int result = userService.checkCardNumber(cardNumber);
        return getHttpStatus(new Response(result));
    }
}
