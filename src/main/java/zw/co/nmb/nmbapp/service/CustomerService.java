package zw.co.nmb.nmbapp.service;

import zw.co.nmb.nmbapp.model.Account;
import zw.co.nmb.nmbapp.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer create(Customer customer);
    List<Customer> getAll();
    Customer getById(Long id);
    Customer delete(Long id);
    Customer update(Long id, Customer customer);
}
