package com.example.notesapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.viewHolder> {
    Context context;
    List<Notes> list;
    DatabaseHelper helper;
    public RecyclerAdapter(Context context, List<Notes> list,DatabaseHelper helper) {
        this.context = context;
        this.list = list;
        this.helper=helper;
    }
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(context).inflate(R.layout.notesshow,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.content.setText(list.get(position).getContent());
        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog=new AlertDialog.Builder(context);
                dialog.setTitle("Delete");
                dialog.setMessage("Are you want to delete?");
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                     public void onClick(DialogInterface dialog, int which) {
                        helper.getDao().delete(new Notes(list.get(position).getId(),
                                list.get(position).getTitle(),list.get(position).getContent()));
                        ((MainActivity)context).showNotes();
                    }
                });
                dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
            }
        });
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view=LayoutInflater.from(context).inflate(R.layout.addnotes,null);
                String Title=list.get(position).getTitle();
                String Content=list.get(position).getContent();
                EditText TITLE,CONTENT;
                Button button;
                button=view.findViewById(R.id.button);
                TITLE=view.findViewById(R.id.title);
                CONTENT=view.findViewById(R.id.content);
                TITLE.setText(Title);
                CONTENT.setText(Content);
                TITLE.setSingleLine(false);
                CONTENT.setSingleLine(false);
                Dialog dialog=new Dialog(context);
                dialog.setContentView(view);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String title1=TITLE.getText().toString();
                        String content1=CONTENT.getText().toString();
                        Notes notes=new Notes(list.get(position).getId(),title1,content1);
                        helper.getDao().update(notes);
                        ((MainActivity)context).showNotes();
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView content;
        ImageView Delete;
        LinearLayout layout;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.Title);
            content=itemView.findViewById(R.id.Content);
            layout=itemView.findViewById(R.id.llv);
            Delete=itemView.findViewById(R.id.delete);

        }
    }
}
