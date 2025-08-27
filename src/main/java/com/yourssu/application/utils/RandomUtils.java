package com.yourssu.application.utils;

import java.util.Random;

public class RandomUtils {
    private static Random random = new Random();
    
    public static int nextInt(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException();
        }
        return random.nextInt(max - min + 1) + min;
    }
    
    public static void resetRandom() {
        random = new Random();
    }
}
