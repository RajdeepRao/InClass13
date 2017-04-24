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
import io.realm.RealmList;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {


    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    CustomAdapter mAdapter;
    String SortBy, Show;
    Realm realm;
    RealmResults<Note> notesList;

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
        realm.init(this);
        realm = Realm.getDefaultInstance();


        Button add= (Button) findViewById(R.id.button);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String note=editText.getText().toString();
                String priority=spinner.getSelectedItem().toString();
                String time=new Date().toString();
                Note noteObj=new Note();
                noteObj.setNote(note);
                noteObj.set_id(01);
                noteObj.setStatus("PENDING");
                noteObj.setPriority(priority);
                noteObj.setUpdate_time(time);
                realm.beginTransaction();
                //Note n =new Note(note,"PENDING",priority,time);
                Note noteObjRealm= realm.copyToRealm(noteObj); // Create a new object
                realm.commitTransaction();

                Log.d("OBJ:",noteObj.toString()+" Created");

                RealmResults<Note> list=realm.where(Note.class).findAll();
                Log.d("Values:",list.toString());

                //noteArrayList.add(n);
                notesList=realm.where(Note.class).findAll();
                //updateListView();

            }
        });

//      noteArrayList = (ArrayList<Note>) dm.getAll();
        notesList=realm.where(Note.class).findAll();
//      updateListView();

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
