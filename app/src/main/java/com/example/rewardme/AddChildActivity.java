package com.example.rewardme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class AddChildActivity extends AppCompatActivity {

    EditText etAddChild;
    Button btnAddChild;
    Kids kids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child);

        etAddChild = (EditText) findViewById(R.id.etAddChild);
        btnAddChild = (Button) findViewById(R.id.btnAddChild);

        btnAddChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getIntent();
                kids = (Kids) i.getSerializableExtra("done");
                DBHelper dbh = new DBHelper(AddChildActivity.this);
                String name = etAddChild.getText().toString();
                String star = kids.getStarCount();
                String choresCount = kids.getChoresCount();
                String chores = kids.getChores();
                if (!name.isEmpty()) {
                    DBHelper dbhelper = new DBHelper(AddChildActivity.this);
                    dbhelper.insertToDo(name, star, choresCount, chores);
                    dbhelper.close();
                    finish();
                }
            }
        });
    }
}
