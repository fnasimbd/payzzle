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

package com.example.payzzle.core.adapter;


import com.example.payzzle.core.domain.port.BinLookupService;
import com.example.payzzle.core.domain.port.BinData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Farhan Nasim on 5/4/2026 8:40 PM
 */
@Service
public class MockBinLookupService implements BinLookupService {

    private final String data = """
        [
          {
            "bin": "411111",
            "scheme": "VISA",
            "brand": "VISA CLASSIC",
            "type": "CREDIT",
            "funding": "CONSUMER",
            "issuer": {
              "name": "JPMorgan Chase Bank",
              "country": "US",
              "currency": "USD"
            },
            "prepaid": false,
            "commercial": false,
            "3ds_supported": true,
            "co_badged": false
          },
          {
            "bin": "550000",
            "scheme": "MASTERCARD",
            "brand": "WORLD MASTERCARD",
            "type": "CREDIT",
            "funding": "CONSUMER",
            "issuer": {
              "name": "Citibank",
              "country": "US",
              "currency": "USD"
            },
            "prepaid": false,
            "commercial": false,
            "3ds_supported": true,
            "co_badged": false
          },
          {
            "bin": "340000",
            "scheme": "AMEX",
            "brand": "AMERICAN EXPRESS",
            "type": "CREDIT",
            "funding": "CONSUMER",
            "issuer": {
              "name": "American Express",
              "country": "US",
              "currency": "USD"
            },
            "prepaid": false,
            "commercial": false,
            "3ds_supported": true,
            "co_badged": false
          },
          {
            "bin": "356600",
            "scheme": "JCB",
            "brand": "JCB GOLD",
            "type": "CREDIT",
            "funding": "CONSUMER",
            "issuer": {
              "name": "Mizuho Bank",
              "country": "JP",
              "currency": "JPY"
            },
            "prepaid": false,
            "commercial": false,
            "3ds_supported": true,
            "co_badged": false
          },
          {
            "bin": "620000",
            "scheme": "UNIONPAY",
            "brand": "UNIONPAY STANDARD",
            "type": "DEBIT",
            "funding": "CONSUMER",
            "issuer": {
              "name": "Industrial and Commercial Bank of China",
              "country": "CN",
              "currency": "CNY"
            },
            "prepaid": false,
            "commercial": false,
            "3ds_supported": false,
            "co_badged": false
          },
          {
            "bin": "400000",
            "scheme": "VISA",
            "brand": "VISA BUSINESS",
            "type": "CREDIT",
            "funding": "COMMERCIAL",
            "issuer": {
              "name": "HSBC Bank",
              "country": "GB",
              "currency": "GBP"
            },
            "prepaid": false,
            "commercial": true,
            "3ds_supported": true,
            "co_badged": false
          },
          {
            "bin": "457100",
            "scheme": "VISA",
            "brand": "VISA PREPAID",
            "type": "DEBIT",
            "funding": "PREPAID",
            "issuer": {
              "name": "DBS Bank",
              "country": "SG",
              "currency": "SGD"
            },
            "prepaid": true,
            "commercial": false,
            "3ds_supported": true,
            "co_badged": false
          },
          {
            "bin": "506699",
            "scheme": "VERVE",
            "brand": "VERVE STANDARD",
            "type": "DEBIT",
            "funding": "CONSUMER",
            "issuer": {
              "name": "Access Bank",
              "country": "NG",
              "currency": "NGN"
            },
            "prepaid": false,
            "commercial": false,
            "3ds_supported": false,
            "co_badged": false
          },
          {
            "bin": "670300",
            "scheme": "MAESTRO",
            "brand": "MAESTRO DEBIT",
            "type": "DEBIT",
            "funding": "CONSUMER",
            "issuer": {
              "name": "Santander",
              "country": "ES",
              "currency": "EUR"
            },
            "prepaid": false,
            "commercial": false,
            "3ds_supported": true,
            "co_badged": true,
            "co_badged_schemes": ["MASTERCARD", "MAESTRO"]
          }
        ]
        """;

    private final ObjectMapper objectMapper;

    @Autowired
    public MockBinLookupService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public BinData findBinData(String bin) {

        try {
            List<BinData> binDataSet = objectMapper.readValue(data, new TypeReference<>() {});

            return binDataSet.stream().
                    filter(entry -> entry.getBin().startsWith(bin.substring(0, 5))).findFirst().orElseThrow();

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
