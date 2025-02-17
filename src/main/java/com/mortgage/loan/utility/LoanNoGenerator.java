package com.mortgage.loan.utility;

import java.security.SecureRandom;
import java.util.Random;

public class LoanNoGenerator {
    private static final Random random = new SecureRandom();
    public static int generateRandomNumber(){
        int lowerBound = 1000000000;
        int upperBound = 2147483647;
        return lowerBound + random.nextInt(upperBound - lowerBound);
    }
}