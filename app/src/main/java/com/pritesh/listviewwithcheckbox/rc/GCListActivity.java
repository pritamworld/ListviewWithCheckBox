package com.pritesh.listviewwithcheckbox.rc;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.pritesh.listviewwithcheckbox.R;

import java.util.ArrayList;

public class GCListActivity extends Activity
{
    RecyclerView rvGCItem;
    ArrayList<GcItem>mGcItemArrayList;
    RCAdapter mRCAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gclist);

        setData();

        rvGCItem = (RecyclerView)findViewById(R.id.rvGCItem);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rvGCItem.setLayoutManager(gridLayoutManager);

        //DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvGCItem.getContext(),
        //        gridLayoutManager.getOrientation());
        //rvGCItem.addItemDecoration(dividerItemDecoration);

        mRCAdapter = new RCAdapter(mGcItemArrayList);
        rvGCItem.setAdapter(mRCAdapter);
        rvGCItem.addOnItemTouchListener(
                new RecyclerItemClickListener(GCListActivity.this, rvGCItem ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                        Log.d("RecyclerView", "onClick：" + position);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
    }

    private void setData()
    {
        mGcItemArrayList = new ArrayList<>();
        mGcItemArrayList.add(new GcItem(R.drawable.buy_green, 1 ,"BUY This Vehicle", true));
        mGcItemArrayList.add(new GcItem(R.drawable.buy_green, 2 ,"PASS On This Vehicle", true));
        mGcItemArrayList.add(new GcItem(R.drawable.buy_green, 3 ,"GUARANTEE Remaining Payments Only", true));
        mGcItemArrayList.add(new GcItem(R.drawable.buy_green, 4 ,"GROUND Another Vehicle", true));
        mGcItemArrayList.add(new GcItem(R.drawable.buy_green, 5 ,"Complete CA Lien Release", true));
        mGcItemArrayList.add(new GcItem(R.drawable.buy_green, 6 ,"HOME", true));
    }
}
