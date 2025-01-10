package sg.edu.nus.iss.paf_day21_workshopA.Serializers;

import org.springframework.stereotype.Component;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import sg.edu.nus.iss.paf_day21_workshopA.Models.Order;

@Component
public class OrderJsonSerializer {
    
    public JsonObject orderToJson(Order order){
        
        System.out.println(order);

        JsonObject orderJson = Json.createObjectBuilder()
                                .add("order_id", order.getOrderId())
                                .add("customer_id", order.getCustomerId())
                                .add("order_date", order.getOrderDate())
                                .add("shipped_date", order.getShippedDate())
                                .add("shipping_fee", order.getShippingFee())
                                .add("payment_type", order.getPaymentType())
                                .add("status_id", order.getStatusId())
                                .build();

        return orderJson;
    }
}
