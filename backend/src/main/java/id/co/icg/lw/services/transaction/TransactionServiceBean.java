package id.co.icg.lw.services.transaction;

import org.springframework.stereotype.Service;

@Service
public class TransactionServiceBean implements TransactionService {
    @Override
    public long getBalanceToday(String userId) {
        return 0;
    }

    @Override
    public long getBalanceThisMonth(String userId) {
        return 0;
    }
}
