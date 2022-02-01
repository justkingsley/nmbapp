package zw.co.nmb.nmbapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zw.co.nmb.nmbapp.model.Account;
import zw.co.nmb.nmbapp.model.Transaction;
import zw.co.nmb.nmbapp.model.TransactionType;
import zw.co.nmb.nmbapp.repository.AccountRepository;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    public Account getById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("account with id " + id + " not found"));
    }

    @Override
    public Account getByAccountNumber(Long accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new EntityNotFoundException("account with account number " + accountNumber + " not found"));
    }

    public Account update(Long id, Account account) {
        Account dbAccount = this.getById(id);

        return accountRepository.save(account);
    }

    @Override
    public Account create(Account account) throws Exception {
        try {
            Account dbAccount = accountRepository.save(account);

            return this.getById(dbAccount.getId());
        } catch(Exception e) {
            throw new Exception("error saving account");
        }
    }

    public Account delete(Long id) {
        Account account = this.getById(id);

        accountRepository.delete(account);

        return account;
    }

    @Override
    public void changeBalance(Transaction transaction) throws Exception {
        Account account = this.getByAccountNumber(transaction.getAccountNumber());

        if(transaction.getTransactionType() == TransactionType.DR && !transaction.isReversed() ||
                transaction.getTransactionType() == TransactionType.CR && transaction.isReversed()) {
            // RESULT
            int result = account.getActiveBalance().compareTo(transaction.getAmount());

            BigDecimal newActiveBalance = account.getActiveBalance().subtract(transaction.getAmount());
            BigDecimal newDebitAmount = account.getDebitAmount().add(transaction.getAmount());

            Account updatedAccount = account.toBuilder()
                    .activeBalance(newActiveBalance)
                    .debitAmount(newDebitAmount)
                    .build();

            // Both values are equal
            if(result ==  0) {
                accountRepository.save(updatedAccount);
            // First Value is greater
            } else if(result == 1) {
                accountRepository.save(updatedAccount);
            // Second value is greater
            } else if(result == -1) {
                throw new Exception("insufficient funds");
            }

        } else if(transaction.getTransactionType() == TransactionType.DR && transaction.isReversed() ||
                transaction.getTransactionType() == TransactionType.CR && !transaction.isReversed()) {
            BigDecimal newActiveBalance = account.getActiveBalance().add(transaction.getAmount());
            BigDecimal newCreditAmount = account.getCreditAmount().add(transaction.getAmount());

            Account updatedAccount = account.toBuilder()
                    .activeBalance(newActiveBalance)
                    .creditAmount(newCreditAmount)
                    .build();

            accountRepository.save(updatedAccount);
        }
    }

}
