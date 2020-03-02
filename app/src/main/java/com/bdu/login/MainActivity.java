package com.bdu.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;

    List<String> users_list;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper=new DatabaseHelper(this);
        recyclerView = findViewById(R.id.recycler);
        recyclerAdapter = new RecyclerAdapter(getAllUser(),getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerAdapter);

        DividerItemDecoration dIviderItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dIviderItemDecoration);





    }
    private List<User> getAllUser() {
        Cursor cursor=databaseHelper.getAll();
        List<User> users=new ArrayList<>();
        while (cursor.moveToNext()){
            User user=new User(cursor.getString(0),cursor.getString(1),
                    cursor.getString(2),cursor.getString(3),
                    cursor.getString(4),cursor.getString(5));
            users.add(user);
        }
        // adapter.notifyDataSetChanged();
        return users;
    }


    }

