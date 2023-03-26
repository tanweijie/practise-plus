package com.twj.math.enums;

public enum Difficulty {
    EASY(21, 99, 19, 89),
    INTER(101, 999, 99, 988),
    HARD(1001, 9999, 199, 9888),
    EASY_DIVISION(10, 50, 2, 10),
    INTER_DIVISION(11, 40, 11, 25),
    HARD_DIVISION(61, 99, 51, 79);

    private int front;
    private int back;
    private int middleFront;
    private int middleEnd;

    Difficulty(int front, int end, int middleFront, int middleEnd) {
        this.front = front;
        this.back = end;
        this.middleFront = middleFront;
        this.middleEnd = middleEnd;
    }

    public int getFront() {
        return front;
    }

    public int getBack() {
        return back;
    }

    public int getMiddleFront() {
        return middleFront;
    }

    public int getMiddleEnd() {
        return middleEnd;
    }
}
