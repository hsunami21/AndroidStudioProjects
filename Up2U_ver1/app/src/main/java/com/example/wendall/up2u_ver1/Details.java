package com.example.wendall.up2u_ver1;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Details extends AppCompatActivity {

    //map coordinates
    static final LatLng CINEPLEX = new LatLng(43.77605,-79.25778);
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView tv = (TextView)findViewById(R.id.textView2);
        Intent i = getIntent();
        String name = LocalData.getInstance().getInfoatID(i.getIntExtra("id", 0)).ActivityName;
        tv.setText(LocalData.getInstance().getInfoatID(i.getIntExtra("id", 0)).ActivityName);
        TextView tv2 = (TextView)findViewById(R.id.textView4);
        tv2.setText(LocalData.getInstance().getInfoatID(i.getIntExtra("id",0)).ActivityAddress);

        TextView tv3 = (TextView)findViewById(R.id.textView7);
        tv3.setText("$" + String.valueOf(LocalData.getInstance().getInfoatID(i.getIntExtra("id",0)).ActivityPrice));
        Toast.makeText(Details.this, name,
                Toast.LENGTH_SHORT).show();

        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        //map.setMapType((GoogleMap.MAP_TYPE_SATELLITE));
        Marker Cineplex = map.addMarker(new MarkerOptions().position(CINEPLEX).title("Cineplex"));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(CINEPLEX, 15));
        map.animateCamera(CameraUpdateFactory.zoomTo(15), 5000, null);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details, menu);

        // Associate searchable.xml configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent searchIntent = new Intent(Details.this, SearchResults.class);
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
                Intent homeIntent = new Intent(Details.this, Home.class);
                startActivity(homeIntent);
                return false;
            }
        });

        return true;
    }
}
