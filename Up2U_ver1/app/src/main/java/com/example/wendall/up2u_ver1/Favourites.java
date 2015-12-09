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
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.SearchView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Favourites extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    SharedPreferences pref;
    SharedPreferences.Editor prefEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        pref = PreferenceManager.getDefaultSharedPreferences(this);
        prefEdit = PreferenceManager.getDefaultSharedPreferences(this).edit();

        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        Log.d("INTENT: ", intent.toString());

            //use the query to search your data somehow

            // get the listview
            expListView = (ExpandableListView) findViewById(R.id.listFav);

            // preparing list data
            listDataHeader = new ArrayList<String>();
            listDataChild = new HashMap<String, List<String>>();

            listDataHeader.add("Favourites");
            List<String> list = new ArrayList<String>();
            for(int i =0; i < 100; i++)
            {
                Log.d("FAVOURITECHECK: " + i, String.valueOf(pref.getBoolean("fav"+i, false)));
                if (pref.getBoolean("fav"+i, false))
                {
                    list.add(LocalData.getInstance().getInfoatID(i).ActivityName);
                }
            }

            listDataChild.put(listDataHeader.get(0), list);

            listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

            expListView.setAdapter(listAdapter);
            expListView.expandGroup(0);
            expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                    Intent detailsIntent = new Intent(Favourites.this, DetailPage.class);
                    detailsIntent.putExtra("id", LocalData.getInstance().GetIDFromName(listAdapter.getChild(groupPosition, childPosition).toString()));
                    detailsIntent.putExtra("btnName", "Remove from Favourites");
                    startActivity(detailsIntent);
                    return false;
                }
            });
    }

    @Override
    public void onResume()
    {
        handleIntent(getIntent());
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_categories, menu);

        // Associate searchable.xml configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent searchIntent = new Intent(Favourites.this, SearchResults.class);
                startActivity(searchIntent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        MenuItem home = menu.findItem(R.id.home);
        home.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent homeIntent = new Intent(Favourites.this, Home.class);
                startActivity(homeIntent);
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
