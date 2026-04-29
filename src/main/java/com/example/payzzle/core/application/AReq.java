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

package com.example.payzzle.core.application;


import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Farhan Nasim on 4/29/2026 9:39 PM
 */
public class AReq {

    // --- Core message metadata ---
    @JsonProperty("threeDSRequestorTransID")
    private String threeDSRequestorTransID;

    @JsonProperty("threeDSServerTransID")
    private String threeDSServerTransID;

    @JsonProperty("messageVersion")
    private String messageVersion = "2.2.0";

    @JsonProperty("messageType")
    private String messageType = "AReq";

    // --- Card info ---
    @JsonProperty("acctNumber")
    private String acctNumber;

    @JsonProperty("cardExpiryDate")
    private String cardExpiryDate; // YYMM

    // --- Merchant info ---
    @JsonProperty("merchantName")
    private String merchantName;

    @JsonProperty("merchantCountryCode")
    private String merchantCountryCode; // ISO numeric

    @JsonProperty("mcc")
    private String mcc;

    // --- Transaction info ---
    @JsonProperty("purchaseAmount")
    private String purchaseAmount;

    @JsonProperty("purchaseCurrency")
    private String purchaseCurrency;

    @JsonProperty("purchaseExponent")
    private String purchaseExponent = "2";

    @JsonProperty("purchaseDate")
    private String purchaseDate;

    // --- Device / browser ---
    @JsonProperty("deviceChannel")
    private String deviceChannel = "02"; // browser

    @JsonProperty("browserUserAgent")
    private String browserUserAgent;

    @JsonProperty("browserAcceptHeader")
    private String browserAcceptHeader;

    // --- Requestor control ---
    @JsonProperty("threeDSRequestorAuthenticationInd")
    private String threeDSRequestorAuthenticationInd = "01";

    @JsonProperty("threeDSRequestorChallengeInd")
    private String threeDSRequestorChallengeInd = "01";

    // --- URLs ---
    @JsonProperty("notificationURL")
    private String notificationURL;

    @JsonProperty("browserReturnURL")
    private String browserReturnURL;

    public String getThreeDSRequestorTransID() {
        return threeDSRequestorTransID;
    }

    public void setThreeDSRequestorTransID(String threeDSRequestorTransID) {
        this.threeDSRequestorTransID = threeDSRequestorTransID;
    }

    public String getThreeDSServerTransID() {
        return threeDSServerTransID;
    }

    public void setThreeDSServerTransID(String threeDSServerTransID) {
        this.threeDSServerTransID = threeDSServerTransID;
    }

    public String getMessageVersion() {
        return messageVersion;
    }

    public void setMessageVersion(String messageVersion) {
        this.messageVersion = messageVersion;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getAcctNumber() {
        return acctNumber;
    }

    public void setAcctNumber(String acctNumber) {
        this.acctNumber = acctNumber;
    }

    public String getCardExpiryDate() {
        return cardExpiryDate;
    }

    public void setCardExpiryDate(String cardExpiryDate) {
        this.cardExpiryDate = cardExpiryDate;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantCountryCode() {
        return merchantCountryCode;
    }

    public void setMerchantCountryCode(String merchantCountryCode) {
        this.merchantCountryCode = merchantCountryCode;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public String getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(String purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public String getPurchaseCurrency() {
        return purchaseCurrency;
    }

    public void setPurchaseCurrency(String purchaseCurrency) {
        this.purchaseCurrency = purchaseCurrency;
    }

    public String getPurchaseExponent() {
        return purchaseExponent;
    }

    public void setPurchaseExponent(String purchaseExponent) {
        this.purchaseExponent = purchaseExponent;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getDeviceChannel() {
        return deviceChannel;
    }

    public void setDeviceChannel(String deviceChannel) {
        this.deviceChannel = deviceChannel;
    }

    public String getBrowserUserAgent() {
        return browserUserAgent;
    }

    public void setBrowserUserAgent(String browserUserAgent) {
        this.browserUserAgent = browserUserAgent;
    }

    public String getBrowserAcceptHeader() {
        return browserAcceptHeader;
    }

    public void setBrowserAcceptHeader(String browserAcceptHeader) {
        this.browserAcceptHeader = browserAcceptHeader;
    }

    public String getThreeDSRequestorAuthenticationInd() {
        return threeDSRequestorAuthenticationInd;
    }

    public void setThreeDSRequestorAuthenticationInd(String threeDSRequestorAuthenticationInd) {
        this.threeDSRequestorAuthenticationInd = threeDSRequestorAuthenticationInd;
    }

    public String getThreeDSRequestorChallengeInd() {
        return threeDSRequestorChallengeInd;
    }

    public void setThreeDSRequestorChallengeInd(String threeDSRequestorChallengeInd) {
        this.threeDSRequestorChallengeInd = threeDSRequestorChallengeInd;
    }

    public String getNotificationURL() {
        return notificationURL;
    }

    public void setNotificationURL(String notificationURL) {
        this.notificationURL = notificationURL;
    }

    public String getBrowserReturnURL() {
        return browserReturnURL;
    }

    public void setBrowserReturnURL(String browserReturnURL) {
        this.browserReturnURL = browserReturnURL;
    }
}
