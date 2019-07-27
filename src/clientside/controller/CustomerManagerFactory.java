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
    /**
     * Return a CustomerManager implementation.
     * @param type Type for implementation. If type=="ANDROID" the factory returns
     * a retrofit based implementation. It returns a JAX RS implementation by default
     * (not compatible with Android)
     * @return A CustomerManager implementation
     */
    public static CustomerManager getCustomerManager(String type){
        CustomerManager manager=null;
        if(type.equals("ANDROID"))
            manager=new AndroidCustomerManagerImplementation();
        else            
            manager=new CustomerManagerImplementation();        
        return manager;
    }
    /**
     * Return a CustomerManager implementation.
     * @return a CustomerManager using a JAX RS implementation.
     */
    public static CustomerManager getCustomerManager(){
        return new CustomerManagerImplementation();
    }    
}
