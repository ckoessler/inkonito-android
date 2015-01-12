package com.ckoessler.inkonito.dataLayer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ckoessler.inkonito.R;
import com.ckoessler.inkonito.models.Interest;
import com.ckoessler.inkonito.models.Work;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by chris on 12/15/14.
 */
public class InterestAdapter extends RecyclerView.Adapter<InterestAdapter.InterestViewHolder> {

    private List<Interest> interestList;
    OnItemClickListener mItemClickListener;
    private Context mContext;

    public InterestAdapter(List<Interest> interestList, Context context){
        this.interestList = interestList;
        this.mContext = context;
    }

    @Override
    public InterestViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_interest, viewGroup, false);

        return new InterestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(InterestViewHolder interestViewHolder, int i) {
        Interest interest = interestList.get(i);
        interestViewHolder.label.setText(interest.getLabel());
        if(!interest.getImage().equalsIgnoreCase("")) {
            Picasso.with(mContext)
                    .load(interest.getImage())
                    .placeholder(R.drawable.ic_loading_network)
                    .error(R.drawable.ic_error_network)
                    .into(interestViewHolder.image);
        }
    }

    @Override
    public int getItemCount() {
        if (interestList != null){
            return interestList.size();
        }
        return 0;
    }

    public class InterestViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView label;
        public ImageView image;

        public InterestViewHolder(View view){
            super(view);
            label = (TextView) view.findViewById(R.id.interest_label);
            image = (ImageView) view.findViewById(R.id.interest_image);
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
