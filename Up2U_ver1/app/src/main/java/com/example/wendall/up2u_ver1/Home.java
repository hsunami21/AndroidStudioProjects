package com.example.wendall.up2u_ver1;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    ImageView imgCategory, imgPreference, imgFavourites;
    TextView txtCategory, txtPreference, txtFavourites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences p = PreferenceManager.getDefaultSharedPreferences(this);
        if (p.getString("city",null) == null)
        {
            SharedPreferences.Editor pe = PreferenceManager.getDefaultSharedPreferences(this).edit();
            pe.putString("city", "Toronto");
            pe.putBoolean("switch", false);
            pe.apply();
        }

        System.out.println(LocalData.getInstance().getInfo().size());
        Log.e("BUILDINGDATA", String.valueOf(LocalData.getInstance().getInfo().size()));
        setContentView(R.layout.activity_home);

        // get the button view
        imgCategory = (ImageView) findViewById(R.id.ivCategory);
        // set a onclick listener for when the button gets clicked
        imgCategory.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {
                Intent categoryIntent = new Intent(Home.this, Categories.class);
                startActivity(categoryIntent);
            }
        });

        // get the button view
        txtCategory = (TextView) findViewById(R.id.tvCategory);
        // set a onclick listener for when the button gets clicked
        txtCategory.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {
                Intent categoryIntent = new Intent(Home.this, Categories.class);
                startActivity(categoryIntent);
            }
        });

        // get the button view
        imgPreference = (ImageView) findViewById(R.id.ivPreference);
        // set a onclick listener for when the button gets clicked
        imgPreference.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {
                Intent preferenceIntent = new Intent(Home.this, Preferences.class);
                startActivity(preferenceIntent);
            }
        });

        // get the button view
        txtPreference = (TextView) findViewById(R.id.tvPreference);
        // set a onclick listener for when the button gets clicked
        txtPreference.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {
                Intent preferenceIntent = new Intent(Home.this, Preferences.class);
                startActivity(preferenceIntent);
            }
        });

        // get the button view
        imgFavourites = (ImageView) findViewById(R.id.ivFavourites);
        // set a onclick listener for when the button gets clicked
        imgFavourites.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {
                Intent favouritesIntent = new Intent(Home.this, Favourites.class);
                startActivity(favouritesIntent);
            }
        });

        // get the button view
        txtFavourites = (TextView) findViewById(R.id.tvFavourites);
        // set a onclick listener for when the button gets clicked
        txtFavourites.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {
                Intent favouritesIntent = new Intent(Home.this, Favourites.class);
                startActivity(favouritesIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);

        // Associate searchable.xml configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("SCREENCALL", "GOING TO SEARCH RESULT");
                Intent searchIntent = new Intent(Home.this, SearchResults.class);
                startActivity(searchIntent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                // write your code here
                return true;
            case R.id.action_favourites:
                Intent favouritesIntent = new Intent(Home.this, Favourites.class);
                startActivity(favouritesIntent);
                return true;
            case R.id.action_categories:
                Intent categoryIntent = new Intent(Home.this, Categories.class);
                startActivity(categoryIntent);
                return true;
            case R.id.action_preferences:
                Intent preferenceIntent = new Intent(Home.this, Preferences.class);
                startActivity(preferenceIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
