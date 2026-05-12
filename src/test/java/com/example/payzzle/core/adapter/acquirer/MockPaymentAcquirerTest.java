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

package com.example.payzzle.core.adapter.acquirer;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Created by Farhan Nasim on 5/9/2026 7:06 PM
 */
class MockPaymentAcquirerTest {

    @Test
    void parse_primary_bitmap_test() {

        Iso8583Codec iso8583Codec = new Iso8583Codec();

        BitSet primaryBitmap = iso8583Codec.parsePrimaryBitmap("7238040000020000");

        assertThat(primaryBitmap.cardinality()).isEqualTo(9);
    }

    @Test
    void parse_authorization_response_test() {

        Iso8583Codec iso8583Codec = new Iso8583Codec();

        String response = "0110" +
                "3020000002800000" +
                "000000" +
                "000000001000" +
                "123456" +
                "00" +
                "TERMID1";

        Map<Integer, String> integerStringMap = iso8583Codec.parseDataElements(response);

        assertThat(integerStringMap.size()).isEqualTo(5);

        ArrayList<Integer> elements = new ArrayList<>();
        elements.add(3);
        elements.add(4);
        elements.add(11);
        elements.add(39);
        elements.add(41);

        assertThat(integerStringMap.keySet().toArray()).containsAll(elements);
    }

    @Test
    void encode_authorization_request_test() {

        Iso8583Codec iso8583Codec = new Iso8583Codec();

        Iso8583AuthRequest authRequest = new Iso8583AuthRequest();
        authRequest.setPan("4761739001010010");
        authRequest.setProcessingCode("072000");
        authRequest.setAmount("100");

        String message = iso8583Codec.encodeAuthorizationRequest(authRequest);
    }
}
