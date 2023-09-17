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
public class EqualAtFirst {
    private List<String> lNames = QuestionConstants.lNames;
    private List<String> lItems = QuestionConstants.lItems;

    public Question formatQn(String name1, String name2, String item, int giveAwayNum1, int giveAwayNum2) {
        String qnTemplate = "%s and %s had an equal amount of %s at first.%n %s gave away %d %s and %s gave away %d %s.%n %s had twice as much %s left as %s.%n a) How many %s did %s have left?%n b) How many %s did %s have at first? %n%n%n%n%n%n%n%n%n%n%n";
        String ansTemplate = "a. %d - %d = %d%n b. (%d x 2) + %d = %d";

        Question question = new Question();
        question.setQn(String.format(qnTemplate,
                                     name1,
                                     name2,
                                     item,
                                     name1,
                                     giveAwayNum1,
                                     item,
                                     name2,
                                     giveAwayNum2,
                                     item,
                                     name2,
                                     item,
                                     name1,
                                     item,
                                     name1,
                                     item,
                                     name2));

        question.setAnswer(String.format(ansTemplate,
                                         giveAwayNum1,
                                         giveAwayNum2,
                                         giveAwayNum1 - giveAwayNum2,
                                         (giveAwayNum1 - giveAwayNum2),
                                         giveAwayNum2,
                                         ((giveAwayNum1 - giveAwayNum2) * 2) + giveAwayNum2));

        return question;
    }

    public void createQuestion(int numQns) {

        List<Question> lQns = new ArrayList<>();

        Question cQn = formatQn("Tommy", "Sue", "balloons", 403, 152);
        cQn.setQnNum(1);
        lQns.add(cQn);
        log.info("Control question - {}", cQn);

        IntStream.range(2, numQns + 1).forEach(qn -> {
            Random random = new Random();
            int giveAwayNum1 = random.ints(2, 999).findFirst().getAsInt();
            int giveAwayNum2 = random.ints(2, 999).filter(i -> i < giveAwayNum1).findFirst().getAsInt();

            int idxName1 = random.ints(1, 100).findAny().getAsInt();
            int idxName2 = random.ints(1, 100).filter(i -> i != idxName1).findAny().getAsInt();
            int idxItem = random.ints(1, 23).findAny().getAsInt();
            Question question = formatQn(lNames.get(idxName1), lNames.get(idxName2), lItems.get(idxItem), giveAwayNum1, giveAwayNum2);
            question.setQnNum(qn);

            lQns.add(question);
            log.info("{} - {}", qn, question);
        });

        PdfGenerator.genPdf("Problem Sum - Equal At First", lQns, "./pdf/problemsum-equalAtFirst.pdf");
    }

}
