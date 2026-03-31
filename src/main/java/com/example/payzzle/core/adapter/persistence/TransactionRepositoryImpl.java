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

package com.example.payzzle.core.adapter.persistence;


import com.example.payzzle.core.domain.model.Transaction;
import com.example.payzzle.core.domain.repositories.TransactionRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Farhan Nasim on 3/31/2026 1:41 AM
 */
@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

    private final EntityManager em;

    @Autowired
    public TransactionRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Transaction save(Transaction transaction) {
         em.persist(transaction);
         return transaction;
    }

    @Override
    public Transaction withId(String transactionId) {

        TypedQuery<Transaction> query =
                em.createQuery("select t from Transaction t where t.transactionId = :transactionId", Transaction.class);

        query.setParameter("transactionId", transactionId);

        return query.getSingleResult();
    }
}
