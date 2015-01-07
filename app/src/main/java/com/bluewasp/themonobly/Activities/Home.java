package com.bluewasp.themonobly.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.MaterialMenuIcon;
import com.balysv.materialmenu.extras.toolbar.MaterialMenuIconToolbar;
import com.bluewasp.themonobly.Adapters.SideMenuAdapter;
import com.bluewasp.themonobly.Beans.Tags;
import com.bluewasp.themonobly.Models.SideMenuItemData;
import com.bluewasp.themonobly.R;
//import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;


public class Home extends ActionBarActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener {

    // drawer
    private DrawerLayout drawerLayout;
    private boolean isDrawerOpened;
    ImageView sideMenuProfileImgV;
    TextView sideMenuNameTV;
    ListView menuLV;
    SideMenuAdapter menuAdapter;
    ArrayList<SideMenuItemData> menuItemList;

    //action bar
    Toolbar toolbar;
    MaterialMenuIconToolbar sideMenuIcon;
    ImageView actionbarProfileImgV;
    TextView actionbarNameTv, actionbarGollarsTv;
    ProgressBar actionbarLevelBar;

    SharedPreferences pref;
    String id, firstName, lastName, profileImagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_home);


        initializeSideMenu();
        initializeActionbar();
        initializePref();
        initializeData(firstName+" "+lastName, profileImagePath);


    }


    public void initializeActionbar(){
        //getting toolbar reference from layout
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        //setting the toolbar as layout actionbar
        if (toolbar != null)
            setSupportActionBar(toolbar);

        //listening to menu icon buttons
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //when drawer open ==> close it
                if (sideMenuIcon.getState() == MaterialMenuDrawable.IconState.ARROW) {
                    //changing menu icon
                    sideMenuIcon.animatePressedState(MaterialMenuDrawable.IconState.BURGER);
                    //closing drawer
                    drawerLayout.closeDrawer(Gravity.LEFT);
                }
                //when drawer closed ==> open it
                else if (sideMenuIcon.getState() == MaterialMenuDrawable.IconState.BURGER) {
                    //changing menu icon
                    sideMenuIcon.animatePressedState(MaterialMenuDrawable.IconState.ARROW);
                    //opening drawer
                    drawerLayout.openDrawer(Gravity.LEFT);
                }
            }
        });

        //attaching menu icon to the toolbar
        sideMenuIcon = new MaterialMenuIconToolbar(this, Color.WHITE, MaterialMenuDrawable.Stroke.THIN) {
            @Override
            public int getToolbarViewId() {
                return R.id.toolbar;
            }
        };

        sideMenuIcon.setNeverDrawTouch(true);

        //getting toolbar components' references
        actionbarNameTv = (TextView) findViewById(R.id.action_bar_name_tv);
        actionbarGollarsTv = (TextView) findViewById(R.id.action_bar_gollars_tv);
        actionbarProfileImgV = (ImageView) findViewById(R.id.action_bar_profile_img);
        actionbarLevelBar = (ProgressBar) findViewById(R.id.action_bar_level_progressBar);
    }

    public void initializeSideMenu() {

        //getting menu list view reference
        menuLV = (ListView) findViewById(R.id.home_left_drawer);

        //initializing menu items array list
        menuItemList = new ArrayList<SideMenuItemData>();

        //adding menu items to the array list
        menuItemList.add(new SideMenuItemData("Market",
                R.drawable.side_menu_market_icon));
        menuItemList.add(new SideMenuItemData("Community",
                R.drawable.side_menu_community_icon));
        menuItemList.add(new SideMenuItemData("Top Players",
                R.drawable.side_menu_top_icon));
        menuItemList.add(new SideMenuItemData("Events",
                R.drawable.side_menu_event_icon));
        menuItemList.add(new SideMenuItemData("Logout",
                R.drawable.side_menu_event_icon));

        //initializing the menu adapter
        menuAdapter = new SideMenuAdapter(this, menuItemList);

        //attaching item click listener to the menu items
        menuLV.setOnItemClickListener(this);

        // setting up header view

        //inflating header view layout
        View menuHeaderV = getLayoutInflater().inflate(
                R.layout.side_menu_header, null);

        //getting side menu components' references
        sideMenuProfileImgV = (ImageView) menuHeaderV
                .findViewById(R.id.side_menu_header_profile_picIV);
        sideMenuNameTV = (TextView) menuHeaderV
                .findViewById(R.id.side_menu_header_nameTV);

        //attaching click listener to the profile image
        sideMenuProfileImgV.setOnClickListener(this);

        //adding header view to the menu list view
        menuLV.addHeaderView(menuHeaderV);

        //setting menu list adapter
        menuLV.setAdapter(menuAdapter);

        //getting drawer layout reference
        drawerLayout = (DrawerLayout) findViewById(R.id.home_drawer);

        //setting drawer layout listener
        drawerLayout.setDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                sideMenuIcon.setTransformationOffset(
                        MaterialMenuDrawable.AnimationState.BURGER_ARROW,
                        isDrawerOpened ? 2 - slideOffset : slideOffset
                );
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                isDrawerOpened = true;
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                isDrawerOpened = false;
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                if (newState == DrawerLayout.STATE_IDLE) {
                    if (isDrawerOpened) sideMenuIcon.setState(MaterialMenuDrawable.IconState.ARROW);
                    else sideMenuIcon.setState(MaterialMenuDrawable.IconState.BURGER);
                }
            }
        });

    }

    public void initializeData(String name, String profileImagePath) {

        //setting profile name
        sideMenuNameTV.setText(name);
        actionbarNameTv.setText(name);

        //setting profile image

    }

    public void initializePref() {
        pref = getSharedPreferences(Tags.TAG_PREF_FILE, MODE_PRIVATE);

        id = pref.getString(Tags.PROFILE_USER_ID, "");
        firstName = pref.getString(Tags.PROFILE_FIRST_NAME, "");
        lastName = pref.getString(Tags.PROFILE_LAST_NAME, "");
        profileImagePath = pref.getString(Tags.PROFILE_PROFILE_IMAGE_PATH, "");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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
        } else if (id == android.R.id.home) {
            //menu.toggle();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        isDrawerOpened = drawerLayout.isDrawerOpen(Gravity.START); // or END, LEFT, RIGHT
        sideMenuIcon.syncState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        sideMenuIcon.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

        Log.i(Tags.TAG_LOG, "on click");
        Intent i;
        switch (v.getId()) {
            case R.id.side_menu_header_profile_picIV:
                i = new Intent(Home.this, Profile.class);
                startActivity(i);
                //closing drawer
                drawerLayout.closeDrawer(Gravity.LEFT);
                break;

            default:
                break;
        }
    }

    // Side Menu Items
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        // TODO Auto-generated method stub
        Log.i(Tags.TAG_LOG, "onitemclick");
        switch (position) {
            case 1:
                // Market
                //closing drawer
                drawerLayout.closeDrawer(Gravity.LEFT);
                break;
            case 2:
                // Community
                //closing drawer
                drawerLayout.closeDrawer(Gravity.LEFT);
                break;
            case 3:
                // Top Players
                //closing drawer
                drawerLayout.closeDrawer(Gravity.LEFT);
                break;
            case 4:
                // Events
                //closing drawer
                drawerLayout.closeDrawer(Gravity.LEFT);
                break;
            case 5:
                // Logout
                AlertDialog.Builder dialog = new AlertDialog.Builder(Home.this);

                dialog.setTitle("Logout")
                        .setMessage("Do you want to logout?")
                        .setPositiveButton("Confirm",
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        // TODO Auto-generated method stub
                                        SharedPreferences pref = getSharedPreferences(Tags.TAG_PREF_FILE,
                                                MODE_PRIVATE);

                                        SharedPreferences.Editor edit = pref.edit();
                                        edit.clear();

                                        edit.putString(Tags.PREF_STATUS, Tags.PREF_NOT_LOGGED_IN);
                                        edit.commit();

                                        Intent i = new Intent(Home.this, Login.class);
                                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(i);
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        // TODO Auto-generated method stub
                                        dialog.cancel();
                                    }
                                });
                AlertDialog c = dialog.create();
                c.show();
                //closing drawer
                drawerLayout.closeDrawer(Gravity.LEFT);
                break;

            default:
                break;
        }
    }


}
