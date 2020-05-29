//package com.example.rewardme;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//
//public class AddChoresActivity extends AppCompatActivity {
//
//    EditText etAddChores;
//    Button btnAddChores;
//    Kids kids;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_chores);
//
//        etAddChores = (EditText) findViewById(R.id.etAddChores);
//        btnAddChores = (Button) findViewById(R.id.btnAddChores);
//
//        Intent i = getIntent();
//        kids = (Kids) i.getSerializableExtra("kidsInfo");
//
//        btnAddChores.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DBHelper dbh = new DBHelper(AddChoresActivity.this);
//                kids.setChores(etAddChores.getText().toString());
//                Intent i = new Intent(AddChoresActivity.this, ChildInfoActivity.class);
//                i.putExtra("done", etAddChores.getText().toString());
//                startActivity(i);
//                dbh.updateChores(kids);
//                dbh.close();
//                setResult(RESULT_OK);
//                finish();
//            }
//        });
//    }
//}
