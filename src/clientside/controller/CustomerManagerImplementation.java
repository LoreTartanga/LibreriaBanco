/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientside.controller;

import clientside.model.Account;
import clientside.model.Customer;
import clientside.model.Movement;
import clientside.restclient.CustomerRESTClient;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Customer manager implementation to get accounts information.
 * @author javi
 */
public class CustomerManagerImplementation implements CustomerManager{
    /**
     * Customer whose info is managed by this manager. It is like a session 
     * attribute.
     */
    private Customer customer;
    /**
     * Default URI for server side application.
     */
    private String URI="http://hz059359:8080/CRUDBankServerSide/webresources";
    private static Logger LOGGER=Logger.getLogger("clientside.controller");
    /**
     * Get customer full info: personal data, accounts and their movements from 
     * a customer RESTful service in a Java EE Server.
     * @param id The customer id.
     * @return A Customer object containing full info.
     */
    @Override
    public Customer getCustomerAccountsFullInfo(Long id) {
        LOGGER.log(Level.INFO,"Getting full info for customer {0}",id.toString());
        CustomerRESTClient client=new CustomerRESTClient();
        client.setWebTarget(URI, "customer");
        customer=client.find(Customer.class, id.toString());
        client.close();
        if(customer!=null)
            LOGGER.log(Level.INFO,"Got info for {0}",customer.toString());
        return customer;
    }
    /**
     * Get accounts list for customer.
     * @param customer The Customer object.
     * @return A list of Account objects containing account info.
     */
    @Override
    public List<Account> getCustomerAccountsList(Customer customer) {
        if(customer!=null)
            LOGGER.log(Level.INFO,"Getting accounts for customer {0}",customer.toString());
        if(this.customer==null || !this.customer.equals(customer))
            this.customer=getCustomerAccountsFullInfo(customer.getId());
        return this.customer.getAccounts();
    }
    /**
     * Get movements list for customer.
     * @param account
     * @return A list of Movement objects containing movements info.
     */
     @Override
    public List<Movement> getAccountMovementsList(Account account) {
        if(account!=null)
            LOGGER.log(Level.INFO,"Getting movements for account {0}",account.toString());
        List<Movement> movements=this.customer.getAccounts().stream()
                .filter(it->it.equals(account))
                .collect(Collectors.toList()).get(0).getMovements();
        return movements;
     }
    /**
     * Set server name for serverside application to be used 
     * @param serverName the serverName in which the server side application resides.
     */
    @Override
    public void setServerName(String serverName) {
        if(serverName!=null)
            LOGGER.log(Level.INFO,"Setting server name to {0}",serverName);
        URI = "http://"+serverName+":8080/CRUDBankServerSide/webresources";
    }
}
