package com.giangnd_svmc.ha18.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.giangnd_svmc.ha18.R;

/**
 * Created by Manh on 4/9/16.
 */

class LoginFragment extends Activity {
    EditText editUsername;
    EditText editPassword;
    Button buttonLogin;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);
        editUsername = (EditText) findViewById(R.id.editUsername);
        editPassword = (EditText) findViewById(R.id.editPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onClickLogin();
            }
        });
    }

    private void onClickLogin() {
        String username = editUsername.getText().toString();
        String passwod = editPassword.getText().toString();
        if (true) {

        } else {

        }
    }

}
