package com.example.aphish.movierental.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.aphish.movierental.R;

/**
 * Created by Aphish on 2016/06/07.
 */
public class ViewActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.view_activity);
        String username = getIntent().getStringExtra("Username");

    }

        public void onButtonClick(View v){

        if (v.getId()==R.id.bView){
            EditText editText1 =(EditText) findViewById(R.id.TextField1);
            EditText editText2 =(EditText) findViewById(R.id.TextField2);
            EditText editText3 =(EditText) findViewById(R.id.TextField3);

            String strFirst = editText1.getText().toString();
            String strSecond = editText2.getText().toString();
            String strThird = editText3.getText().toString();

            Intent i = new Intent(ViewActivity.this, DisplayActivity.class);

            i.putExtra("Entry1", strFirst);
            i.putExtra("Entry2", strSecond);
            i.putExtra("Entry3", strThird);

            startActivity(i);


        }
    }
    }

