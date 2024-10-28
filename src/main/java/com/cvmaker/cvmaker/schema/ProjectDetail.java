package com.cvmaker.cvmaker.schema;

import org.bson.Document;

import java.util.List;

/**
 * The type Project detail.
 */
public class ProjectDetail {
    private String projectName;
    private String projectDescription;
    private List<String> techStack;
    private List<String> highlightedWords;
    private String githubLink;
    private String duration;

    /**
     * Instantiates a new Project detail.
     *
     * @param projectName        the project name
     * @param duration           the duration
     * @param githubLink         the github link
     * @param highlightedWords   the highlighted words
     * @param techStack          the tech stack
     * @param projectDescription the project description
     */
    public ProjectDetail(String projectName, String duration, String githubLink, List<String> highlightedWords, List<String> techStack, String projectDescription) {
        this.projectName = projectName;
        this.duration = duration;
        this.githubLink = githubLink;
        this.highlightedWords = highlightedWords;
        this.techStack = techStack;
        this.projectDescription = projectDescription;
    }

    /**
     * Gets project name.
     *
     * @return the project name
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Sets project name.
     *
     * @param projectName the project name
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * Gets project description.
     *
     * @return the project description
     */
    public String getProjectDescription() {
        return projectDescription;
    }

    /**
     * Sets project description.
     *
     * @param projectDescription the project description
     */
    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    /**
     * Gets tech stack.
     *
     * @return the tech stack
     */
    public List<String> getTechStack() {
        return techStack;
    }

    /**
     * Sets tech stack.
     *
     * @param techStack the tech stack
     */
    public void setTechStack(List<String> techStack) {
        this.techStack = techStack;
    }

    /**
     * Gets highlighted words.
     *
     * @return the highlighted words
     */
    public List<String> getHighlightedWords() {
        return highlightedWords;
    }

    /**
     * Sets highlighted words.
     *
     * @param highlightedWords the highlighted words
     */
    public void setHighlightedWords(List<String> highlightedWords) {
        this.highlightedWords = highlightedWords;
    }

    /**
     * Gets github link.
     *
     * @return the github link
     */
    public String getGithubLink() {
        return githubLink;
    }

    /**
     * Sets github link.
     *
     * @param githubLink the github link
     */
    public void setGithubLink(String githubLink) {
        this.githubLink = githubLink;
    }

    /**
     * Gets duration.
     *
     * @return the duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Sets duration.
     *
     * @param duration the duration
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * Map project detail.
     *
     * @param document the document
     * @return the project detail
     */
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
