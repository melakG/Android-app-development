package com.bdu.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class UserInfo extends AppCompatActivity {

    SharedPreferenceConfig sharedPreferenceConfig;
    DatabaseHelper databaseHelper;

    TextView fullName,userName,gender,email,phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        sharedPreferenceConfig=new SharedPreferenceConfig(this);
        databaseHelper=new DatabaseHelper(this);




        fullName=findViewById(R.id.fn);
        userName=findViewById(R.id.un);
        gender=findViewById(R.id.gn);
        email=findViewById(R.id.ml);
        phone=findViewById(R.id.pn);
        setData(sharedPreferenceConfig.readLoginStatus());

    }

    public void setData(boolean loged){
        Cursor cursor=databaseHelper.getAll();
        cursor.moveToFirst();
        if (cursor.moveToNext()) {
            fullName.setText(cursor.getString(0));
            userName.setText(cursor.getString(1));
            gender.setText(cursor.getString(2));
            email.setText(cursor.getString(4));
            phone.setText(cursor.getString(3));
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(R.id.logout==item.getItemId()) {
            LogOut();
        }
        return true;


    }





    public void LogOut() {
        sharedPreferenceConfig.writeLoginStatus(false);
        startActivity(new Intent(this,login.class));
        finishFromChild(this);
        finish();
    }

    public void see(View view) {
        sharedPreferenceConfig.writeLoginStatus(false);
        startActivity(new Intent(this,MainActivity.class));
        finishFromChild(this);
        finish();
    }
}
