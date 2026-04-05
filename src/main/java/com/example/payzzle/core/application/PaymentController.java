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


import com.example.payzzle.core.domain.model.Merchant;
import com.example.payzzle.core.domain.model.Transaction;
import com.example.payzzle.core.domain.repositories.MerchantRepository;
import com.example.payzzle.core.domain.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;

/**
 * Created by Farhan Nasim on 3/28/2026 1:24 AM
 */
@Controller
public class PaymentController {

    private final MerchantRepository merchantRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public PaymentController(MerchantRepository merchantRepository,
                             TransactionRepository transactionRepository) {
        this.merchantRepository = merchantRepository;
        this.transactionRepository = transactionRepository;
    }

    @GetMapping("/init_payment")
    @Transactional
    public String initiatePayment(@RequestParam(name = "transaction_id") String transactionId,
                                  @RequestParam(name = "amount") Integer amount,
                                  @RequestParam(name = "currency") String currency,
                                  @RequestParam(name = "success_url") String successUrl,
                                  @RequestParam(name = "failure_url") String failureUrl,
                                  @RequestParam(name = "cancel_url") String cancelUrl,
                                  Model model) {

        Long merchantId = 1L;
        Merchant merchant =  merchantRepository.withId(merchantId);

        var transaction = merchant.initiateNewTransaction(
                transactionId, amount, currency, successUrl, failureUrl, cancelUrl);

        transactionRepository.save(transaction);

        model.addAttribute("transaction_id", transactionId);
        model.addAttribute("amount", amount);
        model.addAttribute("currency", currency);
        model.addAttribute("success_url", successUrl);
        model.addAttribute("failure_url", failureUrl);
        model.addAttribute("cancel_url", cancelUrl);

        return "/payment_details";
    }

    @PostMapping("/process_payment")
    public ResponseEntity processPayment(@RequestParam(name = "card_number") String cardNumber,
                                         @RequestParam(name = "name_on_card") String nameOnCard,
                                         @RequestParam(name = "expiry_month") String expiryMonth,
                                         @RequestParam(name = "expiry_year") String expiryYear,
                                         @RequestParam(name = "cvv") String cvv,
                                         @RequestParam(name = "transaction_id") String transactionId,
                                         @RequestParam(name = "amount") String amount,
                                         @RequestParam(name = "currency") String currency,
                                         @RequestParam(name = "success_url") String successUrl,
                                         @RequestParam(name = "failure_url") String failureUrl,
                                         @RequestParam(name = "cancel_url") String cancelUrl) {

        Transaction transaction = transactionRepository.withId(transactionId);

        transaction.markSuccessful();

        return ResponseEntity.
                status(HttpStatus.FOUND).
                location(URI.create(successUrl)).
                build();
    }
}
