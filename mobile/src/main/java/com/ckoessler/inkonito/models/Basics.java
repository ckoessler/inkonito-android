package com.ckoessler.inkonito.models;

/**
 * Created by chris on 12/8/14.
 */
public class Basics {
    private String summary;

    private String phone;

    private String location;

    private String email;

    private String name;

    private String label;

    private Profiles[] profiles;

    public String getSummary ()
    {
        return summary;
    }

    public void setSummary (String summary)
    {
        this.summary = summary;
    }

    public String getPhone ()
    {
        return phone;
    }

    public void setPhone (String phone)
    {
        this.phone = phone;
    }

    public String getLocation ()
    {
        return location;
    }

    public void setLocation (String location)
    {
        this.location = location;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getLabel ()
    {
        return label;
    }

    public void setLabel (String label)
    {
        this.label = label;
    }

    public Profiles[] getProfiles ()
    {
        return profiles;
    }

    public void setProfiles (Profiles[] profiles)
    {
        this.profiles = profiles;
    }
}
