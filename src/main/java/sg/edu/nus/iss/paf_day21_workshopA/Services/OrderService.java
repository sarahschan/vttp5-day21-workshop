package sg.edu.nus.iss.paf_day21_workshopA.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.paf_day21_workshopA.Models.Order;
import sg.edu.nus.iss.paf_day21_workshopA.Repositories.OrderRepository;

@Service
public class OrderService {
    
    @Autowired
    OrderRepository orderRepository;


    public List<Order> getCustomerOrders(int customerId){
        return orderRepository.getCustomerOrders(customerId);
    }
}
