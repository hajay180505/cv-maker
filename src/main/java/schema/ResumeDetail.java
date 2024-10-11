package schema;

import java.sql.Date;
import java.util.List;

public class ResumeDetail {
    String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String name, dateOfBirth, address, phoneNumber, email;
    private List<AcademicQualification> academicQualifications;
    private List<ProjectDetail> nonAcademicProjects, academicProjects, researchProjects;
    private List<SkillSet> skillSets;
    private List<String> areasOfInterest, extraCurricular;

    public ResumeDetail(String title, String name, String dateOfBirth, String address, String phoneNumber, String email){
        this.title = title;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        try{
            checkFields();
        } catch (InvalidFieldValueException e) {
            System.out.println(e.getMessage());
        }
    }

    public void checkFields() throws InvalidFieldValueException {
        try {
            Date.valueOf(dateOfBirth);
            new EmailValidator().validate(email);
            if (phoneNumber.length() != 10 || Long.parseLong(phoneNumber) <= 0){
                throw new InvalidFieldValueException("Invalid phone number.");
            }
        }catch (Exception e) {
            throw new InvalidFieldValueException(e.getMessage());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<AcademicQualification> getAcademicQualifications() {
        return academicQualifications;
    }

    public void setAcademicQualifications(List<AcademicQualification> academicQualifications) {
        this.academicQualifications = academicQualifications;
    }

    public List<ProjectDetail> getNonAcademicProjects() {
        return nonAcademicProjects;
    }

    public void setNonAcademicProjects(List<ProjectDetail> nonAcademicProjects) {
        this.nonAcademicProjects = nonAcademicProjects;
    }

    public List<ProjectDetail> getAcademicProjects() {
        return academicProjects;
    }

    public void setAcademicProjects(List<ProjectDetail> academicProjects) {
        this.academicProjects = academicProjects;
    }

    public List<ProjectDetail> getResearchProjects() {
        return researchProjects;
    }

    public void setResearchProjects(List<ProjectDetail> researchProjects) {
        this.researchProjects = researchProjects;
    }

    public List<SkillSet> getSkillSets() {
        return skillSets;
    }

    public void setSkillSets(List<SkillSet> skillSets) {
        this.skillSets = skillSets;
    }

    public List<String> getAreasOfInterest() {
        return areasOfInterest;
    }

    public void setAreasOfInterest(List<String> areasOfInterest) {
        this.areasOfInterest = areasOfInterest;
    }

    public List<String> getExtraCurricular() {
        return extraCurricular;
    }

    public void setExtraCurricular(List<String> extraCurricular) {
        this.extraCurricular = extraCurricular;
    }
}
