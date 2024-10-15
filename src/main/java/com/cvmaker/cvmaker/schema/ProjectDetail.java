package com.cvmaker.cvmaker.schema;

import org.bson.Document;

import java.util.List;

public class ProjectDetail {
    private String projectName;
    private String projectDescription;
    private List<String> techStack;
    private List<String> highlightedWords;
    private String githubLink;
    private String duration;

    public ProjectDetail(String projectName, String duration, String githubLink, List<String> highlightedWords, List<String> techStack, String projectDescription) {
        this.projectName = projectName;
        this.duration = duration;
        this.githubLink = githubLink;
        this.highlightedWords = highlightedWords;
        this.techStack = techStack;
        this.projectDescription = projectDescription;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public List<String> getTechStack() {
        return techStack;
    }

    public void setTechStack(List<String> techStack) {
        this.techStack = techStack;
    }

    public List<String> getHighlightedWords() {
        return highlightedWords;
    }

    public void setHighlightedWords(List<String> highlightedWords) {
        this.highlightedWords = highlightedWords;
    }

    public String getGithubLink() {
        return githubLink;
    }

    public void setGithubLink(String githubLink) {
        this.githubLink = githubLink;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public static ProjectDetail map(Document document) {
        return new ProjectDetail(
                document.getString("projectName"),
                document.getString("githubLink"),
                document.getString("projectName"),
                document.getList("highlightedWords", String.class),
                document.getList("techStack",  String.class),
                document.getString("projectDescription")
        );
    }
}
