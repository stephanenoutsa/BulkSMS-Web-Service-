/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stephnoutsa.bulksms.resource;

import com.stephnoutsa.bulksms.database.MyDBHandler;
import com.stephnoutsa.bulksms.model.Contact;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author stephnoutsa
 */

@Path("/contacts/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ContactResource {
    
    MyDBHandler dbHandler = new MyDBHandler();
    
    @GET
    public Contact[] getAllContacts() {
        return dbHandler.getAllContacts();
    }
    
    @POST
    public Contact[] addContacts(Contact[] contacts) {
        return dbHandler.addContacts(contacts);
    }
    
}
