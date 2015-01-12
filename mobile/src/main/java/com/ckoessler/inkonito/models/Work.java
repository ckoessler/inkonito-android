package com.ckoessler.inkonito.models;

/**
 * Created by chris on 12/8/14.
 */
public class Work {
    private String summary;
    private String position;
    private String startDate;
    private String company;
    private String endDate;
    private String location;
    private String[] highlights;

    public String getSummary ()
    {
        return summary;
    }

    public void setSummary (String summary)
    {
        this.summary = summary;
    }

    public String getPosition ()
    {
        return position;
    }

    public void setPosition (String position)
    {
        this.position = position;
    }

    public String getStartDate ()
    {
        return startDate;
    }

    public void setStartDate (String startDate)
    {
        this.startDate = startDate;
    }

    public String getCompany ()
    {
        return company;
    }

    public void setCompany (String company)
    {
        this.company = company;
    }

    public String getEndDate ()
    {
        return endDate;
    }

    public void setEndDate (String endDate)
    {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String[] getHighlights ()
    {
        return highlights;
    }

    public void setHighlights (String[] highlights)
    {
        this.highlights = highlights;
    }
}
