package com.cvmaker.cvmaker.schema;

import com.cvmaker.cvmaker.exception.InvalidFieldValueException;
import com.cvmaker.cvmaker.mapper.ListMapper;
import com.cvmaker.cvmaker.validator.EmailValidator;
import org.bson.Document;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Resume detail.
 */
public class ResumeDetail {

    private String title;
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
    private String gitHubLink;
    private String linkedinLink;
    private String declarationLocation;
    private String declarationDate;

    private List<String> languages;
    private List<String> areasOfInterest, extraCurricular;


    private Objective objective;

    private List<AcademicQualification> academicQualifications;
    private List<ProjectDetail> nonAcademicProjects, academicProjects, researchProjects;
    private List<SkillSet> skillSets;


    /**
     * Instantiates a new Resume detail.
     *
     * @param title       the title
     * @param name        the name
     * @param rollNumber  the roll number
     * @param dateOfBirth the date of birth
     * @param address     the address
     * @param phoneNumber the phone number
     * @param email       the email
     */
    public ResumeDetail(String title, String name, String rollNumber, String dateOfBirth, String address, String phoneNumber, String email) {
        this.title = title;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.rollNumber = rollNumber;
        this.academicQualifications = new ArrayList<>();
        this.nonAcademicProjects = new ArrayList<>();
        this.academicProjects = new ArrayList<>();
        this.researchProjects = new ArrayList<>();
        this.skillSets = new ArrayList<>();

        try {
            checkFields();
        } catch (InvalidFieldValueException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets declaration location.
     *
     * @return the declaration location
     */
    public String getDeclarationLocation() {
        return declarationLocation;
    }

    /**
     * Sets declaration location.
     *
     * @param declarationLocation the declaration location
     */
    public void setDeclarationLocation(String declarationLocation) {
        this.declarationLocation = declarationLocation;
    }

    /**
     * Gets declaration date.
     *
     * @return the declaration date
     */
    public String getDeclarationDate() {
        return declarationDate;
    }

    /**
     * Sets declaration date.
     *
     * @param declarationDate the declaration date
     */
    public void setDeclarationDate(String declarationDate) {
        this.declarationDate = declarationDate;
    }

    /**
     * Gets course duration.
     *
     * @return the course duration
     */
    public String getCourseDuration() {
        return courseDuration;
    }

    /**
     * Sets course duration.
     *
     * @param courseDuration the course duration
     */
    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
    }

    /**
     * Gets department name.
     *
     * @return the department name
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * Sets department name.
     *
     * @param departmentName the department name
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * Gets college name.
     *
     * @return the college name
     */
    public String getCollegeName() {
        return collegeName;
    }

    /**
     * Sets college name.
     *
     * @param collegeName the college name
     */
    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    /**
     * Gets course.
     *
     * @return the course
     */
    public String getCourse() {
        return course;
    }

    /**
     * Sets course.
     *
     * @param course the course
     */
    public void setCourse(String course) {
        this.course = course;
    }

    /**
     * Gets year of study.
     *
     * @return the year of study
     */
    public String getYearOfStudy() {
        return yearOfStudy;
    }

    /**
     * Sets year of study.
     *
     * @param yearOfStudy the year of study
     */
    public void setYearOfStudy(String yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    /**
     * Gets objective.
     *
     * @return the objective
     */
    public Objective getObjective() {
        return objective;
    }

    /**
     * Sets objective.
     *
     * @param objective the objective
     */
    public void setObjective(Objective objective) {
        this.objective = objective;
    }

    /**
     * Gets git hub link.
     *
     * @return the git hub link
     */
    public String getGitHubLink() {
        return gitHubLink;
    }

    /**
     * Sets git hub link.
     *
     * @param gitHubLink the git hub link
     */
    public void setGitHubLink(String gitHubLink) {
        this.gitHubLink = gitHubLink;
    }

    /**
     * Gets linkedin link.
     *
     * @return the linkedin link
     */
    public String getLinkedinLink() {
        return linkedinLink;
    }

    /**
     * Sets linkedin link.
     *
     * @param linkedinLink the linkedin link
     */
    public void setLinkedinLink(String linkedinLink) {
        this.linkedinLink = linkedinLink;
    }

    /**
     * Gets gender.
     *
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets gender.
     *
     * @param gender the gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Gets languages.
     *
     * @return the languages
     */
    public List<String> getLanguages() {
        return languages;
    }


    /**
     * Sets languages.
     *
     * @param languages the languages
     */
    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    /**
     * Gets image path.
     *
     * @return the image path
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Sets image path.
     *
     * @param imagePath the image path
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }


    /**
     * Check fields.
     *
     * @throws InvalidFieldValueException the invalid field value exception
     */
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

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets roll number.
     *
     * @return the roll number
     */
    public String getRollNumber() {
        return rollNumber;
    }

    /**
     * Sets roll number.
     *
     * @param rollNumber the roll number
     */
    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    /**
     * Gets date of birth.
     *
     * @return the date of birth
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets date of birth.
     *
     * @param dateOfBirth the date of birth
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets phone number.
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets academic qualifications.
     *
     * @return the academic qualifications
     */
    public List<AcademicQualification> getAcademicQualifications() {
        return academicQualifications;
    }

    /**
     * Sets academic qualifications.
     *
     * @param academicQualifications the academic qualifications
     */
    public void setAcademicQualifications(List<AcademicQualification> academicQualifications) {
        this.academicQualifications = academicQualifications;
    }

    /**
     * Gets non academic projects.
     *
     * @return the non academic projects
     */
    public List<ProjectDetail> getNonAcademicProjects() {
        return nonAcademicProjects;
    }

    /**
     * Sets non academic projects.
     *
     * @param nonAcademicProjects the non academic projects
     */
    public void setNonAcademicProjects(List<ProjectDetail> nonAcademicProjects) {
        this.nonAcademicProjects = nonAcademicProjects;
    }

    /**
     * Gets academic projects.
     *
     * @return the academic projects
     */
    public List<ProjectDetail> getAcademicProjects() {
        return academicProjects;
    }

    /**
     * Sets academic projects.
     *
     * @param academicProjects the academic projects
     */
    public void setAcademicProjects(List<ProjectDetail> academicProjects) {
        this.academicProjects = academicProjects;
    }

    /**
     * Gets research projects.
     *
     * @return the research projects
     */
    public List<ProjectDetail> getResearchProjects() {
        return researchProjects;
    }

    /**
     * Sets research projects.
     *
     * @param researchProjects the research projects
     */
    public void setResearchProjects(List<ProjectDetail> researchProjects) {
        this.researchProjects = researchProjects;
    }

    /**
     * Gets skill sets.
     *
     * @return the skill sets
     */
    public List<SkillSet> getSkillSets() {
        return skillSets;
    }

    /**
     * Sets skill sets.
     *
     * @param skillSets the skill sets
     */
    public void setSkillSets(List<SkillSet> skillSets) {
        this.skillSets = skillSets;
    }

    /**
     * Gets areas of interest.
     *
     * @return the areas of interest
     */
    public List<String> getAreasOfInterest() {
        return areasOfInterest;
    }

    /**
     * Sets areas of interest.
     *
     * @param areasOfInterest the areas of interest
     */
    public void setAreasOfInterest(List<String> areasOfInterest) {
        this.areasOfInterest = areasOfInterest;
    }

    /**
     * Gets extra curricular.
     *
     * @return the extra curricular
     */
    public List<String> getExtraCurricular() {
        return extraCurricular;
    }

    /**
     * Sets extra curricular.
     *
     * @param extraCurricular the extra curricular
     */
    public void setExtraCurricular(List<String> extraCurricular) {
        this.extraCurricular = extraCurricular;
    }


    /**
     * Map resume detail.
     *
     * @param document the document
     * @return the resume detail
     */
    public static ResumeDetail map(Document document) {
        ResumeDetail rd = new ResumeDetail(
                document.getString("title"),
                document.getString("name"),
                document.getString("rollNumber"),
                document.getString("dateOfBirth"),
                document.getString("address"),
                document.getString("phoneNumber"),
                document.getString("email")
        );

        rd.setGender(document.getString("gender"));
        rd.setCollegeName(document.getString("collegeName"));
        rd.setCourse(document.getString("course"));
        rd.setYearOfStudy(document.getString("yearOfStudy"));
        rd.setDepartmentName(document.getString("departmentName"));
        rd.setCourseDuration(document.getString("courseDuration"));
        rd.setGitHubLink(document.getString("gitHubLink"));
        rd.setLinkedinLink(document.getString("linkedinLink"));
        rd.setDeclarationLocation(document.getString("declarationLocation"));
        rd.setDeclarationDate(document.getString("declarationDate"));
        rd.setImagePath(document.getString("imagePath"));

        rd.setLanguages(document.getList("languages", String.class));
        rd.setAreasOfInterest(document.getList("areasOfInterest", String.class));
        rd.setExtraCurricular(document.getList("extraCurricular", String.class));

        rd.setObjective(Objective.map(document.get("objective", Document.class)));

        try {
            ListMapper<AcademicQualification> academicQualificationListMapper = new ListMapper<>(AcademicQualification.class);
            ListMapper<ProjectDetail> projectDetailListMapper = new ListMapper<>(ProjectDetail.class);
            ListMapper<SkillSet> skillSetListMapper = new ListMapper<>(SkillSet.class);

            rd.setAcademicQualifications(
                    academicQualificationListMapper.map(document.getList("academicQualifications", Document.class))
            );
            rd.setNonAcademicProjects(
                    projectDetailListMapper.map(document.getList("nonAcademicProjects", Document.class))
            );
            rd.setAcademicProjects(
                    projectDetailListMapper.map(document.getList("academicProjects", Document.class))
            );
            rd.setResearchProjects(
                    projectDetailListMapper.map(document.getList("researchProjects", Document.class))
            );
            rd.setSkillSets(
                    skillSetListMapper.map(document.getList("skillSets", Document.class))
            );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return rd;
    }
}
