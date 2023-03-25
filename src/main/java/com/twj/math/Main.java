package com.twj.math;

import com.twj.math.enums.Difficulty;
import com.twj.math.enums.equality.Equality;
import com.twj.math.subtraction.Subtraction;

public class Main {
    public static void main(String[] args) {

        Subtraction sub = new Subtraction();
        int numQnsToGen = 1000;

        sub.normalSubtraction(numQnsToGen, Difficulty.EASY);
        sub.normalSubtraction(numQnsToGen, Difficulty.INTER);
        sub.normalSubtraction(numQnsToGen, Difficulty.HARD);

        sub.missingMiddleNumber(numQnsToGen);

        Equality equality = new Equality();
        equality.createQuestions(numQnsToGen, Difficulty.EASY);
        equality.createQuestions(numQnsToGen, Difficulty.INTER);
        equality.createQuestions(numQnsToGen, Difficulty.HARD);

    }
}