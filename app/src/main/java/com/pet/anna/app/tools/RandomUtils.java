package com.pet.anna.app.tools;

import java.util.Random;

/**
 * @author Yevhenii.Khramtsov
 */
public final class RandomUtils {

    private static final Random RANDOM = new Random();

    public static double[] createRandomArray(int size, double lowerBound, double upperBound) {
        final double[] randomArray = new double[size];
        for (int i = 0; i < size; i++) {
            randomArray[i] = randomValue(lowerBound, upperBound);
        }

        return randomArray;
    }

    public static double[][] createRandomArray(int width, int height, double lowerBound, double upperBound) {
        final double[][] randomArray = new double[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                randomArray[i][j] = randomValue(lowerBound, upperBound);
            }
        }

        return randomArray;
    }

    public static double randomValue(double lowerBound, double upperBound) {
        if (upperBound < lowerBound) {
            throw new IllegalArgumentException("lowerBound is bigger than upperBound");
        }
        return RANDOM.nextDouble() * (upperBound - lowerBound) + lowerBound;
    }
}