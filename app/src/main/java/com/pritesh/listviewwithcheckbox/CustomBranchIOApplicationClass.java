package com.pritesh.listviewwithcheckbox;

import android.app.Application;

import org.json.JSONObject;

import io.branch.referral.Branch;

/**
 * Created by pritesh.patel on 2017-06-22, 10:48 AM.
 * ADESA, Canada
 */

public class CustomBranchIOApplicationClass extends Application
{
    private JSONObject branchParams;
    @Override
    public void onCreate() {
        super.onCreate();
        // initialize the Branch object
        //Branch.setPlayStoreReferrerCheckTimeout(0);
        Branch.getAutoInstance(this);
    }

    public JSONObject getBranchParams()
    {
        return branchParams;
    }

    public void setBranchParams(JSONObject branchParams)
    {
        this.branchParams = branchParams;
    }
}
