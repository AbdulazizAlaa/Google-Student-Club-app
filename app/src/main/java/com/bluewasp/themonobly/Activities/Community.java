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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bluewasp.themonobly.Adapters.CommunityAdapter;
import com.bluewasp.themonobly.Beans.NetworkHelper;
import com.bluewasp.themonobly.Beans.Tags;
import com.bluewasp.themonobly.Models.CommunityCardsData;
import com.bluewasp.themonobly.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Community extends ActionBarActivity {


    //action bar
    Toolbar toolbar;


    //cards view
    private RecyclerView communityRecyclerView;
    private StaggeredGridLayoutManager mLayoutManager;
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



        communityRecyclerView = (RecyclerView) findViewById(R.id.community_recycle_view);

        communityRecyclerView.setHasFixedSize(true);

        //mLayoutManager = new GridLayoutManager(this, 2);
        //mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL);
        mLayoutManager.offsetChildrenVertical(200);
        mLayoutManager.offsetChildrenHorizontal(20);
        mLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        communityRecyclerView.setLayoutManager(mLayoutManager);

        communityAdapter = new CommunityAdapter(communityDataList, Community.this);
        communityRecyclerView.setAdapter(communityAdapter);

        JsonObjectRequest reqUsers = new JsonObjectRequest
                (Request.Method.GET, Tags.COMMUNITY_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.i("ha","success");
                try {
                    if(jsonObject.getString(Tags.TAG_MESSAGE).equals(Tags.TAG_SUCCESS)){
                        JSONArray usersArray = jsonObject.getJSONArray(Tags.COMMUNITY_USERS_TAG);
                        for(int i=0 ; i<usersArray.length() ; i++){

                            JSONObject user = (JSONObject) usersArray.get(i);

                            String name, position, committee, level, profileImagePath;

                            name = user.getString(Tags.PROFILE_FIRST_NAME)+" "+user.getString(Tags.PROFILE_LAST_NAME);
                            position = user.getString(Tags.PROFILE_POSITION);
                            committee = user.getString(Tags.PROFILE_COMMITTE);
                            //********BUG*********
                            //still not retrieved dynamically from database should be changed later
                            //level  = user.getString(Tags.PROFILE_LEVEL);
                            level = "Noggler";

                            profileImagePath = user.getString(Tags.PROFILE_PROFILE_IMAGE_PATH);

                            communityDataList.add(new CommunityCardsData(name, position, committee, level, profileImagePath));
                        }
                        communityAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i("ha","Error"+volleyError.getMessage());

            }
        });

        NetworkHelper.getInstance(Community.this).getRequestQueue().add(reqUsers);


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
