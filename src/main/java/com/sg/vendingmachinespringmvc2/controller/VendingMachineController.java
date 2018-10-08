/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc2.controller;

import com.sg.vendingmachinespringmvc2.dao.SnackInventoryPersistenceException;
import com.sg.vendingmachinespringmvc2.model.Snack;
import com.sg.vendingmachinespringmvc2.service.VendingMachineInvalidSelectionException;
import com.sg.vendingmachinespringmvc2.service.VendingMachineServiceLayer;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author timpinkerton
 */
@Controller
public class VendingMachineController {

    VendingMachineServiceLayer service;
    Snack selectedSnack;
    BigDecimal moneyIn = BigDecimal.ZERO;
    String changeReturned;
    String message;

    //@Inject annotation tells the Spring DI container that we would like it 
    //  to inject an object of type VendingMachineServiceLayer into our 
    //  controller when it is constructed.
    @Inject
    public VendingMachineController(VendingMachineServiceLayer service) {
        this.service = service;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String loadSnacks(Model model) throws SnackInventoryPersistenceException {

        //getting all snacks from the service layer
        List<Snack> snackList = service.getAllSnacks();

        //putting the snackList in the model
        model.addAttribute("snackList", snackList);
        model.addAttribute("selectedSnack", selectedSnack);
        model.addAttribute("moneyIn", moneyIn);
        model.addAttribute("changeReturned", changeReturned);
        model.addAttribute("message", message);
        return "index";
    }

    //endpoint when a snack button is clicked
    @RequestMapping(value = "/selectSnack", method = RequestMethod.GET)
    public String selectSnack(HttpServletRequest request, Model model)
            throws SnackInventoryPersistenceException {

        String snackIdParameter = request.getParameter("snackId");

        selectedSnack = service.getOneSnack(snackIdParameter);

        changeReturned = ""; 
        message = ""; 
        
        //redirects back to the index page
        return "redirect:/";
    }

    @RequestMapping(value = "/addMoney", method = RequestMethod.GET)
    public String addMoney(HttpServletRequest request, Model model) {

        //clear the change input when any money is added
        changeReturned = "";

        String moneyString = request.getParameter("money");

        BigDecimal money = new BigDecimal(moneyString);
        moneyIn = moneyIn.add(money);

        //redirects back to the index page
        return "redirect:/";
    }

    @RequestMapping(value = "/getChange", method = RequestMethod.GET)
    public String getChange(HttpServletRequest request, Model model) {

        changeReturned = service.makeChange(moneyIn);

        //resetting moneyIn to $0
        resetMoneyInput();
        message = "";

        //redirects back to the index page
        return "redirect:/";
    }

    @RequestMapping(value = "/makePurchase", method = RequestMethod.GET)
    public String getPurchase(HttpServletRequest request, Model model) {
        
        //check if selectedSnack is null 
        if (selectedSnack == null) {
            message = "You must make a valid selection";
        } else {

            //validating selection, quantity and money input    
            //  getting selectedSnack and moneyIn from global variables above
            try {
                service.validatePurchase(selectedSnack.getSnackId(), moneyIn);
                message = "Thank You !";
                //reduce moneyIn by amount of the purchased item
                moneyIn = moneyIn.subtract(selectedSnack.getPrice());
                service.updateSnack(selectedSnack.getSnackId());
                selectedSnack = null; 

            } catch (VendingMachineInvalidSelectionException
                    | SnackInventoryPersistenceException e) {
                message = e.getMessage();
            }

        }
        //redirects back to the index page
        return "redirect:/";

    }

    public void resetMoneyInput() {
        moneyIn = BigDecimal.ZERO;
    }

}
