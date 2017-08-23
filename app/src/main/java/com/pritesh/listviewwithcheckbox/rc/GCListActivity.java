package com.pritesh.listviewwithcheckbox.rc;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.pritesh.listviewwithcheckbox.R;

import java.util.ArrayList;
import java.util.Locale;

public class GCListActivity extends Activity
{
    RecyclerView rvGCItem;
    ArrayList<GcItem>mGcItemArrayList;
    RCAdapter mRCAdapter;
    Button btnSpeak;
    TextToSpeech t1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gclist);

        setData();

        rvGCItem = (RecyclerView)findViewById(R.id.rvGCItem);
        btnSpeak = (Button)findViewById(R.id.btnSpeak);

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
                        Toast.makeText(GCListActivity.this, String.format(Locale.CANADA,"Click Position [ %d ]",position), Toast.LENGTH_SHORT).show();
                        if(!t1.isSpeaking())
                        {
                            t1.speak(mGcItemArrayList.get(position).getTitle(), TextToSpeech.QUEUE_FLUSH, null,"Test");
                        }
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.CANADA);
                    t1.setSpeechRate(1f);
                }
            }
        });

        btnSpeak.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String toSpeak = "My name is pritesh patel";
                Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null,"Test");
            }
        });
    }

    public void onPause()
    {
        if(t1 !=null){
            t1.stop();
            t1.shutdown();
        }
        super.onPause();
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

        mGcItemArrayList.add(new GcItem(R.drawable.buy_green, 1 ,"BUY This Vehicle", true));
        mGcItemArrayList.add(new GcItem(R.drawable.buy_green, 2 ,"PASS On This Vehicle", true));
        mGcItemArrayList.add(new GcItem(R.drawable.buy_green, 3 ,"GUARANTEE Remaining Payments Only", true));
        mGcItemArrayList.add(new GcItem(R.drawable.buy_green, 4 ,"GROUND Another Vehicle", true));
        mGcItemArrayList.add(new GcItem(R.drawable.buy_green, 5 ,"Complete CA Lien Release", true));
        mGcItemArrayList.add(new GcItem(R.drawable.buy_green, 6 ,"HOME", true));

        mGcItemArrayList.add(new GcItem(R.drawable.buy_green, 1 ,"BUY This Vehicle", true));
        mGcItemArrayList.add(new GcItem(R.drawable.buy_green, 2 ,"PASS On This Vehicle", true));
        mGcItemArrayList.add(new GcItem(R.drawable.buy_green, 3 ,"GUARANTEE Remaining Payments Only", true));
        mGcItemArrayList.add(new GcItem(R.drawable.buy_green, 4 ,"GROUND Another Vehicle", true));
        mGcItemArrayList.add(new GcItem(R.drawable.buy_green, 5 ,"Complete CA Lien Release", true));
        mGcItemArrayList.add(new GcItem(R.drawable.buy_green, 6 ,"HOME", true));

        mGcItemArrayList.add(new GcItem(R.drawable.buy_green, 1 ,"BUY This Vehicle", true));
        mGcItemArrayList.add(new GcItem(R.drawable.buy_green, 2 ,"PASS On This Vehicle", true));
        mGcItemArrayList.add(new GcItem(R.drawable.buy_green, 3 ,"GUARANTEE Remaining Payments Only", true));
        mGcItemArrayList.add(new GcItem(R.drawable.buy_green, 4 ,"GROUND Another Vehicle", true));
        mGcItemArrayList.add(new GcItem(R.drawable.buy_green, 5 ,"Complete CA Lien Release", true));
        mGcItemArrayList.add(new GcItem(R.drawable.buy_green, 6 ,"HOME", true));
    }
}
