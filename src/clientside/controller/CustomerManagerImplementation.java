/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientside.controller;


import clientside.model.Customer;
import clientside.restclient.CustomerRESTClient;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
     * The media type to be used in RESTful client, as defined in javax.ws.rs.core.MediaType.
     */
    private String mediaType=javax.ws.rs.core.MediaType.APPLICATION_XML;
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
        client.setMediaType(mediaType);
        customer=client.find(Customer.class, id.toString());
        client.close();
        if(customer==null)
            throw new NoSuchElementException("Cannot find customer with id # "
                                                +id.toString());
        LOGGER.log(Level.INFO,"Got info for {0}",customer.toString());
        return customer;
    }
    /**
     * Set server name for serverside application to be used 
     * @param serverName the serverName in which the server side application resides.
     */
    @Override
    public void setServerName(String serverName) {
        LOGGER.log(Level.INFO,"Setting server name to {0}",serverName);
        URI = "http://"+serverName+":8080/CRUDBankServerSide/webresources";
    }
    /**
     * Set media type for communication with server side.
     * @param mediaType the media type as defined in javax.ws.rs.core.MediaType.
     */
    @Override
    public void setMediaType(String mediaType){
        this.mediaType=mediaType;
    }    
}
