package com.pritesh.listviewwithcheckbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONObject;

import io.branch.referral.Branch;
import io.branch.referral.BranchError;

public class BranchIOTestActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_iotest);
    }

    @Override
    public void onStart() {
        super.onStart();
        Branch branch = Branch.getInstance();

        branch.initSession(new Branch.BranchReferralInitListener(){
            @Override
            public void onInitFinished(JSONObject referringParams, BranchError error) {
                if (error == null) {
                    ((CustomBranchIOApplicationClass) getApplicationContext()).setBranchParams(referringParams);
                    //BuyerAppContext.getInstance().getUserManager().sendBranchParamsAcquiredBroadcast();
                    Log.d("BRANCH.IO_S", referringParams.toString());
                } else {
                    Log.d("BRANCH.IO_F", error.getMessage());
                }
            }
        }, this.getIntent().getData(), this);
    }

    @Override
    public void onNewIntent(Intent intent) {
        this.setIntent(intent);
    }
}
