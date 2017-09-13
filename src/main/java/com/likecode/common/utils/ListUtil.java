package com.likecode.common.utils;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {

    public static <F, T> List<T> transform(List<F> source, Class clazz) throws IllegalAccessException,
            InstantiationException {
        List<T> result = new ArrayList<>();
        source.forEach((src) -> {
            Object target = null;
            try {
                target = clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            BeanUtils.copyProperties(src, target);
            result.add((T) target);
        });
        return result;
    }

}
