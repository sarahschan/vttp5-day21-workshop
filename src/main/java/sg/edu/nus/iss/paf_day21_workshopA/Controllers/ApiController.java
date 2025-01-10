package sg.edu.nus.iss.paf_day21_workshopA.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import sg.edu.nus.iss.paf_day21_workshopA.Models.Customer;
import sg.edu.nus.iss.paf_day21_workshopA.Serializers.CustomerJsonSerializer;
import sg.edu.nus.iss.paf_day21_workshopA.Services.CustomerService;

@RestController
@RequestMapping("/api")
public class ApiController {    

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerJsonSerializer customerJsonSerializer;

    
    @GetMapping(path = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllCustomers(@RequestParam(defaultValue = "0") int offset, @RequestParam(defaultValue = "5") int limit){

        List<Customer> customers = customerService.getAllCustomers(limit, offset);
        
        try {

            if (customers.isEmpty()) {
                
                JsonObject emptyMessage = Json.createObjectBuilder()
                                            .add("message:", "No customers to display")
                                            .build();

                return ResponseEntity.status(404).body(emptyMessage.toString());

            }


            JsonArrayBuilder customerJsonArray = Json.createArrayBuilder();

            for (Customer customer : customers) {
                customerJsonArray.add(customerJsonSerializer.customerToJson(customer));
            }

            return ResponseEntity.status(200).body(customerJsonArray.build().toString());


        } catch (Exception e) {

            JsonObject errorMessage = Json.createObjectBuilder()
                                        .add("Internal Server Error", String.format("%s: %s", e.getCause(), e.getMessage()))
                                        .build();

            return ResponseEntity.status(500).body(errorMessage.toString());
        }

    }
}
