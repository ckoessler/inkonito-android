package com.ckoessler.inkonito.dataLayer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ckoessler.inkonito.R;
import com.ckoessler.inkonito.models.Work;

import java.util.List;

/**
 * Created by chris on 12/15/14.
 */
public class WorkAdapter extends RecyclerView.Adapter<WorkAdapter.WorkViewHolder> {

    private List<Work> workList;
    OnItemClickListener mItemClickListener;
    private Context mContext;

    public WorkAdapter(List<Work> workList, Context context){
        this.workList = workList;
        this.mContext = context;
    }

    @Override
    public WorkViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_work, viewGroup, false);

        return new WorkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WorkViewHolder workViewHolder, int i) {
        Work work = workList.get(i);
        workViewHolder.companyName.setText(work.getCompany());
        workViewHolder.position.setText(work.getPosition());
        workViewHolder.startDate.setText(work.getStartDate());
        workViewHolder.endDate.setText(work.getEndDate());
        workViewHolder.location.setText(work.getLocation());

        TextView highlightsTextView;
        for(int j=0; j < work.getHighlights().length; j++){
            highlightsTextView = new TextView(mContext);
            highlightsTextView.setText("\u2022 " + work.getHighlights()[j]);
            highlightsTextView.setTextColor(mContext.getResources().getColor(R.color.color_contrast_inverse));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            int margin = Math.round(mContext.getResources().getDimension(R.dimen.dim_small));
            layoutParams.setMargins(margin,margin,margin,margin);
            highlightsTextView.setLayoutParams(layoutParams);
            workViewHolder.highlights.addView(highlightsTextView);
        }

    }

    @Override
    public int getItemCount() {
        if (workList != null){
            return workList.size();
        }
        return 0;
    }

    public class WorkViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView companyName;
        public TextView position;
        public TextView startDate;
        public TextView endDate;
        public TextView location;
        public LinearLayout highlights;

        public WorkViewHolder(View view){
            super(view);
            companyName = (TextView) view.findViewById(R.id.work_company);
            position = (TextView) view.findViewById(R.id.work_position);
            startDate = (TextView) view.findViewById(R.id.work_start_date);
            endDate = (TextView) view.findViewById(R.id.work_end_date);
            location = (TextView) view.findViewById(R.id.work_location);
            highlights = (LinearLayout) view.findViewById(R.id.work_highlights);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mItemClickListener != null){
                mItemClickListener.onItemClick(v, getPosition());
            }
        }
    }

    public interface OnItemClickListener{
        public void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener){
        this.mItemClickListener = mItemClickListener;
    }
}
