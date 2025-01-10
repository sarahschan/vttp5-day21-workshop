package sg.edu.nus.iss.paf_day21_workshopA.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import sg.edu.nus.iss.paf_day21_workshopA.Models.Customer;
import sg.edu.nus.iss.paf_day21_workshopA.Models.Order;
import sg.edu.nus.iss.paf_day21_workshopA.Serializers.CustomerJsonSerializer;
import sg.edu.nus.iss.paf_day21_workshopA.Serializers.OrderJsonSerializer;
import sg.edu.nus.iss.paf_day21_workshopA.Services.CustomerService;
import sg.edu.nus.iss.paf_day21_workshopA.Services.OrderService;


@RestController
@RequestMapping("/api")
public class ApiController {    

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerJsonSerializer customerJsonSerializer;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderJsonSerializer orderJsonSerializer;

    
    @GetMapping(path = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllCustomers(@RequestParam(defaultValue = "0") int offset, @RequestParam(defaultValue = "5") int limit){
        
        try {

            List<Customer> customers = customerService.getAllCustomers(limit, offset);

            if (customers.isEmpty()) {
                
                JsonObject customersEmpty = Json.createObjectBuilder()
                                            .add("message:", "No customers to display")
                                            .build();

                return ResponseEntity.status(404).body(customersEmpty.toString());

            }


            JsonArrayBuilder customerJsonArrayBuilder = Json.createArrayBuilder();

            for (Customer customer : customers) {
                customerJsonArrayBuilder.add(customerJsonSerializer.customerToJson(customer));
            }

            return ResponseEntity.status(200).body(customerJsonArrayBuilder.build().toString());


        } catch (Exception e) {

            JsonObject errorMessage = Json.createObjectBuilder()
                                        .add("Internal Server Error", String.format("%s: %s", e.getCause(), e.getMessage()))
                                        .build();

            return ResponseEntity.status(500).body(errorMessage.toString());
        }

    }


    @GetMapping(path = "/customer/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCustomerById(@PathVariable int customerId){

        try {
            
            Optional<Customer> customerOpt = customerService.getCustomerById(customerId);

            if (customerOpt.isEmpty()) {

                JsonObject customerNotFound = Json.createObjectBuilder()
                                                .add("Cusomter_Not_Found", String.format("Customer with ID %d not found", customerId))
                                                .build();

                return ResponseEntity.status(404).body(customerNotFound.toString());

            }


            Customer customer = customerOpt.get();
            JsonObject customerJson = customerJsonSerializer.customerToJson(customer);

            return ResponseEntity.status(200).body(customerJson.toString());


        } catch (Exception e) {

            JsonObject errorMessage = Json.createObjectBuilder()
                                        .add("Internal Server Error", String.format("%s: %s", e.getCause(), e.getMessage()))
                                        .build();

            return ResponseEntity.status(500).body(errorMessage.toString());

        }
    }


    @GetMapping(path = "/customer/{customerId}/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCustomerOrders(@PathVariable int customerId){

        try {
            
            Optional<Customer> customerOpt = customerService.getCustomerById(customerId);

            if (customerOpt.isEmpty()) {

                JsonObject customerNotFound = Json.createObjectBuilder()
                                                .add("Cusomter_Not_Found", String.format("Customer with ID %d not found", customerId))
                                                .build();

                return ResponseEntity.status(404).body(customerNotFound.toString());

            }


            // new logic here
            List<Order> orderList = orderService.getCustomerOrders(customerId);

            JsonArrayBuilder ordersJsonArrayBuilder = Json.createArrayBuilder();

            for (Order order : orderList) {
                ordersJsonArrayBuilder.add(orderJsonSerializer.orderToJson(order));
            }

            return ResponseEntity.status(200).body(ordersJsonArrayBuilder.build().toString());


        } catch (Exception e) {

            e.printStackTrace();
            
            JsonObject errorMessage = Json.createObjectBuilder()
                                        .add("Internal Server Error", String.format("%s: %s", e.getCause(), e.getMessage()))
                                        .build();

            return ResponseEntity.status(500).body(errorMessage.toString());

        }

    }

}
