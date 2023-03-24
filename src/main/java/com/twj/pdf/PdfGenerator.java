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

import java.io.IOException;
import java.util.List;

public class PdfGenerator {

    public static void genPdf(String title, List<Question> lQns, String filepath) throws IOException {
        PdfWriter writer = new PdfWriter(filepath.replace(".pdf", "-" + RandomStringUtils.random(8, true, true) + ".pdf"));
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
        // Create a PdfFont
        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);

        // add title
        document.add(new Paragraph(title).setFont(font).setFontSize(18).setUnderline());

        Table table = new Table(UnitValue.createPercentArray(15)).useAllAvailableWidth().setFixedLayout();

        // add qns
        lQns.forEach(qn -> {
            table.addCell(new Cell(1, 1).add(new Paragraph(qn.getQnNum() + ".").setFont(font)).setBorder(Border.NO_BORDER));
            table.addCell(new Cell(1, 14).add(new Paragraph(qn.getQn()).setFont(font)).setBorder(Border.NO_BORDER));
        });

        document.add(table);
        document.close();
    }
}
