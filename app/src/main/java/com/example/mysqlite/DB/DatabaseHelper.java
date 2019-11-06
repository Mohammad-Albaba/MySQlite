package com.example.mysqlite.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    SQLiteDatabase db ;

    public DatabaseHelper( Context context) {
        super(context, "notesDB", null, 1);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Note.CreateTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Note.TabeName);
        onCreate(db);
    }

    public boolean InsertDateBase(String note){
        ContentValues values = new ContentValues();
        values.put(Note.Col_Note,note);
        return db.insert(Note.TabeName,null,values)>0;
    }

    public ArrayList<Note> getAllNote(){
        ArrayList<Note> notes = new ArrayList<>();
        String SQL = "SELECT * FROM " + Note.TabeName ;
        Cursor cursor = db.rawQuery(SQL,null);
        if (cursor.moveToFirst()){
            do {
            Note note = new Note();
            note.setId(cursor.getInt(cursor.getColumnIndex(Note.Col_Id)));
            note.setNote(cursor.getString(cursor.getColumnIndex(Note.Col_Note)));
            note.setTimestamp(cursor.getString(cursor.getColumnIndex(Note.Col_Timestamp)));
            notes.add(note);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return notes ;
    }

    public boolean deleteNote(int id){
        return db.delete(Note.TabeName,"id = "+id,null)>0;
    }

    public boolean UpdateNote(int oldID, String note){
        ContentValues values = new ContentValues();
        values.put(Note.Col_Note,note);
        return db.update(Note.TabeName,values,"id ="+oldID,null)>0;
    }


}
