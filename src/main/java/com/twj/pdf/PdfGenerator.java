package com.twj.pdf;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;
import com.twj.vo.Question;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Slf4j
public class PdfGenerator {

    public static void genPdf(String title, List<Question> lQns, String filepath) {
        String randomId = RandomStringUtils.random(8, true, true);
        genQuestions(title, lQns, filepath, randomId);
        genAnswerSheet(title, lQns, filepath, randomId);
    }

    private static void genQuestions(String title, List<Question> lQns, String filepath, String randomId) {

        String filename = genPdfFilename(filepath, randomId, false);

        PdfWriter writer = null;
        try {
            log.info("File path to generate PDF: {}", filename);

            writer = new PdfWriter(filename);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf, PageSize.A4);
            pdf.addEventHandler(PdfDocumentEvent.END_PAGE, new PdfHeaderFooterGenerator(document, randomId, false));

            // create a font
            PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);

            // add title
            document.add(new Paragraph(title).setFont(font).setFontSize(14).setUnderline());

            // add table
            Table table = new Table(UnitValue.createPercentArray(15)).useAllAvailableWidth().setFixedLayout();

            // add qns
            lQns.forEach(qn -> {
                table.addCell(new Cell(1, 1).add(new Paragraph(qn.getQnNum() + ".").setFont(font)).setFontSize(11).setBorder(Border.NO_BORDER));
                table.addCell(new Cell(1, 14).add(new Paragraph(qn.getQn()).setFont(font)).setFontSize(11).setBorder(Border.NO_BORDER));
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

    private static void genAnswerSheet(String title, List<Question> lQns, String filepath, String randomId) {
        String filename = genPdfFilename(filepath, randomId, true);
        PdfWriter writer = null;
        try {
            writer = new PdfWriter(filename);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);
            pdf.addEventHandler(PdfDocumentEvent.END_PAGE, new PdfHeaderFooterGenerator(document, randomId, true));

            // create font
            PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);

            // add title
            document.add(new Paragraph(title + " (Answers)").setFont(font).setFontSize(14).setUnderline());

            // add table
            Table table = new Table(UnitValue.createPercentArray(15)).useAllAvailableWidth().setFixedLayout();

            // add answers
            lQns.forEach(qn -> {
                table.addCell(new Cell(1, 1).add(new Paragraph(qn.getQnNum() + ".").setFont(font)).setFontSize(11).setBorder(Border.NO_BORDER));
                table.addCell(new Cell(1, 14).add(new Paragraph(qn.getAnswer()).setFont(font)).setFontSize(11).setBorder(Border.NO_BORDER));
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
