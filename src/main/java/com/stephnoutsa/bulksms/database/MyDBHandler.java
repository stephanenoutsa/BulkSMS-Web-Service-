/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stephnoutsa.bulksms.database;

import com.stephnoutsa.bulksms.model.Contact;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author stephnoutsa
 */
public class MyDBHandler {
    
    public static final String DATABASE_NAME = "contacts";
    
    public static final String TABLE_CONTACTS = "CONTACTS";
    public static final String CONTACT_COLUMN_ID = "ID";
    public static final String CONTACT_COLUMN_PHONE = "PHONE";
    public static final String CONTACT_COLUMN_SENT = "SENT";
    
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";  
    static final String DB_URL = "jdbc:derby://localhost:1527/" + DATABASE_NAME;
    
    // Database credentials
    static final String USER = "root";
    static final String PASS = "b2kline";
    
    // Main method
    public static void main(String[] args) {
               
    }
    
    // Add new contacts to the Contacts table
    public Contact[] addContacts(Contact[] contacts) {
        Connection conn = null;
        Statement db = null;
        
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            db = conn.createStatement();
            
            for (Contact c : contacts) {
                if (!contactExists(c)) {
                    String query = "INSERT INTO " + TABLE_CONTACTS + "(" + CONTACT_COLUMN_PHONE +
                            ", " + CONTACT_COLUMN_SENT + ") VALUES(\'" + c.getPhone() + "\', \'" +
                            c.getSent() + "\')";
                    db.executeUpdate(query);
                    System.out.println(c.getPhone() + " added");
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (db != null)
                    db.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
               if (conn != null)
                  conn.close();
            } catch (SQLException se) {
               se.printStackTrace();
            }
        }
        
        return contacts;
    }
    
    // Check if contact exists
    public boolean contactExists(Contact contact) {
        boolean exists = false;
        
        Connection conn = null;
        Statement db = null;
        
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            db = conn.createStatement();
            
            String query = "SELECT * FROM " + TABLE_CONTACTS + " WHERE "
                    + CONTACT_COLUMN_PHONE + " = \'" + contact.getPhone() + "\'";
            
            ResultSet result = db.executeQuery(query);
            
            if (result.next()) {
                System.out.println(contact.getPhone() + " already exists");                
                exists = true;
            } else {
                System.out.println(contact.getPhone() + " does not exist");               
                exists = false;
            }
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (db != null)
                    db.close();
            }catch (SQLException se) {
                se.printStackTrace();
            }
            try {
               if (conn != null)
                  conn.close();
            } catch (SQLException se) {
               se.printStackTrace();
            }            
        }
        
        return exists;
    }
    
    // Get all contacts from Contacts table
    public Contact[] getAllContacts() {
        List<Contact> contactList = new ArrayList<>();
        Contact[] contacts = new Contact[contactList.size()];
        
        Connection conn = null;
        Statement db = null;
        
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            db = conn.createStatement();
            
            String query = "SELECT * FROM " + TABLE_CONTACTS;
            ResultSet result = db.executeQuery(query);
            
            while(result.next()) {
                Contact contact = new Contact();
                
                contact.setId(result.getInt(CONTACT_COLUMN_ID));
                contact.setPhone(result.getString(CONTACT_COLUMN_PHONE));
                contact.setSent(result.getString(CONTACT_COLUMN_SENT));
                
                contactList.add(contact);
            }
            
            contacts = contactList.toArray(new Contact[contactList.size()]);
            System.out.println("List of contacts delivered");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (db != null)
                    db.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        
        return contacts;
    }
    
}
