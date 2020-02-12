package com.example.progresscheck;

import android.Manifest;
import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;

public class AddTask extends AppCompatActivity {
    public EditText edittitle,editbody,editDate;
    public Button savetask,viewtask;
    public ImageView addimg;

    final int REQUEST_CODE_GALLERY=999;

    public static SQLiteHelper mSQLiteHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfrag);
        addimg=(ImageView)findViewById(R.id.addimg);
        edittitle=(EditText)findViewById(R.id.edittitle);
        editbody=(EditText)findViewById(R.id.editbody) ;
        editDate=(EditText)findViewById(R.id.editDate);
        savetask=(Button)findViewById(R.id.savetask);
        viewtask=(Button)findViewById(R.id.viewtask);
        //create database
        mSQLiteHelper=new SQLiteHelper(this,"TASKDB.sqlite",null,1);

        //create table in database
        mSQLiteHelper.queryData("CREATE TABLE IF NOT EXISTS TASK(id INTEGER PRIMARY KEY AUTOINCREMENT, TITLE VARCHAR,BODY VARCHAR, DATE VARCHAR, image BLOB)");

        //select image by image view

        addimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //read external storage permission to select image from gallary
                //runtime permission for device android 6.1 and above
                ActivityCompat.requestPermissions( AddTask.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );


            }
        });
        //add record to sqlite
        savetask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    mSQLiteHelper.insertData(
                            edittitle.getText().toString().trim(),
                            editbody.getText().toString().trim(),
                            editDate.getText().toString().trim(),
                            imageViewToByte(addimg)

                    );
                    Toast.makeText(AddTask.this,"Added sucessfully",Toast.LENGTH_SHORT).show();

                    //reset views
                    edittitle.setText("");
                    editbody.setText("");
                    editDate.setText("");
                    addimg.setImageResource(R.drawable.ic_action_name);


                }
                catch(Exception e){
                    Toast.makeText(AddTask.this,"not sucessfull",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }


            }
        });

            viewtask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent move=new Intent(AddTask.this,RecordListActivity.class);
                    startActivity(move);
                }
            });
    }

   //from imageViewToByte
    public static byte[] imageViewToByte(ImageView addimg) {
        Bitmap bitmap=((BitmapDrawable)addimg.getDrawable()).getBitmap();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;

    }

    //for image permission method
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        if(requestCode == REQUEST_CODE_GALLERY) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //grant intent
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, REQUEST_CODE_GALLERY);

            } else {
                Toast.makeText(this, "Dont have permission to  access", Toast.LENGTH_SHORT).show();

            }
            return;
        }
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }
    //add coping a activity in menifest
    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK){
            Uri imageUri= data.getData();
            CropImage.activity(imageUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1,1)// image view will be square
                    .start(this);

        }
        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result =CropImage.getActivityResult(data);
            if(resultCode == RESULT_OK){
                Uri resultUri =result.getUri();
                //set image choosed from gallery t image view
                addimg.setImageURI(resultUri);
            }
            else if(
                    resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                Exception error=result.getError();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }


}
