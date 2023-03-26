package com.twj.math;

import com.twj.math.division.DivisionNoRemainder;
import com.twj.math.enums.Difficulty;

public class Main {
    public static void main(String[] args) {

        int numQnsToGen = 1000;

//        Subtraction sub = new Subtraction();
//
//        sub.normalSubtraction(numQnsToGen, Difficulty.EASY);
//        sub.normalSubtraction(numQnsToGen, Difficulty.INTER);
//        sub.normalSubtraction(numQnsToGen, Difficulty.HARD);
//
//        sub.missingMiddleNumber(numQnsToGen);
//
//        Equality equality = new Equality();
//        equality.createQuestions(numQnsToGen, Difficulty.EASY);
//        equality.createQuestions(numQnsToGen, Difficulty.INTER);
//        equality.createQuestions(numQnsToGen, Difficulty.HARD);

        DivisionNoRemainder divNoRemainder = new DivisionNoRemainder();
        divNoRemainder.createQuestions(5, Difficulty.EASY_DIVISION);
        divNoRemainder.createQuestions(5, Difficulty.INTER_DIVISION);
        divNoRemainder.createQuestions(5, Difficulty.HARD_DIVISION);
    }
}