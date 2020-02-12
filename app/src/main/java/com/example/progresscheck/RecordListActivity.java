package com.example.progresscheck;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class RecordListActivity extends AppCompatActivity {
    ListView mListView;
    ArrayList<Model> mList;
    RecordListAdapter mAdapter = null;
    RecyclerView recyclerView;
    ImageView imageViewIcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_list);

        //ActionBar actionBar=getSupportActionBar();
        //actionBar.setTitle("RECORD LIST");
        //to enable back button in actionbar set parent activity main activity in menifest
       mListView = findViewById(R.id.listview);

        mList = new ArrayList<>();
        mAdapter = new RecordListAdapter(this, R.layout.row, mList);
        mListView.setAdapter(mAdapter);

        //get all data from sqlite
        Cursor cursor = AddTask.mSQLiteHelper.getData("SELECT * FROM TASK");
        mList.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String body = cursor.getString(2);
            String date = cursor.getString(3);
            byte[] image = cursor.getBlob(4);
            //add to List
            mList.add(new Model(id, title, body, date, image));

        }
        mAdapter.notifyDataSetChanged();
        if (mList.size() == 0) {
            //if there is no record in the table of the database which means list view is empoty
            Toast.makeText(this, "No record found", Toast.LENGTH_SHORT).show();
        }
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                return false;
            }
        });
    }}