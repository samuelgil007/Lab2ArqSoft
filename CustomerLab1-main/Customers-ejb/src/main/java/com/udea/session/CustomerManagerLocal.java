/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.session;

import java.util.List;
import javax.ejb.Local;
import com.udea.entity.Customer;

/**
 *
 * @author Usuario
 */
@Local
public interface CustomerManagerLocal {

public List<Customer> getAllCustomers();
public Customer update(Customer customer);
   
}
