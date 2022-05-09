package com.app.proshin934_1;


import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SQLiteActivity extends Activity {

    MyOpenHelper myHelper = null;
    EditText field1, field2, result;
    SQLiteDatabase DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        myHelper=new MyOpenHelper(this, "myDB", null, 1);

        field1= findViewById(R.id.field1);
        field2= findViewById(R.id.field2);
        result= findViewById(R.id.dbResult);
    }

    public void insertIntoDatabase(View v){

        if(!field1.getText().toString().equals("") &&
                !field2.getText().toString().equals("")){

            DB = myHelper.getWritableDatabase();
            String query="create table if not exist " + myHelper.TABLE_NAME +
                    " (_id integer primary key autoincrement, " + myHelper.FIELD_NAME_1 + " TEXT, " + myHelper.FIELD_NAME_2 + " TEXT)";

            ContentValues CV = new ContentValues();
            CV.put(myHelper.FIELD_NAME_1,field1.getText().toString());
            CV.put(myHelper.FIELD_NAME_2,field2.getText().toString());
            DB.insert(myHelper.TABLE_NAME,null,CV);
            DB.close();
            field1.setText("");
            field2.setText("");
        }
    }

    public void readDatabase(View v){

        result.setText("");

        DB = myHelper.getReadableDatabase();

        String columns[]={"_id",myHelper.FIELD_NAME_1, myHelper.FIELD_NAME_2};
        Cursor cursor=DB.query(myHelper.TABLE_NAME, columns, null, null, null,
                null, "_id");
        if(cursor!=null){
            cursor.moveToFirst();
            if (cursor.moveToFirst()) {
                do {
                    result.setText(String.format("%s\n%s) %s,%s", result.getText().toString(), cursor.getString(0), cursor.getString(1), cursor.getString(2)));
                } while (cursor.moveToNext());
            }
        }

        DB.close();
    }

    public void deleteDatabase(View v){
        DB = myHelper.getWritableDatabase();

        DB.delete(myHelper.TABLE_NAME, null, null);
        DB.close();
    }
}
