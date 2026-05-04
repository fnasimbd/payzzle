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

import java.util.List;

/**
 * Created by Farhan Nasim on 5/2/2026 1:39 PM
 */
public class BinData {
//    {
//        "bin": "670300",
//        "scheme": "MAESTRO",
//        "brand": "MAESTRO DEBIT",
//        "type": "DEBIT",
//        "funding": "CONSUMER",
//        "issuer": {
//            "name": "Santander",
//            "country": "ES",
//            "currency": "EUR"
//        },
//        "prepaid": false,
//        "commercial": false,
//        "3ds_supported": true,
//        "co_badged": true,
//        "co_badged_schemes": ["MASTERCARD", "MAESTRO"]
//    }
    private String bin;
    private String scheme;
    private String brand;
    private String type;
    private String funding;
    private Issuer issuer;
    private boolean prepaid;
    private boolean commercial;
    @JsonProperty("3ds_supported")
    private boolean threeDsSupported;
    private boolean coBadged;
    private List<String> coBadgedSchemes;

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public boolean isPrepaid() {
        return prepaid;
    }

    public void setPrepaid(boolean prepaid) {
        this.prepaid = prepaid;
    }

    public String getFunding() {
        return funding;
    }

    public void setFunding(String funding) {
        this.funding = funding;
    }

    public Issuer getIssuer() {
        return issuer;
    }

    public void setIssuer(Issuer issuer) {
        this.issuer = issuer;
    }

    public boolean isCommercial() {
        return commercial;
    }

    public void setCommercial(boolean commercial) {
        this.commercial = commercial;
    }

    public boolean isThreeDsSupported() {
        return threeDsSupported;
    }

    public void setThreeDsSupported(boolean threeDsSupported) {
        this.threeDsSupported = threeDsSupported;
    }

    public boolean isCoBadged() {
        return coBadged;
    }

    public void setCoBadged(boolean coBadged) {
        this.coBadged = coBadged;
    }

    public List<String> getCoBadgedSchemes() {
        return coBadgedSchemes;
    }

    public void setCoBadgedSchemes(List<String> coBadgedSchemes) {
        this.coBadgedSchemes = coBadgedSchemes;
    }
}
