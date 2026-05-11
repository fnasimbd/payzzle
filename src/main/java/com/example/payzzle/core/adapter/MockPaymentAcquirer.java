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

import java.util.Map;

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
    public Iso8583AuthResponse authorizePaymentRequest(Iso8583AuthRequest authRequest) {

        String data = iso8583Codec.encodeAuthorizationRequest(authRequest);

        ResponseEntity<String> authorizationResponse = restTemplate.postForEntity(
                paymentAuthorizationUrl, data, String.class);

        if (authorizationResponse.getStatusCode().is2xxSuccessful()) {

            String body = authorizationResponse.getBody();

            Map<Integer, String> dataElements = iso8583Codec.decodeAuthorizationResponse(body);

            Iso8583AuthResponse response = new Iso8583AuthResponse();
            response.setProcessingCode(dataElements.get(3));
            response.setAmount(dataElements.get(4));
            response.setResponseCode(dataElements.get(39));

            return response;
        } else {
            // todo: throw an exception
        }

        return null;
    }
}
