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

import android.widget.TextView;
import android.widget.Toast;

import static com.jwan2_sizebook.R.drawable.selector;

/**
 * The type Home screen activity.
 */
public class HomeScreenActivity extends AppCompatActivity {

    private static final String FILENAME = "file.sav";
    /**
     * The constant EXTRA_DETAIL.
     */
    public final static String EXTRA_DETAIL = "com.jwan2_sizebook.DETAIL";
    /**
     * The constant EXTRA_NAME.
     */
    public final static String EXTRA_NAME = "com.jwan2_sizebook.NAME";
    /**
     * The constant EXTRA_DATE.
     */
    public final static String EXTRA_DATE = "com.jwan2_sizebook.DATE";
    /**
     * The constant EXTRA_NECK.
     */
    public final static String EXTRA_NECK = "com.jwan2_sizebook.NECK";
    /**
     * The constant EXTRA_BUST.
     */
    public final static String EXTRA_BUST = "com.jwan2_sizebook.BUST";
    /**
     * The constant EXTRA_CHEST.
     */
    public final static String EXTRA_CHEST = "com.jwan2_sizebook.CHEST";
    /**
     * The constant EXTRA_WAIST.
     */
    public final static String EXTRA_WAIST = "com.jwan2_sizebook.WAIST";
    /**
     * The constant EXTRA_HIP.
     */
    public final static String EXTRA_HIP = "com.jwan2_sizebook.HIP";
    /**
     * The constant EXTRA_INSEAM.
     */
    public final static String EXTRA_INSEAM = "com.jwan2_sizebook.INSEAM";
    /**
     * The constant EXTRA_COMMENT.
     */
    public final static String EXTRA_COMMENT = "com.jwan2_sizebook.COMMENT";
    /**
     * The constant EXTRA_INDEX.
     */
    public final static String EXTRA_INDEX = "com.jwan2_sizebook.INDEX";

    private ArrayList<Record> recordList;
    private ArrayAdapter<Record> adapter;
    private ListView recordListView;
    private TextView count;
    private Context context;
    private String name;
    private String date;
    private String neck;
    private String bust;
    private String chest;
    private String waist;
    private String hip;
    private String inseam;
    private String comment;
    private Record selected;
    private String index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        context = getApplicationContext();
        count = (TextView)findViewById(R.id.count);
        recordListView = (ListView) findViewById(R.id.recordListView);

        Button deleteButton = (Button) findViewById(R.id.delete_button);

        recordListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View view, int pos, long id) {
                selected = (Record) adapter.getItemAtPosition(pos);
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (selected == null) {
                    Context context = getApplicationContext();

                    CharSequence text = "Please select a record first.";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                } else {
                    adapter.remove(selected);
                    saveInFile();
                    count.setText("Number of records = " + recordList.size());
                    selected = null;
                    adapter.notifyDataSetChanged();
                }
            }

        });
    }



    @Override
    protected void onStart(){
        super.onStart();
        selected = null;
        loadFormFile();
        count.setText("Number of records = " + recordList.size());

        adapter = new ArrayAdapter<Record>(this,
                R.layout.list_item, recordList);
        recordListView.setAdapter(adapter);

    }


    /**
     * Add record.
     *
     * @param view the view
     */
/* Called when the user click the add button */
    public void addRecord(View view){
        /* This gonna jump to Activity about adding record*/
        Intent intent = new Intent(this, addRecordActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Show detail.
     *
     * @param view the view
     */
    public void showDetail(View view) {
        if (selected == null) {
            Context context = getApplicationContext();

            CharSequence text = "Please select a record first.";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        } else {
            String detail = "Detail:\nName: " + selected.getName() +
                    "\nDate: " + selected.getDate() + "\n" +
                    "Neck: " + selected.getNeck() + "\n" +
                    "Bust: " + selected.getBust() + "\n" +
                    "Chest: " + selected.getChest() + "\n" +
                    "Waist: " + selected.getWaist() + "\n" +
                    "Hip: " + selected.getHip() + "\n" +
                    "Inseam: " + selected.getInseam() + "\n" +
                    "Comment: " + selected.getComment();

            //start showDetailActivity

            Intent intent = new Intent(this, showDetailActivity.class);
            intent.putExtra(EXTRA_DETAIL, detail);
            startActivity(intent);
        }
    }


    /**
     * Edit record.
     *
     * @param view the view
     */
    public void editRecord(View view){
        if (selected == null) {
            Context context = getApplicationContext();

            CharSequence text = "Please select a record first.";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        } else {
            name = selected.getName();
            neck = selected.getNeck();
            bust = selected.getBust();
            chest = selected.getChest();
            waist = selected.getWaist();
            hip = selected.getHip();
            date = selected.getDate();
            inseam = selected.getInseam();
            comment = selected.getComment();

            //start editRecordActivity

            Intent intent = new Intent(this, editRecordActivity.class);
            intent.putExtra(EXTRA_NAME, name);
            intent.putExtra(EXTRA_DATE, date);
            intent.putExtra(EXTRA_NECK, neck);
            intent.putExtra(EXTRA_BUST, bust);
            intent.putExtra(EXTRA_CHEST, chest);
            intent.putExtra(EXTRA_WAIST, waist);
            intent.putExtra(EXTRA_HIP, hip);
            intent.putExtra(EXTRA_INSEAM, inseam);
            intent.putExtra(EXTRA_COMMENT, comment);
            index = ""+recordList.indexOf(selected);

            //  pass the index to editRecordActivity for further edit

            intent.putExtra(EXTRA_INDEX, index);
            startActivity(intent);
            finish();

        }

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
