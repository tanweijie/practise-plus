package com.twj.math;

import com.twj.math.enums.Difficulty;
import com.twj.math.enums.equality.Equality;
import com.twj.math.subtraction.Subtraction;

public class Main {
    public static void main(String[] args) {

        Subtraction sub = new Subtraction();

        sub.normalSubtraction(5, Difficulty.EASY);
        sub.normalSubtraction(5, Difficulty.INTERMEDIATE);
        sub.normalSubtraction(5, Difficulty.HARD);

        sub.missingMiddleNumber(5);

        Equality equality = new Equality();
        equality.createQuestions(5, Difficulty.EASY);
        equality.createQuestions(5, Difficulty.INTERMEDIATE);
        equality.createQuestions(5, Difficulty.HARD);

    }
}