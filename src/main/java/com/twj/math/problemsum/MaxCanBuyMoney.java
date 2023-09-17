package com.twj.math.problemsum;

import com.twj.math.constants.QuestionConstants;
import com.twj.pdf.PdfGenerator;
import com.twj.vo.Question;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Slf4j
public class MaxCanBuyMoney {
    private List<String> lNames = QuestionConstants.lNames;
    private List<String> lItems = QuestionConstants.lItems;

    private Question formatQn(String name, String item, int amtOnHand, int itemQty, int amtItem) {
        String qnTemplate = "%s has $%d, %s are sold at %d for $%d.%nHow many %s can she buy?%n%n%n%n%n%n";
        String ansTemplate = "($%s / $%s) x %s groups = %d %s";

        Question question = new Question();
        question.setQn(String.format(qnTemplate, name, amtOnHand, item, amtItem, itemQty, item));
        question.setAnswer(String.format(ansTemplate,
                                         String.valueOf(amtOnHand),
                                         String.valueOf(amtItem),
                                         String.valueOf(itemQty),
                                         (amtOnHand / amtItem) * itemQty,
                                         item));
        return question;
    }

    public void createQuestion(int numQns) {

        List<Question> lQns = new ArrayList<>();

        Question cQn = formatQn("Time", "pens", 30, 2, 6);
        cQn.setQnNum(1);
        lQns.add(cQn);
        log.info("Control question - {}", cQn);

        IntStream.range(2, numQns + 1).forEach(qn -> {
            Random random = new Random();
            int amtOnHand = random.ints(30, 60).findFirst().getAsInt();
            int itemQty = random.ints(2, 6).findFirst().getAsInt();
            int amtItem = random.ints(2, 11).findFirst().getAsInt();

            int idxName = random.ints(1, 100).findAny().getAsInt();
            int idxItem = random.ints(1, 23).findAny().getAsInt();
            Question question = formatQn(lNames.get(idxName), lItems.get(idxItem), amtOnHand, itemQty, amtItem);
            question.setQnNum(qn);

            lQns.add(question);
            log.info("{} - {}", qn, question);
        });

        PdfGenerator.genPdf("Problem Sum - Maximum can buy (Money)", lQns, "./pdf/problemsum-mcb-money.pdf");
    }
}
