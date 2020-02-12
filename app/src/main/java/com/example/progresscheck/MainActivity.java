package com.example.progresscheck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.transition.Visibility;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
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


        List<ModelClass>modelClassList=new ArrayList<>();
        modelClassList.add(new ModelClass(R.drawable.boy,"Java practise","complete Homework"));
        modelClassList.add(new ModelClass(R.drawable.assignment,"AI practise","Practise for exam"));
        modelClassList.add(new ModelClass(R.drawable.boy,"CSCL practise","Assignments"));
        modelClassList.add(new ModelClass(R.drawable.assignment,"Python practise","Read 4 hours"));
        modelClassList.add(new ModelClass(R.drawable.ic_launcher_background,"CSS practise","exercise complete"));


        Adapter adapter=new Adapter(modelClassList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        //Display first recycler view
        button1=(Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
        button2=(Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move=new Intent(MainActivity.this,FutureActivity.class);
                startActivity(move);
            }
        });
        //Add tasks
        add_btn=(ImageButton)findViewById(R.id.add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent move=new Intent(MainActivity.this,AddTask.class);
                startActivity(move);


            }
        });
        //Archive page
        archive7=(ImageButton)findViewById(R.id.archive7);
        archive7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move= new Intent(MainActivity.this,ArchiveActivity.class);
                startActivity(move);
            }
        });
        //Insights page
        insights_btn=(ImageButton)findViewById(R.id.insights_btn);
        insights_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move=new Intent(MainActivity.this,InsightsActivity.class);
                startActivity(move);
            }
        });



    }

}