package cvMaker.utils;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;
import cvMaker.Main;
import cvMaker.database.Database;
import cvMaker.exception.NoUserFoundException;
import cvMaker.schema.*;

import java.io.File;
import java.io.IOException;
import java.io.InvalidObjectException;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;

public class TwoPagerOfficialResume implements ResumeTemplate {
    ResumeDetail resume;
    DeviceRgb accentColor;
    String dest = BASE_FILE_PATH + "templates/TwoPagerOfficialResume.pdf";

    public TwoPagerOfficialResume() {
        this.accentColor = new DeviceRgb(238, 236, 225);
        if (Main.IS_DEV){
            this.resume = ResumeTemplate.getLocalFillerResumeDetail();
        }
        else{
            try {
                this.resume = new Database().getFillerResumeDetail();
            } catch (NoUserFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean checkNulls(ResumeDetail resumeDetail) {
        return resumeDetail.getPhoneNumber() != null && resumeDetail.getAcademicProjects() != null &&
                resumeDetail.getAddress() != null && resumeDetail.getAcademicQualifications() != null &&
                resumeDetail.getAreasOfInterest() != null && resumeDetail.getCollegeName() != null &&
                resumeDetail.getCourse() != null && resumeDetail.getDateOfBirth() != null &&
                resumeDetail.getEmail() != null && resumeDetail.getGitHubLink() != null &&
                resumeDetail.getGender() != null &&resumeDetail.getExtraCurricular() != null &&
                resumeDetail.getYearOfStudy() != null && resumeDetail.getTitle() != null &&
                resumeDetail.getSkillSets() != null && resumeDetail.getRollNumber() !=null &&
                resumeDetail.getObjective() != null && resumeDetail.getName() != null &&
                resumeDetail.getLinkedinLink() !=null && resumeDetail.getLanguages() != null &&
                resumeDetail.getImagePath() != null ;
    }

    @Override
    public String getTemplate() throws IOException {

        if (!checkNulls(resume)) {
            throw new InvalidObjectException("Resume detail object has null values in mandatory fields");
        }
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

        img.scaleToFit(100, 150);


        table.addCell(new Cell(3, 1).setBorder(Border.NO_BORDER).add(innerTable))
                .addCell(new Cell(4, 1).setBorder(Border.NO_BORDER).add(img).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE))
                .addCell(new Cell(1, 2).setBorder(Border.NO_BORDER).add(
                        new Table(1)
                                .addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph("Address\n").setMultipliedLeading(LINESPACE).setFont(calibriBold)))
                                .addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph(resume.getAddress()).setMultipliedLeading(LINESPACE).setFont(calibriRegular))
                                )));

        document.add(table);
        document.add(separator);

        //Objective
        Div spacer = new Div();
        spacer.setHeight(6);


        document.add(spacer);
        document.add(new Paragraph("OBJECTIVE")
                .setFont(calibriBold).setFontSize(12).setMarginLeft(3)
                .setBackgroundColor(new DeviceRgb(238, 236, 225))
                .setBorderBottom(new SolidBorder(ColorConstants.BLACK, .75f)
                )
        );

        document.add( new Paragraph().add(new Tab()).add(
                        "To obtain a position as a student intern from " +
                                resume.getObjective().getStartMonth() + " " +resume.getObjective().getStartYear() +  " to " +
                                resume.getObjective().getEndMonth() + " " +resume.getObjective().getEndYear() +  ".\n"
                ).setFont(calibriRegular).setFontSize(12).setMultipliedLeading(LINESPACE)
        );


        //academic qualification
        document.add(spacer);
        document.add(new Paragraph("ACADEMIC QUALIFICATION")
                .setFont(calibriBold).setFontSize(12).setMarginLeft(3)
                .setBackgroundColor(new DeviceRgb(238, 236, 225))
                .setBorderBottom(new SolidBorder(ColorConstants.BLACK, .75f)
                ).setMarginBottom(-1)
        );
        document.add( new Paragraph().add(new Tab()).add(
                        "Currently pursuing ").add(
                        Process.processOrdinal(resume.getYearOfStudy(), calibriRegular) ).add(
                        " year of " + resume.getCourseDuration() + " year " + resume.getCourse() +
                                " at the " + resume.getDepartmentName() + " at " + resume.getCollegeName() + "."

                ).setFont(calibriRegular).setFontSize(12).setMultipliedLeading(LINESPACE).setMarginLeft(3)
        );


        //skill set
        document.add(spacer);
        document.add(new Paragraph("SKILL SET")
                .setFont(calibriBold).setFontSize(12).setMarginLeft(3)
                .setBackgroundColor(new DeviceRgb(238, 236, 225))
                .setBorderBottom(new SolidBorder(ColorConstants.BLACK, .75f)
                )
        );
        document.add(spacer);
        document.add(renderSkillSet(resume.getSkillSets(), calibriRegular));

        //areas of interest
        document.add(spacer);
        document.add(new Paragraph("AREAS OF INTEREST")
                .setFont(calibriBold).setFontSize(12).setMarginLeft(3)
                .setBackgroundColor(new DeviceRgb(238, 236, 225))
                .setBorderBottom(new SolidBorder(ColorConstants.BLACK, .75f)
                )
        );
        document.add(renderAreasOfInterest(resume.getAreasOfInterest(), calibriRegular));


        //academic record
        document.add(spacer);
        document.add(new Paragraph("ACADEMIC RECORD")
                .setFont(calibriBold).setFontSize(12).setMarginLeft(3)
                .setBackgroundColor(new DeviceRgb(238, 236, 225))
                .setBorderBottom(new SolidBorder(ColorConstants.BLACK, .75f)
                )
        );
        document.add(renderAcademicRecord(resume.getAcademicQualifications(), calibriRegular, calibriBold));

        //research experience
        if ( resume.getResearchProjects() != null) {
            document.add(spacer);
            document.add(new Paragraph("RESEARCH EXPERIENCE")
                    .setFont(calibriBold).setFontSize(12).setMarginLeft(3)
                    .setBackgroundColor(new DeviceRgb(238, 236, 225))
                    .setBorderBottom(new SolidBorder(ColorConstants.BLACK, .75f)
                    )
            );
            renderProjects(resume.getResearchProjects(), calibriRegular, calibriBold, document);
        }

        //non-academic projects
        document.add(spacer);
        document.add(new Paragraph("NON-ACADEMIC PROJECTS")
                .setFont(calibriBold).setFontSize(12).setMarginLeft(3)
                .setBackgroundColor(new DeviceRgb(238, 236, 225))
                .setBorderBottom(new SolidBorder(ColorConstants.BLACK, .75f)
                )
        );
        renderProjects(resume.getNonAcademicProjects(), calibriRegular, calibriBold, document);


        //academic projects
        document.add(spacer);
        document.add(new Paragraph("ACADEMIC PROJECTS")
                .setFont(calibriBold).setFontSize(12).setMarginLeft(3)
                .setBackgroundColor(new DeviceRgb(238, 236, 225))
                .setBorderBottom(new SolidBorder(ColorConstants.BLACK, .75f)
                )
        );
        renderProjects(resume.getAcademicProjects(), calibriRegular, calibriBold, document);


        //extra-curricular
        document.add(spacer);
        document.add(new Paragraph("EXTRA-CURRICULAR ACTIVITIES AND ACHIEVEMENTS")
                .setFont(calibriBold).setFontSize(12).setMarginLeft(3)
                .setBackgroundColor(new DeviceRgb(238, 236, 225))
                .setBorderBottom(new SolidBorder(ColorConstants.BLACK, .75f)
                )
        );
        List list = new List().setListSymbol("\u2022\u00a0\u00a0\u00a0").setFont(calibriRegular).setFontSize(12);
        for (String s : resume.getExtraCurricular()){
            list.add(s);
        }
        document.add(list);

        //declaration
        document.add(spacer);
        document.add(new Paragraph("DECLARATION")
                .setFont(calibriBold).setFontSize(12).setMarginLeft(3)
                .setBackgroundColor(new DeviceRgb(238, 236, 225))
                .setBorderBottom(new SolidBorder(ColorConstants.BLACK, .75f)
                )
        );
        document.add(new Paragraph().add(new Tab()).add("I, " + resume.getName() + ", do hereby confirm that the information given above is true to the best of my knowledge." +
                "\n"));
        document.add(new Table(
                UnitValue.createPercentArray(new float[]{.9f, .1f})).useAllAvailableWidth()
                .addCell(new Cell().setBorder(Border.NO_BORDER)
                        .add(new Paragraph(
                                        "Place : " + resume.getDeclarationLocation() + "\n"
                                ).setFont(calibriRegular).setFontSize(12).setMultipliedLeading(1)
                        ).add(
                                new Paragraph(
                                        "Date : " + resume.getDeclarationDate() + "\n"
                                ).setFont(calibriRegular).setFontSize(12).setMultipliedLeading(1)
                        )
                )
                .addCell(new Cell().setBorder(Border.NO_BORDER)
                        .add(new Paragraph("\n").setFont(calibriRegular).setFontSize(12).setMultipliedLeading(1))
                        .add(new Paragraph("("+resume.getName() + ")").setHorizontalAlignment(HorizontalAlignment.RIGHT).setFont(calibriRegular).setFontSize(12).setMultipliedLeading(1))
                )
        );

        document.close();

        // create a table and progress
        return dest;
    }

    @Override
    public String generateResume(ResumeDetail resumeDetail, DeviceRgb accentColor, String username, String filename) throws IOException {
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

    public Table renderSkillSet(java.util.List<SkillSet> skillSets, PdfFont font){
        Table table = new Table(
                UnitValue.createPercentArray(
                        new float[]{.3f, .6f}
                )
        )
        .useAllAvailableWidth().setMarginLeft(3).setMarginRight(3);

        for(SkillSet skillset : skillSets){
            table.addCell(
                    new Cell().setBorderRight(Border.NO_BORDER)
                            .setHorizontalAlignment(HorizontalAlignment.LEFT)
                            .add( new Paragraph(skillset.getSkillName()).setFont(font).setFontSize(12)).setBackgroundColor(accentColor)
            )
                    .addCell(new Cell()
                            .setHorizontalAlignment(HorizontalAlignment.LEFT)
                            .add( new Paragraph(Process.process(skillset.getSkills())).setFont(font).setFontSize(12)));

        }
        return table;
    }

    public Table renderAreasOfInterest(java.util.List<String> areasOfInterest, PdfFont font){
        Table table = new Table(
                UnitValue.createPercentArray(
                        new float[]{.5f, .5f}
                )
        )
                .useAllAvailableWidth().setMarginLeft(3).setMarginRight(3);
        int i;
        int len = areasOfInterest.size()/2;
        List leftlist = new List().setListSymbol("\u2022\u00a0\u00a0\u00a0").setFont(font).setFontSize(12).setMarginLeft(30);
        List rightlist = new List().setListSymbol("\u2022\u00a0\u00a0\u00a0").setFont(font).setFontSize(12).setMarginLeft(30);
        for(i = 0 ;i < len ; i ++){
            leftlist.add(areasOfInterest.get(i));
        }
        for (; i<areasOfInterest.size() ; i++){
            rightlist.add(areasOfInterest.get(i));
        }
        table.addCell(new Cell().setBorder(Border.NO_BORDER).add(leftlist)).addCell(new Cell().setBorder(Border.NO_BORDER).add(rightlist));
        return table;
    }

    public Table renderAcademicRecord(java.util.List<AcademicQualification> academicRecord, PdfFont fontRegular, PdfFont fontBold){
        Table table = new Table(
                UnitValue.createPercentArray(
                        new float[]{.85f, .15f}
                )
        ).useAllAvailableWidth().setMarginLeft(3).setMarginRight(3);

        for( AcademicQualification q : academicRecord){
            table
                    .addCell(new Cell().setBorder(Border.NO_BORDER)
                            .setHorizontalAlignment(HorizontalAlignment.LEFT)
                            .add( new Paragraph()
                                    .setFontSize(12)
                                    .setFont(fontBold)
                                    .setMultipliedLeading(1)
                                    .add(q.getQualificationName() + "\n")

                            )
                            .add(
                                    new Paragraph()
                                            .setFontSize(12)
                                            .setMultipliedLeading(1)
                                            .setFont(fontRegular)
                                            .add(q.getInstituteName())
                            )

            )
                    .addCell(
                            new Cell().setBorder(Border.NO_BORDER)
                                    .add( new Paragraph()
                                            .setFontSize(12)
                                            .setFont(fontRegular)
                                            .setMultipliedLeading(1)
                                            .setHorizontalAlignment(HorizontalAlignment.RIGHT)
                                            .add(q.getFromDate() ==null ?
                                                    (q.getToDate() + "\n") :
                                                    (q.getFromDate() + "-" + q.getToDate() + "\n")
                                            )

                                    )
                                    .add(
                                            new Paragraph()
                                                    .setFontSize(12)
                                                    .setFont(fontBold)
                                                    .setMultipliedLeading(1)
                                                    .setHorizontalAlignment(HorizontalAlignment.RIGHT)
                                                    .add(q.getGradeType() == GradeType.CGPA
                                                            ? ( q.getGrade()+ " CGPA") :
                                                            ( q.getGrade()+ " %")
                                                    )

                                    )
                    );

        }
    return table;

    }

    public static void renderProjects(java.util.List<ProjectDetail> projectDetails, PdfFont fontRegular, PdfFont fontBold, Document document) {
            for (ProjectDetail project : projectDetails) {
                // Create a paragraph for the project name with the GitHub link
                Paragraph projectHeader = new Paragraph().setMultipliedLeading(1).setMarginLeft(3);
                projectHeader.add(new Text(project.getProjectName()).setFont(fontBold).setFontSize(12));
                projectHeader.add(new Text(" | " + Process.process(project.getTechStack())).setFont(fontRegular).setFontSize(12));

                // Add duration if available
                if (project.getDuration() != null && !project.getDuration().isEmpty()) {
                    projectHeader.add(new Text(" : [" + project.getDuration() + "]").setFont(fontRegular).setFontSize(12));
                }

                // Create the description with bold highlights on specific words
                projectHeader.add(" - ");
                String projectDesc = project.getProjectDescription();
                java.util.List<String> highlightedWords = project.getHighlightedWords();

                // Split description by spaces to highlight specific words
                String[] words = projectDesc.split(" ");
                for (String word : words) {
                    if (highlightedWords.contains(word)) {
                        projectHeader.add(new Text(word + " ").setFont(fontBold));
                    } else {
                        projectHeader.add(new Text(word + " ").setFont(fontRegular));
                    }
                }
                // Add a little space between projects
                if (project.getGithubLink() != null && !project.getGithubLink().isEmpty()) {
                    projectHeader.add("\n(").add(new Text(project.getGithubLink()).setFont(fontRegular).setFontSize(12).setFontColor(ColorConstants.BLUE).setUnderline())
                            .add(" )");
                }
                document.add(projectHeader);
            }

        }

}
