package com.pritesh.listviewwithcheckbox.timeline;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pritesh.listviewwithcheckbox.R;

import java.util.ArrayList;

/**
 * Created by pritesh.patel on 2017-07-28, 2:21 PM.
 * ADESA, Canada
 */

class TimeLineRecyclerViewAdapter extends RecyclerView.Adapter<TimeLineRecyclerViewAdapter.TimeLineViewHolder>
{
    ArrayList<TimeLineModel>mTimeLineModels;
    public TimeLineRecyclerViewAdapter(ArrayList<TimeLineModel>mTimeLineModels)
    {
        this.mTimeLineModels = mTimeLineModels;
    }

    @Override
    public TimeLineRecyclerViewAdapter.TimeLineViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_time_line, parent, false);

        return new TimeLineViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TimeLineRecyclerViewAdapter.TimeLineViewHolder holder, int position)
    {
        TimeLineModel timeLineModel = mTimeLineModels.get(position);
        holder.info_text.setText(timeLineModel.getTitle());
        holder.imgStart.setImageResource(R.drawable.ic_level_1);
        holder.viewSeperater.setVisibility(View.VISIBLE);
        if(timeLineModel.isEnd())
        {
            holder.viewSeperater.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount()
    {
        return mTimeLineModels.size();
    }

    static class TimeLineViewHolder extends RecyclerView.ViewHolder
    {
        CardView mCardView;
        TextView info_text;
        View viewSeperater;
        ImageView imgStart;
        TimeLineViewHolder(View itemView)
        {
            super(itemView);
            mCardView = (CardView)itemView.findViewById(R.id.card_view);
            imgStart = (ImageView)itemView.findViewById(R.id.imgLogo);
            info_text = (TextView)itemView.findViewById(R.id.info_text);
            viewSeperater = itemView.findViewById(R.id.viewSeperater);
        }
    }
}
