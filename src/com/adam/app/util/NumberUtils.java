/**
 * ===========================================================================
 * Copyright Adam Sample code
 * All Rights Reserved
 * ===========================================================================
 * 
 * File Name: NumberUtils.java
 * Brief: 
 * 
 * Author: AdamChen
 * Create Date: 2018/1/18
 */

package com.adam.app.util;

public class NumberUtils {
    /**
     * Convert a byte to unsigned int.
     */
    public static int unsignedByteToInt(byte b) {
        return b & 0xFF;
    }

    /**
     * Convert a little endian byte array to integer.
     */
    public static int littleEndianByteArrayToInt(byte[] bytes) {
        int length = bytes.length;
        if (length == 0) {
            return 0;
        }
        int result = 0;
        for (int i = length - 1; i >= 0; i--) {
            int value = unsignedByteToInt(bytes[i]);
            result += (value << (i * 8));
        }
        return result;
    }
}
