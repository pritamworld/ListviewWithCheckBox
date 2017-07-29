package com.pritesh.listviewwithcheckbox;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

//https://github.com/pritamworld/ListviewWithCheckBox.git
public class MainActivity extends AppCompatActivity
{
    private ShareActionProvider mShareActionProvider;
    MyCustomAdapter dataAdapter = null;
    ArrayList<NotificationChecks> notificationChecksArrayList;
    ListView listView;
    RadioGroup rbgTime;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rbgTime = (RadioGroup)findViewById(R.id.rbgTime);
        //Generate list View from ArrayList
        displayListView();
    }

    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.txtEnableAll:
                changeStates(true);
                break;

            case R.id.txtDisableAll:
                changeStates(false);
                break;
        }
    }

    private void displayListView()
    {
        notificationChecksArrayList = new ArrayList<>();

        NotificationChecks _states = new NotificationChecks(NotificationChecks.AUTO_SEARCH_PREFERENCE,"Auto Search Updated",false);
        notificationChecksArrayList.add(_states);
        _states = new NotificationChecks(NotificationChecks.OUT_BID_PREFERENCE,"Outbid Notification",true);
        notificationChecksArrayList.add(_states);
        _states = new NotificationChecks(NotificationChecks.WINNING_BID_PREFERENCE,"Winning Bid Notification",false);
        notificationChecksArrayList.add(_states);
        _states = new NotificationChecks(NotificationChecks.DLR_EXCLSVTY_PREF,"Dealer Exclusivity Expiration Notification",true);
        notificationChecksArrayList.add(_states);

        //create an ArrayAdapter from the String Array
        dataAdapter = new MyCustomAdapter(this,R.layout.account_notification_item, notificationChecksArrayList);
        listView = (ListView) findViewById(R.id.lstPushNotificationAccount);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                // When clicked, show a toast with the TextView text
                NotificationChecks state = (NotificationChecks) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),"Clicked on Row: " + state.getName(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private class MyCustomAdapter extends ArrayAdapter<NotificationChecks>
    {

        private ArrayList<NotificationChecks> stateList;

        MyCustomAdapter(Context context, int textViewResourceId,

                        ArrayList<NotificationChecks> stateList)
        {
            super(context, textViewResourceId, stateList);
            this.stateList = new ArrayList<>();
            this.stateList.addAll(stateList);
        }

        private class ViewHolder
        {
            TextView code;
            CheckBox name;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            ViewHolder holder;

            if (convertView == null)
            {
                LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.account_notification_item, null);

                holder = new ViewHolder();
                holder.code = (TextView) convertView.findViewById(R.id.code);
                holder.code.setMovementMethod(new ScrollingMovementMethod());
                holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);

                convertView.setTag(holder);

                holder.name.setOnClickListener( new View.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        CheckBox cb = (CheckBox) v;
                        NotificationChecks _state = (NotificationChecks) cb.getTag();
                        _state.setSelected(cb.isChecked());
                        checkButtonClick();
                    }
                });

            }
            else
            {
                holder = (ViewHolder) convertView.getTag();
            }

            NotificationChecks state = stateList.get(position);
            holder.code.setText(state.getName());
            holder.name.setChecked(state.isSelected());
            holder.name.setTag(state);

            return convertView;
        }

    }

    private void checkButtonClick()
    {
        StringBuffer responseText = new StringBuffer();
        responseText.append("The following were selected...\n");
        HashMap<String,Boolean>modifiedPushNotificationPreferences = new HashMap<>();

        ArrayList<NotificationChecks> stateList = dataAdapter.stateList;

        for(int i=0;i<stateList.size();i++)
        {
            NotificationChecks state = stateList.get(i);

            if(state.isSelected())
            {
                responseText.append("\n" + state.getName());
                modifiedPushNotificationPreferences.put(state.getCode(),true);
            }
        }

        Toast.makeText(getApplicationContext(),
                responseText, Toast.LENGTH_SHORT).show();
        Log.d("SELECTED", "checkButtonClick: " + modifiedPushNotificationPreferences.toString());

    }

    private void changeStates(boolean flag)
    {
        //rbgTime.clearCheck();
        for(int i= 0 ;i<notificationChecksArrayList.size();i++)
        {
            NotificationChecks n =  notificationChecksArrayList.get(i);
            if(n.isSelected() !=flag)
            {
                notificationChecksArrayList.remove(n);
                n.setSelected(flag);
                notificationChecksArrayList.add(i,n);
            }
        }
        dataAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.test_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.item_share:
                shareLinks();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        MenuItem settingsMenuItem = menu.findItem(R.id.item_share);
        SpannableString s = new SpannableString(settingsMenuItem.getTitle());
        s.setSpan(new ForegroundColorSpan(Color.RED), 0, s.length()-2, 0);
        settingsMenuItem.setTitle(s);

        return super.onPrepareOptionsMenu(menu);
    }

    private void shareLinks()
    {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "AndroidSolved");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Now Learn Android with AndroidSolved clicke here to visit https://androidsolved.wordpress.com/ ");
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }
}