/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.csrfdemo.bankdb.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.file.model.Downloadable;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.csrfdemo.bankdb.Account;
import com.csrfdemo.bankdb.service.AccountService;

/**
 * Controller object for domain model class Account.
 * @see Account
 */
@RestController("BankDB.AccountController")
@Api(value = "AccountController", description = "Exposes APIs to work with Account resource.")
@RequestMapping("/BankDB/Account")
public class AccountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    @Qualifier("BankDB.AccountService")
    private AccountService accountService;

    @ApiOperation(value = "Creates a new Account instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Account createAccount(@RequestBody Account account) {
        LOGGER.debug("Create Account with information: {}", account);
        account = accountService.create(account);
        LOGGER.debug("Created Account with information: {}", account);
        return account;
    }

    @ApiOperation(value = "Returns the Account instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Account getAccount(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting Account with id: {}", id);
        Account foundAccount = accountService.getById(id);
        LOGGER.debug("Account details with id: {}", foundAccount);
        return foundAccount;
    }

    @ApiOperation(value = "Updates the Account instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Account editAccount(@PathVariable("id") Integer id, @RequestBody Account account) throws EntityNotFoundException {
        LOGGER.debug("Editing Account with id: {}", account.getId());
        account.setId(id);
        account = accountService.update(account);
        LOGGER.debug("Account details with id: {}", account);
        return account;
    }

    @ApiOperation(value = "Deletes the Account instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteAccount(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting Account with id: {}", id);
        Account deletedAccount = accountService.delete(id);
        return deletedAccount != null;
    }

    /**
     * @deprecated Use {@link #findAccounts(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of Account instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Account> findAccounts(Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering Accounts list");
        return accountService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the list of Account instances matching the search criteria.")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Account> findAccounts(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Accounts list");
        return accountService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data.")
    @RequestMapping(value = "/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportAccounts(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        return accountService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns the total count of Account instances.")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Long countAccounts(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
        LOGGER.debug("counting Accounts");
        return accountService.count(query);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service AccountService instance
	 */
    protected void setAccountService(AccountService service) {
        this.accountService = service;
    }
}
