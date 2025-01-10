package sg.edu.nus.iss.paf_day21_workshopA.Repositories;

public class Queries {
    
    public static final String SQL_GET_ALL_CUSTOMERS_LIMIT_OFFSET = 
        """
            select * from customers 
                limit ?
                offset ?
        """;
    
    
    public static final String SQL_GET_CUSTOMER_BY_ID =
        """
            select * from customers
                where id = ?    
        """;;
}
