/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientside.controller;

/**
 * CustomerManager factory.
 * @author javi
 */
public class CustomerManagerFactory {
    public static CustomerManager getCustomerManager(){
        return new CustomerManagerImplementation();
    }
    
}
