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
public class Age {
    private List<String> lNames = QuestionConstants.lNames;

    private Question formatQn(String name1, String name2, int age1, int age2, int timesOfAge) {
        String qnTemplate = "%s is %d years old and %s is %d years old.%nHow old will %s be when %s is %d times her age?%n%n%n%n%n%n";
        String ansTemplate = "(%s - %s) / %s = %d %s";

        Question question = new Question();
        question.setQn(String.format(qnTemplate, name1, age1, name2, age2, name1, name2, timesOfAge));
        question.setAnswer(String.format(ansTemplate,
                                         String.valueOf(age2),
                                         String.valueOf(age1),
                                         String.valueOf(timesOfAge - 1),
                                         (age2 - age1) / (timesOfAge - 1),
                                         "years old"));
        return question;
    }

    public void createQuestion(int numQns) {

        List<Question> lQns = new ArrayList<>();

        Question cQn = formatQn("Charlotte", "Lisa", 9, 41, 3);
        cQn.setQnNum(1);
        lQns.add(cQn);
        log.info("Control question - {}", cQn);

        IntStream.range(2, numQns + 1).forEach(qn -> {
            Random random = new Random();
            int timesOfAge = random.ints(3, 7).findFirst().getAsInt();
            int age1 = random.ints(2, 10).findFirst().getAsInt();
            int age2 = ((timesOfAge-1) * random.ints(8, 12).findFirst().getAsInt()) + age1;

            int idxName1 = random.ints(1, 100).findAny().getAsInt();
            int idxName2 = random.ints(1, 100).findAny().getAsInt();
            Question question = formatQn(lNames.get(idxName1), lNames.get(idxName2), age1, age2, timesOfAge);
            question.setQnNum(qn);

            lQns.add(question);
            log.info("{} - {}", qn, question);
        });

        PdfGenerator.genPdf("Problem Sum - Age", lQns, "./pdf/problemsum-age.pdf");
    }
}
