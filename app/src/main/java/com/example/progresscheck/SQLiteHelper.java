package com.example.progresscheck;



import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
public class SQLiteHelper extends SQLiteOpenHelper{
    //constructor
    SQLiteHelper(Context context,
                 String name,
                 SQLiteDatabase.CursorFactory factory,
                 int version){
        super(context,name,factory,version);
    }
    public void queryData(String sql){
        SQLiteDatabase database=getWritableDatabase();
        database.execSQL(sql);
    }
    //insertdata
    public void insertData(String title,String body,String date,byte[] image) {
        SQLiteDatabase database = getWritableDatabase();
        //query to insert records in database table
        String sql = "INSERT INTO TASK VALUES(NULL,?,?,?,?)";//where the recoud is table nae
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, title);
        statement.bindString(2, body);
        statement.bindString(3, date);
        statement.bindBlob(4, image);
        statement.executeInsert();
    }
    public void updateData(String title,String body,String date, byte[] image,int id){
        SQLiteDatabase database=getWritableDatabase();
        //query to update records
        String sql="UPDATE TASK SET title=?,body=?,date=?,image=? WHERE id=?";
        SQLiteStatement statement=database.compileStatement(sql);

        statement.bindString(1, title);
        statement.bindString(2, body);
        statement.bindString(3, date);
        statement.bindBlob(4, image);
        statement.bindDouble(5,(double)id);
        statement.execute();
        database.close();
    }
    //delete data
    public void deleteData(int id){
        SQLiteDatabase database=getWritableDatabase();
        //query to delete records using id
        String sql="DELETE FROM TASK WHERE id=?";
        SQLiteStatement statement=database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1,(double)id);
        statement.execute();
        database.close();
    }
    public Cursor getData(String sql){
        SQLiteDatabase database=getReadableDatabase();
        return database.rawQuery(sql,null);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
