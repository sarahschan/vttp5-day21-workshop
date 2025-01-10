package sg.edu.nus.iss.paf_day21_workshopA.Repositories;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.paf_day21_workshopA.Models.Customer;

@Repository
public class CustomerRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Customer> getAllCustomers(int limit, int offset){
        
        SqlRowSet rs = jdbcTemplate.queryForRowSet(Queries.SQL_GET_ALL_CUSTOMERS_LIMIT_OFFSET, limit, offset);

        List<Customer> customers = new LinkedList<>();

        while (rs.next()){
            customers.add(Customer.toCustomer(rs));
        }

        return customers;

    }
}
