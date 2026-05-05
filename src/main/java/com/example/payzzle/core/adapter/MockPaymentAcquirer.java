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


import com.example.payzzle.core.domain.port.PaymentAcquirer;
import com.example.payzzle.core.domain.port.Iso8583AuthRequest;
import com.example.payzzle.core.domain.port.Iso8583AuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Farhan Nasim on 4/14/2026 1:48 AM
 */
@Service
public class MockPaymentAcquirer implements PaymentAcquirer {

    private final RestTemplate restTemplate;

    private String paymentAuthorizationUrl;

    public MockPaymentAcquirer(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Iso8583AuthResponse authorizePaymentRequest(Iso8583AuthRequest authRequest) {

        // todo: serialize 'authRequest' as ISO 8583 0100 message
        // 0110B23AE4012AE080340000000004000020072000000000001860072420050101219016050107
        // 24072407245411084000210A10420003141F0447708090104....D03081015541477F0F2F0F6F0

        ResponseEntity<String> authorizationResponse = restTemplate.postForEntity(
                paymentAuthorizationUrl,
                null, // todo: authRequest serialized to string as request body
                String.class);

        if (authorizationResponse.getStatusCode().is2xxSuccessful()) {

            String body = authorizationResponse.getBody();

            // todo: deserialize body into ISO 8583 0110 response object

            Iso8583AuthResponse response = new Iso8583AuthResponse();

            return response;
        } else {
            // todo: throw an exception
        }

        return null;
    }
}
