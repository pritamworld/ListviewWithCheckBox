package com.pritesh.listviewwithcheckbox;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class PhoneEmailActivity extends AppCompatActivity
{

    private  final  int CALL_PERMISSIONS_REQUEST =100;
    Intent callIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_email);
        Log.d("DIV", getCurrentVersion());
    }

    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btnContact:
                showContactAlertDialog(this);
                break;
        }
    }

    public void sendEmail(String email)
    {
        String textMessage = "\n\n\n" + getCurrentVersion();
        String[] TO = {email};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        //emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, textMessage);
        // Verify that the callIntent will resolve to an activity
        if (emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(emailIntent);
        }
    }

    public void makeCall(String number)
    {
        callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CALL_PHONE}, CALL_PERMISSIONS_REQUEST);
        }
        else
        {
            startActivity(callIntent);
        }
    }

    private String getCurrentVersion()
    {
        PackageManager pm = this.getPackageManager();
        PackageInfo pInfo ;
        String dInfo = "OS_Type(Android),";                             //os_type

        try
        {
            pInfo = pm.getPackageInfo(this.getPackageName(), 0);
            dInfo += "OS_Version(Android " + Build.VERSION.RELEASE +"),";   //os_version
            dInfo += "App_Version(" + pInfo.versionName + "),";             //app_version

            if(getResources().getString(R.string.screen_type).equalsIgnoreCase("MOBILE"))
            {
                dInfo += "Device_Type(Phone)";  // device_type
            }
            else
            {
                dInfo += "Device_Type(Tablet)";  // device_type
            }
        }
        catch(PackageManager.NameNotFoundException e1)
        {
            e1.printStackTrace();
        }

        return dInfo;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults)
    {
        switch(requestCode)
        {
            case CALL_PERMISSIONS_REQUEST:
            {
                // If request is cancelled, the result arrays are empty.
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    startActivity(callIntent);
                }
            }
        }
    }

    public void showContactAlertDialog(final Context context)
    {
        final Dialog dialog = new Dialog(context);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.contact_layout);
        TextView ok_text = (TextView)dialog.findViewById(R.id.ok_text);
        TextView call_tv = (TextView)dialog.findViewById(R.id.call_tv);
        TextView time_tv = (TextView)dialog.findViewById(R.id.time_tv);
        TextView email_tv = (TextView)dialog.findViewById(R.id.email_tv);
        call_tv.setText("Call 1-877-522-3372");
        time_tv.setText("9:00 A.M - 8:00 P.M ET M-F");
        email_tv.setText("Email: Dealerdirectsupport@adesa.com");

        call_tv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                dialog.dismiss();
                new AlertDialog.Builder(context)
                        .setMessage("+1-877-522-3372?")
                        .setPositiveButton("Call", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                makeCall("5554");
                                dialog.dismiss();
                            }

                        })
                        .setNegativeButton("Cancel", null)
                        .show();

            }
        });

        email_tv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                dialog.dismiss();
                sendEmail("Dealerdirectsupport@adesa.com");
            }
        });

        ok_text.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.setCancelable(false);
    }

}
