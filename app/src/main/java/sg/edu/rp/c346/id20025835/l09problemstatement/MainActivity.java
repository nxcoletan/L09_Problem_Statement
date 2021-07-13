package sg.edu.rp.c346.id20025835.l09problemstatement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etTitle, etName, etYear;
    RadioGroup rgBtn;
    RadioButton rd1,rd2,rd3,rd4,rd5;
    Button btnInsert, btnList;
    ArrayList<Song> al;
    ArrayAdapter<Song> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = findViewById(R.id.etTitle);
        etName = findViewById(R.id.etName);
        etYear = findViewById(R.id.etYear);
        rgBtn = findViewById(R.id.rg);
        rd1 = findViewById(R.id.radioButton);
        rd2 = findViewById(R.id.radioButton2);
        rd3 = findViewById(R.id.radioButton3);
        rd4 = findViewById(R.id.radioButton4);
        rd5 = findViewById(R.id.radioButton5);
        btnInsert = findViewById(R.id.btnInsert);
        btnList = findViewById(R.id.btnList);

        al = new ArrayList<Song>();
        aa = new ArrayAdapter<Song>(this,
                android.R.layout.simple_list_item_1, al);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = etTitle.getText().toString();
                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_id = dbh.insertSong(data);

                al.addAll(dbh.getAllSongs());
                aa.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Insert successful",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Song target = al.get(0);

                Intent i = new Intent(MainActivity.this,
                        MainActivity2.class);
                i.putExtra("data", target);
                startActivity(i);
            }
        });
    }
}