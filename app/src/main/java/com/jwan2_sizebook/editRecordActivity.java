package com.jwan2_sizebook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * The type Edit record activity.
 */
public class editRecordActivity extends AppCompatActivity {

    private static final String FILENAME = "file.sav";

    private EditText nameT;
    private EditText dateT;
    private EditText neckT;
    private EditText bustT;
    private EditText chestT;
    private EditText waistT;
    private EditText hipT;
    private EditText inseamT;
    private EditText commentT;
    private Boolean doneSave;

    private String name;
    private String date;
    private String neck;
    private String bust;
    private String chest;
    private String waist;
    private String hip;
    private String inseam;
    private String comment;
    private String index;


    private ArrayList<Record> recordList;
    private ArrayAdapter<Record> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_record);

        nameT = (EditText) findViewById(R.id.NameText);
        dateT = (EditText) findViewById(R.id.dateText);
        neckT = (EditText) findViewById(R.id.neckText);
        bustT = (EditText) findViewById(R.id.bustText);
        chestT = (EditText) findViewById(R.id.chestText);
        waistT = (EditText) findViewById(R.id.waistText);
        hipT = (EditText) findViewById(R.id.hipText);
        inseamT = (EditText) findViewById(R.id.inseamText);
        commentT = (EditText) findViewById(R.id.commentText);
        doneSave = Boolean.FALSE;

        Button comfirmButton = (Button) findViewById(R.id.confirm_buttom);

        Intent intent = getIntent();
        name = intent.getStringExtra(HomeScreenActivity.EXTRA_NAME);
        date = intent.getStringExtra(HomeScreenActivity.EXTRA_DATE);
        neck = intent.getStringExtra(HomeScreenActivity.EXTRA_NECK);
        bust = intent.getStringExtra(HomeScreenActivity.EXTRA_BUST);
        chest = intent.getStringExtra(HomeScreenActivity.EXTRA_CHEST);
        waist = intent.getStringExtra(HomeScreenActivity.EXTRA_WAIST);
        hip = intent.getStringExtra(HomeScreenActivity.EXTRA_HIP);
        inseam = intent.getStringExtra(HomeScreenActivity.EXTRA_INSEAM);
        comment = intent.getStringExtra(HomeScreenActivity.EXTRA_COMMENT);
        index = intent.getStringExtra(HomeScreenActivity.EXTRA_INDEX);

        nameT.setText(name);
        dateT.setText(date);
        neckT.setText(neck);
        bustT.setText(bust);
        chestT.setText(chest);
        waistT.setText(waist);
        hipT.setText(hip);
        inseamT.setText(inseam);
        commentT.setText(comment);



        comfirmButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                name = nameT.getText().toString();
                date = dateT.getText().toString();
                neck = neckT.getText().toString();
                bust = bustT.getText().toString();
                chest = chestT.getText().toString();
                waist= waistT.getText().toString();
                hip= hipT.getText().toString();
                inseam = inseamT.getText().toString();
                comment = commentT.getText().toString();


                //make a Toast when name is empty
                if (name.length() == 0) {

                    Context context = getApplicationContext();
                    CharSequence text = "Please at least enter your name.";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                }else {
                    setResult(RESULT_OK);
                    Record record = new Record(name);
                    record.setName(name);
                    record.setDate(date);
                    record.setNeck(neck);
                    record.setBust(bust);
                    record.setChest(chest);
                    record.setWaist(waist);
                    record.setHip(hip);
                    record.setInseam(inseam);
                    record.setComment(comment);

                    recordList.set(Integer.parseInt(index), record);

                    saveInFile();
                    doneSave = Boolean.TRUE;
                    if (doneSave) {
                        Intent intent = new Intent(v.getContext(), HomeScreenActivity.class);
                        startActivity(intent);
                        finish();
                    }

                }
                }

            });

    }

//
    @Override
    protected void onStart(){
        super.onStart();
        loadFromFile();

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

    private void loadFromFile(){
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

}