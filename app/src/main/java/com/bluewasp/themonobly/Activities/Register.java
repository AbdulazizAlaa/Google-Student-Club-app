package com.bluewasp.themonobly.Activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bluewasp.themonobly.Beans.ServiceHandler;
import com.bluewasp.themonobly.Beans.Tags;
import com.bluewasp.themonobly.R;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;


public class Register extends ActionBarActivity implements View.OnClickListener {

    EditText firstNameED, lastNameED, emailED, phoneED, passwordED,
            confirmPasswordED, serialNumED;
    Button registerB;
    AlertDialog.Builder failureDialog;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        // initialize resources
        init();
    }



    public void init() {
        firstNameED = (EditText) findViewById(R.id.register_first_name_ED);
        lastNameED = (EditText) findViewById(R.id.register_last_name_ED);
        emailED = (EditText) findViewById(R.id.register_email_ED);
        phoneED = (EditText) findViewById(R.id.register_phone_ED);
        passwordED = (EditText) findViewById(R.id.register_password_ED);
        confirmPasswordED = (EditText) findViewById(R.id.register_confirm_password_ED);
        serialNumED = (EditText) findViewById(R.id.register_serial_number_ED);

        registerB = (Button) findViewById(R.id.register_registeristration_B);

        registerB.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        Intent i;
        switch (v.getId()) {
            case R.id.register_registeristration_B:

                new RegistraionProcess().execute(Tags.TAG_URL
                                + "registration/mobile_register_user", serialNumED
                                .getText().toString(), firstNameED.getText().toString(),
                        lastNameED.getText().toString(), phoneED.getText()
                                .toString(), emailED.getText().toString(),
                        passwordED.getText().toString());

                break;

            default:
                break;
        }
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();

        Register.this.finish();
    }

    private class RegistraionProcess extends AsyncTask<String, Void, Void> {

        ProgressDialog progress;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            progress = new ProgressDialog(Register.this);
            progress.setCancelable(false);
            progress.setMessage("Registration In Progress...");
            progress.show();

        }

        @Override
        protected Void doInBackground(String... params) {
            // TODO Auto-generated method stub

            String response, id, firstName, lastName, mobile, email, profileImagePath, committe, position, money, rankId, exp;
            ServiceHandler sr = new ServiceHandler();
            // adding parameters email and password to verify user
            ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();
            param.add(new BasicNameValuePair(Tags.REG_USER_ID, params[1]));
            param.add(new BasicNameValuePair(Tags.REG_FIRST_NAME, params[2]));
            param.add(new BasicNameValuePair(Tags.REG_LAST_NAME, params[3]));
            param.add(new BasicNameValuePair(Tags.REG_PHONE, params[4]));
            param.add(new BasicNameValuePair(Tags.REG_EMAIL, params[5]));
            param.add(new BasicNameValuePair(Tags.REG_PASSWORD, params[6]));

            try {
                // calling the login function
                response = sr.makeServiceCall(params[0], ServiceHandler.POST,
                        param);

                if (!response.equals("")) {

                    Log.i(Tags.TAG_LOG, "register:" + response);

                    JSONObject json = new JSONObject(response);
                    message = json.getString(Tags.TAG_MESSAGE);
                    if (message.equals(Tags.TAG_SUCCESS)) {

                        Log.i(Tags.TAG_LOG, "success");


                        //database issue should be fixed
						/*JSONArray dataArray = json.getJSONArray(Tags.TAG_DATA);
						JSONObject user = dataArray.getJSONObject(0);

						id = user.getString(Tags.PROFILE_USER_ID);
						firstName = user.getString(Tags.PROFILE_FIRST_NAME);
						lastName = user.getString(Tags.PROFILE_LAST_NAME);
						mobile = user.getString(Tags.PROFILE_MOBILE);
						email = user.getString(Tags.PROFILE_EMAIL);
						profileImagePath = user
								.getString(Tags.PROFILE_PROFILE_IMAGE_PATH);
						committe = user.getString(Tags.PROFILE_COMMITTE);
						position = user.getString(Tags.PROFILE_POSITION);
						money = user.getString(Tags.PROFILE_MONEY);
						rankId = user.getString(Tags.PROFILE_RANK_ID);
						exp = user.getString(Tags.PROFILE_EXPERIENCE);

						SharedPreferences pref = getSharedPreferences(
								Tags.TAG_PREF_FILE, MODE_PRIVATE);
						Editor edit = pref.edit();

						edit.putString(Tags.PROFILE_USER_ID, id);
						edit.putString(Tags.PROFILE_FIRST_NAME, firstName);
						edit.putString(Tags.PROFILE_LAST_NAME, lastName);
						edit.putString(Tags.PROFILE_MOBILE, mobile);
						edit.putString(Tags.PROFILE_EMAIL, email);
						edit.putString(Tags.PROFILE_PROFILE_IMAGE_PATH,
								profileImagePath);
						edit.putString(Tags.PROFILE_COMMITTE, committe);
						edit.putString(Tags.PROFILE_POSITION, position);
						edit.putString(Tags.PROFILE_MONEY, money);
						edit.putString(Tags.PROFILE_RANK_ID, rankId);
						edit.putString(Tags.PROFILE_EXPERIENCE, exp);
						edit.putString(Tags.PREF_STATUS, Tags.PREF_LOGGED_IN);

						edit.commit();*/

                        Intent i = new Intent(Register.this, Home.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);

                    } else if (message.equals(Tags.TAG_FAILURE)) {
                        Log.i(Tags.TAG_LOG, "failure");
                        String cause = json.getString(Tags.TAG_CAUSE);
                        failureDialog = new AlertDialog.Builder(Register.this);

                        failureDialog.setTitle("Failure");
                        if (cause.equals(Tags.TAG_USER_EXISTS)) {
                            failureDialog.setMessage("User already exists !!")
                                    .setPositiveButton(
                                            "Go to login",
                                            new DialogInterface.OnClickListener() {

                                                @Override
                                                public void onClick(
                                                        DialogInterface dialog,
                                                        int which) {
                                                    // TODO Auto-generated
                                                    // method
                                                    // stub
                                                    Intent i = new Intent(
                                                            Register.this,
                                                            Login.class);
                                                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                    startActivity(i);
                                                }
                                            })
                                    .setNegativeButton(
                                            "Cancel",
                                            new DialogInterface.OnClickListener() {

                                                @Override
                                                public void onClick(
                                                        DialogInterface dialog,
                                                        int which) {
                                                    // TODO Auto-generated
                                                    // method stub
                                                    dialog.cancel();
                                                }
                                            });
                        } else if (cause.equals(Tags.TAG_WRONG_SERIAL)) {
                            failureDialog.setMessage("Wrong Serial number !!")
                                    .setPositiveButton(
                                            "Try again",
                                            new DialogInterface.OnClickListener() {

                                                @Override
                                                public void onClick(
                                                        DialogInterface dialog,
                                                        int which) {
                                                    // TODO Auto-generated
                                                    // method
                                                    // stub
                                                    dialog.cancel();
                                                }
                                            });
                        }
                    }
                } else {
                    // no response
                    Log.i(Tags.TAG_LOG, "no response");
                }

            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
                Log.i(Tags.TAG_LOG, "register:" + e.getMessage());
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                Log.i(Tags.TAG_LOG, "register:" + e.getMessage());
                e.printStackTrace();
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                Log.i(Tags.TAG_LOG, "register:" + e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            if (progress.isShowing()) {
                progress.dismiss();
            }
            if (message.equals(Tags.TAG_FAILURE)){
                AlertDialog c = failureDialog.create();
                c.show();
            }


        }
    }

}
