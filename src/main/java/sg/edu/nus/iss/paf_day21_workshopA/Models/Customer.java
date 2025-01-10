package sg.edu.nus.iss.paf_day21_workshopA.Models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Customer {
    
    private int id;
    private String company;
    private String lastName;
    private String firstName;
    private String jobTitle;
    private String email;
    private String businessPhone;
    
    
    public Customer() {
    }
    

    public static Customer toCustomer(SqlRowSet rs){
        Customer customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setCompany(rs.getString("company"));
            customer.setLastName(rs.getString("last_name"));
            customer.setFirstName(rs.getString("first_name"));
            customer.setJobTitle(rs.getString("job_title"));
            customer.setEmail(rs.getString("email_address") == null ? "" : rs.getString("email_address"));
            customer.setBusinessPhone(rs.getString("business_phone"));

        return customer;
    }


    @Override
    public String toString() {
        return "Customer [id=" + id + ", company=" + company + ", lastName=" + lastName + ", firstName=" + firstName
                + ", jobTitle=" + jobTitle + ", email=" + email + ", mobilePhone=" + businessPhone + "]";
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getJobTitle() {
        return jobTitle;
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getBusinessPhone() {
        return businessPhone;
    }
    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }
}
