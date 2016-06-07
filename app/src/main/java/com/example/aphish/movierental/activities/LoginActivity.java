package com.example.aphish.movierental.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.aphish.movierental.R;

/**
 * Created by Aphish on 2016/06/07.
 */
public class LoginActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_movie_rental);
    }

    public void onButtonClick(View v) {
        if(v.getId() == R.id.bSubmit){
            Intent i = new Intent(LoginActivity.this, ViewActivity.class);
            startActivity(i);
        }

    }
}



