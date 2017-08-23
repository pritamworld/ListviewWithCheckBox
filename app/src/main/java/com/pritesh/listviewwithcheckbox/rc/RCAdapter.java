package com.pritesh.listviewwithcheckbox.rc;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pritesh.listviewwithcheckbox.R;

import java.util.List;

/**
 * Created by pritesh.patel on 2017-08-22, 3:03 PM.
 * ADESA, Canada
 */

public class RCAdapter extends RecyclerView.Adapter<RCAdapter.ViewHolder>
{

    private List<GcItem> mGcItemList;
    RCAdapter(List<GcItem> mGcItemList)
    {
        this.mGcItemList = mGcItemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gc_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        GcItem gcItem = mGcItemList.get(position);
        holder.ivGC.setImageResource(gcItem.getImageId());
        holder.tvTitleGC.setText(gcItem.getTitle());
    }

    @Override
    public int getItemCount()
    {
        return mGcItemList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView ivGC;
        TextView tvTitleGC;
        ViewHolder(View view)
        {
            super(view);

            ivGC = (ImageView)view.findViewById(R.id.imgGC);
            tvTitleGC = (TextView) view.findViewById(R.id.tvTitleGC);

            view.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {

                }
            });

        }
    }
}
