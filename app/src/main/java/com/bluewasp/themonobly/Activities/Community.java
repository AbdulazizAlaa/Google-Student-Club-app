package com.bluewasp.themonobly.Activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.bluewasp.themonobly.Adapters.CommunityAdapter;
import com.bluewasp.themonobly.Models.CommunityCardsData;
import com.bluewasp.themonobly.R;

import java.util.ArrayList;

public class Community extends ActionBarActivity {


    //action bar
    Toolbar toolbar;


    //cards view
    private RecyclerView communityRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private CommunityAdapter communityAdapter;
    private ArrayList<CommunityCardsData> communityDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        initializeActionbar();

        communityDataList = new ArrayList<CommunityCardsData>();

        communityDataList.add(new CommunityCardsData(BitmapFactory.decodeResource(this.getResources(), R.drawable.user), "Abdulaziz Alaa", "Head", "Game Developers", "Noggler"));
        communityDataList.add(new CommunityCardsData(BitmapFactory.decodeResource(this.getResources(), R.drawable.user), "Abdulaziz Alaa", "Head", "Game Developers", "Noggler"));
        communityDataList.add(new CommunityCardsData(BitmapFactory.decodeResource(this.getResources(), R.drawable.user), "Abdulaziz Alaa", "Head", "Game Developers", "Noggler"));
        communityDataList.add(new CommunityCardsData(BitmapFactory.decodeResource(this.getResources(), R.drawable.user), "Abdulaziz Alaa", "Head", "Game Developers", "Noggler"));
        communityDataList.add(new CommunityCardsData(BitmapFactory.decodeResource(this.getResources(), R.drawable.user), "Abdulaziz Alaa", "Head", "Game Developers", "Noggler"));
        communityDataList.add(new CommunityCardsData(BitmapFactory.decodeResource(this.getResources(), R.drawable.user), "Abdulaziz Alaa", "Head", "Game Developers", "Noggler"));
        communityDataList.add(new CommunityCardsData(BitmapFactory.decodeResource(this.getResources(), R.drawable.user), "Abdulaziz Alaa", "Head", "Game Developers", "Noggler"));



        communityRecyclerView = (RecyclerView) findViewById(R.id.community_recycle_view);

        communityRecyclerView.setHasFixedSize(true);

        //mLayoutManager = new GridLayoutManager(this, 2);
        //mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL);
        communityRecyclerView.setLayoutManager(mLayoutManager);

        communityAdapter = new CommunityAdapter(communityDataList);
        communityRecyclerView.setAdapter(communityAdapter);

    }

    public void initializeActionbar() {
        //getting toolbar reference from layout
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        //setting the toolbar as layout actionbar
        if (toolbar != null)
            setSupportActionBar(toolbar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_community, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
