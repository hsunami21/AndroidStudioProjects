package com.example.wendall.up2u;

import android.app.ExpandableListActivity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Categories extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
   // final Intent detailsIntent = new Intent(this, Details.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.list);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                Toast.makeText(Categories.this, "Clicked On Child" + childPosition,
                        Toast.LENGTH_SHORT).show();
                Intent detailsIntent = new Intent(Categories.this, Details.class);
                //listAdapter
                switch (groupPosition) {
                    case 0:
                        switch (childPosition) {
                            case 0:
                                startActivity(detailsIntent);
                                break;
                            case 1:
                                startActivity(detailsIntent);
                                break;
                            case 2:
                                startActivity(detailsIntent);
                                break;
                        }
                        break;
                    case 1:
                        switch (childPosition) {
                            case 0:
                                startActivity(detailsIntent);
                                break;
                            case 1:
                                startActivity(detailsIntent);
                                break;
                            case 2:
                                startActivity(detailsIntent);
                                break;
                            case 3:
                                startActivity(detailsIntent);
                                break;
                        }
                        break;
                }


                return false;
            }
        });

    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Banks");
        listDataHeader.add("Entertainment");
        listDataHeader.add("Food and Drink");
        listDataHeader.add("Parks and Recreation");

        // Adding child data
        List<String> banks = new ArrayList<String>();
        banks.add("Bank of Nova Scotia");
        banks.add("TD Canada Trust");
        banks.add("Scotiabank");

        List<String> entertainment = new ArrayList<String>();
        entertainment.add("Cineplex");
        entertainment.add("The Keg");
        entertainment.add("Scarborough Music Theatre Inc.");
        entertainment.add("Scarborough Town Centre");

        List<String> food = new ArrayList<String>();
        food.add("Hakka Legend");
        food.add("McDonald's");
        food.add("Subway");
        food.add("Super Buffer");
        food.add("The Keg");
        food.add("Tim Hortons");
        food.add("Popeyes");

        List<String> recreation = new ArrayList<String>();
        recreation.add("Centennial Recreation Centre - Scarborough");
        recreation.add("Woburn Park");
        recreation.add("Wynn Fitness Clubs");
        recreation.add("YMCA of Greater Toronto");

        listDataChild.put(listDataHeader.get(0), banks); // Header, Child data
        listDataChild.put(listDataHeader.get(1), entertainment);
        listDataChild.put(listDataHeader.get(2), food);
        listDataChild.put(listDataHeader.get(3), recreation);
    }

    // GENERIC LIST
        /*************************************************************
        final ListView lv = (ListView) findViewById(R.id.list);


        // Defined Array values to show in ListView
        String[] values = new String[] {
                "Food",
                "Parks and Recreation",
                "Entertainment"
        };

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        // Assign adapter to ListView
        lv.setAdapter(adapter);

        // ListView Item Click Listener
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) lv.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(), "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_SHORT).show();

            }

        });

         **********************************************************/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_categories, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent searchIntent = new Intent(Categories.this, SearchResults.class);
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
