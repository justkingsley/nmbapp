package zw.co.nmb.nmbapp.service;

import zw.co.nmb.nmbapp.model.Account;
import zw.co.nmb.nmbapp.model.Transaction;

import java.util.List;

public interface AccountService {
    List<Account> getAll();
    Account getById(Long id);
    Account getByAccountNumber(Long accountNumber);
    Account update(Long id, Account account);
    Account create(Account account) throws Exception;
    Account delete(Long id);
    void changeBalance(Transaction transaction) throws Exception;
}
