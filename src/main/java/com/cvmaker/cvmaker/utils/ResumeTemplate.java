package com.cvmaker.cvmaker.utils;

import com.itextpdf.kernel.colors.DeviceRgb;
import com.cvmaker.cvmaker.schema.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The interface Resume template.
 */
public interface  ResumeTemplate {

    /**
     * The constant BASE_FILE_PATH.
     */
    String BASE_FILE_PATH = "src/main/resources/com/cvmaker/cvmaker/";

    /**
     * Get local filler resume detail resume detail.
     *
     * @return the resume detail
     */
    static ResumeDetail getLocalFillerResumeDetail(){

        ResumeDetail resume = new ResumeDetail("SUMMA",
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
        resume.setObjective( new Objective("May", "December", "2025", "2025" ));


        resume.setCollegeName("PSG College of Technology");
        resume.setYearOfStudy("3");
        resume.setCourse("Integrated MSc Software Systems");
        resume.setCourseDuration("5");
        resume.setDepartmentName("Department of Applied Mathematics and Computational Sciences");
        resume.setDeclarationLocation("Coimbatore");
        resume.setDeclarationDate("18/05/2005");

        ArrayList<String> lang = new ArrayList<>();
        lang.add("Python");
        lang.add("JavaScript");
        lang.add("C/C++");
        lang.add("SQL");
        lang.add("Java");

        ArrayList<String> dbs = new ArrayList<>();
        dbs.add("MySQL");
        dbs.add("PostgreSQL");
        dbs.add("MongoDB");


        ArrayList<String> platforms = new ArrayList<>();
        platforms.add("Linux");
        platforms.add("Windows");

        ArrayList<String> tools = new ArrayList<>();
        tools.add("Git");
        tools.add("Postman");
        tools.add("Docker");
        tools.add("Obsidian");

        SkillSet s1 = new SkillSet("Languages", lang);
        SkillSet s2 = new SkillSet("Databases", dbs);
        SkillSet s3 = new SkillSet("Platform", platforms);
        SkillSet s4 = new SkillSet("Tools", tools);

        resume.setSkillSets(Arrays.asList(s1,s2,s3,s4));

        resume.setAreasOfInterest(Arrays.asList("Database design", "Full Stack Development", "Object Oriented Programming", "Operating Systems"));

        AcademicQualification aq1 = new AcademicQualification("M.Sc Software Systems", "2022","2027", "PSG College of Technology, Coimbatore", "9.86", "CGPA");
        AcademicQualification aq2 = new AcademicQualification("XII (Higher secondary, State Board)",null,"2022", "Kongu Vellalar Matric. Hr. Sec School, Coimbatore", "99", "PERCENTAGE");
        AcademicQualification aq3 = new AcademicQualification("X (SSLC, State board)", null,"2020", "Kongu Vellalar Matric. Hr. Sec School, Coimbatore", "99", "PERCENTAGE");
        resume.setAcademicQualifications(
                Arrays.asList(aq1, aq2, aq3 )
        );

        ProjectDetail research = new ProjectDetail(
                "Department of RAE & Ministry of Heavy Industries",
                "May 2024 - June 2024",
                "https://github.com/hajay180505/verano-24",
                Arrays.asList("PSG", "AR2", "Robot", "Heavy", "Industries", "Robotic", "Controller", "Welding", "Applications.", "PyQt", "ROS", "image", "classification", "Transfer", "Learning"),
                Arrays.asList("Research and Development Intern"),
                "Worked on the Design and Development of User Interface for PSG AR2 Robot as a part of the Ministry of Heavy Industries (MHI) funded research project on the Development of Robotic Controller for Welding Applications. Used PyQt for UI development and explored ROS. Accomplished image classification of surface defects using Transfer Learning."
        );
        resume.setResearchProjects(Arrays.asList(research));

        ProjectDetail nap1 = new ProjectDetail(
                "Proleap",
                null,
                "https://proleap.vercel.app/",
                Arrays.asList("Django", "Rest", "Framework", "JWT", "Postman"),
                Arrays.asList("Django", "React", "Heroku"),
                "A web application that is used to automate career sessions with interactive activities to engage and build student’s profiles. Used Django and Django Rest Framework for the backend design and JWT for authentication and authorisation. Thorough API testing was done using Postman. Email automation is another notable feature."
        );
        ProjectDetail nap2 = new ProjectDetail(
                "Darpanet",
                null,
                null,
                Arrays.asList("PaaS", "cvMaker/database", "design", "testing", "API,", "Unit", "testing,", "Data", "Flow", "Stress", "Postman", "seeding"),
                Arrays.asList("NestJS", "PostgreSQL", "Redis", "React", "Docker"),
                "Darpanet is a PaaS (Platform as a Service) for event management systems. Contributed by helping with the database design, testing the API, including Unit testing, Data Flow testing, Stress testing using Postman, and seeding the database for testing."
        );

        resume.setNonAcademicProjects(Arrays.asList(nap1,nap2));


        ProjectDetail ap2 = new ProjectDetail(
                "Need For Feast",
                null,
                "https://github.com/hajay180505/need-for-feast",
                Arrays.asList("Role", "based", "interfaces", "Django", "sqlite3", "CSS"),
                Arrays.asList("Django", "CSS", "SQLite"),
                "A food delivery web application where customers order food from various restaurants that are within their vicinity. Role based interfaces are provided for Customer, Shop owners and Deliverer. Used Django for backend, django templates with CSS for frontend and sqlite3 for the database."
        );
        ProjectDetail ap1 = new ProjectDetail(
                "LocateMe",
                null,
                "https://github.com/hajay180505/LocateMe",
                Arrays.asList("KNN"),
                Arrays.asList("Python", "RPI", "KNN"),
                "A raspberry pi based location predictor that predicts the location of the user in a floor. Uses the trained data of the wifi signal strengths from the routers in the building and KNN for predicting the location with the RSSI values of fixed routers in the building."
        );

        ProjectDetail ap3 = new ProjectDetail(
                "MIPS 32 Emulator",
                "May 2024 - June 2024",
                "https://github.com/hajay180505/verano-24",
                Arrays.asList("C++", "OOP"),
                Arrays.asList("C++", "OOP"),
                "A parser for MIPS 32 instruction set. Used modern C++ features with OOP principles for implementing the parser."
        );
        resume.setAcademicProjects(Arrays.asList(ap1,ap2,ap3));

        resume.setExtraCurricular(Arrays.asList("Member of the creativity team of PSG Radio Hub.",
                "Member of Fine Arts Club, PSGCT.",
                "Best Outgoing Student in High School.",
                "Winner of Ideation ’23 , a contest conducted by Coding Club, PSGCT.",
                "Making papercraft works, origami models and poetry in spare time."));

        return resume;
    }

    /**
     * Gets template.
     *
     * @return the template
     * @throws IOException the io exception
     */
    String getTemplate() throws IOException;

    /**
     * Generate resume string.
     *
     * @param resumeDetail the resume detail
     * @param accentColor  the accent color
     * @param username     the username
     * @param filename     the filename
     * @return the string
     * @throws IOException the io exception
     */
    String generateResume(ResumeDetail resumeDetail, DeviceRgb accentColor, String username, String filename) throws IOException;
}
