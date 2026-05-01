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


import com.example.payzzle.core.domain.model.Card;
import org.springframework.stereotype.Service;

/**
 * Created by Farhan Nasim on 4/13/2026 1:14 AM
 */
@Service
public class CardIssuerResolverImpl implements CardIssuerResolver {

    @Override
    public Card resolveCardDetails(String cardNumber, String nameOnCard, Integer expiryMonth, Integer expiryYear, Integer cvv) {

        String binNumber = cardNumber.substring(0, 1);

        switch (binNumber) {
            case "4": return new Card(cardNumber, binNumber, "VISA", nameOnCard, expiryMonth, expiryYear);
            case "5": return new Card(cardNumber, binNumber, "MASTERCARD", nameOnCard, expiryMonth, expiryYear);
            case "3": return new Card(cardNumber, binNumber, "AMEX", nameOnCard, expiryMonth, expiryYear);
            default: throw new RuntimeException("Unsupported card type");
        }
    }
}
