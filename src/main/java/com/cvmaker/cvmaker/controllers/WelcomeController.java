package com.cvmaker.cvmaker.controllers;

import com.cvmaker.cvmaker.database.Database;
import com.cvmaker.cvmaker.database.User;
import com.cvmaker.cvmaker.exception.InsertionException;
import com.cvmaker.cvmaker.exception.InvalidFieldValueException;
import com.cvmaker.cvmaker.schema.*;
import com.cvmaker.cvmaker.utils.ResumeTemplate;
import com.cvmaker.cvmaker.utils.TwoPagerOfficialResume;
import com.cvmaker.cvmaker.validator.DateValidator;
import com.cvmaker.cvmaker.validator.EmailValidator;
import com.cvmaker.cvmaker.validator.LinkValidator;
import com.cvmaker.cvmaker.validator.PhoneNumberValidator;
import com.itextpdf.kernel.colors.DeviceRgb;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.stage.DirectoryChooser;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * The type Welcome controller.
 */
public class WelcomeController {

    private int totalProjects;
    private int currentProjectIndex = 0;
    private int currentAcademicQualificationDetailIndex = 0;

    /**
     * Handle sign up.
     *
     * @param stage the stage
     */
    public void handleSignUp(@NotNull Stage stage) {

        VBox wrapper = new VBox();
        wrapper.setPadding(new Insets(20));
        wrapper.setStyle("-fx-background-color: #e6f7ff;");

        Label header = new Label("Basic information");
        header.setFont(Font.font("Arial", 24));

        GridPane signUpPage = new GridPane();
        signUpPage.setStyle("-fx-background-color: #e6f7ff;");

        signUpPage.setPadding(new Insets(20));
        signUpPage.setHgap(10);
        signUpPage.setVgap(10);

        // Create UI components
        Label usernameLabel = new Label("Username");
        usernameLabel.setFont(Font.font("Arial", 18));

        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter your username");

        Label emailLabel = new Label("Email");
        emailLabel.setFont(Font.font("Arial", 18));

        TextField emailField = new TextField();
        emailField.setPromptText("Enter your email address");

        Label passwordLabel = new Label("Password");
        passwordLabel.setFont(Font.font("Arial", 18));

        TextField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");

        Label confirmPasswordLabel = new Label("Confirm Password");
        confirmPasswordLabel.setFont(Font.font("Arial", 18));

        TextField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Re-enter your password");

        Button signUpButton = new Button("Next");
        signUpButton.setStyle("-fx-background-color: #008CBA; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10 20;");
        Label messageLabel = new Label();
        messageLabel.setTextFill(Color.valueOf("#EA4335"));

        // Add components to the layout
        signUpPage.add(usernameLabel, 0, 0);
        signUpPage.add(usernameField, 1, 0);
        signUpPage.add(emailLabel, 0, 1);
        signUpPage.add(emailField, 1, 1);
        signUpPage.add(passwordLabel, 0, 2);
        signUpPage.add(passwordField, 1, 2);
        signUpPage.add(confirmPasswordLabel, 0, 3);
        signUpPage.add(confirmPasswordField, 1, 3);
        signUpPage.add(signUpButton, 0, 4, 2, 1); // span 2 columns
        signUpPage.add(messageLabel, 0, 5, 2, 1); // span 2 columns

        // Set button action
        signUpButton.setOnAction(e -> {
            String username = usernameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();

            // Implement your sign-up logic here
            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                messageLabel.setText("Please fill in all fields.");
            } else if (!password.equals(confirmPassword)) {
                messageLabel.setText("Passwords do not match.");
            } else {
                try {
                    new EmailValidator().validate(email);
                    messageLabel.setText("Sign-up successful!");
                    User u = new User(username, new ArrayList<>(), email, password);
                    getPersonalDetails(stage, u);
                } catch (InvalidFieldValueException ex) {
                    messageLabel.setText("Invalid email.");
                }
                //signUpButton.setOnAction(event -> handleUserInput(stage1));
                // Placeholder message
                // Proceed with successful sign-up logic (e.g., send confirmation email)
            }
        });
        wrapper.getChildren().addAll(header, new Separator(Orientation.HORIZONTAL), signUpPage);

