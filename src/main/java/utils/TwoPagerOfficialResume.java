package utils;

import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfAnnotationBorder;
import com.itextpdf.kernel.pdf.PdfDocument;

import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.kernel.pdf.annot.PdfAnnotation;
import com.itextpdf.kernel.pdf.annot.PdfLinkAnnotation;
import com.itextpdf.kernel.pdf.canvas.draw.ILineDrawer;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;
import config.CONNECTION_CREDENTIALS;
import database.Database;
import schema.NoUserFoundException;
import schema.ResumeDetail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

public class TwoPagerOfficialResume implements ResumeTemplate {
    ResumeDetail resume;
    String accentColor;
    String dest = BASE_FILE_PATH + "templates/TwoPagerOfficialResume.pdf";


    public boolean checkNulls(Object o) {
        boolean allNull = true;
        try {
            for (Method m : o.getClass().getMethods()) {
                if (
                        (m.getName().startsWith("get") ||
                                m.getName().startsWith("is")) ||
                                m.getParameters().length == 0 && !m.getReturnType().equals(Void.TYPE)
                ) {
                    allNull &= m.invoke(o) == null;
                }
            }
        } catch (InvocationTargetException | IllegalAccessException ignored) {
            return true;
        }
        return allNull;
    }

    public TwoPagerOfficialResume() {

        resume = new ResumeDetail("SUMMA",
                "AJAY H", "22PW01",
                "2005-05-18",
                "123, ABC PURAM, DB ROAD, COIMBATORE - 641459",
                "9090912345",
                "22pw01@psgtech.ac.in");

        resume.setGender("Male");
        resume.setLanguages(Arrays.asList("English", "Tamil"));
        resume.setLinkedinLink("https://github.com/hajay180505");
        resume.setGitHubLink("https://github.com/hajay180505");
        resume.setImagePath("/home/ajayh/Downloads/Ajay-H.jpg");
//        Database db = new Database(
//                CONNECTION_CREDENTIALS.URI,
//                CONNECTION_CREDENTIALS.DATABASE_NAME,
//                CONNECTION_CREDENTIALS.COLLECTION_NAME);
//        try {
//            resume = db.getFillerResumeDetail();
//        } catch (NoUserFoundException e) {
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public String getTemplate() throws IOException {
        //generate a pdf
        PdfWriter writer = new PdfWriter(dest);

        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);

        //fonts
        final String CALIBRI_REGULAR = "fonts/Calibri/calibri-regular.ttf";
        final String CALIBRI_BOLD = "fonts/Calibri/calibri-bold.ttf";
        final String CALIBRI_ITALIC = "fonts/Calibri/calibri-italic.ttf";

        PdfFont calibriRegular = PdfFontFactory.createFont(CALIBRI_REGULAR);
        PdfFont calibriBold = PdfFontFactory.createFont(CALIBRI_BOLD);
        PdfFont calibriItalic = PdfFontFactory.createFont(CALIBRI_ITALIC);


        // Initialize document
        Document document = new Document(pdf, PageSize.A4);
        document.setMargins(40, 50, 0, 50);
        document.add(
                new Paragraph()
                        .add(resume.getName() + "\n").setFont(calibriBold).setMarginLeft(2f)
                        .add(resume.getRollNumber() + "\n").setFont(calibriBold).setFontSize(14).setMultipliedLeading(1).setPaddingLeft(1f)
        );
        // Create a solid line (1 pt thickness)
        SolidLine solidLine = new SolidLine(.7f);  // 1pt thickness
        solidLine.setColor(ColorConstants.BLACK);  // Optional: set line color

        // Create the LineSeparator with the SolidLine as the drawer
        LineSeparator separator = new LineSeparator(solidLine);
        separator.setWidth(UnitValue.createPercentValue(100));  // Set the width to 100% of the page width

        document.add(separator);

        Table table = new Table(
                UnitValue.createPercentArray(
                        new float[]{.66f, .34f}
                )
        ).useAllAvailableWidth();

        final float LINESPACE = 0.75f;

        Table innerTable = new Table(
                UnitValue.createPercentArray(new float[]{.22f, .44f})).useAllAvailableWidth()
                .addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph("Gender").setMultipliedLeading(LINESPACE)).setHorizontalAlignment(HorizontalAlignment.LEFT).setFont(calibriRegular).setFontSize(12))
                .addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph(resume.getGender()).setMultipliedLeading(LINESPACE)).setHorizontalAlignment(HorizontalAlignment.LEFT).setFont(calibriRegular).setFontSize(12))
                .addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph("Date of Birth").setMultipliedLeading(LINESPACE)).setHorizontalAlignment(HorizontalAlignment.LEFT).setFont(calibriRegular).setFontSize(12))
                .addCell(new Cell().setBorder(Border.NO_BORDER).add(Process.processDate(resume.getDateOfBirth(), calibriRegular).setMultipliedLeading(LINESPACE)).setHorizontalAlignment(HorizontalAlignment.LEFT).setFont(calibriRegular).setFontSize(12))
                .addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph("Languages known").setMultipliedLeading(LINESPACE)).setHorizontalAlignment(HorizontalAlignment.LEFT).setFont(calibriRegular).setFontSize(12))
                .addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph(Process.process(resume.getLanguages())).setMultipliedLeading(LINESPACE)).setHorizontalAlignment(HorizontalAlignment.LEFT).setFont(calibriRegular).setFontSize(12))
                .addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph("Email").setMultipliedLeading(LINESPACE)).setHorizontalAlignment(HorizontalAlignment.LEFT).setFont(calibriBold).setFontSize(12))
                .addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph(resume.getEmail()).setMultipliedLeading(LINESPACE)).setHorizontalAlignment(HorizontalAlignment.LEFT).setFont(calibriBold).setFontSize(12))
                .addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph("Mobile").setMultipliedLeading(LINESPACE)).setHorizontalAlignment(HorizontalAlignment.LEFT).setFont(calibriBold).setFontSize(12))
                .addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph(Process.processMobile(resume.getPhoneNumber())).setMultipliedLeading(LINESPACE)).setHorizontalAlignment(HorizontalAlignment.LEFT).setFont(calibriBold).setFontSize(12))
                .addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph("LinkedIn").setMultipliedLeading(LINESPACE)).setHorizontalAlignment(HorizontalAlignment.LEFT).setFont(calibriBold).setFontSize(12))
                .addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph(resume.getLinkedinLink()).setBorder(Border.NO_BORDER).setFontColor(ColorConstants.BLUE).setUnderline().setMultipliedLeading(LINESPACE)).setHorizontalAlignment(HorizontalAlignment.LEFT).setFont(calibriBold).setFontSize(12))
                .addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph("Github").setMultipliedLeading(LINESPACE)).setHorizontalAlignment(HorizontalAlignment.LEFT).setFont(calibriBold).setFontSize(12).setFont(calibriBold).setFontSize(12))
                .addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph(resume.getGitHubLink()).setBorder(Border.NO_BORDER).setFontColor(ColorConstants.BLUE).setUnderline().setMultipliedLeading(LINESPACE)).setHorizontalAlignment(HorizontalAlignment.LEFT).setFont(calibriBold).setFontSize(12).setFont(calibriBold).setFontSize(12));

