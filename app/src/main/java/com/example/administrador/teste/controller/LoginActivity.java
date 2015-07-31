package com.example.administrador.teste.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrador.teste.R;
import com.example.administrador.teste.model.entities.Client;
import com.example.administrador.teste.model.entities.User;
import com.example.administrador.teste.util.FormHelper;

/**
 * Created by Administrador on 20/07/2015.
 */
public class LoginActivity extends AppCompatActivity {

    Button buttonLogin;
    User user;
    EditText editTextUserName;
    EditText editTextPassword;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUserName = (EditText) findViewById(R.id.editTextUserName);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        bindLoginService();
    }


    private void bindLoginService() {
        buttonLogin = (Button) findViewById(R.id.btnlogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User userBD = user.getOneUser();
                String name = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();

                if(FormHelper.requiredValidate(LoginActivity.this, editTextUserName, editTextPassword)) {
                    if (userBD.getUserName().equals(name) && userBD.getPassword().equals(password)) {
                        startActivity(new Intent(LoginActivity.this, ClientListActivity.class));
                    } else {
                        Toast.makeText(LoginActivity.this, R.string.loginFail, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

}
