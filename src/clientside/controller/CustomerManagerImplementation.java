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
import java.util.stream.Collectors;

/**
 * Customer manager implementation to get accounts information.
 * @author javi
 */
public class CustomerManagerImplementation implements CustomerManager{

    private CustomerRESTClient client;
    
    private Customer customer;

    public CustomerManagerImplementation(){
        client=new CustomerRESTClient();
    } 
    @Override
    public Customer getCustomerAccountsFullInfo(Long id) {
        customer=client.find(Customer.class, id.toString());
        return customer;
    }

    @Override
    public List<Account> getCustomerAccountsList(Customer customer) {
        if(this.customer==null || !this.customer.equals(customer))
            this.customer=client.find(Customer.class, customer.getId().toString());
        return this.customer.getAccounts();
    }

    @Override
    public List<Movement> getAccountMovementsList(Account account) {
        if(this.customer==null || !this.customer.equals(customer))
            this.customer=client.find(Customer.class, customer.getId().toString());
        return this.customer.getAccounts().stream()
                .filter(it->it.equals(account))
                .collect(Collectors.toList()).get(0).getMovements();
     }

    @Override
    public void setServerName(String serverName) {
        String URI = "http://"+serverName+":8080/CRUDBankServerSide/webresources";
        client.setWebTarget(URI, "customer");
    }
    
}
