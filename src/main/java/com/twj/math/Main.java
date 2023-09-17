package com.twj.math;

import com.twj.math.problemsum.Age;

public class Main {
    public static void main(String[] args) {

        int numQnsToGen = 10;

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
//
//        DivisionNoRemainder divNoRemainder = new DivisionNoRemainder();
//        divNoRemainder.createQuestions(5, Difficulty.EASY_DIVISION);
//        divNoRemainder.createQuestions(5, Difficulty.INTER_DIVISION);
//        divNoRemainder.createQuestions(5, Difficulty.HARD_DIVISION);

//        HaveAtFirst haveAtFirst = new HaveAtFirst();
//        haveAtFirst.createQuestion(numQnsToGen);
//
//        Proportion proportion = new Proportion();
//        proportion.createQuestion(numQnsToGen);
//
//        EqualAtFirst equalAtFirst = new EqualAtFirst();
//        equalAtFirst.createQuestion(5);

//        Mcq moneyMcq = new Mcq();
//        moneyMcq.createQuestion(12);
//
//        HaveAtFirstMoney hafm = new HaveAtFirstMoney();
//        hafm.createQuestion(6);

//        MaxCanBuyMoney mcb = new MaxCanBuyMoney();
//        mcb.createQuestion(6);

        Age problem = new Age();
        problem.createQuestion(6);
    }
}