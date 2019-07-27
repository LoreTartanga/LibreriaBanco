/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientside.restclient;

import clientside.model.Customer;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Retrofit compliant RESTful client interface.
 * @author javi
 */
public interface AndroidCustomerRESTClient {
    @GET("customer/{id}")
    @Headers({"Accept: application/json"})
    Call<Customer> find(@Path("id") String id);    
}
