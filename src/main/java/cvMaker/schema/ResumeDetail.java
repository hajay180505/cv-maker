package cvMaker.schema;

import cvMaker.exception.InvalidFieldValueException;

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

    private String name;
    private String rollNumber;
    private String dateOfBirth;
    private String gender;
    private String collegeName;
    private String course;
    private String yearOfStudy;
    private String departmentName;
    private String courseDuration;


    private String address;
    private String phoneNumber;
    private String email;
    private String imagePath;
    private List<String> languages;
    private String gitHubLink;
    private String linkedinLink;
    private Objective objective;
    private String declarationLocation;
    private String declarationDate;

    public String getDeclarationLocation() {
        return declarationLocation;
    }

    public void setDeclarationLocation(String declarationLocation) {
        this.declarationLocation = declarationLocation;
    }

    public String getDeclarationDate() {
        return declarationDate;
    }

    public void setDeclarationDate(String declarationDate) {
        this.declarationDate = declarationDate;
    }

    public String getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(String yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public Objective getObjective() {
        return objective;
    }

    public void setObjective(Objective objective) {
        this.objective = objective;
    }

    public String getGitHubLink() {
        return gitHubLink;
    }

    public void setGitHubLink(String gitHubLink) {
        this.gitHubLink = gitHubLink;
    }

    public String getLinkedinLink() {
        return linkedinLink;
    }

    public void setLinkedinLink(String linkedinLink) {
        this.linkedinLink = linkedinLink;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<String> getLanguages() {
        return languages;
    }


    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    private List<AcademicQualification> academicQualifications;
    private List<ProjectDetail> nonAcademicProjects, academicProjects, researchProjects;
    private List<SkillSet> skillSets;
    private List<String> areasOfInterest, extraCurricular;

    public ResumeDetail(String title, String name, String rollNumber, String dateOfBirth, String address, String phoneNumber, String email) {
        this.title = title;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.rollNumber = rollNumber;
        try {
            checkFields();
        } catch (InvalidFieldValueException e) {
            System.out.println(e.getMessage());
        }
    }

    public void checkFields() throws InvalidFieldValueException {
        try {
            Date.valueOf(dateOfBirth);
            new EmailValidator().validate(email);
            if (phoneNumber.length() != 10 || Long.parseLong(phoneNumber) <= 0) {
                throw new InvalidFieldValueException("Invalid phone number.");
            }
        } catch (Exception e) {
            throw new InvalidFieldValueException(e.getMessage());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
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
