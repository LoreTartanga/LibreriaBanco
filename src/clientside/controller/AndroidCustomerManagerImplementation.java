/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientside.controller;

import clientside.model.Customer;
import clientside.restclient.AndroidCustomerRESTClient;
import clientside.restclient.CustomerRESTClient;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * CustomerManager implementation for Android. It uses Retrofit API for RESTful 
 * communication.
 * @author javi
 */
public class AndroidCustomerManagerImplementation implements CustomerManager {
    /**
     * Customer whose info is managed by this manager. It is like a session 
     * attribute.
     */
    private Customer customer;
    /**
     * The media type to be used in RESTful client, as defined in javax.ws.rs.core.MediaType.
     */
    private String mediaType;
    /**
     * Default URI for server side application.
     */
    private String URI;
    /**
     * RESTful client.
     */
    private AndroidCustomerRESTClient client;
    /**
     * Logger for class.
     */
    private static Logger LOGGER=Logger.getLogger("clientside.controller");
    /**
     * Default constructor;
     */
    public AndroidCustomerManagerImplementation(){
        mediaType=javax.ws.rs.core.MediaType.APPLICATION_JSON;
        URI="http://localhost:8080/CRUDBankServerSide/webresources/";
        setClient();
    }
    /**
     * Set RESTful client for URI.
     */
    private void setClient(){
        Retrofit restAdapter=new Retrofit.Builder()
                        .baseUrl(URI)
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .addConverterFactory(JacksonConverterFactory.create())
                        .build();
        client= restAdapter.create(AndroidCustomerRESTClient.class);
    }
    /**
     * Get customer full info: personal data, accounts and their movements from 
     * a customer RESTful service in a Java EE Server.
     * @param id The customer id.
     * @return A Customer object containing full info.
     */
    @Override
    public Customer getCustomerAccountsFullInfo(Long id) {
        customer=null;
        try {
            LOGGER.log(Level.INFO,"Getting full info for customer {0}",id.toString());
            customer=client.find(id.toString()).execute().body();
            if(customer==null)
                throw new NoSuchElementException("Cannot find customer with id # "
                        +id.toString());
            LOGGER.log(Level.INFO,"Got info for {0}",customer.toString());
            return customer;
        } catch (IOException ex) {
            LOGGER.severe(ex.getMessage());
            throw new NoSuchElementException("Error finding customer with id # "
                        +id.toString());
        }
    }
    /**
     * Set server name for serverside application to be used 
     * @param serverName the serverName in which the server side application resides.
     */
    @Override
    public void setServerName(String serverName) {
        LOGGER.log(Level.INFO,"Setting server name to {0}",serverName);
        URI = "http://"+serverName+":8080/CRUDBankServerSide/webresources/";
        setClient();
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
