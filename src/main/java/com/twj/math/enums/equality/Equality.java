package com.twj.math.enums.equality;

import com.twj.math.constants.DisplayConstants;
import com.twj.math.enums.Difficulty;
import com.twj.pdf.PdfGenerator;
import com.twj.util.DifficultyUtil;
import com.twj.vo.Question;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Slf4j
public class Equality {

    public void createQuestions(int numQns, Difficulty difficulty) {

        List<String> lAnswer = new ArrayList<>();
        List<Question> lQns = new ArrayList<>();

        log.info("\nQuestions");
        IntStream.range(1, numQns + 1).forEach(qn -> {
            Random random = new Random();
            int num1 = DifficultyUtil.getaddend(difficulty);
            int num2 = DifficultyUtil.getaddend(difficulty);
            int num4 = DifficultyUtil.getSubtrahend(difficulty, num1 > num2 ? num2 : num1);
            int sum = num1 + num2;
            int ans = sum - num4;

            log.info("{}.\t{} + {} = ___ - {} = {}", qn, num1, num2, num4, sum);
            lQns.add(new Question(qn,
                                  String.format("%d + %d = %s - %d = %s", num1, num2, DisplayConstants.BLANK_LINE, num4, DisplayConstants.BLANK_LINE),
                                  ans + "," + sum));
            lAnswer.add(qn + "\t- " + ans + "," + sum);
        });

        log.info("\nAnswers");
        lAnswer.forEach(ans -> log.info(ans));

        try {
            PdfGenerator.genPdf("Equality - Missing Number and Result", lQns, "./equality-mnar-" + difficulty.name() + ".pdf");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
