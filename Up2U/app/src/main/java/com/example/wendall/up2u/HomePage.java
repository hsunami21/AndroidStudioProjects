package com.example.wendall.up2u;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by drkdagron on 2015-11-12.
 */
public class HomePage  extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void PreferencesClicked(View view)
    {
        Intent intent = new Intent(this, Preferences.class);
        startActivity(intent);
    }

    public void ListCatagoryClicked(View view)
    {
        Intent intent = new Intent(this, Categories.class);
        startActivity(intent);
    }

}
