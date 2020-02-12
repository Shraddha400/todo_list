package com.example.progresscheck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Visibility;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;


public class FutureActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    public Button button1;
    public Button button2;
    public ImageButton add_btn,archive7,insights_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //set recycler view and its linearlayout orientation
        recyclerView=findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        //make list wise to dispaly in this recycler view



        //Display first recycler view

        List<ModelClass>modelClassList=new ArrayList<>();
        modelClassList.add(new ModelClass(R.drawable.boy,"Project ","date: 7th jan"));
        modelClassList.add(new ModelClass(R.drawable.ic_launcher_background,"Cultural program","Music deadline 8th feb"));


        Adapter adapter=new Adapter(modelClassList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        button1=(Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move=new Intent(FutureActivity.this,MainActivity.class);
                startActivity(move);
            }
        });



        //Add tasks
        add_btn=(ImageButton)findViewById(R.id.add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
        //Archive page
        archive7=(ImageButton)findViewById(R.id.archive7);
        archive7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move= new Intent(FutureActivity.this,ArchiveActivity.class);
                startActivity(move);
            }
        });
        //Insights page
        insights_btn=(ImageButton)findViewById(R.id.insights_btn);
        insights_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move=new Intent(FutureActivity.this,InsightsActivity.class);
                startActivity(move);
            }
        });



    }
}