package com.example.wendall.up2u_ver1;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.SearchView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchResults extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    SharedPreferences pref;
    SharedPreferences.Editor prefEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        pref = PreferenceManager.getDefaultSharedPreferences(this);
        prefEdit = PreferenceManager.getDefaultSharedPreferences(this).edit();

        Switch s = (Switch)findViewById(R.id.listToggle);
        s.setChecked(pref.getBoolean("sr_switch", false));
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    Log.d("SWITCH:", String.valueOf(b));
                    prefEdit.putBoolean("sr_switch", true);
                } else if (b == false) {
                    Log.d("SWITCH:", String.valueOf(b));
                    prefEdit.putBoolean("sr_switch", false);
                }
                prefEdit.apply();

                handleIntent(getIntent());
            }
        });

        handleIntent(getIntent());


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d(null, "In on Key Down");
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent homeIntent = new Intent(SearchResults.this, Home.class);
            startActivity(homeIntent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Toast.makeText(getApplicationContext(), query , Toast.LENGTH_SHORT).show();
            //use the query to search your data somehow

            // get the listview
            expListView = (ExpandableListView) findViewById(R.id.list);

            // preparing list data
            listDataHeader = new ArrayList<String>();
            listDataChild = new HashMap<String, List<String>>();

            listDataHeader.add("Results");
            List<String> list;
            if (!pref.getBoolean("sr_switch", false)) {
                list = LocalData.getInstance().Search(query);
            }
            else {
                list = LocalData.getInstance().Search(query, pref.getString("city", null));
            }

            listDataChild.put(listDataHeader.get(0), list);

            listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

            expListView.setAdapter(listAdapter);
            expListView.expandGroup(0);
            expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                    Intent detailsIntent = new Intent(SearchResults.this, DetailPage.class);
                    detailsIntent.putExtra("id", LocalData.getInstance().GetIDFromName(listAdapter.getChild(groupPosition, childPosition).toString()));
                    detailsIntent.putExtra("btnName", "Add to Favourites");
                    startActivity(detailsIntent);
                    return false;
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);

        // Associate searchable.xml configuration with the SearchView

        MenuItem home = menu.findItem(R.id.home);
        home.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Log.d("SCREENTEST: ", "Going Home");
                Intent homeIntent = new Intent(SearchResults.this, Home.class);
                startActivity(homeIntent);
                return false;
            }
        });

        return true;
    }


}
