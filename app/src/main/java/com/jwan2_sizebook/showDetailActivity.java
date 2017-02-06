package com.jwan2_sizebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


/**
 * The type Show detail activity.
 */
public class showDetailActivity extends AppCompatActivity {
    private TextView detail_record;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView detail_record = (TextView)findViewById(R.id.detail_record);
        Intent intent = getIntent();
        String detail = intent.getStringExtra(HomeScreenActivity.EXTRA_DETAIL);
        detail_record.setText(detail);

    }
}
