/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc2.service;

import com.sg.vendingmachinespringmvc2.dao.SnackInventoryPersistenceException;
import com.sg.vendingmachinespringmvc2.model.Snack;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author timpinkerton
 */
public interface VendingMachineServiceLayer {
    
    //get all snacks
    List<Snack> getAllSnacks() throws SnackInventoryPersistenceException; 
    
    //get one snack with snackId and UserMoney
    void validatePurchase(String snackId, BigDecimal userMoney) throws VendingMachineInvalidSelectionException ; 
    
    //get one snack with just snackId
    Snack getOneSnack(String snackId);
            
    //update one snack to reduce quantity
    void updateSnack(String snackId) throws SnackInventoryPersistenceException; 
            
    //make change
    String makeChange(BigDecimal changeDue); 
}
