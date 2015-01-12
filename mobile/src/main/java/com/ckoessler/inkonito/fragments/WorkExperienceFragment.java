package com.ckoessler.inkonito.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ckoessler.inkonito.R;
import com.ckoessler.inkonito.activities.MainActivity;
import com.ckoessler.inkonito.dataLayer.BusProvider;
import com.ckoessler.inkonito.dataLayer.WorkAdapter;
import com.ckoessler.inkonito.models.Resume;
import com.squareup.otto.Subscribe;

import java.util.Arrays;

public class WorkExperienceFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private Context mContext;

    public WorkExperienceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity().getApplicationContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workexperience, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.work_recycler);
        mRecyclerView.setHasFixedSize(false);
        setNewData();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //Enable the auto hide for toolbar when scrolling down.
        //Help free some space on the screen to display more content
        ((MainActivity)getActivity()).enableActionBarAutoHide(mRecyclerView);
        return view;
    }

    @Override
    public void onPause(){
        super.onPause();
        //Unregister the fragment from the bus provider
        BusProvider.getInstance().unregister(this);
    }

    @Override
    public void onResume(){
        super.onResume();
        //Register the fragment to be able to receive events through the bus provider
        BusProvider.getInstance().register(this);
    }

    @Subscribe
    public void onResume(Resume resume){
        String TAG = Thread.currentThread().getStackTrace()[2].getClassName();
        Log.v(TAG, "Got New Resume Data!");
        setNewData();
    }

    private void setNewData() {
        String TAG = Thread.currentThread().getStackTrace()[2].getClassName();
        try {
            if (MainActivity.mResume != null) {
                mRecyclerView.setAdapter(new WorkAdapter(Arrays.asList(MainActivity.mResume.getWork())
                        , mContext));
                Log.v(TAG, "Experience Recycler View Loaded!");
            }
        } catch (Exception e) {
            Log.e(TAG, "An Exception Occurred");
            if (e.getStackTrace() != null) {
                Log.e(TAG, e.getStackTrace().toString());
            }
        }
    }
}
