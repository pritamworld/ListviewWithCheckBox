package com.pritesh.listviewwithcheckbox.rc;

/**
 * Created by pritesh.patel on 2017-08-22, 2:49 PM.
 * ADESA, Canada
 */

public class GcItem
{
    int imageId;
    int type;
    String title;
    boolean isVisible;

    GcItem(int imageId, int type, String title, boolean isVisible)
    {
        this.imageId = imageId;
        this.type = type;
        this.title = title;
        this.isVisible = isVisible;
    }

    public void setImageId(int imageId)
    {
        this.imageId = imageId;
    }

    public int getImageId()
    {
        return this.imageId;
    }

    public void setType(int type)
    {
        this.type = type;
    }

    public int getType()
    {
        return this.type;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return this.title;
    }

    public void setIsVisible(boolean isVisible)
    {
        this.isVisible = isVisible;
    }

    public boolean getIsVisible()
    {
        return this.isVisible;
    }
}
