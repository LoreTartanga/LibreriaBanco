/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientside.restclient;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

/**
 * Jersey REST client generated for REST resource:CustomerFacadeREST
 * [customer]<br>
 * USAGE:
 * <pre>
 *        CustomerRESTClient client = new CustomerRESTClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author javi
 */
public class CustomerRESTClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/CRUDBankServerSide/webresources";
    private String mediaType;
    /**
     * Construct a JAX RS client for default localhost server and customer service.
     */
    public CustomerRESTClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("customer");
        mediaType = javax.ws.rs.core.MediaType.APPLICATION_XML;
    }
    /**
     * Set the target for the RESTful client.
     * @param base_uri URI value for the RESTful service to be invoked. It does not
     * include path identifying the service. See next parameter.
     * @param path Path value added to base_uri to select RESTful service to be used.
     */
    public void setWebTarget(String base_uri,String path){
        webTarget = client.target(base_uri).path(path);
    }
    /**
     * Set media type for received or sent entities. 
     * @param mediaType as defined in javax.ws.rs.core.MediaType.
     */
    public void setMediaType(String mediaType){
        this.mediaType=mediaType;
    }
    /**
     * Get customers count. 
     * @return The customers count.
     * @throws ClientErrorException HTTP error.
     */
    public String countREST() throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("count");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }
    /**
     * Update customer data.
     * @param requestEntity Customer instance containing data. 
     * @param id Customer id.
     * @throws ClientErrorException HTTP error.
     */
    public void edit(Object requestEntity, String id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{id})).request(mediaType).put(javax.ws.rs.client.Entity.entity(requestEntity, mediaType));
    }
    /**
     * Get customer data.
     * @param <T> Customer.
     * @param responseType Customer class.
     * @param id Customer id.
     * @return Customer.
     * @throws ClientErrorException HTTP error.
     */
    public <T> T find(Class<T> responseType, String id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
        return resource.request(mediaType).get(responseType);
    }
    /**
     * Get a range of customers (useful for management of big data volume). 
     * @param <T> type of the collection of customers.
     * @param responseType class for the type of the collection of customers.
     * @param from initial index value for query.
     * @param to final index value for query.
     * @return A collection of Customers.
     * @throws ClientErrorException HTTP error.
     */
    public <T> T findRange(Class<T> responseType, String from, String to) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}/{1}", new Object[]{from, to}));
        return resource.request(mediaType).get(responseType);
    }
    /**
     * Create a Customer on server side.
     * @param requestEntity Customer instance to be created.
     * @throws ClientErrorException HTTP error.
     */
    public void create(Object requestEntity) throws ClientErrorException {
        webTarget.request(mediaType).post(javax.ws.rs.client.Entity.entity(requestEntity, mediaType));
    }
    /**
     * Get all Customers data.
     * @param <T> type of the collection of customers.
     * @param responseType class of the type of the collection of customers.
     * @return A collection of Customers.
     * @throws ClientErrorException HTTP error.
     */     
    public <T> T findAll(GenericType<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(mediaType).get(responseType);
    }
    /**
     * Delete a Customer on server side.
     * @param id id of the Customer to be deleted.
     * @throws ClientErrorException HTTP error.
     */
    public void remove(String id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{id})).request().delete();
    }
    /**
     * Close client.
     */
    public void close() {
        client.close();
    }
    
}
