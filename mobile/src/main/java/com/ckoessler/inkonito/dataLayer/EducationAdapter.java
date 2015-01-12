package com.ckoessler.inkonito.dataLayer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ckoessler.inkonito.R;
import com.ckoessler.inkonito.models.Education;

import java.util.List;

/**
 * Created by chris on 12/15/14.
 */
public class EducationAdapter extends RecyclerView.Adapter<EducationAdapter.educationViewHolder> {

    private List<Education> educationList;
    OnItemClickListener mItemClickListener;
    private Context mContext;

    public EducationAdapter(List<Education> educationList, Context context){
        this.educationList = educationList;
        this.mContext = context;
    }

    @Override
    public educationViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_education, viewGroup, false);

        return new educationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(educationViewHolder educationViewHolder, int i) {
        Education education = educationList.get(i);
        educationViewHolder.study.setText(education.getStudyType());
        educationViewHolder.area.setText(education.getArea());
        educationViewHolder.institution.setText(education.getInstitution());

    }

    @Override
    public int getItemCount() {
        if (educationList != null){
            return educationList.size();
        }
        return 0;
    }

    public class educationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView study;
        public TextView area;
        public TextView institution;

        public educationViewHolder(View view){
            super(view);
            study = (TextView) view.findViewById(R.id.education_study);
            area = (TextView) view.findViewById(R.id.education_area);
            institution = (TextView) view.findViewById(R.id.education_institution);
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
