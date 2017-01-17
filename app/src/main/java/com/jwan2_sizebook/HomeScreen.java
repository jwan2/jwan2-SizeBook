package com.jwan2_sizebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
    }

    /* Called when the user click the add button */
    public void addRecord(View view){
        /* This gonna jump to Activity about adding record*/
        Intent intent = new Intent(this, addRecordActivity.class);
        startActivity(intent);
    }
}
