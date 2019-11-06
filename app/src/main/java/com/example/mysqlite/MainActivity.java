package com.example.mysqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.mysqlite.DB.DatabaseHelper;
import com.example.mysqlite.DB.Note;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList List;
    Button add;
    EditText text;
    DatabaseHelper helper ;
    Adapter adapter ;
    SQLiteDatabase db ;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new DatabaseHelper(this);
        Intilizer();
        getNote();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddDatabaseHleper();
            }
        });
    }
    public void Intilizer(){
        recyclerView =findViewById(R.id.res);
        add = findViewById(R.id.btn_Add);
        text = findViewById(R.id.edit_txt);
        List=new ArrayList();
        adapter=new Adapter(List,this);
        LinearLayoutManager moh=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(moh);
        recyclerView.setAdapter(adapter);
    }
    public void AddDatabaseHleper(){
        helper = new DatabaseHelper(this);
        if (helper.InsertDateBase(text.getText().toString())){
            Toast.makeText(this, "Add Sucuessful", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Filed Add", Toast.LENGTH_SHORT).show();
        }

    }

    private void getNote() {
        helper = new DatabaseHelper(this);
        db = helper.getWritableDatabase();
        String SQL = "SELECT * FROM " + Note.TabeName ;
        Cursor cursor = db.rawQuery(SQL,null);
        if (cursor.moveToFirst()){
            do {
                Note note = new Note();
                note.setId(cursor.getInt(cursor.getColumnIndex(Note.Col_Id)));
                note.setNote(cursor.getString(cursor.getColumnIndex(Note.Col_Note)));
                note.setTimestamp(cursor.getString(cursor.getColumnIndex(Note.Col_Timestamp)));
                List.add(note);
            }while (cursor.moveToNext());
        }
            adapter.notifyDataSetChanged();

        helper.close();

    }
}
