package com.bdu.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {


    SharedPreferenceConfig sharedPreferenceConfig;
    DatabaseHelper helper;
    EditText emailFilled, passwordFilled;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        emailFilled = findViewById(R.id.mail);
        passwordFilled = findViewById(R.id.pass);
        helper = new DatabaseHelper(this);
        sharedPreferenceConfig = new SharedPreferenceConfig(getApplicationContext());

        if (sharedPreferenceConfig.readLoginStatus()) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

    }

    public void loginit(View view) {

        String userName = emailFilled.getText().toString();
        String password = passwordFilled.getText().toString();

        if(!userName.isEmpty() && !password.isEmpty()) {
            if (helper.is_vallide(userName, password)) {
                Toast.makeText(this, "Successfully Login", Toast.LENGTH_LONG).show();
                sharedPreferenceConfig.writeLoginStatus(true);//saving true if Login successfully

                startActivity(new Intent(this, MainActivity.class));
                Log.e("logg", String.valueOf(sharedPreferenceConfig.readLoginStatus()));
                finish();


            } else {


                Toast.makeText(this, "Invalid your Name or password..try..again", Toast.LENGTH_LONG).show();
                emailFilled.setText("");
                passwordFilled.setText("");
            }
        }else{
            Toast.makeText(this, "Field Required", Toast.LENGTH_LONG).show();

        }
    }
    public void create (View view){

        startActivity(new Intent(this, Register.class));
    }

}
