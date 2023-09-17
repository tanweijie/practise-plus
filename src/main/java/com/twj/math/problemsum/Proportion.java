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
public class Proportion {

    private List<String> lNames = QuestionConstants.lNames;
    private List<String> lItems = QuestionConstants.lItems;

    private Question formatQn(String name, String item, int boughtNum, int freeNum, int totalNum) {
        String qnTemplate = "For every %d %s bought.%n%s will be given %d %s free.%nHow many %s should %s buy if she wants to have a total of %d %s?%n%n%n%n%n%n%n%n%n%n%n%n%n";
        String ansTemplate = "(%d / (%d + %d)) x %d = %d";
        int ans = (totalNum / (boughtNum + freeNum)) * boughtNum;

        Question question = new Question();
        question.setQn(String.format(qnTemplate, boughtNum, item, name, freeNum, item, item, name, totalNum, item));
        question.setAnswer(String.format(ansTemplate, totalNum, boughtNum, freeNum, boughtNum, ans));

        return question;
    }

    public void createQuestion(int numQns) {

        List<Question> lQns = new ArrayList<>();

        Question cQn = formatQn("Maddy", "zoo tickets", 7, 2, 27);
        cQn.setQnNum(1);
        lQns.add(cQn);
        log.info("Control question - {}", cQn);

        IntStream.range(2, numQns + 1).forEach(qn -> {
            Random random = new Random();
            int giveAwayNum = random.ints(2, 4).findFirst().getAsInt();
            int boughtNum = random.ints(5, 30).findFirst().getAsInt();
            int inTheEndNum = random.ints(1, 7).findFirst().getAsInt() * (boughtNum + giveAwayNum);

            int idxName = random.ints(1, 100).findAny().getAsInt();
            int idxItem = random.ints(1, 23).findAny().getAsInt();
            Question question = formatQn(lNames.get(idxName), lItems.get(idxItem), boughtNum, giveAwayNum, inTheEndNum);
            question.setQnNum(qn);

            lQns.add(question);
            log.info("{} - {}", qn, question);
        });

        PdfGenerator.genPdf("Problem Sum - Proportion", lQns, "./pdf/problemsum-proportion.pdf");
    }
}
