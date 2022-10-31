package com.example.bt10sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.bt10sqlite.Control.Database;
import com.example.bt10sqlite.Model.Category;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Database db;
    List<Category> categories = new ArrayList<Category>();

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new Database(this,"COMPUTER.sqlite",null,1);
        db.QueryData("create table IF NOT EXISTS Category(idCategory VARCHAR(100),nameCategory nVARCHAR(100))");
        db.QueryData("create table IF NOT EXISTS Computer(idComputer VARCHAR(100),nameComputer VARCHAR(100),idCategory VARCHAR(100))");

        Cursor cursor = db.GetData("select * from Category");
        if (cursor.getCount()==0){
            db.insertCa("Category 1","Asus");
            db.insertCa("Category 2","Dell");
            db.insertCa("Category 3","HP");
        }
        cursor = db.GetData("select * from Computer");
        if (cursor.getCount()==0){
            db.insertC("PC01","Computer Asus 1","Category 1");
            db.insertC("PC02","Computer Asus 2","Category 1");
            db.insertC("PC03","Computer Asus 3","Category 1");
            db.insertC("PC04","Computer Asus 4","Category 1");
        }

        ListView lvLop = (ListView) findViewById(R.id.listviewCategory);

        cursor = db.GetData("select * from Category");
        System.out.println("ABC " + cursor.getCount());
        if (cursor.getCount()>0){
            System.out.println("abc");
            while (cursor.moveToNext()){
                String idCa = cursor.getString(cursor.getColumnIndex("idCategory"));
                String nameCa = cursor.getString(cursor.getColumnIndex("nameCategory"));
                categories.add(new Category(idCa,nameCa));
            }
        }
        ArrayAdapter<Category> lopArrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,categories);
        lvLop.setAdapter(lopArrayAdapter);
        lvLop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,ListComputer.class);
                intent.putExtra("idCategory",categories.get(i).getIdCategory());
                System.out.println(categories.get(i).getIdCategory());
                startActivity(intent);
            }
        });
    }
}