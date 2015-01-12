package com.ckoessler.inkonito.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ckoessler.inkonito.R;
import com.ckoessler.inkonito.dataLayer.ApiClient;
import com.ckoessler.inkonito.dataLayer.BusProvider;
import com.ckoessler.inkonito.dataLayer.PagerAdapter;
import com.ckoessler.inkonito.fragments.EducationFragment;
import com.ckoessler.inkonito.fragments.WorkExperienceFragment;
import com.ckoessler.inkonito.fragments.InterestsFragment;
import com.ckoessler.inkonito.models.Resume;
import com.ckoessler.inkonito.widgets.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends ActionBarActivity{

    private PagerAdapter mPagerAdapter;
    private SlidingTabLayout mSlidingTabLayout;
    private Toolbar toolbar;
    private LinearLayout mBasicContainerLinearLayout;
    private TextView mBasicNameTextView;
    private TextView mBasicLabelTextView;
    private TextView mBasicPhoneTextView;
    private TextView mBasicEmailTextView;
    public static Resume mResume;

    // variables that control the Action Bar auto hide behavior (aka "quick recall")
    private boolean mToolbarAutoHideEnabled = false;
    private int mToolbarAutoHideSensivity = 0;
    private ArrayList<View> mHideableHeaderViews = new ArrayList<View>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the action toolbar.

        toolbar = (Toolbar) findViewById(R.id.action_bar_toolbar);
        mBasicContainerLinearLayout = (LinearLayout) findViewById(R.id.basic_container);
        mBasicNameTextView = (TextView) findViewById(R.id.basic_name);
        mBasicLabelTextView = (TextView) findViewById(R.id.basic_label);
        mBasicPhoneTextView = (TextView) findViewById(R.id.basic_phone);
        mBasicEmailTextView = (TextView) findViewById(R.id.basic_email);
        setSupportActionBar(toolbar);

        registerHideableHeaderView(mBasicContainerLinearLayout);

        List<Fragment> fragments = new Vector<Fragment>();
        fragments.add(Fragment.instantiate(this, WorkExperienceFragment.class.getName()));
        fragments.add(Fragment.instantiate(this, EducationFragment.class.getName()));
        fragments.add(Fragment.instantiate(this, InterestsFragment.class.getName()));

        mPagerAdapter = new PagerAdapter(this.getSupportFragmentManager(), fragments);
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(mPagerAdapter);
        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setViewPager(pager);
        mSlidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.accent));
        mSlidingTabLayout.setDividerColors(getResources().getColor(R.color.color_contrast));

        getResume();
    }

    private void getResume(){
        ApiClient.getResumeApiClient().getResume(new Callback<Resume>() {
            String TAG = Thread.currentThread().getStackTrace()[2].getClassName();
            @Override
            public void success(Resume resume, Response response) {
                Log.v(TAG, "Success!");
                mResume = resume;

                mBasicNameTextView.setText(resume.getBasics().getName());
                mBasicLabelTextView.setText(resume.getBasics().getLabel());
                mBasicPhoneTextView.setText(resume.getBasics().getPhone());
                mBasicEmailTextView.setText(resume.getBasics().getEmail());
                Log.v(TAG, "Basic Info Loaded!");

                BusProvider.getInstance().post(resume);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.v(TAG, "Something happened... something BAD!");
            }
        });
    }

    public void enableActionBarAutoHide(final RecyclerView recyclerView) {
        initActionBarAutoHide();
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                //Make sure the toolbar shows when the view is scrolled to the top
                if(listReachedTop(recyclerView)){
                    showToolbar(true);
                }else{
                    //Detect if the scroll was big enough to avoid glitches while hiding the view
                    if(dy > mToolbarAutoHideSensivity || dy < 0) {
                        if(dy < 0) {
                            if (mBasicContainerLinearLayout.getVisibility() == View.VISIBLE) {
                                //If the toolbar is already visible and we are scrolling up, we don't have to do anything
                                return;
                            }
                        }
                        if (((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition() == 0) {
                            showToolbar(true);
                        }else {
                            showToolbar(false);
                        }
                    }
                }
            }
        });
    }

    private boolean listReachedTop(RecyclerView recyclerView){
        //We reached automatically reached the top if there are no items or if the last visible item is the first one
        if(recyclerView.getChildCount() == 0
                ||((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition()==0){
            return true;
        }
        return recyclerView.getChildAt(0).getTop() == 0;
    }

    private void initActionBarAutoHide() {
        mToolbarAutoHideEnabled = true;
        mToolbarAutoHideSensivity = getResources().getDimensionPixelSize(
                R.dimen.toolbar_auto_hide_sensitivity);
    }

    protected void showToolbar(boolean show) {
        if ((mBasicContainerLinearLayout.getVisibility() == View.VISIBLE && show)
                || (mBasicContainerLinearLayout.getVisibility() == View.GONE && !show)) {
            return;
        }

        onToolbarAutoShow(show);
    }

    protected void onToolbarAutoShow(boolean show) {
        for (View view : mHideableHeaderViews) {
            if (show) {
                view.setVisibility(View.VISIBLE);
            } else {
                view.setVisibility(View.GONE);
            }
        }
    }

    protected void registerHideableHeaderView(View hideableHeaderView) {
        if (!mHideableHeaderViews.contains(hideableHeaderView)) {
            mHideableHeaderViews.add(hideableHeaderView);
        }
    }

    protected void deregisterHideableHeaderView(View hideableHeaderView) {
        if (mHideableHeaderViews.contains(hideableHeaderView)) {
            mHideableHeaderViews.remove(hideableHeaderView);
        }
    }
}