        Scene signUpScene = new Scene(wrapper, 600, 300); // Adjust the size as needed
        stage.setScene(signUpScene);
        stage.show();
    }

    /**
     * Gets personal details.
     *
     * @param stage the stage
     * @param u     the user
     */
    void getPersonalDetails(@NotNull Stage stage, User u) {
        VBox wrapper = new VBox();
        wrapper.setPadding(new Insets(20));
        wrapper.setStyle("-fx-background-color: #e6f7ff;");

        Label header = new Label("Personal Details");
        header.setFont(Font.font("Arial", 24));

        GridPane inputPage = new GridPane();
        inputPage.setPadding(new Insets(20));
        inputPage.setHgap(10);
        inputPage.setVgap(10);

        // Add labels and text fields for user input
        Label nameLabel = new Label("Name");
        nameLabel.setFont(Font.font("Arial", 18));
        TextField nameField = new TextField();
        setMaxLength(nameField, 20);
        nameField.setPrefWidth(250);

        Label rollNoLabel = new Label("Roll Number");
        rollNoLabel.setFont(Font.font("Arial", 18));
        TextField rollNoField = new TextField();
        setMaxLength(rollNoField, 20);
        rollNoField.setPrefWidth(250);

        Label genderLabel = new Label("Gender");
        genderLabel.setFont(Font.font("Arial", 18));
        ToggleGroup genderGroup = new ToggleGroup();

        RadioButton maleButton = new RadioButton("Male");
        maleButton.setFont(Font.font("Arial", 18));

        RadioButton femaleButton = new RadioButton("Female");
        femaleButton.setFont(Font.font("Arial", 18));

        maleButton.setToggleGroup(genderGroup);
        femaleButton.setToggleGroup(genderGroup);

        Label dobLabel = new Label("Date of Birth");
        dobLabel.setFont(Font.font("Arial", 18));
        DatePicker dobPicker = new DatePicker();

        Label emailLabel = new Label("Email ID");
        emailLabel.setFont(Font.font("Arial", 18));
        TextField emailField = new TextField();
        setMaxLength(emailField, 20);
        emailField.setPrefWidth(250);

        Label phoneLabel = new Label("Phone Number");
        phoneLabel.setFont(Font.font("Arial", 18));
        TextField phoneField = new TextField();
        setMaxLength(phoneField, 20);
        phoneField.setPrefWidth(250);

        Label languagesLabel = new Label("Languages Known");
        languagesLabel.setFont(Font.font("Arial", 18));
        TextField languagesField = new TextField();
        languagesField.setPromptText("Separate by commas");
        setMaxLength(languagesField, 20);
        languagesField.setPrefWidth(250);

        Label linkedinLabel = new Label("LinkedIn Link");
        linkedinLabel.setFont(Font.font("Arial", 18));
        TextField linkedinField = new TextField();
        setMaxLength(linkedinField, 20);
        linkedinField.setPrefWidth(250);

        Label githubLabel = new Label("GitHub Link");
        githubLabel.setFont(Font.font("Arial", 18));
        TextField githubField = new TextField();
        setMaxLength(githubField, 20);
        githubField.setPrefWidth(250);

        Label addressLabel = new Label("Address");
        addressLabel.setFont(Font.font("Arial", 18));
        TextArea addressField = new TextArea();
        addressField.setPrefRowCount(3);
        addressField.setPrefWidth(250);
        setMaxLength(addressField, 100);

        // Add components to the layout
        inputPage.add(nameLabel, 0, 0);
        inputPage.add(nameField, 1, 0);
        inputPage.add(rollNoLabel, 0, 1);
        inputPage.add(rollNoField, 1, 1);
        inputPage.add(genderLabel, 0, 2);
        inputPage.add(maleButton, 1, 2);
        inputPage.add(femaleButton, 1, 3);
        inputPage.add(dobLabel, 0, 4);
        inputPage.add(dobPicker, 1, 4);
        inputPage.add(emailLabel, 0, 5);
        inputPage.add(emailField, 1, 5);
        inputPage.add(phoneLabel, 0, 6);
        inputPage.add(phoneField, 1, 6);
        inputPage.add(languagesLabel, 0, 7);
        inputPage.add(languagesField, 1, 7);
        inputPage.add(linkedinLabel, 0, 8);
        inputPage.add(linkedinField, 1, 8);
        inputPage.add(githubLabel, 0, 9);
        inputPage.add(githubField, 1, 9);
        inputPage.add(addressLabel, 0, 10);
        inputPage.add(addressField, 1, 10);

        // Create a button to submit the input
        Button submitButton = new Button("Next");
        submitButton.setStyle("-fx-background-color: #008CBA; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10 20;");

        Label messageLabel = new Label();
        messageLabel.setTextFill(Color.valueOf("#EA4335"));
        messageLabel.setFont(Font.font("Arial", 18));

        inputPage.add(messageLabel, 0, 12, 2, 1); // Position it at the end

        submitButton.setOnAction(event -> {
            String name = nameField.getText();
            String rollNo = rollNoField.getText();
            String gender = ((RadioButton) genderGroup.getSelectedToggle()).getText();
            String dob = dobPicker.getValue() != null ? dobPicker.getValue().toString() : "";
            String email = emailField.getText();
            String phoneNumber = phoneField.getText();
            String languages = languagesField.getText();
            String linkedin = linkedinField.getText();
            String github = githubField.getText();
            String address = addressField.getText();

            AtomicBoolean allCorrect = new AtomicBoolean(true);

            System.out.println("DOB = " + dob );
            if (name.isEmpty() || rollNo.isEmpty() || gender.isEmpty() || dob.isEmpty() || email.isEmpty() ||
                    phoneNumber.isEmpty() || languages.isEmpty() || linkedin.isEmpty() || github.isEmpty() || address.isEmpty()) {
                allCorrect.set(false);
                messageLabel.setText("Please fill in all fields.");
            }

            if (! DateValidator.isDateBeforeKYears(dobPicker.getValue(), 18)){
                allCorrect.set(false);
                messageLabel.setText("Invalid date of birth");
            }

            List<String> languagesList = Arrays.asList( languages.split(","));
            languagesList.forEach( language ->{
                if (language.isEmpty()){
                    allCorrect.set(false);
                    messageLabel.setText("Invalid languages field");
                }
            });
            languagesList = languagesList.stream().map(String::trim).collect(Collectors.toList());

            try{
                new EmailValidator().validate(email);
                new PhoneNumberValidator().validate(phoneNumber);
                new LinkValidator().validate(github);
                new LinkValidator().validate(linkedin);

            } catch (InvalidFieldValueException e){
                allCorrect.set(false);
                messageLabel.setText(e.getMessage());
            }

            if (allCorrect.get()){
                ResumeDetail resumeDetail = new ResumeDetail(
                        u.getName().toLowerCase().trim() +"-resume",
                        name, rollNo, dob, address, phoneNumber, email
                );
                resumeDetail.setEmail(email);
                resumeDetail.setAddress(address);
                resumeDetail.setLanguages(languagesList);
                resumeDetail.setGender(gender);
                resumeDetail.setGitHubLink(github);
                resumeDetail.setLinkedinLink(linkedin);
                resumeDetail.setDateOfBirth(dob);

                u.getResumeDetails().add(resumeDetail);
                getObjectiveExtraCurricularAndAreasOfInterestDetails(stage, u, resumeDetail);

            }

        });


        inputPage.add(submitButton, 0, 11, 2, 1); // span 2 columns

        // Set the scene on the stage
        wrapper.getChildren().addAll(header, new Separator(Orientation.HORIZONTAL), inputPage);

        Scene inputScene = new Scene(wrapper, 600, 700); // Adjust dimensions as needed
        stage.setScene(inputScene);
    }

    /**
     * Gets objective, extra-curricular and areas of interest details.
     *
     * @param stage        the stage
     * @param u            the user
     * @param resumeDetail the resume detail
     */
    void getObjectiveExtraCurricularAndAreasOfInterestDetails(@NotNull Stage stage, User u, ResumeDetail resumeDetail) {
        VBox wrapper = new VBox();
        wrapper.setPadding(new Insets(20));
        wrapper.setStyle("-fx-background-color: #e6f7ff;");

        Label header = new Label("Objective Details");
        header.setFont(Font.font("Arial", 24));

        GridPane objectivePage = new GridPane();
        objectivePage.setPadding(new Insets(20));
        objectivePage.setHgap(10);
        objectivePage.setVgap(10);

        // Add labels and text fields for objective details
        Label startMonthLabel = new Label("Start Month");
        startMonthLabel.setFont(Font.font("Arial", 18));
        TextField startMonthField = new TextField();
        setMaxLength(startMonthField, 20);
        startMonthField.setPrefWidth(150);

        Label endMonthLabel = new Label("End Month");
        endMonthLabel.setFont(Font.font("Arial", 18));
        TextField endMonthField = new TextField();
        setMaxLength(endMonthField, 20);
        endMonthField.setPrefWidth(150);

        Label startYearLabel = new Label("Start Year");
        startYearLabel.setFont(Font.font("Arial", 18));
        TextField startYearField = new TextField();
        setMaxLength(startYearField, 4);
        startYearField.setPrefWidth(150);

        Label endYearLabel = new Label("End Year");
        endYearLabel.setFont(Font.font("Arial", 18));
        TextField endYearField = new TextField();
        setMaxLength(endYearField, 4);
        endYearField.setPrefWidth(150);

        Label extraCurricularLabel = new Label("Extra-curricular details");
        extraCurricularLabel.setFont(Font.font("Arial", 18));
        TextField extraCurricularField = new TextField();
        extraCurricularField.setPromptText("Separate by semicolon ; ");
        setMaxLength(extraCurricularField, 100);
        extraCurricularField.setPrefWidth(150);

        Label areasOfInterestLabel = new Label("Areas of Interest");
        areasOfInterestLabel.setFont(Font.font("Arial", 18));
        TextField areasOfInterestField = new TextField();
        areasOfInterestField.setPromptText("Separate by semicolon ; ");
        setMaxLength(areasOfInterestField, 100);
        areasOfInterestField.setPrefWidth(150);


        // Add components to the layout
        objectivePage.add(startMonthLabel, 0, 0);
        objectivePage.add(startMonthField, 1, 0);
        objectivePage.add(endMonthLabel, 0, 1);
        objectivePage.add(endMonthField, 1, 1);
        objectivePage.add(startYearLabel, 0, 2);
        objectivePage.add(startYearField, 1, 2);
        objectivePage.add(endYearLabel, 0, 3);
        objectivePage.add(endYearField, 1, 3);
        objectivePage.add(extraCurricularLabel, 0, 4);
        objectivePage.add(extraCurricularField, 1, 4);
        objectivePage.add(areasOfInterestLabel, 0, 5);
        objectivePage.add(areasOfInterestField, 1, 5);

        Label messageLabel = new Label();
        messageLabel.setTextFill(Color.valueOf("#EA4335"));
        messageLabel.setFont(Font.font("Arial", 18));

        objectivePage.add(messageLabel, 0, 12, 2, 1);

        // Create a button to submit the input
        Button submitButton = new Button("Next");
        submitButton.setStyle("-fx-background-color: #008CBA; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10 20;");

        submitButton.setOnAction(event -> {
            String startMonth = startMonthField.getText();
            String endMonth = endMonthField.getText();
            String startYear = startYearField.getText();
            String endYear = endYearField.getText();
            String extraCurriculars = extraCurricularField.getText();
            String areasOfInterest = areasOfInterestField.getText();
            AtomicBoolean allCorrect = new AtomicBoolean(true);
            List<String> ecList = Arrays.asList( extraCurriculars.split(";"));
            ecList.forEach(language ->{
                if (language.isEmpty()){
                    allCorrect.set(false);
                    messageLabel.setText("Invalid extra curricular field");
                }
            });
            ecList = ecList.stream().map(String::trim).collect(Collectors.toList());

            List<String> areasList = Arrays.asList(areasOfInterest.split(";"));
            areasList.forEach(areas -> {
                if (areas.isEmpty()){
                    allCorrect.set(false);
                    messageLabel.setText("Invalid areas of interest field");
                }
            });
            areasList = areasList.stream().map(String::trim).collect(Collectors.toList());

            // Validate input
            if (startMonth.isEmpty() || endMonth.isEmpty() || startYear.isEmpty() ||
                    endYear.isEmpty() || extraCurriculars.isEmpty() || areasOfInterest.isEmpty()) {
                allCorrect.set(false);
                messageLabel.setText("Please fill in all fields.");
            } else {
                try{
                    List<String> months = Arrays.asList(
                            "january", " february", "march", "april", "may", "june", "july", "august", "september", "october", "november", "december"
                    );

                    if(Integer.parseInt(startYear) < LocalDate.now().getYear() || Integer.parseInt(endYear) < LocalDate.now().getYear() ) {
                        messageLabel.setText("Invalid Year");
                        allCorrect.set(false);
                    }

                    if (! months.contains(startMonth.toLowerCase()) || ! months.contains(endMonth.toLowerCase())) {
                        messageLabel.setText("Invalid Month");
                        allCorrect.set(false);
                    }

                } catch ( NumberFormatException e ) {
                    messageLabel.setText("Invalid Year");
                }

                if (allCorrect.get()){
                    Objective objective = new Objective(startMonth, endMonth, startYear, endYear);
                    resumeDetail.setObjective(objective);
                    resumeDetail.setExtraCurricular(ecList);
                    resumeDetail.setAreasOfInterest(areasList);
                    getDegreeDetails(stage, u, resumeDetail);
                }
            }
        });

        objectivePage.add(submitButton, 0, 6, 2, 1); // span 2 columns

        // Set the scene on the stage
        wrapper.getChildren().addAll(header, new Separator(Orientation.HORIZONTAL), objectivePage);
        Scene objectiveScene = new Scene(wrapper, 600, 700); // Adjust dimensions as needed
        stage.setScene(objectiveScene);
    }

    /**
     * Get degree details.
     *
     * @param stage        the stage
     * @param u            the user
     * @param resumeDetail the resume detail
     */
    void getDegreeDetails(@NotNull Stage stage, User u, ResumeDetail resumeDetail){
        VBox wrapper = new VBox();
        wrapper.setPadding(new Insets(20));
        wrapper.setStyle("-fx-background-color: #e6f7ff;");

        Label header = new Label("Degree Details");
        header.setFont(Font.font("Arial", 24));

        GridPane objectivePage = new GridPane();
        objectivePage.setPadding(new Insets(20));
        objectivePage.setHgap(10);
        objectivePage.setVgap(10);

        // Add labels and text fields for objective details
        Label courseNameLabel = new Label("Course name");
        courseNameLabel.setFont(Font.font("Arial", 18));
        TextField courseNameField = new TextField();
        setMaxLength(courseNameField, 20);
        courseNameField.setPrefWidth(150);

        Label collegeNameLabel = new Label("College Name");
        collegeNameLabel.setFont(Font.font("Arial", 18));
        TextField collegeNameField = new TextField();
        setMaxLength(collegeNameField, 20);
        collegeNameField.setPrefWidth(150);

        Label yearOfStudyLabel = new Label("Year of study");
        yearOfStudyLabel.setFont(Font.font("Arial", 18));
        TextField yearOfStudyField = new TextField();
        setMaxLength(yearOfStudyField, 4);
        yearOfStudyField.setPrefWidth(150);

        Label departmentNameLabel = new Label("Department Name");
        departmentNameLabel.setFont(Font.font("Arial", 18));
        TextField departmentNameField = new TextField();
        setMaxLength(departmentNameField, 4);
        departmentNameField.setPrefWidth(150);

        Label courseDurationLabel = new Label("Course Duration");
        courseDurationLabel.setFont(Font.font("Arial", 18));
        TextField courseDurationField = new TextField();
        setMaxLength(courseDurationField, 100);
        courseDurationField.setPrefWidth(150);


        // Add components to the layout
        objectivePage.add(courseNameLabel, 0, 0);
        objectivePage.add(courseNameField, 1, 0);
        objectivePage.add(collegeNameLabel, 0, 1);
        objectivePage.add(collegeNameField, 1, 1);
        objectivePage.add(yearOfStudyLabel, 0, 2);
        objectivePage.add(yearOfStudyField, 1, 2);
        objectivePage.add(departmentNameLabel, 0, 3);
        objectivePage.add(departmentNameField, 1, 3);
        objectivePage.add(courseDurationLabel, 0, 4);
        objectivePage.add(courseDurationField, 1, 4);

        Label messageLabel = new Label();
        messageLabel.setTextFill(Color.valueOf("#EA4335"));
        messageLabel.setFont(Font.font("Arial", 18));

        objectivePage.add(messageLabel, 0, 12, 2, 1);

        // Create a button to submit the input
        Button submitButton = new Button("Next");
        submitButton.setStyle("-fx-background-color: #008CBA; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10 20;");

        submitButton.setOnAction(event -> {
            String courseName = courseNameField.getText();
            String collegeName = collegeNameField.getText();
            String yearOfStudy = yearOfStudyField.getText();
            String departmentName = departmentNameField.getText();
            String courseDuration = courseDurationField.getText();
            AtomicBoolean allCorrect = new AtomicBoolean(true);


            // Validate input
            if (courseName.isEmpty() || collegeName.isEmpty() || yearOfStudy.isEmpty() ||
                    departmentName.isEmpty() || courseDuration.isEmpty() ) {
                allCorrect.set(false);
                messageLabel.setText("Please fill in all fields.");
            } else {
                try{
                  Integer.parseInt(yearOfStudy);
                  Integer.parseInt(courseDuration);
                } catch ( NumberFormatException e ) {
                    messageLabel.setText(e.getMessage());
                }

                if (allCorrect.get()){
                    resumeDetail.setCourse(courseName);
                    resumeDetail.setCollegeName(collegeName);
                    resumeDetail.setYearOfStudy(yearOfStudy);
                    resumeDetail.setDepartmentName(departmentName);
                    resumeDetail.setCourseDuration(courseDuration);
                    getAcademicQualifications(stage, u, resumeDetail);


                }
            }
        });

        objectivePage.add(submitButton, 0, 6, 2, 1); // span 2 columns

        // Set the scene on the stage
        wrapper.getChildren().addAll(header, new Separator(Orientation.HORIZONTAL), objectivePage);
        Scene objectiveScene = new Scene(wrapper, 600, 700); // Adjust dimensions as needed
        stage.setScene(objectiveScene);

    }

    /**
     * Gets academic qualifications.
     *
     * @param stage        the stage
     * @param u            the user
     * @param resumeDetail the resume detail
     */
    void getAcademicQualifications(@NotNull Stage stage, User u, ResumeDetail resumeDetail) {
        if (currentAcademicQualificationDetailIndex >=3){
            System.out.println("ok");
            getProjectDetailsCount(stage,u,resumeDetail);
            return;
        }

        VBox wrapper = new VBox();
        wrapper.setPadding(new Insets(20));
        wrapper.setStyle("-fx-background-color: #e6f7ff;");

        Label header = new Label("Academic Qualifications");
        header.setFont(Font.font("Arial", 24));

        Label counter = new Label("(" + (currentAcademicQualificationDetailIndex + 1) +" of 3)");

        GridPane academicPage = new GridPane();
        academicPage.setPadding(new Insets(20));
        academicPage.setHgap(10);
        academicPage.setVgap(10);

        // Add labels and input fields for academic qualifications
        Label fromDateLabel = new Label("From Date");
        fromDateLabel.setFont(Font.font("Arial", 18));
        DatePicker fromDatePicker = new DatePicker();
        fromDatePicker.setPrefWidth(150);

        Label toDateLabel = new Label("To Date");
        toDateLabel.setFont(Font.font("Arial", 18));
        DatePicker toDatePicker = new DatePicker();
        toDatePicker.setPrefWidth(150);

        Label qualificationLabel = new Label("Qualification Name");
        qualificationLabel.setFont(Font.font("Arial", 18));
        TextField qualificationField = new TextField();
        setMaxLength(qualificationField, 100);
        qualificationField.setPrefWidth(150);

        Label instituteLabel = new Label("Institute Name");
        instituteLabel.setFont(Font.font("Arial", 18));
        TextField instituteField = new TextField();
        setMaxLength(instituteField, 100);
        instituteField.setPrefWidth(150);

        Label gradeTypeLabel = new Label("Grade Type");
        gradeTypeLabel.setFont(Font.font("Arial", 18));
        ToggleGroup gradeGroup = new ToggleGroup();
        RadioButton cgpaButton = new RadioButton("CGPA");
        RadioButton percentageButton = new RadioButton("Percentage");
        cgpaButton.setToggleGroup(gradeGroup);
        percentageButton.setToggleGroup(gradeGroup);
        HBox gradeTypeBox = new HBox(10, cgpaButton, percentageButton);

        Label gradeLabel = new Label("Grade");
        gradeLabel.setFont(Font.font("Arial", 18));
        TextField gradeField = new TextField();
        setMaxLength(gradeField, 10);
        gradeField.setPrefWidth(150);


        // Add components to the layout
        academicPage.add(fromDateLabel, 0, 0);
        academicPage.add(fromDatePicker, 1, 0);
        academicPage.add(toDateLabel, 0, 1);
        academicPage.add(toDatePicker, 1, 1);
        academicPage.add(qualificationLabel, 0, 2);
        academicPage.add(qualificationField, 1, 2);
        academicPage.add(instituteLabel, 0, 3);
        academicPage.add(instituteField, 1, 3);
        academicPage.add(gradeTypeLabel, 0, 4);
        academicPage.add(gradeTypeBox, 1, 4);
        academicPage.add(gradeLabel, 0, 5);
        academicPage.add(gradeField, 1, 5);

        // Create a button to submit the input
        Button submitButton = new Button("Next");
        submitButton.setStyle("-fx-background-color: #008CBA; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10 20;");

        Label messageLabel = new Label();
        messageLabel.setTextFill(Color.valueOf("#EA4335"));
        messageLabel.setFont(Font.font("Arial", 18));

        academicPage.add(messageLabel, 0, 12, 2, 1);

        submitButton.setOnAction( event -> {
            String fromDate = fromDatePicker.getValue() != null ? fromDatePicker.getValue().toString() : "";
            String toDate = toDatePicker.getValue() != null ? toDatePicker.getValue().toString() : "";
            String qualificationName = qualificationField.getText();
            String instituteName = instituteField.getText();
            String gradeType = gradeGroup.getSelectedToggle() != null ?
                    ((RadioButton) gradeGroup.getSelectedToggle()).getText() : "";
            String grade = gradeField.getText();

            // Validate input
            if (fromDate.isEmpty() || toDate.isEmpty() || qualificationName.isEmpty() ||
                    instituteName.isEmpty() || gradeType.isEmpty() || grade.isEmpty()) {
                messageLabel.setText("Please fill in all fields.");
            } else {
                resumeDetail.getAcademicQualifications()
                        .add( new  AcademicQualification(
                                qualificationName,
                                fromDate,
                                toDate,
                                instituteName,
                                grade,
                                gradeType

                            )
                        );
                currentAcademicQualificationDetailIndex ++;
                getAcademicQualifications(stage, u, resumeDetail);
            }
        });

        academicPage.add(submitButton, 0, 6, 2, 1); // span 2 columns


        // Set the scene on the stage
        wrapper.getChildren().addAll(header, counter, new Separator(Orientation.HORIZONTAL), academicPage);

        Scene academicScene = new Scene(wrapper, 600, 700); // Adjust dimensions as needed
        stage.setScene(academicScene);
    }

    /**
     * Gets project details count.
     *
     * @param stage        the stage
     * @param u            the user
     * @param resumeDetail the resume detail
     */
    void getProjectDetailsCount(Stage stage, User u, ResumeDetail resumeDetail) {
        TextInputDialog dialog = new TextInputDialog("1");
        dialog.setTitle("Number of Projects");
        dialog.setHeaderText("Enter the number of projects");
        dialog.setContentText("Number of Projects");

        dialog.showAndWait().ifPresent(number -> {
            try {
                totalProjects = Integer.parseInt(number);
                if (totalProjects > 0) {
                    getProjectDetails(stage, u, resumeDetail);
                } else {
                    System.out.println("Please enter a positive number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number.");
            }
        });
    }

    /**
     * Gets project details.
     *
     * @param stage        the stage
     * @param u            the user
     * @param resumeDetail the resume detail
     */
    void getProjectDetails(Stage stage, User u, ResumeDetail resumeDetail) {
        if (currentProjectIndex >= totalProjects) {
            System.out.println("All project details submitted successfully!");
            getSkillDetails(stage, u, resumeDetail);
            // Show summary or move to next step
            return;
        }

        VBox wrapper = new VBox();
        wrapper.setPadding(new Insets(20));
        wrapper.setStyle("-fx-background-color: #e6f7ff;");

        Label header = new Label("Project Details");
        header.setFont(Font.font("Arial", 24));
        Label counter = new Label("(" + (currentProjectIndex+1) + " of " + totalProjects + ")");

        GridPane projectPage = new GridPane();
        projectPage.setPadding(new Insets(20));
        projectPage.setHgap(10);
        projectPage.setVgap(10);

        Label projectTypeLabel = new Label("Type");
        projectTypeLabel.setFont(Font.font("Arial", 18));

        ToggleGroup projectTypeGroup = new ToggleGroup();

        RadioButton academicButton = new RadioButton("Academic");
        academicButton.setFont(Font.font("Arial", 18));

        RadioButton nonAcademicButton = new RadioButton("Non Academic");
        nonAcademicButton.setFont(Font.font("Arial", 18));

        RadioButton researchButton = new RadioButton("Research");
        researchButton.setFont(Font.font("Arial", 18));

        academicButton.setToggleGroup(projectTypeGroup);
        nonAcademicButton.setToggleGroup(projectTypeGroup);
        researchButton.setToggleGroup(projectTypeGroup);

        HBox projectTypeBox = new HBox(10, academicButton, nonAcademicButton, researchButton);

        Label nameLabel = new Label("Name");
        nameLabel.setFont(Font.font("Arial", 18));

        TextField nameField = new TextField();
        nameField.setPrefWidth(150);
        setMaxLength(nameField, 100);

        Label descriptionLabel = new Label("Description");
        descriptionLabel.setFont(Font.font("Arial", 18));
        TextArea descriptionField = new TextArea();
        descriptionField.setPrefWidth(150);
        descriptionField.setPrefRowCount(4);
        setMaxLength(descriptionField, 250);

        Label githubLinkLabel = new Label("GitHub Link");
        githubLinkLabel.setFont(Font.font("Arial", 18));
        TextField githubLinkField = new TextField();
        githubLinkField.setPrefWidth(150);
        setMaxLength(githubLinkField, 100);

        Label durationLabel = new Label("Duration");
        durationLabel.setFont(Font.font("Arial", 18));
        TextField durationField = new TextField();
        durationField.setPrefWidth(150);
        setMaxLength(durationField, 50);

        Label techStackLabel = new Label("Tech Stack");
        techStackLabel.setFont(Font.font("Arial", 18));
        TextArea techStackField = new TextArea();
        techStackField.setPrefWidth(150);
        techStackField.setPrefRowCount(2);
        techStackField.setPromptText("Separate with comma");
        setMaxLength(techStackField, 150);

        Label highlightedWordsLabel = new Label("Highlighted Words");
        highlightedWordsLabel.setFont(Font.font("Arial", 18));
        TextArea highlightedWordsField = new TextArea();
        highlightedWordsField.setPromptText("Separate with #");
        highlightedWordsField.setPrefWidth(150);
        highlightedWordsField.setPrefRowCount(2);
        setMaxLength(highlightedWordsField, 150);

        projectPage.add(projectTypeLabel, 0, 0);
        projectPage.add(projectTypeBox, 1, 0);
        projectPage.add(nameLabel, 0, 1);
        projectPage.add(nameField, 1, 1);
        projectPage.add(descriptionLabel, 0, 2);
        projectPage.add(descriptionField, 1, 2);
        projectPage.add(githubLinkLabel, 0, 3);
        projectPage.add(githubLinkField, 1, 3);
        projectPage.add(durationLabel, 0, 4);
        projectPage.add(durationField, 1, 4);
        projectPage.add(techStackLabel, 0, 5);
        projectPage.add(techStackField, 1, 5);
        projectPage.add(highlightedWordsLabel, 0, 6);
        projectPage.add(highlightedWordsField, 1, 6);

        Button submitButton = new Button("Submit Project " + (currentProjectIndex + 1));
        submitButton.setStyle("-fx-background-color: #008CBA; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10 20;");

        Label messageLabel = new Label();
        messageLabel.setTextFill(Color.valueOf("#EA4335"));
        projectPage.add(messageLabel, 0, 12, 2, 1);


        submitButton.setOnAction(event -> {
                String projectType = projectTypeGroup.getSelectedToggle() != null ?
                        ((RadioButton) projectTypeGroup.getSelectedToggle()).getText() : "";
                String name = nameField.getText();
                String description = descriptionField.getText();
                String githubLink = githubLinkField.getText();
                String duration = durationField.getText();
                String techStack = techStackField.getText();
                String highlightedWords = highlightedWordsField.getText();

                AtomicBoolean allCorrect = new AtomicBoolean(true);

                try{
                    new LinkValidator().validate(githubLink);
                } catch (InvalidFieldValueException e) {
                    allCorrect.set(false);
                    messageLabel.setText(e.getMessage());
                }

                List<String> techList = Arrays.asList( techStack.split(","));
                techList.forEach(tech ->{
                    if (tech.isEmpty()){
                        allCorrect.set(false);
                        messageLabel.setText("Invalid techstack field");
                    }
                });
                techList = techList.stream().map(String::trim).collect(Collectors.toList());

                List<String> words = Arrays.asList( techStack.split("#"));
                words.forEach(word ->{
                    if (word.isEmpty()){
                        allCorrect.set(false);
                        messageLabel.setText("Invalid highlighted words field");
                    }
                });
                words = words.stream().map(String::trim).collect(Collectors.toList());


            if (projectType.isEmpty() || name.isEmpty() || description.isEmpty() || githubLink.isEmpty() ||
                        duration.isEmpty() || techStack.isEmpty() || highlightedWords.isEmpty()) {
                    messageLabel.setText("Please fill in all fields.");
            }
            else {
                    if (allCorrect.get()){
                        if (projectType.equals("Academic") ){
                            resumeDetail.getAcademicProjects()
                                    .add(new ProjectDetail(
                                            name,
                                            duration,
                                            githubLink,
                                            techList,
                                            words,
                                            description)
                                    );
                        }
                        else if (projectType.equals("Non Academic")){
                            resumeDetail.getNonAcademicProjects()
                                    .add(new ProjectDetail(
                                            name,
                                            duration,
                                            githubLink,
                                            techList,
                                            words,
                                            description)
                                    );
                        }
                        else{
                            resumeDetail.getResearchProjects()
                                    .add(new ProjectDetail(
                                            name,
                                            duration,
                                            githubLink,
                                            techList,
                                            words,
                                            description)
                                    );

                        }
                    }
                    System.out.println("Project " + (currentProjectIndex + 1) + " submitted successfully!");
                }
            currentProjectIndex++;
            getProjectDetails(stage, u, resumeDetail); // Reload for next project
        });
        projectPage.add(submitButton, 0, 7, 2, 1);
        wrapper.getChildren().addAll(header,counter, new Separator(Orientation.HORIZONTAL), projectPage);

        Scene projectScene = new Scene(wrapper, 600, 700);
        stage.setScene(projectScene);
    }

    /**
     * Gets skill details.
     *
     * @param stage        the stage
     * @param u            the user
     * @param resumeDetail the resume detail
     */
    void getSkillDetails(Stage stage, User u, ResumeDetail resumeDetail) {
        VBox wrapper = new VBox();
        wrapper.setPadding(new Insets(20));
        wrapper.setStyle("-fx-background-color: #e6f7ff;");

        Label header = new Label("Skill Details");
        header.setFont(Font.font("Arial", 24));

        GridPane skillPage = new GridPane();
        skillPage.setPadding(new Insets(20));
        skillPage.setHgap(10);
        skillPage.setVgap(10);

        Label languagesLabel = new Label("Languages");
        languagesLabel.setFont(Font.font("Arial", 18));
        TextField languagesField = new TextField();
        setMaxLength(languagesField, 100);

        Label databasesLabel = new Label("Databases");
        databasesLabel.setFont(Font.font("Arial", 18));
        TextField databasesField = new TextField();
        setMaxLength(databasesField, 100);

        Label platformLabel = new Label("Platform");
        platformLabel.setFont(Font.font("Arial", 18));
        TextField platformField = new TextField();
        setMaxLength(platformField, 100);

        Label toolsLabel = new Label("Tools");
        toolsLabel.setFont(Font.font("Arial", 18));
        TextField toolsField = new TextField();
        setMaxLength(toolsField, 100);

//        skillPage.add(new Label("Skill Details"), 0, 0, 2, 1);
        skillPage.add(languagesLabel, 0, 1);
        skillPage.add(languagesField, 1, 1);
        skillPage.add(databasesLabel, 0, 2);
        skillPage.add(databasesField, 1, 2);
        skillPage.add(platformLabel, 0, 3);
        skillPage.add(platformField, 1, 3);
        skillPage.add(toolsLabel, 0, 4);
        skillPage.add(toolsField, 1, 4);

        Button submitButton = new Button("Next");
        submitButton.setStyle("-fx-background-color: #008CBA; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10 20;");

        Label messageLabel = new Label();
        messageLabel.setTextFill(Color.valueOf("#EA4335"));
        messageLabel.setFont(Font.font(""));
        skillPage.add(messageLabel, 0, 12, 2, 1);


        submitButton.setOnAction(event -> {
            String languages = languagesField.getText();
            String databases = databasesField.getText();
            String platform = platformField.getText();
            String tools = toolsField.getText();
            AtomicBoolean allCorrect = new AtomicBoolean(true);

            List<String> langList = Arrays.asList( languages.split(","));
            langList.forEach(lang ->{
                if (lang.isEmpty()){
                    allCorrect.set(false);
                    messageLabel.setText("Invalid language field");
                }
            });
            langList = langList.stream().map(String::trim).collect(Collectors.toList());


            List<String> databaseList = Arrays.asList( databases.split(","));
            databaseList.forEach(db ->{
                if (db.isEmpty()){
                    allCorrect.set(false);
                    messageLabel.setText("Invalid techstack field");
                }
            });
            databaseList = databaseList.stream().map(String::trim).collect(Collectors.toList());


            List<String> platformList = Arrays.asList( platform.split(","));
            platformList.forEach(p ->{
                if (p.isEmpty()){
                    allCorrect.set(false);
                    messageLabel.setText("Invalid techstack field");
                }
            });
            platformList = platformList.stream().map(String::trim).collect(Collectors.toList());


            List<String> toolList = Arrays.asList( tools.split(","));
            toolList.forEach(tool ->{
                if (tool.isEmpty()){
                    allCorrect.set(false);
                    messageLabel.setText("Invalid techstack field");
                }
            });
            toolList = toolList.stream().map(String::trim).collect(Collectors.toList());

            if (languages.isEmpty() || databases.isEmpty() || platform.isEmpty() || tools.isEmpty()) {
                messageLabel.setText("Please fill in all fields.");
            } else {
                resumeDetail.getSkillSets().add(new SkillSet("Languages", langList));
                resumeDetail.getSkillSets().add(new SkillSet("Databases", databaseList));
                resumeDetail.getSkillSets().add(new SkillSet("Platform", platformList));
                resumeDetail.getSkillSets().add(new SkillSet("Tools", toolList));
                System.out.println("Skills submitted successfully!");

                getImagePath(stage, u, resumeDetail);
            }
//            showConfirmationScreen(stage);
            // Move to next step or show a summary
        });
        skillPage.add(submitButton, 0, 5, 2, 1);
        wrapper.getChildren().addAll(header, new Separator(Orientation.HORIZONTAL), skillPage);

        Scene skillScene = new Scene(wrapper, 600, 300);
        stage.setScene(skillScene);
    }

    /**
     * Gets image path.
     *
     * @param stage        the stage
     * @param u            the user
     * @param resumeDetail the resume detail
     */
    void getImagePath(Stage stage, User u, ResumeDetail resumeDetail) {

        // Layout with 10px spacing
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #e6f7ff;");

        Label header = new Label("Your Picture");
        header.setFont(Font.font("Arial", 24));

        Button imagePickerButton = new Button("Select Image");
        imagePickerButton.setStyle("-fx-background-color: #008CBA; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10 20;");

        ImageView imageView = new ImageView();
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
        imageView.setPreserveRatio(true);

        imagePickerButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose an Image");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
            File selectedFile = fileChooser.showOpenDialog(stage);

            if (selectedFile != null) {
                Image image = new Image(selectedFile.toURI().toString());
                imageView.setImage(image);
                resumeDetail.setImagePath(selectedFile.getAbsolutePath());
                getLocationAndDate(stage, u, resumeDetail);
            }
        });

        // Add components to layout
        layout.getChildren().addAll(header, new Separator(Orientation.HORIZONTAL), imagePickerButton, imageView);


        Scene imageScene = new Scene(layout, 600, 500);
        stage.setScene(imageScene);
    }

    /**
     * Gets location and date.
     *
     * @param stage        the stage
     * @param u            the user
     * @param resumeDetail the resume detail
     */
    void getLocationAndDate(Stage stage, User u, ResumeDetail resumeDetail) {

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #e6f7ff;");

        Label header = new Label("Declaration Details");
        header.setFont(Font.font("Arial", 24));


        // Place TextField
        Label placeLabel = new Label("Place:");
        TextField placeField = new TextField();
        placeField.setPromptText("Enter place");

        // Date TextField
        Label dateLabel = new Label("Date:");
        TextField dateField = new TextField();
        dateField.setPromptText("Enter date");

        Button submitButton = new Button("Next");
        submitButton.setStyle("-fx-background-color: #008CBA; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10 20;");

        submitButton.setOnAction(event -> {
            resumeDetail.setDeclarationLocation(placeField.getText());
            resumeDetail.setDeclarationDate(dateField.getText());
            showConfirmationScreen(stage,u, resumeDetail);

        });

        layout.getChildren().addAll(header, new Separator(Orientation.HORIZONTAL), placeField, dateLabel, dateField, submitButton);

        Scene declareationScene = new Scene(layout, 600, 700);
        stage.setScene(declareationScene);
    }

    /**
     * Show confirmation screen.
     *
     * @param stage        the stage
     * @param user         the user
     * @param resumeDetail the resume detail
     */
    void showConfirmationScreen(Stage stage, User user, ResumeDetail resumeDetail) {
        VBox confirmationBox = new VBox(20);
        confirmationBox.setPadding(new Insets(20));
        confirmationBox.setAlignment(Pos.CENTER);
        confirmationBox.setStyle("-fx-background-color: #e6f7ff;");
        try {
            Database.getDatabase().insertUser(user);
        } catch (InsertionException e) {
            throw new RuntimeException(e);
        }

        Label confirmationLabel = new Label("Uploaded details to database!");
        confirmationLabel.setStyle("-fx-font-size: 16px;");

        Button customizeTemplateButton = new Button("Customize template");
        customizeTemplateButton.setOnAction(event -> {
            showCustomizeTemplateScreen(stage, user, resumeDetail);
            // Add functionality for customizing template
        });

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(event -> {
            stage.close(); // Close the application
        });

        confirmationBox.getChildren().addAll(confirmationLabel, customizeTemplateButton, exitButton);

        Scene confirmationScene = new Scene(confirmationBox, 400, 200);
        stage.setScene(confirmationScene);
    }

    /**
     * Show customize template screen.
     *
     * @param stage        the stage
     * @param u            the u
     * @param resumeDetail the resume detail
     */
    void showCustomizeTemplateScreen(Stage stage, User u, ResumeDetail resumeDetail) {
        GridPane customizePage = new GridPane();
        customizePage.setPadding(new Insets(20));
        customizePage.setHgap(10);
        customizePage.setVgap(10);
        customizePage.setStyle("-fx-background-color: #e6f7ff;");

        // Title label
        Label titleLabel = new Label("Customize template");
        titleLabel.setStyle("-fx-font-size: 18px;");

        // Pick color section
        Label colorLabel = new Label("Pick color:");
        ColorPicker colorPicker = new ColorPicker();

        // Template choice section
        Label templateLabel = new Label("Choose template:");
        ToggleGroup templateGroup = new ToggleGroup();
//        RadioButton onePager = new RadioButton("One pager");
//        onePager.setToggleGroup(templateGroup);
        RadioButton twoPager = new RadioButton("Two pager");
        twoPager.setToggleGroup(templateGroup);

        // Filename input section
        Label filenameLabel = new Label("Filename:");
        TextField filenameField = new TextField();
        filenameField.setPromptText("Enter filename");

        Label directoryLabel = new Label("Save to directory:");
        TextField directoryPathField = new TextField();
        directoryPathField.setPromptText("Choose directory");
        directoryPathField.setEditable(false);  // Non-editable field to display the chosen directory

        Button browseButton = new Button("Browse...");
        browseButton.setOnAction(event -> {
            // Create and configure DirectoryChooser
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Select Directory to Save File");

            // Open DirectoryChooser dialog
            File selectedDirectory = directoryChooser.showDialog(stage);

            if (selectedDirectory != null) {
                // Set selected directory path in the text field
                directoryPathField.setText(selectedDirectory.getAbsolutePath());
            }
        });

        // Create button to save the file
        Button createButton = new Button("Create");
        createButton.setOnAction(event -> {
            Color selectedColor = colorPicker.getValue();
            String selectedTemplate = "Two pager";
            String filename = filenameField.getText();
            String directoryPath = directoryPathField.getText();
            stage.close();
            if (filename.isEmpty() || directoryPath.isEmpty()) {
                System.out.println("Please enter a filename and select a directory.");
            } else {
                System.out.println("Template created with color: " + selectedColor + ", template type: " + selectedTemplate);
                // Display the selected file save path in the console (for now)
                String fullFilePath = directoryPath + filename ;
                System.out.println("File will be saved at: " + fullFilePath);


                ResumeTemplate twoPagerOfficial = new TwoPagerOfficialResume();
                try {
                    twoPagerOfficial.generateResume(
                            resumeDetail,
                            new DeviceRgb(
                                    (float)selectedColor.getRed(),
                                    (float)selectedColor.getBlue(),
                                    (float)selectedColor.getGreen()
                            ), u == null ? "static" : u.getName(),
                            fullFilePath

                    );
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                // Additional code can go here to actually save the file at this location
            }
        });

        // Add components to the layout (e.g., GridPane)
        // Add title, color picker, and other components to the layout
        customizePage.add(titleLabel, 0, 0, 2, 1); // Span title across 2 columns
        customizePage.add(colorLabel, 0, 1);
        customizePage.add(colorPicker, 1, 1);
        customizePage.add(templateLabel, 0, 2);
//        customizePage.add(onePager, 1, 2);
        customizePage.add(twoPager, 1, 3);

        // Filename and directory chooser section
        customizePage.add(filenameLabel, 0, 4);
        customizePage.add(filenameField, 1, 4);
        customizePage.add(directoryLabel, 0, 5);
        customizePage.add(directoryPathField, 1, 5);
        customizePage.add(browseButton, 2, 5); // Position browse button next to directory path field

        // Create button at the bottom
        customizePage.add(createButton, 0, 6, 2, 1); // Span across 2 columns

        Scene customizeScene = new Scene(customizePage, 400, 300);
        stage.setScene(customizeScene); // Set the scene on the stage to display the customize template screen

    }

    /**
     * Handle login info.
     *
     * @param stage the stage
     */
    public void handleLoginInfo(Stage stage) {
        GridPane loginPage = new GridPane();
        loginPage.setPadding(new Insets(20));
        loginPage.setHgap(10);
        loginPage.setVgap(10);
        loginPage.setStyle("-fx-background-color: #e6f7ff;");

        // Create UI components
        Label usernameLabel = new Label("Username");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter your username");

        Label passwordLabel = new Label("Password");
        TextField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");

        Button loginButton = new Button("Login");
        Label messageLabel = new Label();

        // Add components to the layout
        loginPage.add(usernameLabel, 0, 0);
        loginPage.add(usernameField, 1, 0);
        loginPage.add(passwordLabel, 0, 1);
        loginPage.add(passwordField, 1, 1);
        loginPage.add(loginButton, 0, 2, 2, 1); // span 2 columns
        loginPage.add(messageLabel, 0, 3, 2, 1); // span 2 columns

        // Set button action
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            // Implement your login logic here
            if (username.isEmpty() || password.isEmpty()) {
                messageLabel.setText("Please enter both username and password.");
            } else {
                messageLabel.setText("Login successful!"); // Placeholder message
                showCustomizeTemplateScreen(stage, null, ResumeTemplate.getLocalFillerResumeDetail());
                // Proceed with successful login logic
            }


        } );
        Scene customizeScene = new Scene(loginPage, 600, 500);
        stage.setScene(customizeScene); // Set the scene on the stage to display the customize template screen

    }

    private void setMaxLength(@NotNull TextField textField, int maxLength) {
        textField.setTextFormatter(new TextFormatter<String>(change -> {
            if (change.getControlNewText().length() > maxLength) {
                return null; // Reject the change
            }
            return change;
        }));
    }

    private void setMaxLength(@NotNull TextArea textArea, int maxLength) {
        textArea.setTextFormatter(new TextFormatter<String>(change -> {
            if (change.getControlNewText().length() > maxLength) {
                return null; // Reject the change
            }
            return change;
        }));
    }
}




