package com.example.wendall.up2u_ver1;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchResults extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);



        handleIntent(getIntent());

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

            // Adding child data
            if (query.equalsIgnoreCase("hakka legend")) {
                listDataHeader.add("Food and Drink");

                List<String> food = new ArrayList<String>();
                food.add("Hakka Legend");

                listDataChild.put(listDataHeader.get(0), food); // Header, Child data

                listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

                // setting list adapter
                expListView.setAdapter(listAdapter);

                expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                        Toast.makeText(SearchResults.this, "Clicked On Child " + childPosition,
                                Toast.LENGTH_SHORT).show();
                        //first choices of each categories
                        Intent detailsIntent = new Intent(SearchResults.this, FoodAndDrinkActivity.class);

                        switch (groupPosition) {
                            case 0:
                                switch (childPosition) {
                                    case 0:
                                        startActivity(detailsIntent);
                                        break;
                                }
                                break;
                        }
                        return false;
                    }
                });
            } else if(query.equalsIgnoreCase("food and entertainment")) {
                listDataHeader.add("Entertainment");
                listDataHeader.add("Food and Drink");

                List<String> entertainment = new ArrayList<String>();
                entertainment.add("Cineplex");
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

                listDataChild.put(listDataHeader.get(0), entertainment);
                listDataChild.put(listDataHeader.get(1), food);

                listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

                // setting list adapter
                expListView.setAdapter(listAdapter);

                expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                        Toast.makeText(SearchResults.this, "Clicked On Child " + childPosition,
                                Toast.LENGTH_SHORT).show();
                        //first choices of each categories
                        Intent detailsIntent = new Intent(SearchResults.this, Details.class);
                        Intent foodAndDrinkIntent = new Intent(SearchResults.this, FoodAndDrinkActivity.class);


                        switch (groupPosition) {
                            case 0:
                                switch (childPosition) {
                                    case 0:
                                        startActivity(detailsIntent);
                                        break;
                                }
                                break;
                            case 1:
                                switch (childPosition) {
                                    case 0:
                                        startActivity(foodAndDrinkIntent);
                                        break;
                                }
                                break;
                        }
                        return false;
                    }
                });
            }

        }
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
                Intent searchIntent = new Intent(SearchResults.this, SearchResults.class);
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
                Intent homeIntent = new Intent(SearchResults.this, Home.class);
                startActivity(homeIntent);
                return false;
            }
        });

        return true;
    }


}
