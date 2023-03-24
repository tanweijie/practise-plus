package com.twj.vo;

public class Question {

    private int qnNum;
    private String qn;
    private String answer;

    public Question(int qnNum, String qn, String answer) {
        this.qnNum = qnNum;
        this.qn = qn;
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getQnNum() {
        return qnNum;
    }

    public void setQnNum(int qnNum) {
        this.qnNum = qnNum;
    }

    public String getQn() {
        return qn;
    }

    public void setQn(String qn) {
        this.qn = qn;
    }
}
