package zw.co.nmb.nmbapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zw.co.nmb.nmbapp.model.Customer;
import zw.co.nmb.nmbapp.repository.CustomerRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CustomerServiceImpl implements  CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer create(Customer customer) {

        Customer dbCustomer = customerRepository.save(customer);

        return customerRepository.findById(dbCustomer.getId()).get();
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("customer with id " + id + " not found"));
    }

    @Override
    public Customer delete(Long id) {
        Customer customer = this.getById(id);

        customerRepository.delete(customer);

        return customer;
    }

    @Override
    public Customer update(Long id, Customer customer) {

        Customer dbCustomer = this.getById(id);

        return customerRepository.save(customer);
    }
}
