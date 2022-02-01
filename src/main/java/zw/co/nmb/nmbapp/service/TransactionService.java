package zw.co.nmb.nmbapp.service;

import zw.co.nmb.nmbapp.model.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction create(Transaction transaction) throws Exception;
    List<Transaction> getAll();
    Transaction getById(Long id);
    Transaction reverse(Long id);
    List<Transaction> getAllByAccountNumber(Long accountNumber);
}
