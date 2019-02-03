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
     * Set server name for serverside application to be used 
     * @param serverName the serverName in which the server side application resides.
     */
    public void setServerName(String serverName);
}
