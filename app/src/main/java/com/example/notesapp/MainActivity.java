package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseHelper helper;
    TextView textView;
    Notes notes;
    FloatingActionButton button;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerview);
        textView=findViewById(R.id.emptytext);
        button=findViewById(R.id.floatbutton);
        helper=DatabaseHelper.getHelper(this);
        showNotes();
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog=new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.addnotes);
                EditText title,content;
                Button button1;
                title=dialog.findViewById(R.id.title);
                content=dialog.findViewById(R.id.content);
                content.setSingleLine(false);
                title.setSingleLine(false);
                button1=dialog.findViewById(R.id.button);
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String Title,Content;
                        Title=title.getText().toString();
                        Content=content.getText().toString();
                        if (!Content.equals(""))
                        {
                            notes=new Notes(Title,Content);
                            helper.getDao().add(notes);
                            showNotes();
                            dialog.dismiss();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Please enter notes", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.show();
            }
        });

    }
    public void showNotes() {
        List<Notes> list=helper.getDao().getList();
        if(list.size()>0)
        {
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setAdapter(new RecyclerAdapter(this,list,helper));
            textView.setVisibility(View.GONE);
        }
        else {
            textView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }
}