package com.jwan2_sizebook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import static android.provider.Telephony.Mms.Part.FILENAME;

public class HomeScreenActivity extends AppCompatActivity {

    private static final String FILENAME = "file.sav";

    private ArrayList<Record> recordList;
    private ArrayAdapter<Record> adapter;
    private ListView recordListView;

    private Record selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        recordListView = (ListView) findViewById(R.id.recordListView);
        Button deleteButton = (Button) findViewById(R.id.delete_button);

        recordListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View view, int pos, long id) {
                selected = (Record) adapter.getItemAtPosition(pos);
            }
        });

    }

    public void delete(View view) {
        adapter.remove(selected);
        saveInFile();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart(){
        super.onStart();
        loadFormFile();

        adapter = new ArrayAdapter<Record>(this,
                R.layout.list_item, recordList);
        recordListView.setAdapter(adapter);



    }

    /* Called when the user click the add button */
    public void addRecord(View view){
        /* This gonna jump to Activity about adding record*/
        Intent intent = new Intent(this, addRecordActivity.class);
        startActivity(intent);
    }
    private void loadFormFile(){
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader (new InputStreamReader(fis));

            Gson gson = new Gson();

            recordList = gson.fromJson(in, new TypeToken<ArrayList<Record>>() {
            }.getType());

            fis.close();
            }catch (FileNotFoundException e){
            recordList = new ArrayList<Record>();
        }catch (IOException e){
            throw new RuntimeException();
        }


    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(recordList, out);

            out.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e){
            throw new RuntimeException();
        }
    }

}
