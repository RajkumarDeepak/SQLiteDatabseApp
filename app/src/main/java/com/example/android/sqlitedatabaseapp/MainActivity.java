package com.example.android.sqlitedatabaseapp;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  DatabaseHelper myDb;
  EditText editname, editsurname, editmarks;
  Button btnAdddata;
  Button viewAllButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    myDb = new DatabaseHelper(this);

    editname = (EditText)findViewById(R.id.tvname);
    editsurname = (EditText)findViewById(R.id.tvsurname);
    editmarks = (EditText)findViewById(R.id.tvmarks);
    btnAdddata = (Button)findViewById(R.id.btnadddata);
    viewAllButton =(Button) findViewById(R.id.button2);
    AddData();
    veiwAll();


  }

  public void AddData(){

  btnAdddata.setOnClickListener(
    new View.OnClickListener( ) {
      @Override
      public void onClick(View v) {
        boolean isInserted = myDb.insertData(editname.getText().toString(),
          editsurname.getText().toString(), editmarks.getText().toString());
        if(isInserted = true)
          Toast.makeText(MainActivity.this,"data inserrted",Toast.LENGTH_LONG).show();
        else
          Toast.makeText(MainActivity.this,"data not inserrted",Toast.LENGTH_LONG).show();



      }
    }
  );
  }

  public void veiwAll(){
    viewAllButton.setOnClickListener(
      new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          Cursor res = myDb.getAllData();
          if (res.getCount()==0){

            //show message
            showMessage("error", "nothing found");

            return;
          }

          StringBuffer buffer = new StringBuffer();

          while (res.moveToNext()){
            buffer.append("ID:"+ res.getString(0)+"\n");
            buffer.append("NAME:"+ res.getString(1)+"\n");
            buffer.append("SURNAME:"+ res.getString(2)+"\n");
            buffer.append("MARKS:"+ res.getString(3)+"\n\n");
          }
          // show all data
          showMessage("Data",buffer.toString());
        }
      }
    );
  }

  public void showMessage (String title, String message){

    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setCancelable(true);
    builder.setTitle(title);
    builder.setMessage(message);
    builder.show();
  }

}

