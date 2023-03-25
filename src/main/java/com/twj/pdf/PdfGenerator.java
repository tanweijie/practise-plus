package com.twj.pdf;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;
import com.twj.vo.Question;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class PdfGenerator {

    public static void genPdf(String title, List<Question> lQns, String filepath) {
        String randomId = RandomStringUtils.random(8, true, true);
        genQuestions(title, lQns, genPdfFilename(filepath, randomId, false));
        genAnswerSheet(title, lQns, genPdfFilename(filepath, randomId, true));
    }

    private static void genQuestions(String title, List<Question> lQns, String filepath) {
        PdfWriter writer = null;
        try {
            writer = new PdfWriter(filepath);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // create a font
            PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);

            // add title
            document.add(new Paragraph(title).setFont(font).setFontSize(18).setUnderline());

            // add table
            Table table = new Table(UnitValue.createPercentArray(15)).useAllAvailableWidth().setFixedLayout();

            // add qns
            lQns.forEach(qn -> {
                table.addCell(new Cell(1, 1).add(new Paragraph(qn.getQnNum() + ".").setFont(font)).setBorder(Border.NO_BORDER));
                table.addCell(new Cell(1, 14).add(new Paragraph(qn.getQn()).setFont(font)).setBorder(Border.NO_BORDER));
            });

            document.add(table);
            document.close();
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void genAnswerSheet(String title, List<Question> lQns, String filepath) {
        PdfWriter writer = null;
        try {
            writer = new PdfWriter(filepath);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // create font
            PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);

            // add title
            document.add(new Paragraph(title + " (Answers)").setFont(font).setFontSize(18).setUnderline());

            // add table
            Table table = new Table(UnitValue.createPercentArray(15)).useAllAvailableWidth().setFixedLayout();

            // add answers
            lQns.forEach(qn -> {
                table.addCell(new Cell(1, 1).add(new Paragraph(qn.getQnNum() + ".").setFont(font)).setBorder(Border.NO_BORDER));
                table.addCell(new Cell(1, 14).add(new Paragraph(qn.getAnswer()).setFont(font)).setBorder(Border.NO_BORDER));
            });

            document.add(table);
            document.close();
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String genPdfFilename(String filepath, String randomId, boolean isAnswerSheet) {
        String pdfFilename = "xxx.pdf";
        if(isAnswerSheet)
            pdfFilename = filepath.replace(".pdf", "-" + randomId + "-answersheet.pdf");
        else
            pdfFilename = filepath.replace(".pdf", "-" + randomId + ".pdf");

        return pdfFilename;
    }
}
