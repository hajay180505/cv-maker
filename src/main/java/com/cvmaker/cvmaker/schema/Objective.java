package com.cvmaker.cvmaker.schema;

import org.bson.Document;

/**
 * The type Objective.
 */
public class Objective{
    private String startMonth;
    private String endMonth;
    private String startYear;
    private String endYear;

    /**
     * Instantiates a new Objective.
     *
     * @param startMonth the start month
     * @param endMonth   the end month
     * @param startYear  the start year
     * @param endYear    the end year
     */
    public Objective(String startMonth, String endMonth, String startYear, String endYear) {
        this.startMonth = startMonth;
        this.endMonth = endMonth;
        this.startYear = startYear;
        this.endYear = endYear;
    }

    /**
     * Gets start month.
     *
     * @return the start month
     */
    public String getStartMonth() {
        return startMonth;
    }

    /**
     * Sets start month.
     *
     * @param startMonth the start month
     */
    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    /**
     * Gets end month.
     *
     * @return the end month
     */
    public String getEndMonth() {
        return endMonth;
    }

    /**
     * Sets end month.
     *
     * @param endMonth the end month
     */
    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }

    /**
     * Gets start year.
     *
     * @return the start year
     */
    public String getStartYear() {
        return startYear;
    }

    /**
     * Sets start year.
     *
     * @param startYear the start year
     */
    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    /**
     * Gets end year.
     *
     * @return the end year
     */
    public String getEndYear() {
        return endYear;
    }

    /**
     * Sets end year.
     *
     * @param endYear the end year
     */
    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    /**
     * Map objective.
     *
     * @param document the document
     * @return the objective
     */
    public static Objective map(Document document) {
        return new Objective(
                document.getString("startMonth"),
                document.getString("endMonth"),
                document.getString("startYear"),
                document.getString("endYear")

        );
    }
}
