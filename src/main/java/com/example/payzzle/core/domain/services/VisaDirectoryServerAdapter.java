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

package com.example.payzzle.core.domain.services;


import com.example.payzzle.core.application.AReq;
import com.example.payzzle.core.application.ARes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Farhan Nasim on 4/29/2026 9:33 PM
 */
public class VisaDirectoryServerAdapter implements DirectoryServerAdapter {

    private final String directoryServerUrl = "http://localhost:8080/card_network/authenticate_owner";

    private RestTemplate restTemplate;

    @Override
    public ARes authenticate(AReq request) {

        ResponseEntity<ARes> response = restTemplate.postForEntity(directoryServerUrl, request, ARes.class);

        if (response.getStatusCode().isError()) {
            throw new RuntimeException(response.getStatusCode().toString()); // todo: throw appropriate exception
        }

        return response.getBody();
    }
}
