package zw.co.nmb.nmbapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.nmb.nmbapp.model.Account;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    public Optional<Account> findByAccountNumber(Long accountNumber);
}
