package com.pritesh.listviewwithcheckbox.timeline;

/**
 * Created by pritesh.patel on 2017-07-28, 2:22 PM.
 * ADESA, Canada
 */

class TimeLineModel
{
    public TimeLineModel(String title, boolean isEnd)
    {
        this.title = title;
        this.isEnd = isEnd;
    }

    String title;
    boolean isEnd;

    public boolean isEnd()
    {
        return isEnd;
    }

    public void setEnd(boolean end)
    {
        isEnd = end;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }


}
