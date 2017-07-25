package com.pritesh.listviewwithcheckbox;

/**
 * Created by pritesh.patel on 2017-06-15, 10:36 AM.
 * ADESA, Canada
 */


class NotificationChecks
{
    private String code = null;
    private String name = null;
    private boolean selected = false;
    public static final String AUTO_SEARCH_PREFERENCE = "AUTO_SEARCH_PREFERENCE";
    public static final String DLR_EXCLSVTY_PREF = "DLR_EXCLSVTY_PREF";
    public static final String WINNING_BID_PREFERENCE = "WINNING_BID_PREFERENCE";
    public static final String OUT_BID_PREFERENCE = "OUT_BID_PREFERENCE";


    NotificationChecks(String code, String name, boolean selected)
    {
        super();
        this.code = code;
        this.name = name;
        this.selected = selected;
    }

    String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    boolean isSelected()
    {
        return selected;
    }

    void setSelected(boolean selected)
    {
        this.selected = selected;
    }

}