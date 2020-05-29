package com.example.rewardme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnAddChild, btnRefrsh;
    ArrayList<Kids> kidsArr;
    KidsAdapter ka;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddChild = (Button) findViewById(R.id.btnAddChild);
        lv = (ListView) findViewById(R.id.lvChild);

        kidsArr = new ArrayList<>();
        ka = new KidsAdapter(this, R.layout.row, kidsArr);
        lv.setAdapter(ka);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Kids n = kidsArr.get(position);
                Intent i = new Intent(MainActivity.this, ChildInfoActivity.class);
                i.putExtra("selected", n);
                startActivity(i);
            }
        });
                kidsArr.clear();
                DBHelper dbhelper = new DBHelper(MainActivity.this);
                kidsArr.addAll(dbhelper.getData());
                dbhelper.close();
                ka.notifyDataSetChanged();

        btnAddChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AddChildActivity.class);
                startActivity(i);
            }
        });

    }
}
