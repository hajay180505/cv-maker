package com.cvmaker.cvmaker;

import com.cvmaker.cvmaker.controllers.WelcomeController;
import com.cvmaker.cvmaker.database.Database;
import com.cvmaker.cvmaker.database.User;
import com.cvmaker.cvmaker.exception.InsertionException;
import com.cvmaker.cvmaker.schema.*;
import com.cvmaker.cvmaker.utils.ResumeTemplate;
import com.cvmaker.cvmaker.utils.TwoPagerOfficialResume;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Separator;
import javafx.stage.Stage;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main extends Application {

    public static void insertFiller(){
        ResumeDetail resume = new ResumeDetail("TwoPagerOfficial",
                "John Doe", "21PX01",
                "2008-08-12",
                "123, ABD Puram, DB road, Montana, Florida - 641459",
                "9090912345",
                "21px01@institute.dom.cou");

        resume.setGender("Female");
        resume.setLanguages(Arrays.asList("English", "Arabic"));
        resume.setLinkedinLink("https://github.com/hajay180505");
        resume.setGitHubLink("https://github.com/hajay180505");
        resume.setImagePath("src/main/resources/com/cvmaker/cvmaker/images/resume-user-image.jpeg");
        resume.setObjective( new Objective("May", "December", "2021", "2021" ));


        resume.setCollegeName("University of North Ireland");
        resume.setYearOfStudy("3");
        resume.setCourse("Integrated MSc Management Systems");
        resume.setCourseDuration("5");
        resume.setDepartmentName("Department of Management and Services");

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

        AcademicQualification aq1 = new AcademicQualification("M.Sc Management Systems", "2021","2026", "University of North Ireland, Iowa", "8.86", "CGPA");
        AcademicQualification aq2 = new AcademicQualification("XII (Higher secondary, State Board)",null,"2022", "Kendraiya Vidhyalaya, Belarus", "99", "PERCENTAGE");
        AcademicQualification aq3 = new AcademicQualification("X (SSLC, State board)", null,"2020", "Kendraiya Vidhyalaya, Belarus", "99", "PERCENTAGE");
        resume.setAcademicQualifications(
                Arrays.asList(aq1, aq2, aq3 )
        );

        ProjectDetail research = new ProjectDetail(
                "Research project 1",
                "May 2020 - June 2021",
                "https://github.com/hajay180505/verano-24",
                Arrays.asList("Lorem", "Ipsum", "et", "per"),
                Arrays.asList("Research and Development Intern"),
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
        );
        resume.setResearchProjects(Arrays.asList(research));

        ProjectDetail nap1 = new ProjectDetail(
                "Project 1",
                null,
                "https://github.com/hajay180505/",
                Arrays.asList("amet", "adipiscing", "cillum", "deserunt"),
                Arrays.asList("Django", "React", "Heroku"),
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."

        );
        ProjectDetail nap2 = new ProjectDetail(
                "Project 2",
                null,
                null,
                Arrays.asList("sit", "enim", "nostrud", "reprehenderit"),
                Arrays.asList("NestJS", "PostgreSQL", "Redis", "React", "Docker"),
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."

        );

        resume.setNonAcademicProjects(Arrays.asList(nap1,nap2));


        ProjectDetail ap2 = new ProjectDetail(
                "Project 3",
                null,
                "https://github.com/hajay180505/need-for-feast",
                Arrays.asList("Role", "based", "interfaces", "Django", "sqlite3", "CSS"),
                Arrays.asList("Django", "CSS", "SQLite"),
                "A food delivery web application where customers order food from various restaurants that are within their vicinity. Role based interfaces are provided for Customer, Shop owners and Deliverer. Used Django for backend, django templates with CSS for frontend and sqlite3 for the database."
        );
        ProjectDetail ap1 = new ProjectDetail(
                "Project 4",
                null,
                "https://github.com/hajay180505/LocateMe",
                Arrays.asList("KNN"),
                Arrays.asList("Python", "RPI", "KNN"),
                "A raspberry pi based location predictor that predicts the location of the user in a floor. Uses the trained data of the wifi signal strengths from the routers in the building and KNN for predicting the location with the RSSI values of fixed routers in the building."
        );

        resume.setAcademicProjects(Arrays.asList(ap1,ap2));

        resume.setExtraCurricular(Arrays.asList("Member of the potato farming team of UNI.",
                "Member of Underachievers Club, KV.",
                "Best Outgoing Student in High School.",
                "Winner of Ideation ’23 , a contest conducted by Coding Club, Meta.",
                "Making papercraft works, origami models and poetry in spare time."));

        List<ResumeDetail> r = new ArrayList<>();
        r.add(resume);
        User u = new User("static", r ,"", "");
        System.out.println(u.getName());
        try {
            Database.getDatabase().insertUser(u);
        } catch (InsertionException e) {
            throw new RuntimeException(e);
        }
    }

    public final static boolean IS_DEV = true;
    @Override
    public void start(Stage stage) throws IOException {

        // singleton
        Database db = Database.getDatabase();

        if (db.ping()){
            System.out.println("PONG");
        }
        else {
            System.out.println("ERROR");
        }

        ResumeTemplate rt = new TwoPagerOfficialResume();
        try {
            System.out.println("Filler resume at : " + rt.getTemplate());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Label welcomeLabel = new Label("Welcome to CV Maker!");
        welcomeLabel.setFont(Font.font("Arial", 24));
        welcomeLabel.setTextFill(Color.valueOf("#003366"));

        Label optionLabel = new Label("Please choose an option");
        optionLabel.setFont(Font.font("Arial", 18));
        optionLabel.setTextFill(Color.valueOf("#003366"));


        WelcomeController controller = new WelcomeController();
        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10 20;");
        loginButton.setOnAction(event -> controller.handleLoginInfo(stage));
        Button signUpButton = new Button("Sign Up");
        signUpButton.setStyle("-fx-background-color: #008CBA; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10 20;");
        signUpButton.setOnAction(event -> controller.handleSignUp(stage));

        HBox hBoxTop = new HBox(10);
        hBoxTop.getChildren().addAll(welcomeLabel, optionLabel);
        hBoxTop.setAlignment(Pos.CENTER);

        HBox hBoxBottom = new HBox(10);
        hBoxBottom.getChildren().addAll(loginButton, signUpButton);
        hBoxBottom.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(15);
        vbox.setPadding(new Insets(20));
        vbox.setStyle("-fx-background-color: #e6f7ff;");
        vbox.getChildren().addAll(welcomeLabel, new Separator(Orientation.HORIZONTAL), optionLabel, hBoxTop, hBoxBottom);//, loginButton, signUpButton);

        Scene scene = new Scene(vbox, 600, 300);
        stage.setTitle("CV Maker");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        // thank you beyonce!!!! <3
        launch(args);
    }
}