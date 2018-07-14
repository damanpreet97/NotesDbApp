package com.example.delllatitude.lec14hwnotesusingpreferences.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.delllatitude.lec14hwnotesusingpreferences.models.NoteData;

import java.util.ArrayList;

import static com.example.delllatitude.lec14hwnotesusingpreferences.Constants.*;
//import static com.example.delllatitude.lec14hwnotesusingpreferences.Constants.TABLE_NAME;

public class NoteDb extends SQLiteOpenHelper {


//    public NoteDb(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }

    public NoteDb(Context context) {
        super(context, "notedb", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = CREATE + TABLE_NAME + RBR + COLUMN_ID + INTEGER + PRIMARY_KEY + COMMA + COLUMN_TITLE + TEXT +NOT_NULL + LBR + TERMINATION;
        db.execSQL(query);
        db.execSQL("ALTER TABLE" + TABLE_NAME + "ADD COLUMN" + COLUMN_DES + TEXT + TERMINATION);
        db.execSQL("ALTER TABLE" + TABLE_NAME + "ADD COLUMN" + COLUMN_FLAG + INTEGER + TERMINATION);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        if (newVersion > oldVersion) {        }
    }


    public long insertNoteInDb(NoteData noteData){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, noteData.getTime());
        contentValues.put(COLUMN_TITLE, noteData.getTitle());

        return getWritableDatabase().insert(TABLE_NAME, null, contentValues);
    }

    public ArrayList<NoteData> getAllNotes(){
//        String[] projection = {COLUMN_ID, COLUMN_TITLE};
        ArrayList<NoteData> noteDataArrayList = new ArrayList<>();

        Cursor c= getReadableDatabase().query(TABLE_NAME, null, null, null,null,null,null);
        while(c.moveToNext()){

            Long id = c.getLong(c.getColumnIndex(COLUMN_ID.trim()));
            String title = c.getString(c.getColumnIndex(COLUMN_TITLE.trim()));
            String description = c.getString(c.getColumnIndex(COLUMN_DES.trim()));
            int flag = c.getInt(c.getColumnIndex(COLUMN_FLAG.trim()));

            boolean status = false;
            if(flag==1){
                status=true;
            }

            noteDataArrayList.add(new NoteData(title, description, id.toString(), status));
        }
        c.close();

        return noteDataArrayList;
    }


}
