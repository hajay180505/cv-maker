package com.cvmaker.cvmaker.schema;

import org.bson.Document;

public class Objective{
    private String startMonth;
    private String endMonth;
    private String startYear;
    private String endYear;

    public Objective(String startMonth, String endMonth, String startYear, String endYear) {
        this.startMonth = startMonth;
        this.endMonth = endMonth;
        this.startYear = startYear;
        this.endYear = endYear;
    }

    public String getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    public String getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getEndYear() {
        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    public static Objective map(Document document) {
        return new Objective(
                document.getString("startMonth"),
                document.getString("endMonth"),
                document.getString("startYear"),
                document.getString("endYear")

        );
    }
}
