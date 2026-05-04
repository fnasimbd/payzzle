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

package com.example.payzzle.core.domain.port;


import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Farhan Nasim on 4/29/2026 9:55 PM
 */
public class ARes {

//    This minimal ARes lets you handle the two core outcomes from the Access Control Server:

//    ✅ Frictionless flow
//    transStatus = "Y" → authenticated
//    transStatus = "N" → failed
//    transStatus = "U" → unavailable
//
//👉 You can complete or fail the transaction immediately.
//
//    🔐 Challenge flow
//    transStatus = "C"
//    Use acsURL to redirect the user to the challenge
//
//👉 This is the only extra data you really need for a demo.

    // --- Core message metadata ---
    @JsonProperty("threeDSServerTransID")
    private String threeDSServerTransID;

    @JsonProperty("acsTransID")
    private String acsTransID;

    @JsonProperty("dsTransID")
    private String dsTransID;

    @JsonProperty("messageVersion")
    private String messageVersion;

    @JsonProperty("messageType")
    private String messageType = "ARes";

    // --- Transaction status ---
    @JsonProperty("transStatus")
    private String transStatus; // Y, N, U, C, R

    // --- Challenge info (only if transStatus = C) ---
    @JsonProperty("acsChallengeMandated")
    private String acsChallengeMandated;

    @JsonProperty("acsURL")
    private String acsURL;

    // --- Optional message / error context ---
    @JsonProperty("messageCategory")
    private String messageCategory; // 01 = PA, 02 = NPA

    @JsonProperty("transStatusReason")
    private String transStatusReason;

    public String getThreeDSServerTransID() {
        return threeDSServerTransID;
    }

    public void setThreeDSServerTransID(String threeDSServerTransID) {
        this.threeDSServerTransID = threeDSServerTransID;
    }

    public String getAcsTransID() {
        return acsTransID;
    }

    public void setAcsTransID(String acsTransID) {
        this.acsTransID = acsTransID;
    }

    public String getDsTransID() {
        return dsTransID;
    }

    public void setDsTransID(String dsTransID) {
        this.dsTransID = dsTransID;
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

    public String getTransStatus() {
        return transStatus;
    }

    public void setTransStatus(String transStatus) {
        this.transStatus = transStatus;
    }

    public String getAcsChallengeMandated() {
        return acsChallengeMandated;
    }

    public void setAcsChallengeMandated(String acsChallengeMandated) {
        this.acsChallengeMandated = acsChallengeMandated;
    }

    public String getAcsURL() {
        return acsURL;
    }

    public void setAcsURL(String acsURL) {
        this.acsURL = acsURL;
    }

    public String getMessageCategory() {
        return messageCategory;
    }

    public void setMessageCategory(String messageCategory) {
        this.messageCategory = messageCategory;
    }

    public String getTransStatusReason() {
        return transStatusReason;
    }

    public void setTransStatusReason(String transStatusReason) {
        this.transStatusReason = transStatusReason;
    }
}
