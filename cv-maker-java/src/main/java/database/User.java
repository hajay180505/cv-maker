package database;

import schema.ResumeDetail;

import java.util.List;

public class User {
    String name;
    List<ResumeDetail> resumeDetails;

    public User(String name, List<ResumeDetail> resumeDetails) {
        this.name = name;
        this.resumeDetails = resumeDetails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ResumeDetail> getResumeDetails() {
        return resumeDetails;
    }

    public void setResumeDetails(List<ResumeDetail> resumeDetails) {
        this.resumeDetails = resumeDetails;
    }
}
