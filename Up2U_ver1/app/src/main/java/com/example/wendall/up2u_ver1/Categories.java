package com.example.wendall.up2u_ver1;

import android.app.ExpandableListActivity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

//map imports
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Categories extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

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

                Toast.makeText(Categories.this, "Clicked On Child " + childPosition,
                        Toast.LENGTH_SHORT).show();
                //first choices of each categories
                Intent bankIntent = new Intent(Categories.this, BankActivity.class);
                Intent detailsIntent = new Intent(Categories.this, Details.class);
                Intent foodAndDrinkIntent = new Intent(Categories.this, FoodAndDrinkActivity.class);
                Intent parksAndRecIntent = new Intent(Categories.this, ParksAndRecActivity.class);
                //listAdapter
                switch (groupPosition) {
                    case 0:
                        switch (childPosition) {
                            case 0:

                                startActivity(bankIntent);
                                break;
                            case 1:
                                startActivity(bankIntent);
                                break;
                            case 2:
                                startActivity(bankIntent);
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
                    case 2:
                        switch (childPosition){
                            case 0:
                                startActivity(foodAndDrinkIntent);
                                break;
                        }
                        break;
                    case 3:
                        switch (childPosition){
                            case 0:
                                startActivity(parksAndRecIntent);
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
        // ADD FOOD AND DRINKS
        List<RestaurantInfo> food = new ArrayList<RestaurantInfo>();
        for (int i = 0; i < 100; i+=4) {
            food.add(LocalData.getInstance().getInfoatID(i));
        }

        Log.d("Entertainment List", food.toString());

        List<String> foodNames = new ArrayList<>();
        for (int i = 0; i < food.size(); i++) {
            foodNames.add(food.get(i).ActivityName);
        }

        Log.d("Entertainment Names", foodNames.toString());

        // ADD BANKS
        List<RestaurantInfo> banks = new ArrayList<RestaurantInfo>();
        for (int i = 1; i < 100; i+=4) {
            banks.add(LocalData.getInstance().getInfoatID(i));
        }

        Log.d("Bank List", banks.toString());

        List<String> bankNames = new ArrayList<>();
        for (int i = 0; i < banks.size(); i++) {
            bankNames.add(banks.get(i).ActivityName);
        }

        Log.d("Bank Names", bankNames.toString());

        // ADD PARKS AND RECREATION
        List<RestaurantInfo> recreation = new ArrayList<RestaurantInfo>();
        for (int i = 2; i < 100; i+=4) {
            recreation.add(LocalData.getInstance().getInfoatID(i));
        }

        Log.d("Entertainment List", recreation.toString());

        List<String> recreationNames = new ArrayList<>();
        for (int i = 0; i < recreation.size(); i++) {
            recreationNames.add(recreation.get(i).ActivityName);
        }

        Log.d("Entertainment Names", recreationNames.toString());

        // ADD ENTERTAINMENT
        List<RestaurantInfo> entertainment = new ArrayList<RestaurantInfo>();
        for (int i = 3; i < 100; i+=4) {
            entertainment.add(LocalData.getInstance().getInfoatID(i));
        }

        Log.d("Entertainment List", entertainment.toString());

        List<String> entertainmentNames = new ArrayList<>();
        for (int i = 0; i < entertainment.size(); i++) {
            entertainmentNames.add(entertainment.get(i).ActivityName);
        }

        Log.d("Entertainment Names", entertainmentNames.toString());

//        List<String> banks = new ArrayList<String>();
//        banks.add("Bank of Nova Scotia");
//        banks.add("TD Canada Trust");
//        banks.add("Scotiabank");
//
//        List<String> entertainment = new ArrayList<String>();
//        entertainment.add("Cineplex");
//        entertainment.add("The Keg");
//        entertainment.add("Scarborough Music Theatre Inc.");
//        entertainment.add("Scarborough Town Centre");
//
//        List<String> food = new ArrayList<String>();
//        food.add("Hakka Legend");
//        food.add("McDonald's");
//        food.add("Subway");
//        food.add("Super Buffer");
//        food.add("The Keg");
//        food.add("Tim Hortons");
//        food.add("Popeyes");
//
//        List<String> recreation = new ArrayList<String>();
//        recreation.add("Centennial Recreation Centre - Scarborough");
//        recreation.add("Woburn Park");
//        recreation.add("Wynn Fitness Clubs");
//        recreation.add("YMCA of Greater Toronto");

        listDataChild.put(listDataHeader.get(0), bankNames); // Header, Child data
        listDataChild.put(listDataHeader.get(1), entertainmentNames);
        listDataChild.put(listDataHeader.get(2), foodNames);
        listDataChild.put(listDataHeader.get(3), recreationNames);
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
                Intent searchIntent = new Intent(Categories.this, SearchResults.class);
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
                Intent homeIntent = new Intent(Categories.this, Home.class);
                startActivity(homeIntent);
                return false;
            }
        });

        return true;
    }
}
