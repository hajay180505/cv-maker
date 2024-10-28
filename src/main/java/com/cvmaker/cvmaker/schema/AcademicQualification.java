package com.cvmaker.cvmaker.schema;

import com.cvmaker.cvmaker.exception.InvalidFieldValueException;
import org.bson.Document;

/**
 * The type Academic qualification.
 */
public class AcademicQualification {
    private String qualificationName;
    private String fromDate;
    private String toDate;
    private String instituteName;
    private String grade;
    private GradeType gradeType;


    /**
     * Instantiates a new Academic qualification.
     *
     * @param qualificationName the qualification name
     * @param fromDate          the from date
     * @param toDate            the to date
     * @param instituteName     the institute name
     * @param grade             the grade
     * @param gradeType         the grade type
     */
    public AcademicQualification(String qualificationName, String fromDate, String toDate, String instituteName, String grade, String gradeType) {
        this.qualificationName = qualificationName;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.instituteName = instituteName;
        this.grade = grade;
        this.gradeType = GradeType.valueOf(gradeType.toUpperCase());

        try {
            checkFields();
        } catch (InvalidFieldValueException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Check fields.
     *
     * @throws InvalidFieldValueException the invalid field value exception
     */
    public void checkFields() throws InvalidFieldValueException {
        try {
            Float.valueOf(grade);
        } catch (Exception e) {
            throw new InvalidFieldValueException(e.getMessage());
        }
    }

    /**
     * Gets from date.
     *
     * @return the from date
     */
    public String getFromDate() {
        return fromDate;
    }

    /**
     * Sets from date.
     *
     * @param fromDate the from date
     */
    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * Gets qualification name.
     *
     * @return the qualification name
     */
    public String getQualificationName() {
        return qualificationName;
    }

    /**
     * Sets qualification name.
     *
     * @param qualificationName the qualification name
     */
    public void setQualificationName(String qualificationName) {
        this.qualificationName = qualificationName;
    }

    /**
     * Gets to date.
     *
     * @return the to date
     */
    public String getToDate() {
        return toDate;
    }

    /**
     * Sets to date.
     *
     * @param toDate the to date
     */
    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    /**
     * Gets institute name.
     *
     * @return the institute name
     */
    public String getInstituteName() {
        return instituteName;
    }

    /**
     * Sets institute name.
     *
     * @param instituteName the institute name
     */
    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    /**
     * Gets grade.
     *
     * @return the grade
     */
    public String getGrade() {
        return grade;
    }

    /**
     * Sets grade.
     *
     * @param grade the grade
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * Gets grade type.
     *
     * @return the grade type
     */
    public GradeType getGradeType() {
        return gradeType;
    }

    /**
     * Sets grade type.
     *
     * @param gradeType the grade type
     */
    public void setGradeType(GradeType gradeType) {
        this.gradeType = gradeType;
    }


    /**
     * Map academic qualification.
     *
     * @param document the document
     * @return the academic qualification
     */
    public static AcademicQualification map(Document document) {
        return new AcademicQualification(document.getString("qualificationName"),
                document.getString("fromDate"),
                document.getString("toDate"),
                document.getString("instituteName"),
                document.getString("grade"),
                document.getString("gradeType")
        );
    }

}
