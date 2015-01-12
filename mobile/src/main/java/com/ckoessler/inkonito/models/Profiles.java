package com.ckoessler.inkonito.models;

/**
 * Created by chris on 12/8/14.
 */
public class Profiles {
    private String username;

    private String url;

    private String network;

    public String getUsername ()
    {
        return username;
    }

    public void setUsername (String username)
    {
        this.username = username;
    }

    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }

    public String getNetwork ()
    {
        return network;
    }

    public void setNetwork (String network)
    {
        this.network = network;
    }
}
