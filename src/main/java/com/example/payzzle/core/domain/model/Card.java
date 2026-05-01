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


import java.time.LocalDate;
import java.util.Date;

/**
 * Created by Farhan Nasim on 4/12/2026 8:40 PM
 */
public class Card {

    private String cardNumber;
    private String bin;
    private String scheme;
    private String nameOnCard;
    private Integer expiryMonth;
    private Integer expiryYear;
    private String type;
    private String brand;
    private String bank;
    private String country;

    public Card(String cardNumber,
                String bin,
                String scheme,
                String nameOnCard,
                Integer expiryMonth,
                Integer expiryYear) {
        setCardNumber(cardNumber);
        setBin(bin);
        setScheme(scheme);
        setNameOnCard(nameOnCard);
        setExpiryMonth(expiryMonth);
        setExpiryYear(expiryYear);
    }

    public String getCardNumber() {
        return cardNumber;
    }

    private void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getBin() {
        return bin;
    }

    private void setBin(String bin) {
        this.bin = bin;
    }

    public String getScheme() {
        return scheme;
    }

    private void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    private void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public Integer getExpiryMonth() {
        return expiryMonth;
    }

    private void setExpiryMonth(Integer expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    public Integer getExpiryYear() {
        return expiryYear;
    }

    private void setExpiryYear(Integer expiryYear) {
        this.expiryYear = expiryYear;
    }

    public String getType() {
        return type;
    }

    private void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    private void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBank() {
        return bank;
    }

    private void setBank(String bank) {
        this.bank = bank;
    }

    public String getCountry() {
        return country;
    }

    private void setCountry(String country) {
        this.country = country;
    }

    public Date expiryDate() {
        LocalDate expiryDate = LocalDate.of(getExpiryYear(), getExpiryMonth(), 1);
        return java.sql.Date.valueOf(expiryDate);
    }
}
