/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc2.service;

/**
 *
 * @author timpinkerton
 */
public class VendingMachineInvalidSelectionException extends Exception{
    
    public VendingMachineInvalidSelectionException(String message) {
        super(message);
    }

    public VendingMachineInvalidSelectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
