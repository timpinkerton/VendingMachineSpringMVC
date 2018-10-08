/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc2.service;

import com.sg.vendingmachinespringmvc2.dao.SnackInventoryPersistenceException;
import com.sg.vendingmachinespringmvc2.dao.VendingMachineDao;
import com.sg.vendingmachinespringmvc2.model.Coin;
import com.sg.vendingmachinespringmvc2.model.Snack;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author timpinkerton
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    private VendingMachineDao dao;

    //Constructor **************************************************************
    public VendingMachineServiceLayerImpl(VendingMachineDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Snack> getAllSnacks() throws SnackInventoryPersistenceException {
        return dao.getAllSnacks();
    }

    @Override
    public void validatePurchase(String snackId, BigDecimal userMoney)
        throws VendingMachineInvalidSelectionException {
        
        //checking that the selection valid
        validateSelection(snackId);
        //checking that quantity of selected snack is not 0
        checkInventory(snackId);
        //checking that there is enough money input for the selected snack
        sufficientMoney(snackId, userMoney);

    }

    @Override
    public Snack getOneSnack(String snackId) {
        return dao.getOneSnack(snackId);
    }

    @Override
    public void updateSnack(String snackId) throws SnackInventoryPersistenceException {
        dao.updateSnack(snackId);
    }

    @Override
    public String makeChange(BigDecimal changeDue) {

        BigDecimal quarter = new BigDecimal("0.25");
        BigDecimal dime = new BigDecimal("0.10");
        BigDecimal nickel = new BigDecimal("0.05");
        BigDecimal penny = new BigDecimal("0.01");

        BigDecimal numberOfQuarters;
        BigDecimal numberOfDimes;
        BigDecimal numberOfNickels;
        BigDecimal numberOfPennies;

        BigDecimal valueOfQuartersDispensed;
        BigDecimal valueOfDimesDispensed;
        BigDecimal valueOfNickelsDispensed;

        Map<Coin, BigDecimal> listOfChange = new HashMap<>();

        //calculates the number of quarters
        numberOfQuarters = changeDue.divide(quarter);
        numberOfQuarters = numberOfQuarters.setScale(0, RoundingMode.DOWN);
        valueOfQuartersDispensed = numberOfQuarters.multiply(quarter);

        //adding quarters to the hashmap
        listOfChange.put(Coin.QUARTER, numberOfQuarters);

        BigDecimal changeAfterQuarters = changeDue.subtract(valueOfQuartersDispensed);

        //calculates the number of dimes
        numberOfDimes = changeAfterQuarters.divide(dime);
        numberOfDimes = numberOfDimes.setScale(0, RoundingMode.DOWN);
        valueOfDimesDispensed = numberOfDimes.multiply(dime);

        listOfChange.put(Coin.DIME, numberOfDimes);

        BigDecimal changeAfterDimes = changeAfterQuarters.subtract(valueOfDimesDispensed);

        //calculates the number of nickels
        numberOfNickels = changeAfterDimes.divide(nickel);
        numberOfNickels = numberOfNickels.setScale(0, RoundingMode.DOWN);
        valueOfNickelsDispensed = numberOfNickels.multiply(nickel);

        listOfChange.put(Coin.NICKEL, numberOfNickels);

        BigDecimal changeAfterNickels = changeAfterDimes.subtract(valueOfNickelsDispensed);

        //calculates the number of pennies
        numberOfPennies = changeAfterNickels.divide(penny);

        listOfChange.put(Coin.PENNY, numberOfPennies);

        String changeReturned = ""; 

        if (numberOfQuarters.compareTo(BigDecimal.ZERO) > 0) {
            changeReturned += numberOfQuarters + " quarter(s) ";
        }

        if (numberOfDimes.compareTo(BigDecimal.ZERO) > 0) {
            changeReturned += numberOfDimes + " dime(s) ";
        }

        if (numberOfNickels.compareTo(BigDecimal.ZERO) > 0) {
            changeReturned += numberOfNickels + " nickel(s) ";
        }

        if (numberOfPennies.compareTo(BigDecimal.ZERO) > 0) {
            changeReturned += numberOfPennies + " pennies";
        }

        if (changeReturned.equals("")) {
            changeReturned = "No Change"; 
        }
        return changeReturned;

    }

    
    //Helper Methods ***********************************************************

    private void validateSelection(String selection)
            throws VendingMachineInvalidSelectionException {
        
        if (dao.getOneSnack(selection) == null) {
            throw new VendingMachineInvalidSelectionException("ERROR: That"
                    + " is not a valid selection.");
        }
    }

    private void checkInventory(String snackId)
            throws VendingMachineInvalidSelectionException {
        
        Snack selectedSnack = dao.getOneSnack(snackId);
        if (selectedSnack.getQuantity() < 1) {
            throw new VendingMachineInvalidSelectionException("Out Of Stock");
        }
    }

    private void sufficientMoney(String snackId, BigDecimal userMoney)
            throws VendingMachineInvalidSelectionException {
        
        Snack selectedSnack = dao.getOneSnack(snackId);
        
        BigDecimal moneyNeeded = selectedSnack.getPrice().subtract(userMoney); 

        if (userMoney.compareTo(selectedSnack.getPrice()) < 0) {
            throw new VendingMachineInvalidSelectionException("Please deposit: $ " + moneyNeeded);
        }
    }
    
    
}
