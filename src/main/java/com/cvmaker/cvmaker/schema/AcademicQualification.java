package com.cvmaker.cvmaker.schema;

import com.cvmaker.cvmaker.exception.InvalidFieldValueException;

public class AcademicQualification {
    private String qualificationName;
    private String fromDate;
    private String toDate;
    private String instituteName;
    private String grade;
    private GradeType gradeType;


    public AcademicQualification(String qualificationName, String fromDate, String toDate, String instituteName, String grade, GradeType gradeType) {
        this.qualificationName = qualificationName;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.instituteName = instituteName;
        this.grade = grade;
        this.gradeType = gradeType;

        try {
            checkFields();
        } catch (InvalidFieldValueException e) {
            System.out.println(e.getMessage());
        }
    }

    public void checkFields() throws InvalidFieldValueException {
        try {
            Float.valueOf(grade);
        } catch (Exception e) {
            throw new InvalidFieldValueException(e.getMessage());
        }
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getQualificationName() {
        return qualificationName;
    }

    public void setQualificationName(String qualificationName) {
        this.qualificationName = qualificationName;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public GradeType getGradeType() {
        return gradeType;
    }

    public void setGradeType(GradeType gradeType) {
        this.gradeType = gradeType;
    }

}
