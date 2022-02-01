package zw.co.nmb.nmbapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import zw.co.nmb.nmbapp.model.Account;
import zw.co.nmb.nmbapp.repository.AccountRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Arrays;
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
}
