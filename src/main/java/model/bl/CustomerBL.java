package model.bl;

import controller.exceptions.customer.DuplicateCustomerNameException;
import controller.exceptions.customer.NoCustomerFoundException;
import lombok.Getter;
import model.da.CustomerDA;
import model.entity.Customer;
import model.tools.CRUD;
import model.tools.Validator;

import java.util.List;

public class CustomerBL implements CRUD<Customer> {
    @Getter
    private static final CustomerBL customerBl = new CustomerBL();

    public CustomerBL() {
    }

    @Override
    public Customer save(Customer customer) throws Exception {
        try (CustomerDA customerDA = new CustomerDA()) {
            if (customerDA.findByCustomerName(customer.getFirstName()) == null || customerDA.findByCustomerFamily(customer.getLastName()) == null) {
                if (Validator.nameValidator(customer.getFirstName()) && Validator.nameValidator(customer.getLastName()) && Validator.phoneNumberValidator(customer.getPhoneNumber())) {
                    customerDA.save(customer);
                    return customer;
                } else {
                    throw new Exception("Invalid Customer Name or Phone Number");
                }
            } else {
                throw new DuplicateCustomerNameException();
            }
        }
    }

    @Override
    public Customer edit(Customer customer) throws Exception {
        try (CustomerDA customerDA = new CustomerDA()) {
            if (customerDA.findById(customer.getCustomerId()) != null) {
                if (Validator.nameValidator(customer.getFirstName()) && Validator.nameValidator(customer.getLastName()) && Validator.phoneNumberValidator(customer.getPhoneNumber())) {
                    customerDA.edit(customer);
                    return customer;
                } else {
                    throw new Exception("Invalid Customer Name or Phone Number");
                }
            } else {
                throw new NoCustomerFoundException();
            }
        }
    }

    @Override
    public Customer remove(int id) throws Exception {
        try (CustomerDA customerDA = new CustomerDA()) {
            Customer customer = customerDA.findById(id);
            if (customer != null) {
                customerDA.remove(id);
                return customer;
            } else {
                throw new NoCustomerFoundException();
            }
        }
    }

    @Override
    public List<Customer> findAll() throws Exception {
        try (CustomerDA customerDA = new CustomerDA()) {
            List<Customer> perosnList = customerDA.findAll();
            if (!perosnList.isEmpty()) {
                return perosnList;
            } else {
                throw new NoCustomerFoundException();
            }
        }
    }

    @Override
    public Customer findById(int id) throws Exception {
        try (CustomerDA customerDA = new CustomerDA()) {
            Customer customer = customerDA.findById(id);
            if (customer != null) {
                return customer;
            } else {
                throw new NoCustomerFoundException();
            }
        }
    }

    public Customer findByCustomerName(String customerName) throws Exception {
        try (CustomerDA customerDA = new CustomerDA()) {
            Customer customer = customerDA.findByCustomerName(customerName);
            if (customer != null) {
                return customer;
            } else {
                throw new NoCustomerFoundException();
            }
        }
    }

    public Customer findByCustomerFamily(String customerFamily) throws Exception {
        try (CustomerDA customerDA = new CustomerDA()) {
            Customer customer = customerDA.findByCustomerFamily(customerFamily);
            if (customer != null) {
                return customer;
            } else {
                throw new NoCustomerFoundException();
            }
        }
    }
}
