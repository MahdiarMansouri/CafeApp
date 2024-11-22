package model.bl;

import controller.exceptions.customer.DuplicateCustomerNameException;
import controller.exceptions.customer.NoCustomerFoundException;
import lombok.Getter;
import model.da.CustomerDA;
import model.entity.Customer;
import model.tools.CRUD;

import java.util.List;

public class CustomerBL implements CRUD<Customer> {
    @Getter
    private static final CustomerBL customerBl = new CustomerBL();

    private CustomerBL() {
    }

    @Override
    public Customer save(Customer customer) throws Exception {
        try (CustomerDA CustomerDA = new CustomerDA()) {
            if (CustomerDA.findByCustomerName(customer.getFirstName()) == null || CustomerDA.findByCustomerFamily(customer.getLastName()) == null) {
                CustomerDA.save(customer);
                return customer;
            }
            throw new DuplicateCustomerNameException();
        }
    }

    @Override
    public Customer edit(Customer customer) throws Exception {
        try (CustomerDA CustomerDA = new CustomerDA()) {
            if (CustomerDA.findById(customer.getCustomerId()) != null) {
                CustomerDA.edit(customer);
                return customer;
            } else {
                throw new NoCustomerFoundException();
            }
        }
    }

    @Override
    public Customer remove(int id) throws Exception {
        try (CustomerDA CustomerDA = new CustomerDA()) {
            Customer customer = CustomerDA.findById(id);
            if (customer != null) {
                CustomerDA.remove(id);
                return customer;
            } else {
                throw new NoCustomerFoundException();
            }
        }
    }

    @Override
    public List<Customer> findAll() throws Exception {
        try (CustomerDA CustomerDA = new CustomerDA()) {
            List<Customer> perosnList = CustomerDA.findAll();
            if (!perosnList.isEmpty()) {
                return perosnList;
            } else {
                throw new NoCustomerFoundException();
            }
        }
    }

    @Override
    public Customer findById(int id) throws Exception {
        try (CustomerDA CustomerDA = new CustomerDA()) {
            Customer customer = CustomerDA.findById(id);
            if (customer != null) {
                return customer;
            } else {
                throw new NoCustomerFoundException();
            }
        }
    }

    public Customer findByCustomerName(String customerName) throws Exception {
        try (CustomerDA CustomerDA = new CustomerDA()) {
            Customer customer = CustomerDA.findByCustomerName(customerName);
            if (customer != null) {
                return customer;
            } else {
                throw new NoCustomerFoundException();
            }
        }
    }

    public Customer findByCustomerFamily(String customerFamily) throws Exception {
        try (CustomerDA CustomerDA = new CustomerDA()) {
            Customer customer = CustomerDA.findByCustomerFamily(customerFamily);
            if (customer != null) {
                return customer;
            } else {
                throw new NoCustomerFoundException();
            }
        }
    }
}
