package com.pritesh.listviewwithcheckbox.timeline;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.pritesh.listviewwithcheckbox.R;

import java.util.ArrayList;

public class TimeLineActivity extends Activity
{
    RecyclerView rvTimeLine;
    ArrayList<TimeLineModel>mTimeLineModelArrayList;
    TimeLineRecyclerViewAdapter mTimeLineRecyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_line);

        rvTimeLine = (RecyclerView)findViewById(R.id.rvTimeLine);
        mTimeLineModelArrayList = new ArrayList<>();

        mTimeLineRecyclerViewAdapter = new TimeLineRecyclerViewAdapter(mTimeLineModelArrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvTimeLine.setLayoutManager(mLayoutManager);
        rvTimeLine.setAdapter(mTimeLineRecyclerViewAdapter);
        prepareTimeLineData();
    }

    private void prepareTimeLineData()
    {
        TimeLineModel timeLineModel = new TimeLineModel("Start",false);
        mTimeLineModelArrayList.add(timeLineModel);

        timeLineModel = new TimeLineModel("Level 1",false);
        mTimeLineModelArrayList.add(timeLineModel);

        timeLineModel = new TimeLineModel("Level 2",false);
        mTimeLineModelArrayList.add(timeLineModel);

        timeLineModel = new TimeLineModel("Level 3",false);
        mTimeLineModelArrayList.add(timeLineModel);

        timeLineModel = new TimeLineModel("Level 4",false);
        mTimeLineModelArrayList.add(timeLineModel);

        timeLineModel = new TimeLineModel("Level 5",false);
        mTimeLineModelArrayList.add(timeLineModel);

        timeLineModel = new TimeLineModel("Level 6",false);
        mTimeLineModelArrayList.add(timeLineModel);

        timeLineModel = new TimeLineModel("Level 7",false);
        mTimeLineModelArrayList.add(timeLineModel);

        timeLineModel = new TimeLineModel("End",true);
        mTimeLineModelArrayList.add(timeLineModel);

        mTimeLineRecyclerViewAdapter.notifyDataSetChanged();
    }
}
