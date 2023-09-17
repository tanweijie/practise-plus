package com.twj.pdf;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.properties.TextAlignment;

import java.io.IOException;

public class PdfHeaderFooterGenerator implements IEventHandler {
    Document doc;
    String randomId;
    boolean isAnswerSheet;

    public PdfHeaderFooterGenerator(Document doc, String randomId, boolean isAnswerSheet) {
        this.doc = doc;
        this.randomId = randomId;
        this.isAnswerSheet = isAnswerSheet;
    }

    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        Rectangle pageSize = docEvent.getPage().getPageSize();
        String paperIdTxt = isAnswerSheet ? "Practise Plus Answer Sheet for Paper ID: " + randomId : "Practise Plus Paper ID: " + randomId + " (Use the Paper ID to search for the corresponding answer sheet if you are are contributor)";

        float coordX = pageSize.getLeft() + doc.getLeftMargin();
        float headerY = pageSize.getTop() - doc.getTopMargin() + 10;
        float footerY = doc.getBottomMargin();

        Canvas canvas = new Canvas(docEvent.getPage(), pageSize);
        try {
            // add logo
            String img = "./src/main/resources/pp-logo-200x200.png";
            ImageData data = ImageDataFactory.create(img);
            Image image = new Image(data);
            image.setHeight(50f);
            image.setWidth(50f);
            image.setFixedPosition(coordX + 495, headerY - 40);

            canvas.add(image);
            canvas.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_OBLIQUE))
                  .setFontSize(5)
                  .showTextAligned(paperIdTxt, coordX, footerY + 20, TextAlignment.JUSTIFIED)
                  .showTextAligned(
                          "The information provided by Practise Plus on https://practise.plus is for informational/educational purposes only. All information on the site is provided in good faith,however we make no representation or warranty of any kind, express or",
                          coordX,
                          footerY,
                          TextAlignment.JUSTIFIED)
                  .showTextAligned(
                          "implied regarding adequacy, availability and completeness of any information on the worksheet. Under no circumstances shall we have any liability to you for any loss or damage of any kind incurred as a result of the use of the worksheet.",
                          coordX,
                          footerY - 7,
                          TextAlignment.JUSTIFIED)
                  .showTextAligned("Your reliance on any information on the worksheet is solely AT YOUR OWN RISK.",
                                   coordX,
                                   footerY - 14,
                                   TextAlignment.JUSTIFIED)
                  .close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
