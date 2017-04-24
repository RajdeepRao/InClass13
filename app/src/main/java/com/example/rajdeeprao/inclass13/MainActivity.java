package com.example.rajdeeprao.inclass13;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {


    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    CustomAdapter mAdapter;
    String SortBy, Show;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SortBy = "PRIORITY";
        Show = "All";

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.priority_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        final EditText editText= (EditText) findViewById(R.id.editText);
        realm = Realm.getDefaultInstance();


        Button add= (Button) findViewById(R.id.button);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String note=editText.getText().toString();
                String priority=spinner.getSelectedItem().toString();
                String time=new Date().toString();
                realm.beginTransaction();
                //Note n =new Note(note,"PENDING",priority,time);
                Note noteObj= realm.createObject(Note.class); // Create a new object
                noteObj.setNote(note);
                noteObj.setStatus("PENDING");
                noteObj.setPriority(priority);
                noteObj.setUpdate_time(time);
                realm.commitTransaction();
                Log.d("OBJ:",noteObj.toString()+" Created");
                //noteArrayList.add(n);
                //updateListView();

            }
        });

//        noteArrayList = (ArrayList<Note>) dm.getAll();
//        updateListView();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.MenuAll:
                SortBy = "PRIORITY";
                Show = "All";
                //updateListView();
                return true;
            case R.id.MenuCompleted:
                SortBy = "PRIORITY";
                Show = "COMPLETED";
                //updateListView();
                return true;
            case R.id.MenuPending:
                SortBy = "PRIORITY";
                Show ="PENDING";
                //updateListView();
                return true;
            case R.id.MenuPriority:
                SortBy = "PRIORITY";
                Show = "All";
                //updateListView();
                return true;
            case R.id.MenuTime:
                SortBy = "TIME";
                Show = "All";
                //updateListView();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }


}
