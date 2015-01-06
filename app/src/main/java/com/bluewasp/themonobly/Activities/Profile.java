package com.bluewasp.themonobly.Activities;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.bluewasp.themonobly.Beans.Tags;
import com.bluewasp.themonobly.R;


public class Profile extends ActionBarActivity {

    SharedPreferences pref;
    String id, firstName, lastName, mobile, email, profileImagePath, committe, position, money, rankID, exp;
    TextView idTV, firstNameTV, lastNameTV, mobileTV, emailTV, profileImagePathTV, committeTV, positionTV, moneyTV, rankIDTV, expTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();
        initializeProfile();

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

        //getSupportActionBar().setTitle(firstName+" "+lastName);
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
