package com.Select.Project.Users;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int customerId;

    
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private String subscription_id;
    private String subscriptionStatus;
    private String subscriptionPaymentMethod;

    public Customers() {
    }

    public Customers(int customerId, String customerName, String customerEmail, String customerPhone, String subscription_id, String subscriptionStatus, String subscriptionPaymentMethod) {
        this.customerId = customerId;
        this.customerName = customerName;   
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.subscription_id = subscription_id;
        this.subscriptionStatus = subscriptionStatus;
        this.subscriptionPaymentMethod = subscriptionPaymentMethod;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }       

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
        }

    public String getSubscription_id() {
        return subscription_id;
    }

    public String getSubscriptionStatus() {
        return subscriptionStatus;
    }

    public String getSubscriptionPaymentMethod() {
        return subscriptionPaymentMethod;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }   

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public void setSubscription_id(String subscription_id) {
        this.subscription_id = subscription_id;
    }
    
}
