package com.twj.math.equality;

import com.twj.math.constants.DisplayConstants;
import com.twj.math.enums.Difficulty;
import com.twj.pdf.PdfGenerator;
import com.twj.util.DifficultyUtil;
import com.twj.vo.Question;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Slf4j
public class Equality {

    public void createQuestions(int numQns, Difficulty difficulty) {

        List<Question> lQns = new ArrayList<>();

        log.info("\nQuestions");
        IntStream.range(1, numQns + 1).forEach(qnNum -> {
            Random random = new Random();
            int num1 = DifficultyUtil.getAddend(difficulty);
            int num2 = DifficultyUtil.getAddend(difficulty);
            int num4 = DifficultyUtil.getSubtrahend(difficulty, num1 > num2 ? num2 : num1);
            int sum = num1 + num2;
            int ans = sum + num4;

            log.info("{}.\t{} + {} = ___ - {} = {}", qnNum, num1, num2, num4, sum);
            lQns.add(new Question(qnNum,
                                  String.format("%d + %d = %s - %d = %s", num1, num2, DisplayConstants.BLANK_LINE, num4, DisplayConstants.BLANK_LINE),
                                  ans + ", " + sum));
        });

        PdfGenerator.genPdf("Equality - Missing Number and Result", lQns, "./pdf/equality-mnar-" + difficulty.name() + ".pdf");
    }
}
