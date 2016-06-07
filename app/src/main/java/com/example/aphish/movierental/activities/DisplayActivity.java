package com.example.aphish.movierental.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aphish.movierental.R;
import com.example.aphish.movierental.domain.Movie;
import com.example.aphish.movierental.repository.factories.MoviesRepository;
import com.example.aphish.movierental.repository.factories.impl.MoviesRepositoryImpl;

/**
 * Created by Aphish on 2016/06/07.
 */
public class DisplayActivity extends AppCompatActivity {
   MoviesRepositoryImpl moviesRepository;
    Movie movie;

    TextView firstView;
    TextView secondView;
    TextView thirdView;
    Button submit;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        String entry1 = getIntent().getStringExtra("Entry1");
        String entry2 = getIntent().getStringExtra("Entry2");
        String entry3 = getIntent().getStringExtra("Entry3");

        firstView = (TextView) findViewById(R.id.TextField1);
        secondView = (TextView) findViewById(R.id.TextField2);
        thirdView = (TextView) findViewById(R.id.TextField3);
        submit = (Button) findViewById(R.id.bView);

        firstView.setText(entry1);
        secondView.setText(entry2);
        thirdView.setText(entry3);

       moviesRepository = new MoviesRepositoryImpl(this);


    }

    public void onButtonClick(View view){
        if (view.getId() == R.id.bSubmit) {
            Intent i = new Intent(DisplayActivity.this, DataBaseViewActivity.class);
            startActivity(i);
        }
    }


}
