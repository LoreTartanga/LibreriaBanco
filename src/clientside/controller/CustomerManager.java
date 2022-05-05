/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientside.controller;

import clientside.model.Account;
import clientside.model.Customer;
import clientside.model.Movement;
import java.util.List;

/**
 * Business logic interface to get customer's accounts info.
 * @author javi
 */
public interface CustomerManager {
    /**
     * Get customer full info: personal data, accounts and their movements.
     * @param id The customer id.
     * @return A Customer object containing full info.
     */
    public Customer getCustomerAccountsFullInfo(Long id);
   /**
     * Get all customer info: personal data, accounts and their movements.
     * @return list Customers
     */
    public List<Customer> getAllCustomer();
    /**
     * Set server name for server side application to be used 
     * @param serverName the serverName in which the server side application resides.
     */
    public void setServerName(String serverName);
    /**
     * Set media type for communication with server side.
     * @param mediaType the media type as defined in javax.ws.rs.core.MediaType.
     */
    public void setMediaType(String mediaType);
}
