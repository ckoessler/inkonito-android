package com.ckoessler.inkonito.models;

/**
 * Created by chris on 12/8/14.
 */
public class Resume {
    private Work[] work;

    private Basics basics;

    private Interest[] interests;

    private Education[] education;

    public Work[] getWork ()
    {
        return work;
    }

    public void setWork (Work[] work)
    {
        this.work = work;
    }

    public Basics getBasics ()
    {
        return basics;
    }

    public void setBasics (Basics basics)
    {
        this.basics = basics;
    }

    public Interest[] getInterests ()
    {
        return interests;
    }

    public void setInterests (Interest[] interests)
    {
        this.interests = interests;
    }

    public Education[] getEducation ()
    {
        return education;
    }

    public void setEducation (Education[] education)
    {
        this.education = education;
    }
}
