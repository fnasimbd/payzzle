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


import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * Created by Farhan Nasim on 3/30/2026 1:29 AM
 */
@Entity
@Table(name = "transaction")
@SequenceGenerator(name = "transaction_seq", sequenceName = "transaction_seq")
public class Transaction extends BaseEntity {

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Merchant merchant;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "currency")
    private String currency;

    @Column(name = "timeout")
    private LocalDateTime timeout;

    protected Transaction() {
    }

    public Transaction(Merchant merchant,
                       String transactionId,
                       Integer amount,
                       String currency,
                       LocalDateTime timeout) {

        setMerchant(merchant);
        setTransactionId(transactionId);
        setAmount(amount);
        setCurrency(currency);
        setTimeout(timeout);
    }

    public Transaction(Long id,
                       Merchant merchant,
                       String transactionId,
                       Integer amount,
                       String currency,
                       LocalDateTime timeout) {

        this(merchant, transactionId, amount, currency, timeout);

        setId(id);
    }

    public Merchant getMerchant() {
        return merchant;
    }

    private void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public String getTransactionId() {
        return transactionId;
    }

    private void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getAmount() {
        return amount;
    }

    private void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    private void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDateTime getTimeout() {
        return timeout;
    }

    private void setTimeout(LocalDateTime timeout) {
        this.timeout = timeout;
    }

    public boolean hasTimedOut() {
        return getTimeout().isBefore(LocalDateTime.now());
    }

    public void markSuccessful() {

    }

    public void markFailed() {

    }
}
