package com.ckoessler.inkonito.dataLayer;

import com.ckoessler.inkonito.models.Resume;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.Response;
import retrofit.http.GET;

/**
 * Created by chris on 12/7/14.
 */
public class ApiClient {
    private static ResumeApiInterface resumeApiInterface;

    public static ResumeApiInterface getResumeApiClient(){
        if(resumeApiInterface == null){
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint("http://inkonito.herokuapp.com")
                    .build();

            resumeApiInterface = restAdapter.create(ResumeApiInterface.class);
        }
        return resumeApiInterface;
    }

    public interface ResumeApiInterface{
        @GET("/resume")
        void getResume(Callback<Resume> callback);
    }
}
