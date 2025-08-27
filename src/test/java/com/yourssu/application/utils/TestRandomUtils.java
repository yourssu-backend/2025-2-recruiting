package com.yourssu.application.utils;

import java.lang.reflect.Field;
import java.util.Random;

public class TestRandomUtils {
    private static final String RANDOM_FIELD = "random";

    public static void setFixedRandomSequence(int... values) {
        try {
            Field randomField = RandomUtils.class.getDeclaredField(RANDOM_FIELD);
            randomField.setAccessible(true);
            
            Random mockRandom = new Random() {
                private int index = 0;
                
                @Override
                public int nextInt(int bound) {
                    if (index < values.length) {
                        int value = values[index++] - 1;
                        return Math.min(value, bound - 1);
                    }
                    return super.nextInt(bound);
                }
            };
            randomField.set(null, mockRandom);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    
    public static void resetRandom() {
        RandomUtils.resetRandom();
    }
}
