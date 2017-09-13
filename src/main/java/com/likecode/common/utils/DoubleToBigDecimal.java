package com.likecode.common.utils;

import java.math.BigDecimal;

/**
 * Created by wangkai on 2017/8/9.
 */
public class DoubleToBigDecimal {

    public static BigDecimal doubleToBigDecimal(double d){
        String doubleStr = String.valueOf(d);
        return new BigDecimal(doubleStr);
    }
}