//                .addCell(new Cell().add(new Paragraph("Name")).setHorizontalAlignment(HorizontalAlignment.LEFT))
//                .addCell(new Cell().add(new Paragraph("Name")).setHorizontalAlignment(HorizontalAlignment.LEFT));


        String imagePath = resume.getImagePath();  // Replace with the actual image path
        ImageData imageData = ImageDataFactory.create(imagePath);
        Image img = new Image(imageData);

        img.scaleToFit(100,150);



        table.addCell(new Cell(3, 1).setBorder(Border.NO_BORDER).add(innerTable))
                .addCell(new Cell(4, 1).setBorder(Border.NO_BORDER).add(img).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE))
                .addCell(new Cell(1, 2).setBorder(Border.NO_BORDER).add(
                                new Table(1)
                                        .addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph("Address\n").setMultipliedLeading(LINESPACE).setFont(calibriBold)))
                                        .addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph(resume.getAddress()).setMultipliedLeading(LINESPACE).setFont(calibriRegular))
                )));

        document.add(table);
        document.add(separator);



        document.close();

        // create a table and progress
        return dest;
    }

    @Override
    public String generateResume(ResumeDetail resumeDetail, String accentColor, String username, String filename) throws IOException {
        this.dest = BASE_FILE_PATH + username + "/" + filename + ".pdf";
        File f = new File(dest);
        if (f.exists()) {
            f.delete();
        }
        f.mkdirs();
        this.resume = resumeDetail;
        this.accentColor = accentColor;
        return getTemplate();
    }
}
