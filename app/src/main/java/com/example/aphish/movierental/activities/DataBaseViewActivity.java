package com.example.aphish.movierental.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.aphish.movierental.R;

/**
 * Created by Aphish on 2016/06/07.
 */
public class DataBaseViewActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database_view_activity);
    }

    public void onButtonClick(View v){
        if (v.getId() == R.id.bSubmit){
            Intent i = new Intent(DataBaseViewActivity.this, LoginActivity.class);
            startActivity(i);
        }
    }
}
