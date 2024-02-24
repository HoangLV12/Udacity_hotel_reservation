package service;

import model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {

    private static CustomerService customerService;
    private final Map<String, Customer> listCustomer = new HashMap<>();

    private CustomerService() {

    }

    public static CustomerService getInstance() {
        if (customerService == null) {
            customerService = new CustomerService();
        }
        return customerService;
    }

    public void addCustomer(String email, String firstName, String lastname) {
        try {
            if (listCustomer.containsKey(email)) {
                throw new IllegalArgumentException("Email already exists");
            }
            listCustomer.put(email, new Customer(firstName, lastname, email));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public Customer getCustomer(String customerEmail) {
        return listCustomer.get(customerEmail);
    }

    public Collection<Customer> getAllCustomer() {
        return listCustomer.values();
    }
}
