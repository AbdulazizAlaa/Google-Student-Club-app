package com.bluewasp.themonobly.Activities;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.extras.toolbar.MaterialMenuIconToolbar;
import com.bluewasp.themonobly.Beans.Tags;
import com.bluewasp.themonobly.R;


public class Profile extends ActionBarActivity {

    //action bar
    Toolbar toolbar;
    MaterialMenuIconToolbar sideMenuIcon;
    ImageView actionbarProfileImgV;
    TextView actionbarNameTv, actionbarGollarsTv;
    ProgressBar actionbarLevelBar;

    SharedPreferences pref;
    String id, firstName, lastName, mobile, email, profileImagePath, committe, position, money, rankID, exp;
    TextView idTV, firstNameTV, lastNameTV, mobileTV, emailTV, profileImagePathTV, committeTV, positionTV, moneyTV, rankIDTV, expTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initializeActionbar();
        init();
        initializeProfile();

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
                //destroy activity
                if (sideMenuIcon.getState() == MaterialMenuDrawable.IconState.ARROW) {
                    //changing menu icon
                    sideMenuIcon.animatePressedState(MaterialMenuDrawable.IconState.BURGER);
                    //going back
                    finish();
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

        sideMenuIcon.animatePressedState(MaterialMenuDrawable.IconState.ARROW);

        sideMenuIcon.setNeverDrawTouch(true);

        //getting toolbar components' references
        actionbarNameTv = (TextView) findViewById(R.id.action_bar_name_tv);
        actionbarGollarsTv = (TextView) findViewById(R.id.action_bar_gollars_tv);
        actionbarProfileImgV = (ImageView) findViewById(R.id.action_bar_profile_img);
        actionbarLevelBar = (ProgressBar) findViewById(R.id.action_bar_level_progressBar);
    }


    public void init(){
        idTV = (TextView) findViewById(R.id.profile_user_id_TV);
        firstNameTV = (TextView) findViewById(R.id.profile_first_name_TV);
        lastNameTV = (TextView) findViewById(R.id.profile_last_name_TV);
        mobileTV = (TextView) findViewById(R.id.profile_phone_TV);
        emailTV = (TextView) findViewById(R.id.profile_email_TV);
        profileImagePathTV = (TextView) findViewById(R.id.profile_profile_image_path_TV);
        committeTV = (TextView) findViewById(R.id.profile_committe_TV);
        positionTV = (TextView) findViewById(R.id.profile_position_TV);
        moneyTV = (TextView) findViewById(R.id.profile_money_TV);
        rankIDTV = (TextView) findViewById(R.id.profile_rank_id_TV);
        expTV = (TextView) findViewById(R.id.profile_exp_TV);
    }

    public void initializeProfile(){
        pref = getSharedPreferences(Tags.TAG_PREF_FILE, MODE_PRIVATE);

        id = pref.getString(Tags.PROFILE_USER_ID, "");
        firstName = pref.getString(Tags.PROFILE_FIRST_NAME, "");
        lastName = pref.getString(Tags.PROFILE_LAST_NAME, "");
        mobile = pref.getString(Tags.PROFILE_MOBILE, "");
        email = pref.getString(Tags.PROFILE_EMAIL, "");
        profileImagePath = pref.getString(Tags.PROFILE_PROFILE_IMAGE_PATH, "");
        committe = pref.getString(Tags.PROFILE_COMMITTE, "");
        position = pref.getString(Tags.PROFILE_POSITION, "");
        money = pref.getString(Tags.PROFILE_MONEY, "");
        rankID = pref.getString(Tags.PROFILE_RANK_ID, "");
        exp = pref.getString(Tags.PROFILE_EXPERIENCE, "");

        idTV.setText(id);
        firstNameTV.setText(firstName);
        lastNameTV.setText(lastName);
        mobileTV.setText(mobile);
        emailTV.setText(email);
        profileImagePathTV.setText(profileImagePath);
        committeTV.setText(committe);
        positionTV.setText(position);
        moneyTV.setText(money);
        rankIDTV.setText(rankID);
        expTV.setText(exp);
        actionbarNameTv.setText(firstName);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
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
