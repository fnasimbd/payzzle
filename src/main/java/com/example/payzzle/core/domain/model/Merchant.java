/*
 * Copyright (c) 2026 Farhan Nasim
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
 * OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.example.payzzle.core.domain.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

/**
 * Created by Farhan Nasim on 3/27/2026 11:36 PM
 */
@Entity
@Table(name = "merchant")
@SequenceGenerator(name = "merchant_seq", sequenceName = "merchant_seq")
public class Merchant extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private MerchantSettings settings;

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public MerchantSettings getSettings() {
        return settings;
    }

    private void setSettings(MerchantSettings settings) {
        this.settings = settings;
    }

    public Transaction initiateNewTransaction(String transactionId,
                                              Integer amount,
                                              String currency,
                                              String successUrl,
                                              String failureUrl,
                                              String cancelUrl) {

        if (!successUrl.equals(getSettings().getSuccessUrl())) {
            throw new IllegalArgumentException("Invalid initiation request.");
        }

        if (!failureUrl.equals(getSettings().getFailureUrl())) {
            throw new IllegalArgumentException();
        }

        if (!cancelUrl.equals(getSettings().getCancelUrl())) {
            throw new IllegalArgumentException();
        }

        return new Transaction(
                this,
                transactionId,
                amount,
                currency,
                getSettings().transactionTimeoutFromNow());
    }
}
