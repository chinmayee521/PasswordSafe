package com.example.passwordsafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ViewAll extends AppCompatActivity {

    // creating variables for our array list,
    // dbhandler, adapter and recycler view.
    private ArrayList<PwdSafe> list;
    private DbOperations db;
    private PassRVAdapter adapter;
    private RecyclerView RV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        // initializing our all variables.
        list = new ArrayList<>();
        db = new DbOperations(ViewAll.this);

        // getting our course array
        // list from db handler class.
        list = db.readAllData();

        // on below line passing our array lost to our adapter class.
        adapter = new PassRVAdapter(list, ViewAll.this);
        RV = findViewById(R.id.idRVAllData);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewAll.this, RecyclerView.VERTICAL, false);
        RV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        RV.setAdapter(adapter);


    }
}
