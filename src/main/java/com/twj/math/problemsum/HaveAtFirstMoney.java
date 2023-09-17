package com.twj.math.problemsum;

import com.twj.math.constants.QuestionConstants;
import com.twj.pdf.PdfGenerator;
import com.twj.vo.Question;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Slf4j
public class HaveAtFirstMoney {
    private List<String> lNames = QuestionConstants.lNames;
    private List<String> lItems = QuestionConstants.lItem;

    private Question formatQn(String name, String item1, String item2, double amtLeft, double amtSpent1, double amtSpent2) {
        String qnTemplate = "%s had $%s left after spending $%s on a %s and $%s on a %s.%nHow many money did %s have at first?%n%n%n%n%n%n";
        String ansTemplate = "$%s + $%s + $%s = $%s";

        Question question = new Question();
        question.setQn(String.format(qnTemplate, name, String.valueOf(amtLeft), String.valueOf(amtSpent1), item1, String.valueOf(amtSpent2), item2, name));
        question.setAnswer(String.format(ansTemplate,
                                         String.valueOf(amtLeft),
                                         String.valueOf(amtSpent1),
                                         String.valueOf(amtSpent2),
                                         String.valueOf(amtLeft + amtSpent1 + amtSpent2)));

        return question;
    }

    public void createQuestion(int numQns) {

        List<Question> lQns = new ArrayList<>();

        Question cQn = formatQn("Peter", "story book", "packet of potato chips", 8.65, 19.90, 2.95);
        cQn.setQnNum(1);
        lQns.add(cQn);
        log.info("Control question - {}", cQn);

        IntStream.range(2, numQns + 1).forEach(qn -> {
            Random random = new Random();
            double item1Price = new BigDecimal(random.doubles(0.8, 20).findFirst().getAsDouble()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            double item2Price = new BigDecimal(random.doubles(5, 18).findFirst().getAsDouble()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            double amtLeft = new BigDecimal(random.doubles(0.5, 12).findFirst().getAsDouble()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

            int idxName = random.ints(1, 100).findAny().getAsInt();
            int idxItem1 = random.ints(1, 23).findAny().getAsInt();
            int idxItem2 = random.ints(1, 23).findAny().getAsInt();
            Question question = formatQn(lNames.get(idxName), lItems.get(idxItem1), lItems.get(idxItem2), amtLeft, item1Price, item2Price);
            question.setQnNum(qn);

            lQns.add(question);
            log.info("{} - {}", qn, question);
        });

        PdfGenerator.genPdf("Problem Sum - Have At First (Money)", lQns, "./pdf/problemsum-haf-money.pdf");
    }
}
