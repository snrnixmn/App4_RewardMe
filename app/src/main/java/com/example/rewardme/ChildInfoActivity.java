package com.example.rewardme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ChildInfoActivity extends AppCompatActivity {

    TextView tvChildName2, tvStarNum, tvChoresCount;
    ListView lvChores;
    ArrayList<Kids> al;
    ArrayList<String> arr;
    EditText etChores;
    Kids kids;
    ImageView ivChosenAvatar;
    Button btnAddChores;
    ArrayAdapter<String> aa;
    KidsAdapter ka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_info);

        tvChildName2 = (TextView) findViewById(R.id.tvKidName);
        tvStarNum = (TextView) findViewById(R.id.tvStarNo);
        tvChoresCount = (TextView) findViewById(R.id.tvChoreNum);
        ivChosenAvatar = (ImageView) findViewById(R.id.ivChosenAvatar);
        al = new ArrayList<Kids>();
        lvChores = (ListView) findViewById(R.id.lvChores);
        btnAddChores = (Button) findViewById(R.id.btnAddNewChores);
        etChores = (EditText) findViewById(R.id.etChores);

        ivChosenAvatar.setImageResource(R.drawable.flower);

        DBHelper dbh = new DBHelper(ChildInfoActivity.this);
        al.clear();
        al.addAll(dbh.getData());
        dbh.close();

        Intent i = getIntent();
        kids = (Kids) i.getSerializableExtra("selected");

        tvChildName2.setText(kids.getName());
        tvStarNum.setText(kids.getStarCount());
        String choresCount = kids.getChoresCount();

        if (!choresCount.isEmpty()) {
            tvChoresCount.setText(choresCount);
        } else {
            tvChoresCount.setText("none");
        }

        DBHelper db = new DBHelper(ChildInfoActivity.this);
        al.addAll(db.getChores());
        db.close();

        ka = new KidsAdapter(this, R.layout.row, al);
        lvChores.setAdapter(aa);

        lvChores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Kids n = al.get(position);
                Intent i = new Intent(ChildInfoActivity.this, ChildInfoActivity.class);
                i.putExtra("done", n);
                startActivity(i);
            }
        });

        btnAddChores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ChildInfoActivity.this);
                kids.setChores(etChores.getText().toString());
                dbh.updateChores(kids);
                dbh.close();
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

        } else {

        }
    }
}


