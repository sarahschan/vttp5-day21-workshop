package sg.edu.nus.iss.paf_day21_workshopA.Serializers;

import org.springframework.stereotype.Component;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import sg.edu.nus.iss.paf_day21_workshopA.Models.Customer;

@Component
public class CustomerJsonSerializer {
    
    public JsonObject customerToJson(Customer customer) {

        JsonObject customerJson = Json.createObjectBuilder()
                                    .add("id", customer.getId())
                                    .add("company", customer.getCompany())
                                    .add("last_name", customer.getLastName())
                                    .add("first_name", customer.getFirstName())
                                    .add("job_title", customer.getJobTitle())
                                    .add("email_address", customer.getEmail())
                                    .add("business_phone", customer.getBusinessPhone())
                                    .build();

        return customerJson;
        
    }
}
