package com.twj.math.subtraction;

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
public class Subtraction {

    public void normalSubtraction(int numQns, Difficulty difficulty) {

        List<String> lAnswer = new ArrayList<>();
        List<Question> lQns = new ArrayList<>();

        log.info("\nQuestions");
        IntStream.range(1, numQns + 1).forEach(qn -> {
            Random random = new Random();
            int bigNum = DifficultyUtil.getMinuend(difficulty);
            int middleNum = DifficultyUtil.getSubtrahend(difficulty, bigNum);
            int ans = bigNum - middleNum;

            log.info("{}.\t{} - {} = _", qn, bigNum, middleNum);
            lQns.add(new Question(qn, String.format("%d - %d = %s", bigNum, middleNum, DisplayConstants.BLANK_LINE), "" + ans));
            lAnswer.add(qn + "\t- " + ans);
        });

        log.info("\nAnswers");
        lAnswer.forEach(ans -> log.info(ans));

        try {
            PdfGenerator.genPdf("Subtraction", lQns, "./subtraction-" + difficulty.name() + ".pdf");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void missingMiddleNumber(int numQns) {

        List<String> lAnswer = new ArrayList<>();
        List<Question> lQns = new ArrayList<>();

        log.info("\nQuestions");
        IntStream.range(1, numQns + 1).forEach(qnNum -> {
            Random random = new Random();
            int bigNum = random.ints(1001, 9888).findFirst().getAsInt();
            int middleNum = random.ints(199, bigNum - 200).findFirst().getAsInt();
            int ans = bigNum - middleNum;
            String sMiddleNumber = String.valueOf(middleNum);
            String maskedMiddleNumber = "";

            // mask middle num
            if (qnNum % 2 == 0) {
                maskedMiddleNumber = sMiddleNumber.substring(0, 2) + DisplayConstants.SHORT_BLANK_LINE + sMiddleNumber.substring(3);
            }
            else {
                maskedMiddleNumber = sMiddleNumber.substring(0, 1) + DisplayConstants.SHORT_BLANK_LINE + sMiddleNumber.substring(2);
            }
            lQns.add(new Question(qnNum, String.format("%d - %s = %d", bigNum, maskedMiddleNumber, ans), "" + ans));
        });

        try {
            PdfGenerator.genPdf("Subtraction - Missing Middle Number", lQns, "./subtraction-mmn.pdf");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
