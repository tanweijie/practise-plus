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
public class HaveAtFirst {
    private List<String> lNames = QuestionConstants.lNames;
    private List<String> lItems = QuestionConstants.lItems;

    public Question formatQn(String name, String item, int giveAwayNum, int boughtNum, int inTheEndNum) {
        String qnTemplate = "%s had some %s.%n She gave away %d %s and bought another %d %s.%n She has %d %s in the end.%n How many %s did she have at first?%n%n%n%n%n%n%n%n%n%n%n%n";
        String ansTemplate = "(%d - %d) + %d = %d";

        Question question = new Question();
        question.setQn(String.format(qnTemplate, name, item, giveAwayNum, item, boughtNum, item, inTheEndNum, item, item));
        question.setAnswer(String.format(ansTemplate, inTheEndNum, boughtNum, giveAwayNum, (inTheEndNum - boughtNum) + giveAwayNum));

        return question;
    }

    public void createQuestion(int numQns) {

        List<Question> lQns = new ArrayList<>();

        Question cQn = formatQn("Maddy", "sweets", 334, 383, 1000);
        cQn.setQnNum(1);
        lQns.add(cQn);
        log.info("Control question - {}", cQn);

        IntStream.range(2, numQns + 1).forEach(qn -> {
            Random random = new Random();
            int giveAwayNum = random.ints(2, 99).findFirst().getAsInt();
            int boughtNum = random.ints(199, 498).findFirst().getAsInt();
            int inTheEndNum = random.ints(500, 1200).findFirst().getAsInt();

            int idxName = random.ints(1, 100).findAny().getAsInt();
            int idxItem = random.ints(1, 23).findAny().getAsInt();
            Question question = formatQn(lNames.get(idxName), lItems.get(idxItem), giveAwayNum, boughtNum, inTheEndNum);
            question.setQnNum(qn);

            lQns.add(question);
            log.info("{} - {}", qn, question);
        });

        PdfGenerator.genPdf("Problem Sum - Have At First", lQns, "./pdf/problemsum-haf.pdf");
    }
}
