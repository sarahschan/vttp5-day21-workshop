package sg.edu.nus.iss.paf_day21_workshopA.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.paf_day21_workshopA.Models.Customer;
import sg.edu.nus.iss.paf_day21_workshopA.Repositories.CustomerRepository;

@Service
public class CustomerService {
    
    @Autowired
    CustomerRepository customerRepository;


    public List<Customer> getAllCustomers(int limit, int offset){
        return customerRepository.getAllCustomers(limit, offset);
    }
}
