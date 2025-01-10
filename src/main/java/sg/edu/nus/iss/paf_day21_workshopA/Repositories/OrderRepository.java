package sg.edu.nus.iss.paf_day21_workshopA.Repositories;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.paf_day21_workshopA.Models.Order;

@Repository
public class OrderRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Order> getCustomerOrders(int customerId){
        
        SqlRowSet rs = jdbcTemplate.queryForRowSet(Queries.SQL_GET_CUSTOMER_ORDERS, customerId);

        List<Order> orders = new LinkedList<>();

        while (rs.next()){
            orders.add(Order.toOrder(rs));
        }

        return orders;
        
    }
}

