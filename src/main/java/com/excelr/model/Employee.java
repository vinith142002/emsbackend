package com.excelr.model;


import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Employee {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long  id;
    
    private String fullname;
    private String email;
    private String gender;
    private String dob;
    private String mobilenumber;
    private String emergencycontactnumber;
    
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "city", column = @jakarta.persistence.Column(name = "current_city")),
        @AttributeOverride(name = "address1", column = @jakarta.persistence.Column(name = "current_address1")),
        @AttributeOverride(name = "address2", column = @jakarta.persistence.Column(name = "current_address2")),
        @AttributeOverride(name = "pincode", column = @jakarta.persistence.Column(name = "current_pincode"))
    })
    private Address currentAddress;
    
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "city", column = @jakarta.persistence.Column(name = "permanent_city")),
        @AttributeOverride(name = "address1", column = @jakarta.persistence.Column(name = "permanent_address1")),
        @AttributeOverride(name = "address2", column = @jakarta.persistence.Column(name = "permanent_address2")),
        @AttributeOverride(name = "pincode", column = @jakarta.persistence.Column(name = "permanent_pincode"))
    })
    private Address permanentAddress;
    private String empcode;
    private String companyname;
    private String officelocation;
    private String city;
    private String department;
    private String experience;
    private String joiningdate;
    private String offid;
    private String hrname;
    private String officephone;
    private String reportingmanager;
    private String aadharnumber;
    private String pancardnumber;
    private String bankaccountnumber;
    private String ifsc;
    private Long projectId;

}


