package utils;

import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;

import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import config.CONNECTION_CREDENTIALS;
import database.Database;
import schema.NoUserFoundException;
import schema.ResumeDetail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TwoPagerOfficialResume implements ResumeTemplate{
    ResumeDetail resume;
    String accentColor;
    String dest = BASE_FILE_PATH + "templates/TwoPagerOfficialResume.pdf";

    public TwoPagerOfficialResume(){

        resume = new ResumeDetail("SUMMA",
                "AJAY H", "22PW01",
                "2005-05-18",
                "123, ABC PURAM, DB ROAD, COIMBATORE - 641459",
                "9090912345",
                "22pw01@psgtech.ac.in" );
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
        document.setMargins(50, 55, 0, 55);
        document.add(
                new Paragraph()
                .add(resume.getName() + "\n").setFont(calibriBold)
                .add(resume.getRollNumber() + "\n").setFont(calibriBold).setFontSize(14).setMultipliedLeading(1)
                        .add(new Text("\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0".repeat(21)).setLineThrough())
        );

        document.close();
        // create a table and progress
        return dest;
    }

    @Override
    public String generateResume(ResumeDetail resumeDetail, String accentColor, String username, String filename) throws IOException {
        this.dest = BASE_FILE_PATH + username + "/" + filename + ".pdf";
        File f = new File(dest);
        if(f.exists()){
            f.delete();
        }
        f.mkdirs();
        this.resume = resumeDetail;
        this.accentColor = accentColor;
        return getTemplate();
    }
}
