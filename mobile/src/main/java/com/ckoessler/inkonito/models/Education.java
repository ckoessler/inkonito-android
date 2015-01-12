package com.ckoessler.inkonito.models;

/**
 * Created by chris on 12/8/14.
 */
public class Education {
    private String area;

    private String studyType;

    private String institution;

    public String getArea ()
    {
        return area;
    }

    public void setArea (String area)
    {
        this.area = area;
    }

    public String getStudyType ()
    {
        return studyType;
    }

    public void setStudyType (String studyType)
    {
        this.studyType = studyType;
    }

    public String getInstitution ()
    {
        return institution;
    }

    public void setInstitution (String institution)
    {
        this.institution = institution;
    }
}
