package com.example.bt10sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.example.bt10sqlite.Control.ComputerAdapter;
import com.example.bt10sqlite.Model.Computer;
import com.example.bt10sqlite.Control.Database;

import java.util.ArrayList;
import java.util.List;

public class ListComputer extends AppCompatActivity {
    RecyclerView recyclerView;
    ComputerAdapter adapter;
    List<Computer> computers=new ArrayList<Computer>();
    Database db;


    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_computer);

        recyclerView = findViewById(R.id.RecycleViewComputer);
        Intent intent = getIntent();
        String idCa = intent.getStringExtra("idCategory");
        System.out.println(idCa.toString().trim());
        db = new Database(this,"COMPUTER.sqlite",null,1);
        Cursor cursor = db.GetData("Select * from Computer where idCategory = '"+idCa.toString().trim() +"'");
        while (cursor.moveToNext()){
                String idC =cursor.getString(cursor.getColumnIndex("idComputer"));
                String nameC = cursor.getString(cursor.getColumnIndex("nameComputer"));
                String idCategory = cursor.getString(cursor.getColumnIndex("idCategory"));
                System.out.println(idC+" "+nameC+" "+idCategory);
                computers.add(new Computer(idC,nameC,idCategory));
//            computers.add(new Computer(cursor.getString(cursor.getColumnIndex("idComputer")),cursor.getString(cursor.getColumnIndex("nameComputer")),cursor.getString(cursor.getColumnIndex("idCategory"))));
        }
            adapter = new ComputerAdapter(computers, this);
            GridLayoutManager linearLayoutManager = new GridLayoutManager(this,1);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(linearLayoutManager);
    }
}