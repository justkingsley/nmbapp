package zw.co.nmb.nmbapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import zw.co.nmb.nmbapp.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
