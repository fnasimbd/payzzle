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
import com.example.payzzle.core.domain.model.Card;
import org.springframework.stereotype.Service;

/**
 * Created by Farhan Nasim on 4/18/2026 2:51 PM
 */
@Service
public class ThreeDSAuthenticatorImpl implements ThreeDSAuthenticator {

    @Override
    public ARes authenticate(Card card) {

        DirectoryServerAdapter directoryServerAdapter = null;

        if (card.getScheme().equals("VISA")) {
            directoryServerAdapter = new VisaDirectoryServerAdapter();
        }

        AReq request = new AReq();

        // todo: populate AReq

        return directoryServerAdapter.authenticate(request);
    }
}
