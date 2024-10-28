package com.cvmaker.cvmaker.database;

import com.cvmaker.cvmaker.schema.ResumeDetail;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * The type User.
 */
public class User {
    /**
     * The Name.
     */
    String name;
    /**
     * The Password.
     */
    String password, /**
     * The Email.
     */
    email;
    /**
     * The Resume details.
     */
    List<ResumeDetail> resumeDetails;


    public User() {}
    /**
     * Instantiates a new User.
     *
     * @param name          the name
     * @param resumeDetails the resume details
     * @param email         the email
     * @param password      the password
     */
    public User(String name, List<ResumeDetail> resumeDetails, String email, String password) {
        this.name = name;
        this.resumeDetails = resumeDetails;
        this.password = password;
        this.email = email;
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
     * Gets resume details.
     *
     * @return the resume details
     */
    public List<ResumeDetail> getResumeDetails() {
        return resumeDetails;
    }

    /**
     * Sets resume details.
     *
     * @param resumeDetails the resume details
     */
    public void setResumeDetails(List<ResumeDetail> resumeDetails) {
        this.resumeDetails = resumeDetails;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
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
}
