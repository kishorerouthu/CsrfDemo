/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.csrfdemo.transferservice;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import com.csrfdemo.bankdb.Account;
import com.csrfdemo.bankdb.service.AccountService;


import com.wavemaker.runtime.security.SecurityService;
import com.wavemaker.runtime.service.annotations.ExposeToClient;
import com.wavemaker.runtime.service.annotations.HideFromClient;

//import com.csrfdemo.transferservice.model.*;

/**
 * This is a singleton class with all its public methods exposed as REST APIs via generated controller class.
 * To avoid exposing an API for a particular public method, annotate it with @HideFromClient.
 *
 * Method names will play a major role in defining the Http Method for the generated APIs. For example, a method name
 * that starts with delete/remove, will make the API exposed as Http Method "DELETE".
 *
 * Method Parameters of type primitives (including java.lang.String) will be exposed as Query Parameters &
 * Complex Types/Objects will become part of the Request body in the generated API.
 */
@ExposeToClient
public class TransferService {

    private static final Logger logger = LoggerFactory.getLogger(TransferService.class);

    @Autowired
    private SecurityService securityService;
    
    @Autowired
    private AccountService accountService;

    /**
     * This is sample java operation that accepts an input from the caller and responds with "Hello".
     *
     * SecurityService that is Autowired will provide access to the security context of the caller. It has methods like isAuthenticated(),
     * getUserName() and getUserId() etc which returns the information based on the caller context.
     *
     * Methods in this class can declare HttpServletRequest, HttpServletResponse as input parameters to access the
     * caller's request/response objects respectively. These parameters will be injected when request is made (during API invocation).
     */
    
    public Double setTransfer(Integer fromAcId, Integer toAcId, Double amount) {
        Double balanceFrom = 0d;
        Double balanceTo = 0d;
        try {
        Account fromAccount =  accountService.getById(fromAcId);
        balanceFrom = fromAccount.getBalance() - amount;
        fromAccount.setBalance(balanceFrom);
        accountService.update(fromAccount);
        
        Account toAccount =  accountService.getById(toAcId);
        balanceTo = toAccount.getBalance() + amount;
        toAccount.setBalance(balanceTo);
        accountService.update(toAccount);
        
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Failed to read account");
        }
        //return "Transfered " + amount + " From " + fromAcId + " To " + toAcId + " \n BALANCE :: \n " + "Acc : " + fromAcId + " = " + balanceFrom + "\nAcc : " + toAcId + " = " + balanceTo;
        return balanceFrom;
    }

}
