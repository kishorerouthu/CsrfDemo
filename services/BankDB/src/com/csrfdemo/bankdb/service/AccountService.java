/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.csrfdemo.bankdb.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.file.model.Downloadable;

import com.csrfdemo.bankdb.Account;

/**
 * Service object for domain model class {@link Account}.
 */
public interface AccountService {

    /**
     * Creates a new Account. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Account if any.
     *
     * @param account Details of the Account to be created; value cannot be null.
     * @return The newly created Account.
     */
	Account create(Account account);


	/**
	 * Returns Account by given id if exists.
	 *
	 * @param accountId The id of the Account to get; value cannot be null.
	 * @return Account associated with the given accountId.
     * @throws EntityNotFoundException If no Account is found.
	 */
	Account getById(Integer accountId) throws EntityNotFoundException;

    /**
	 * Find and return the Account by given id if exists, returns null otherwise.
	 *
	 * @param accountId The id of the Account to get; value cannot be null.
	 * @return Account associated with the given accountId.
	 */
	Account findById(Integer accountId);


	/**
	 * Updates the details of an existing Account. It replaces all fields of the existing Account with the given account.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on Account if any.
     *
	 * @param account The details of the Account to be updated; value cannot be null.
	 * @return The updated Account.
	 * @throws EntityNotFoundException if no Account is found with given input.
	 */
	Account update(Account account) throws EntityNotFoundException;

    /**
	 * Deletes an existing Account with the given id.
	 *
	 * @param accountId The id of the Account to be deleted; value cannot be null.
	 * @return The deleted Account.
	 * @throws EntityNotFoundException if no Account found with the given id.
	 */
	Account delete(Integer accountId) throws EntityNotFoundException;

	/**
	 * Find all Accounts matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Accounts.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<Account> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all Accounts matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Accounts.
     *
     * @see Pageable
     * @see Page
	 */
    Page<Account> findAll(String query, Pageable pageable);

    /**
	 * Exports all Accounts matching the given input query to the given exportType format.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param exportType The format in which to export the data; value cannot be null.
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null exports all matching records.
     * @return The Downloadable file in given export type.
     *
     * @see Pageable
     * @see ExportType
     * @see Downloadable
	 */
    Downloadable export(ExportType exportType, String query, Pageable pageable);

	/**
	 * Retrieve the count of the Accounts in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the Account.
	 */
	long count(String query);


}
