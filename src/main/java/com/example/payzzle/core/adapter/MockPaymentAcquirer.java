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


import com.example.payzzle.core.domain.model.AuthorizationResult;
import com.example.payzzle.core.domain.port.PaymentAcquirer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Farhan Nasim on 4/14/2026 1:48 AM
 */
@Service
public class MockPaymentAcquirer implements PaymentAcquirer {

    private final RestTemplate restTemplate;
    private final Iso8583Codec iso8583Codec;

    private String paymentAuthorizationUrl;

    public MockPaymentAcquirer(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.iso8583Codec = new Iso8583Codec();
    }

    @Override
    public AuthorizationResult authorizePaymentRequest(String cardNumber,
                                                       Integer amount) {

        Iso8583AuthRequest authRequest = new Iso8583AuthRequest();
        authRequest.setPan(cardNumber);
        authRequest.setAmount(amount.toString());

        String data = iso8583Codec.encodeAuthorizationRequest(authRequest);

        ResponseEntity<String> authorizationResponse = restTemplate.postForEntity(
                paymentAuthorizationUrl, data, String.class);

        if (authorizationResponse.getStatusCode().is2xxSuccessful()) {

            String body = authorizationResponse.getBody();

            Iso8583AuthResponse response = iso8583Codec.decodeAuthorizationResponse(body);

            var response1 = new AuthorizationResult();
            response1.setApproved(response.getResponseCode().equals("00"));

            return response1;
        } else {
            // todo: throw an exception
        }

        return null;
    }
}
