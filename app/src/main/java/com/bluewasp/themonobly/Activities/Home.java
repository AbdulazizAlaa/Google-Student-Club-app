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

    Toolbar toolbar;
    // menu
    private DrawerLayout drawerLayout;
    private boolean isDrawerOpened;
    MaterialMenuIconToolbar sideMenuIcon;
    ImageView profilePicIV;
    TextView nameTV;
    ListView menuLV;
    SideMenuAdapter menuAdapter;
    ArrayList<SideMenuItemData> menuItemList;

    SharedPreferences pref;
    String id, firstName, lastName, profileImagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.sidemenu_icon_selector);
        //getSupportActionBar().setDisplayShowHomeEnabled(false);

        setContentView(R.layout.activity_home);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (toolbar != null)
            setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sideMenuIcon.getState() == MaterialMenuDrawable.IconState.ARROW) {
                    sideMenuIcon.animatePressedState(MaterialMenuDrawable.IconState.BURGER);
                    drawerLayout.closeDrawer(Gravity.LEFT);
                } else if (sideMenuIcon.getState() == MaterialMenuDrawable.IconState.BURGER) {
                    sideMenuIcon.animatePressedState(MaterialMenuDrawable.IconState.ARROW);
                    drawerLayout.openDrawer(Gravity.LEFT);
                }

            }
        });

        sideMenuIcon = new MaterialMenuIconToolbar(this, Color.WHITE, MaterialMenuDrawable.Stroke.THIN) {
            @Override
            public int getToolbarViewId() {
                return R.id.toolbar;
            }
        };

        sideMenuIcon.setNeverDrawTouch(true);


        initPref();
        initializeSideMenu();
        //initInfo(firstName+" "+lastName, profileImagePath);


    }


    public void initializeSideMenu() {
        // setting up the menu View
        //View menuV = getLayoutInflater().inflate(R.layout.side_menu_list_view,null);

        menuLV = (ListView) findViewById(R.id.home_left_drawer);

        menuItemList = new ArrayList<SideMenuItemData>();

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

        menuAdapter = new SideMenuAdapter(this, menuItemList);

        //menuLV.setAdapter(menuAdapter);

        menuLV.setOnItemClickListener(this);

        // setting up header view
        View menuHeaderV = getLayoutInflater().inflate(
                R.layout.side_menu_header, null);

        profilePicIV = (ImageView) menuHeaderV
                .findViewById(R.id.side_menu_header_profile_picIV);
        nameTV = (TextView) menuHeaderV
                .findViewById(R.id.side_menu_header_nameTV);

        profilePicIV.setOnClickListener(this);

        menuLV.addHeaderView(menuHeaderV);

        menuLV.setAdapter(menuAdapter);

        drawerLayout = (DrawerLayout) findViewById(R.id.home_drawer);
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

    public void initInfo(String name, String profileImagePath) {

        nameTV.setText(name);

        //load profile image

    }

    public void initPref() {
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
                //menu.toggle();
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
                //menu.toggle();
                break;
            case 2:
                // Community
                //menu.toggle();
                break;
            case 3:
                // Top Players
                //menu.toggle();
                break;
            case 4:
                // Events
                //menu.toggle();
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
                //menu.toggle();
                break;

            default:
                break;
        }
    }


}
