package com.example.delllatitude.lec14hwnotesusingpreferences;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.delllatitude.lec14hwnotesusingpreferences.models.NoteData;
import com.example.delllatitude.lec14hwnotesusingpreferences.db.NoteDb;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tvHint;
    RecyclerView rv;
    FloatingActionButton fab;
    ArrayList<NoteData> noteDataArrayList;
    RecyclerAdapter recyclerAdapter;
    NoteDb noteDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noteDb = new NoteDb(this);
//        SharedPreferences sharedPreferences = getSharedPreferences("my_shared_prefs", MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();

        rv = findViewById(R.id.rv);

        tvHint = findViewById(R.id.tvHint);

        fab = findViewById(R.id.floatingActionButton);

        noteDataArrayList = new ArrayList<>();

        recyclerAdapter = new RecyclerAdapter(this, noteDataArrayList, tvHint, noteDb);

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rv.setAdapter(recyclerAdapter);
//        Gson gson = new Gson();
//        int i = 0;
//        while (!(sharedPreferences.getString(i + "", "none").equals("none"))) {
//            String json = sharedPreferences.getString(i + "", "none");
//            NoteData noteData = gson.fromJson(json, NoteData.class);
//            noteDataArrayList.add(noteData);
//            editor.remove(i + "");
//            i++;
//        }
        if(noteDb.getAllNotes()!=null) {
            noteDataArrayList = noteDb.getAllNotes();
            for(NoteData noteData: noteDataArrayList){
                new NoteData(noteData.getTitle(), noteData.getMessage(), noteData.getTime(), noteData.isStatus());
            }
            recyclerAdapter.notifyDataSetChanged();
        }

        checkArrayListSize();

        final View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_view, null, true);

        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle("Create New Note").setView(dialogView).setCancelable(true)
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText etTitle = dialogView.findViewById(R.id.etTitle);
                        EditText etMessage = dialogView.findViewById(R.id.etMessage);
                        NoteData newNote = new NoteData(etTitle.getText().toString(),
                                etMessage.getText().toString(), System.currentTimeMillis() + "", false);
                        noteDataArrayList.add(newNote);
//                        Log.e("TAG", "insertNote: "+noteDb.insertNoteInDb(newNote) );
                        etTitle.setText("");
                        etMessage.setText("");
                        recyclerAdapter.notifyDataSetChanged();
                        checkArrayListSize();
                    }
                }).create();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.show();
//                checkArrayListSize();
//                recyclerAdapter.notifyDataSetChanged();
            }
        });

    }

    public void checkArrayListSize() {
        if (noteDataArrayList.size() != 0) {
            tvHint.setVisibility(View.GONE);
//            rv.setVisibility(View.VISIBLE);
        }
    }

//    @Override
//    protected void onStop() {
//        super.onStop();

    //To save from any Broadcast or other external changes in database, we will update our arrayList before the end
    //of this method.
//        noteDataArrayList = noteDb.getAllNotes();
//        recyclerAdapter.notifyDataSetChanged();
        /*        SharedPreferences sharedPreferences = getSharedPreferences("my_shared_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        sharedPreferences.getAll();
        for (int i = 0; i < noteDataArrayList.size(); i++) {
            if (!(sharedPreferences.contains(i + ""))) {
                NoteData currNote = noteDataArrayList.get(i);
                editor.putString(i + "", gson.toJson(currNote));
            }
        }
        editor.apply();
        */
//    }
}
