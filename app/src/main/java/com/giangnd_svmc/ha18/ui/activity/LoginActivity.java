package com.giangnd_svmc.ha18.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.giangnd_svmc.ha18.R;
import com.giangnd_svmc.ha18.entity.JsonParser;
import com.giangnd_svmc.ha18.entity.MyUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Manh on 4/9/16.
 */

public class LoginActivity extends Activity implements View.OnClickListener {
    EditText editUsername;
    EditText editPassword;
    CardView buttonLogin;
    ProgressBar progress;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);
        editUsername = (EditText) findViewById(R.id.editUsername);
        editPassword = (EditText) findViewById(R.id.editPassword);
        buttonLogin = (CardView) findViewById(R.id.buttonLogin);
        progress = (ProgressBar) findViewById(R.id.progress);
        progress.setVisibility(View.GONE);

        buttonLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.buttonLogin) {
            new Login().execute();
        }
    }


    class Login extends AsyncTask {
        String username = "";
        String password = "";
        JSONObject jsonObject;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress.setVisibility(View.VISIBLE);

            username = editUsername.getText().toString();
            password = editPassword.getText().toString();
            buttonLogin.setOnClickListener(null);
            editPassword.setEnabled(false);
            editUsername.setEnabled(false);
        }

        @Override
        protected Object doInBackground(Object[] params) {
            Log.e("TAG", MyUtils.urlLoginTeacher(username, password), null);
            JsonParser jsonParser = new JsonParser();
            jsonObject = jsonParser.getJsonFromUrl(MyUtils.urlLoginTeacher(username, password));
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            progress.setVisibility(View.GONE);
            buttonLogin.setOnClickListener(LoginActivity.this);
            editPassword.setEnabled(true);
            editUsername.setEnabled(true);
            if (jsonObject == null)
                Snackbar.make(getCurrentFocus(), "FAIL", Snackbar.LENGTH_LONG).show();
            else
                try {
                    int success = jsonObject.getInt(MyUtils.TAG_SUCCESS);
                    if (success == 1) {
                        Intent intent= new Intent(this,MainActivity.class);
                        finish();
                    } else {
                        Snackbar.make(getCurrentFocus(), "FAIL", Snackbar.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Snackbar.make(getCurrentFocus(), "FAIL", Snackbar.LENGTH_LONG).show();

                }

        }
    }
}
