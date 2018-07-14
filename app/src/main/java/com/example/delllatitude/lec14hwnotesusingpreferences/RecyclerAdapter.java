package com.example.delllatitude.lec14hwnotesusingpreferences;

import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.delllatitude.lec14hwnotesusingpreferences.db.NoteDb;
import com.example.delllatitude.lec14hwnotesusingpreferences.models.NoteData;

import java.util.ArrayList;

import static com.example.delllatitude.lec14hwnotesusingpreferences.Constants.*;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Context ctx;
    private ArrayList<NoteData> noteDataArrayList;
    private TextView tvHint;
    private NoteDb noteDb;
//    public RecyclerAdapter(Context ctx, ArrayList<NoteData> noteDataArrayList, TextView tvHint, NoteDb noteDb) {
//        this.ctx = ctx;
//        this.noteDataArrayList = noteDataArrayList;
//        this.tvHint = tvHint;
//        this.noteDb = noteDb;
//    }


    public RecyclerAdapter(Context ctx, ArrayList<NoteData> noteDataArrayList, TextView tvHint, NoteDb noteDb) {
        this.ctx = ctx;
        this.noteDataArrayList = noteDataArrayList;
        this.tvHint = tvHint;
        this.noteDb = noteDb;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.note_item_view, parent, false );
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NoteData currNote = noteDataArrayList.get(position);
        holder.noteTitle.setText(currNote.getTitle());
        holder.noteMessage.setText(currNote.getMessage());
    }

    @Override
    public int getItemCount() {
        return noteDataArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView noteTitle, noteMessage;

        public ViewHolder(View itemView) {
            super(itemView);
            noteTitle = itemView.findViewById(R.id.tvNote);
            noteMessage = itemView.findViewById(R.id.tvDetails);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    ContentValues contentValues = new ContentValues();
//                    int flag = 0;
//                    if(noteDataArrayList.get(getAdapterPosition()).isStatus()){
//                        noteDataArrayList.get(getAdapterPosition()).setStatus(false);
//                    }else{
//                        noteDataArrayList.get(getAdapterPosition()).setStatus(true);
//                        flag =1;
//                    }
//                    notifyDataSetChanged();
//                    contentValues.put(COLUMN_FLAG, flag);
//                    noteDb.getWritableDatabase().update(TABLE_NAME, contentValues,
//                            COLUMN_ID + " = ? ", new String[]{noteDataArrayList.get(getAdapterPosition()).getTime()});
//                }
//            });
//
//            itemView.setOnLongClickListener(new View.OnLongClickListener(){
//                @Override
//                public boolean onLongClick(View v) {
//                    int position = getAdapterPosition();
//                    noteDataArrayList.remove(position);
//                    noteDb.getWritableDatabase().delete(TABLE_NAME, COLUMN_ID + " = ? ", new String[]{noteDataArrayList.get(position).getTime()});
//                    if(noteDataArrayList.size()==0){
//                        tvHint.setVisibility(View.VISIBLE);
//                    }
//                    notifyDataSetChanged();
//                    return false;
//                }
//            });
        }
    }
}
