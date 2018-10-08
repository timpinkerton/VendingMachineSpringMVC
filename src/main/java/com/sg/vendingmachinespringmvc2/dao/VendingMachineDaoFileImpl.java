/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc2.dao;

import com.sg.vendingmachinespringmvc2.model.Coin;
import com.sg.vendingmachinespringmvc2.model.Snack;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author timpinkerton
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao {

    private final Map<String, Snack> snackInventory = new HashMap<>();
    
    //Constructor **************************************************************
    public VendingMachineDaoFileImpl() throws SnackInventoryPersistenceException {
        //loads the snackInventory map with all the snacks below
        readSnackInventory();
    }
    

    @Override
    public List<Snack> getAllSnacks() throws SnackInventoryPersistenceException {
        return new ArrayList<>(snackInventory.values());
    }

    @Override
    public Snack getOneSnack(String snackId) {
        return snackInventory.get(snackId); 
    }

    @Override
    public void updateSnack(String snackId) throws SnackInventoryPersistenceException {
        Snack snackToUpdate = snackInventory.get(snackId);
        int currentQty = snackToUpdate.getQuantity();

        snackToUpdate.setQuantity(currentQty - 1);

        snackInventory.put(snackId, snackToUpdate);
    }

    @Override
    public Map<Coin, BigDecimal> makeChange(BigDecimal changeDue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Snack addSnack(String snackId, Snack snack) throws SnackInventoryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Snack removeSnack(String snackId) throws SnackInventoryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * populates the snackInventory map with snacks
     *
     * @throws com.sg.vendingmachinespringmvc2.dao.SnackInventoryPersistenceException
     */
    public void readSnackInventory() throws SnackInventoryPersistenceException {
        
        Snack snackOne = new Snack("1"); 
        snackOne.setName("Zebra Cake");
        snackOne.setPrice(new BigDecimal("3.60"));
        snackOne.setQuantity(14);
        
        snackInventory.put(snackOne.getSnackId(), snackOne);
        
        Snack snackTwo = new Snack("2"); 
        snackTwo.setName("Egg Burrito");
        snackTwo.setPrice(new BigDecimal("1.75"));
        snackTwo.setQuantity(4);

        snackInventory.put(snackTwo.getSnackId(), snackTwo);

        Snack snackThree = new Snack("3"); 
        snackThree.setName("PB&J");
        snackThree.setPrice(new BigDecimal("2.00"));
        snackThree.setQuantity(8);

        snackInventory.put(snackThree.getSnackId(), snackThree); 
        
        Snack snackFour = new Snack("4"); 
        snackFour.setName("Chicken Wing");
        snackFour.setPrice(new BigDecimal("8.25"));
        snackFour.setQuantity(33);

        snackInventory.put(snackFour.getSnackId(), snackFour);
        
        Snack snackFive = new Snack("5"); 
        snackFive.setName("Ice Cream Sandwich");
        snackFive.setPrice(new BigDecimal("1.25"));
        snackFive.setQuantity(4);
        
        snackInventory.put(snackFive.getSnackId(), snackFive);
        
        Snack snackSix = new Snack("6"); 
        snackSix.setName("Egg Sandwich");
        snackSix.setPrice(new BigDecimal("4.50"));
        snackSix.setQuantity(22);

        snackInventory.put(snackSix.getSnackId(), snackSix);

        Snack snackSeven = new Snack("7"); 
        snackSeven.setName("Cereal");
        snackSeven.setPrice(new BigDecimal("3.80"));
        snackSeven.setQuantity(2);

        snackInventory.put(snackSeven.getSnackId(), snackSeven); 
        
        Snack snackEight = new Snack("8"); 
        snackEight.setName("Frozen Coffee");
        snackEight.setPrice(new BigDecimal("3.90"));
        snackEight.setQuantity(97);

        snackInventory.put(snackEight.getSnackId(), snackEight);
        
        Snack snackNine = new Snack("9"); 
        snackNine.setName("Banana");
        snackNine.setPrice(new BigDecimal("1.00"));
        snackNine.setQuantity(300);

        snackInventory.put(snackNine.getSnackId(), snackNine);
        
    }

  

}
