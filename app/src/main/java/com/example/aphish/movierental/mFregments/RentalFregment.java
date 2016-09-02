package com.example.aphish.movierental.mFregments;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aphish.movierental.R;

/**
 * Created by Aphish on 2016/09/01.
 */
public class RentalFregment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // force connection to open
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        View rootView = inflater.inflate(R.layout.rental_fragment,container,false);
        return rootView;
    }
}
