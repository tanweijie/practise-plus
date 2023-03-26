package com.twj.math.division;

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

/**
 * The formula to calculate the division of two numbers is: Dividend ÷ Divisor = Quotient + Remainder.
 * The remainder is the leftover number in the division operation. If 46 is divided by 5, we get the quotient as 9 and the remainder 1.
 */
@Slf4j
public class DivisionNoRemainder {

    public void createQuestions(int numQns, Difficulty difficulty) {

        List<Question> lQns = new ArrayList<>();

        log.info("\nQuestions");
        IntStream.range(1, numQns + 1).forEach(qn -> {
            Random random = new Random();
            int divisor = DifficultyUtil.getDivisor(difficulty);
            int dividend = divisor * DifficultyUtil.getDividend(difficulty);
            int ans = dividend / divisor;

            log.info("{}.\t{} / {} = _ ({})", qn, dividend, divisor, ans);
            lQns.add(new Question(qn, String.format("%d / %d = %s", dividend, divisor, DisplayConstants.BLANK_LINE), "" + ans));
        });

        PdfGenerator.genPdf("Division (No Remainder)", lQns, "./pdf/division-" + difficulty.name() + ".pdf");
    }
}
