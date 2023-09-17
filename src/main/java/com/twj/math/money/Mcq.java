package com.twj.math.money;

import com.twj.math.constants.MoneyConstants;
import com.twj.math.constants.QuestionConstants;
import com.twj.pdf.PdfGenerator;
import com.twj.vo.Question;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.IntStream;

@Slf4j
public class Mcq {
    private List<String> lNames = QuestionConstants.lNames;
    private List<String> lItems = QuestionConstants.lItems;
    private List<Integer> lCents = MoneyConstants.CENTS;
    private List<Integer> lDollars = MoneyConstants.DOLLARS;

    private Question formatCoinsInDollarsQn(int cents, double dollar) {
        int ans = (int)(dollar * 100) / cents;
        double trickAns = ans * 10;
        List<Integer> lAnsChoices = Arrays.asList((int) ans, (int) trickAns, (int) trickAns / 2, (int) trickAns / 5);

        // shuffle e ans
        Collections.shuffle(lAnsChoices);

        // get e mcq answer
        int mcqAns = 0;
        for (int i = 0; i < 4; i++) {
            if (lAnsChoices.get(i) == ans)
                mcqAns = i + 1;
        }

        String qnTemplate = "How many %d cents coins are there in $%s0?%n(1) %d \t\t\t (2) %d \t\t\t (3) %d \t\t\t (4) %d%n%n";
        String ansTemplate = "%d";

        Question question = new Question();
        question.setQn(String.format(qnTemplate, cents, String.valueOf(dollar), lAnsChoices.get(0), lAnsChoices.get(1), lAnsChoices.get(2), lAnsChoices.get(3)));
        question.setAnswer(String.format(ansTemplate, mcqAns));

        return question;
    }

    public void createQuestion(int numQns) {

        List<Question> lQns = new ArrayList<>();

        Question cQn = formatCoinsInDollarsQn(5, 6);
        cQn.setQnNum(1);
        lQns.add(cQn);
        log.info("Control question - {}", cQn);

        IntStream.range(2, numQns + 1).forEach(qn -> {
            Random random = new Random();
            int idxCents = random.ints(0, 4).findFirst().getAsInt();
            int idxDollars = random.ints(0, 3).findFirst().getAsInt();

            int cents = lCents.get(idxCents);
            double randomDollarsFactor = new BigDecimal(random.doubles(0.1, 19).findFirst().getAsDouble()).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
            double dollars = new BigDecimal(lDollars.get(idxDollars) * randomDollarsFactor).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            Question question = formatCoinsInDollarsQn(cents, dollars);
            question.setQnNum(qn);

            lQns.add(question);
            log.info("{} - {}", qn, question);
        });

        PdfGenerator.genPdf("Money - MCQ", lQns, "./pdf/money-mcq.pdf");
    }
}
