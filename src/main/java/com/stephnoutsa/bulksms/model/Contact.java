/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stephnoutsa.bulksms.model;

/**
 *
 * @author stephnoutsa
 */
public class Contact {
    
    // Private variables
    int id;
    String phone, sent;
    
    // Empty constructor
    public Contact() {
        
    }
    
    // Constructor
    public Contact(int id, String phone, String sent) {
        this.id = id;
        this.phone = phone;
        this.sent = sent;
    }
    
    // Constructor
    public Contact(String phone, String sent) {
        this.phone = phone;
        this.sent = sent;
    }
    
    // Getter and setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSent() {
        return sent;
    }

    public void setSent(String sent) {
        this.sent = sent;
    }    
    
}
