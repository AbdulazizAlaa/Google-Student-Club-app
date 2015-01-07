package com.bluewasp.themonobly.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.bluewasp.themonobly.Beans.Tags;
import com.bluewasp.themonobly.R;

public class LoginRegister extends ActionBarActivity {

    Button loginB, registerB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);


        // check if logged in
        SharedPreferences pref = getSharedPreferences(Tags.TAG_PREF_FILE,
                MODE_PRIVATE);
        String status = pref.getString(Tags.PREF_STATUS, "");
        if (status.equals(Tags.PREF_LOGGED_IN)) {
            Intent i = new Intent(LoginRegister.this, Home.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            LoginRegister.this.finish();
        }else{
            loginB = (Button) findViewById(R.id.login_register_login_b);
            registerB = (Button) findViewById(R.id.login_register_register_b);

            loginB.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {

                    Intent i = new Intent(LoginRegister.this, Login.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                    LoginRegister.this.finish();
                }
            });

            registerB.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {

                    Intent i = new Intent(LoginRegister.this, Register.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                    LoginRegister.this.finish();
                }
            });

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login_register, menu);
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
