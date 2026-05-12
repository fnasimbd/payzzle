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


import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Farhan Nasim on 5/11/2026 11:02 PM
 */
public class Iso8583Codec {

    public static final int PRIMARY_BITMAP_LENGTH = 64;

    public String encodeAuthorizationRequest(Iso8583AuthRequest authRequest) {

        StringBuilder sb = new StringBuilder("0100");

        BitSet primaryBitmap = constructPrimaryBitmap(authRequest);

        String primaryBitmapHexString = convertBitSetToHexString(primaryBitmap);

        sb.append(primaryBitmapHexString);

        for (int i = 0; i < primaryBitmap.length(); i++) {
            if (primaryBitmap.get(i)) {
                switch (i) {
                    case 1:
                        sb.append(primaryBitmapHexString);
                        break;
                    case 2:
                        sb.append(authRequest.getPan().length());
                        sb.append(authRequest.getPan());
                        break;
                    case 3:
                        sb.append(authRequest.getProcessingCode());
                        break;
                    case 4:
                        sb.append(authRequest.getAmount());
                        break;
                }
            }
        }

        return sb.toString();
    }

    public Iso8583AuthResponse decodeAuthorizationResponse(String body) {

        Map<Integer, String> dataElements = parseDataElements(body);

        var response = new Iso8583AuthResponse();
        response.setProcessingCode(dataElements.get(3));
        response.setAmount(dataElements.get(4));
        response.setResponseCode(dataElements.get(39));

        return response;
    }

    protected Map<Integer, String> parseDataElements(String body) {

        final Map<Integer, Integer> lengths = new HashMap<>();
        lengths.put(2, 11); // PAN
        lengths.put(3, 3); // processing code
        lengths.put(4, 6); // transaction amount
        lengths.put(6, 6); // cardholder billing amount
        lengths.put(7, 5); // transmission date and time
        lengths.put(10, 4); // cardholder billing conversion rate
        lengths.put(11, 3); // system trace audit number (STAN)
        lengths.put(12, 3); // local transaction time
        lengths.put(13, 2); // local transaction date
        lengths.put(21, 2); // forwarding institution country code
        lengths.put(22, 2); // point of service entry mode
        lengths.put(37, 12); // retrieval reference number
        lengths.put(39, 2); // response code
        lengths.put(41, 7); // card acceptor terminal identification (varies, can be 8 or 15 too)
        lengths.put(46, 0); // invalid message reason
        lengths.put(49, 2); // transaction currency code

        var primaryBitmap = parsePrimaryBitmap(body.substring(4, 20));

        int cur = 20;

        Map<Integer, String> dataElements = new HashMap<>(primaryBitmap.length());

        for (int i = 0; i < primaryBitmap.length(); i++) {

            if (primaryBitmap.get(i)) {

                int len;

                if (i == 2) {
                    len = Integer.parseInt(body.substring(cur, cur + 2));
                    cur += 2;
                } else if (i == 39) {
                    len = lengths.get(i);
                } else if (i == 41) {
                    len = lengths.get(i);
                } else {
                    len = lengths.get(i) * 2;
                }

                String substring = body.substring(cur, cur + len);
                dataElements.put(i, substring);

                cur += len;
            }
        }

        return dataElements;
    }

    private String convertBitSetToHexString(BitSet bitmap) {

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < PRIMARY_BITMAP_LENGTH + 1; i += 4) {

            byte aByte = 0;

            for (int j = 0; j < 4; j++) {
                aByte |= (byte) (bitmap.get(i + j) ? (1 << 3 - j) : 0x00);
            }

            sb.append(String.format("%01x", aByte));
        }

        return sb.toString();
    }

    private BitSet constructPrimaryBitmap(Iso8583AuthRequest authRequest) {

        BitSet primaryBitmap = new BitSet(PRIMARY_BITMAP_LENGTH + 1);

        if (authRequest.getPan() != null) {
            primaryBitmap.set(2);
        }

        if (authRequest.getProcessingCode() != null) {
            primaryBitmap.set(3);
        }

        if (authRequest.getAmount() != null) {
            primaryBitmap.set(4);
        }

        return primaryBitmap;
    }

    protected BitSet parsePrimaryBitmap(String primaryBitmapString) {

        byte[] bitmapBytes = hexStringToBytes(primaryBitmapString);

        BitSet primaryBitmap =  new BitSet(PRIMARY_BITMAP_LENGTH);
        int byteCount  = 0;

        for (int aByte : bitmapBytes) {

            int bit = 0;

            while (aByte > 0) {

                primaryBitmap.set(byteCount + 3 - bit + 1, (aByte & 1) != 0);

                aByte >>= 1;
                bit++;
            }

            byteCount += 4;
        }

        return primaryBitmap;
    }

    private static byte[] hexStringToBytes(String byteString) {

        byte[] bytes = new byte[byteString.length()];

        for (int i = 0; i < byteString.length(); i++) {
            bytes[i] = (byte) Character.digit(byteString.charAt(i), 16);
        }

        return bytes;
    }
}
