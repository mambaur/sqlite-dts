package com.example.sqlite_dts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnStore, btnGetAll, btnDeleteAll;
    private EditText eName;
    private TextView txtResult;
    private DatabaseHelper databaseHelper;
    private ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("SQLite DTS");

        btnStore = (Button) findViewById(R.id.btnStore);
        btnGetAll = (Button) findViewById(R.id.btnGetAll);
        btnDeleteAll = (Button) findViewById(R.id.btnDeleteAll);
        eName = (EditText) findViewById(R.id.eName);
        txtResult = (TextView) findViewById(R.id.txtResult);

        databaseHelper = new DatabaseHelper(this);
        getAllData();
        btnGetAll.setOnClickListener(this);
        btnStore.setOnClickListener(this);
        btnDeleteAll.setOnClickListener(this);
    }

    void getAllData(){
        arrayList = databaseHelper.getAllStudentList();
        String data = "";
        for (int i=0; i<arrayList.size(); i++){
            data += arrayList.get(i) +", ";
            txtResult.setText(data);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnStore:
                if (!eName.getText().toString().equals("")){
                    databaseHelper.addStudentDetail(eName.getText().toString());
                    eName.setText("");
                    Toast.makeText(this, "Stored Successfully!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Please enter name!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnGetAll:
                getAllData();
                break;
            case R.id.btnDeleteAll:
                databaseHelper.deleteAllStudent();
                Toast.makeText(this, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
                txtResult.setText("");
                break;
        }
    }
}
