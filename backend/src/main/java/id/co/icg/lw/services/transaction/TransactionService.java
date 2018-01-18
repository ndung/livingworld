package id.co.icg.lw.services.transaction;

public interface TransactionService {
    long getBalanceToday(String userId);
    long getBalanceThisMonth(String userId);
}
