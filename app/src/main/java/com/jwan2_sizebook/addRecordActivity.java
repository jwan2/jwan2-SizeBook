package com.jwan2_sizebook;

import android.content.Context;
import android.content.Intent;
import android.icu.text.AlphabeticIndex;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Date;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import static android.R.attr.button;
import static android.provider.Telephony.Mms.Part.FILENAME;

public class addRecordActivity extends AppCompatActivity {

    private static final String FILENAME = "file.sav";

    private EditText FirstNameT;
    private EditText LastNameT;
    private EditText dateT;
    private EditText neckT;
    private EditText bustT;
    private EditText chestT;
    private EditText waistT;
    private EditText inseamT;
    private EditText commentT;
    private Boolean doneSave;



    private ArrayList <Record> recordList;
    private ArrayAdapter<Record> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        FirstNameT = (EditText) findViewById(R.id.firstNameText);
        LastNameT = (EditText) findViewById(R.id.lastNameText);
        dateT = (EditText) findViewById(R.id.dateText);
        neckT = (EditText) findViewById(R.id.nectText);
        bustT = (EditText) findViewById(R.id.bustText);
        chestT = (EditText) findViewById(R.id.checsText);
        waistT = (EditText) findViewById(R.id.waistText);
        inseamT = (EditText) findViewById(R.id.inseamText);
        commentT = (EditText) findViewById(R.id.commentText);
        doneSave = Boolean.FALSE;

        Button comfirmButton = (Button) findViewById(R.id.confirm_buttom);


        comfirmButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                String firstName = FirstNameT.getText().toString();
                String lastName = LastNameT.getText().toString();
                String dateText = dateT.getText().toString();
                String neckText = neckT.getText().toString();
                String bustText = bustT.getText().toString();
                String chestText = chestT.getText().toString();
                String waistText = waistT.getText().toString();
                String inseamText = inseamT.getText().toString();
                String commentText = commentT.getText().toString();

                //make a Toast when name is empty
                if (firstName.length() == 0 || lastName.length() == 0) {

                    Context context = getApplicationContext();
                    CharSequence text = "Please at least enter your name.";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                }else {
                    setResult(RESULT_OK);
                    Record record = new Record();
                    record.setFirstName(firstName);
                    record.setLastName(lastName);
                    record.setDate(dateText);
                    //need to round
                    record.setNeck(neckText);
                    record.setBust(bustText);
                    record.setChest(chestText);
                    record.setWaist(waistText);
                    record.setInseam(inseamText);
                    record.setComment(commentText);


                    recordList.add(record);

                    saveInFile();
                    doneSave = Boolean.TRUE;
                    if (doneSave) {
                        Intent intent = new Intent(v.getContext(), HomeScreenActivity.class);
                        startActivity(intent);
                    }

                }
                }

            });

        }


    @Override
    protected void onStart(){
        super.onStart();
        loadFromFile();

    }

    //go back to homescreen
    //public void homeScreen(View view) {
      //  if (doneSave) {
        //    Intent intent = new Intent(this, HomeScreenActivity.class);
          //  startActivity(intent);
       // }
   // }

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
