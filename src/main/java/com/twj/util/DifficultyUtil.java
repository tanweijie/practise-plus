package com.twj.util;

import com.twj.math.enums.Difficulty;

import java.util.Random;

public class DifficultyUtil {

    public static int getMinuend(Difficulty difficulty) {
        Random random = new Random();
        return random.ints(difficulty.getFront(), difficulty.getBack()).findFirst().getAsInt();
    }

    public static int getSubtrahend(Difficulty difficulty, int minuend) {
        Random random = new Random();
        return random.ints(difficulty.getMiddleFront(), minuend - 1).findFirst().getAsInt();
    }

    public static int getAddend(Difficulty difficulty) {
        Random random = new Random();
        return random.ints(difficulty.getFront(), difficulty.getBack()).findFirst().getAsInt();
    }
}
