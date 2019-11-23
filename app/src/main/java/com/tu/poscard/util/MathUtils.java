package com.tu.poscard.util;

import java.math.BigDecimal;

import timber.log.Timber;

/**
 * @auther Tu
 * @date 2018/12/12
 * @email enum@foxmail.com
 */
public class MathUtils {
    private MathUtils() {
    }

    public static BigDecimal defaultBigDecimal(BigDecimal bigDecimal) {
        if (null == bigDecimal) {
            return BigDecimal.ZERO;
        }
        return bigDecimal;
    }

    public static BigDecimal format(String str) {
        if (null == str) {
            return defaultBigDecimal(null);
        }
        try {
            BigDecimal bigDecimal = new BigDecimal(str);
            return bigDecimal;
        } catch (NumberFormatException e) {
            Timber.e(e);
        }
        return defaultBigDecimal(null);
    }

    public static String toString(BigDecimal bigDecimal) {
        return toString(bigDecimal,2);
    }

    public static String toString(BigDecimal bigDecimal,int newScale) {
        return defaultBigDecimal(bigDecimal).setScale(newScale, BigDecimal.ROUND_HALF_UP).toString();
    }

    public static BigDecimal subtract(BigDecimal bigDecimal1, BigDecimal bigDecimal2) {
        return defaultBigDecimal(bigDecimal1).subtract(defaultBigDecimal(bigDecimal2));
    }
}
