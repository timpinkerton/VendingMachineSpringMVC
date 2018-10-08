/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc2.dao;

import com.sg.vendingmachinespringmvc2.model.Coin;
import com.sg.vendingmachinespringmvc2.model.Snack;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *
 * @author timpinkerton
 */
public interface VendingMachineDao {
    
        //get all snacks
    List<Snack> getAllSnacks() throws SnackInventoryPersistenceException; 
    
    //get one snack
    Snack getOneSnack(String snackId); 
            
    //update one snack to reduce quantity
    void updateSnack(String snackId) throws SnackInventoryPersistenceException; 
            
    //make change
    Map<Coin, BigDecimal> makeChange(BigDecimal changeDue); 
    
    //to add an item to inventory, for testing only
    Snack addSnack(String snackId, Snack snack) 
            throws SnackInventoryPersistenceException;
    
    //to remove and item from the inventory, for testing only
    Snack removeSnack(String snackId) throws SnackInventoryPersistenceException;
    
}
