package zw.co.nmb.nmbapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.nmb.nmbapp.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
