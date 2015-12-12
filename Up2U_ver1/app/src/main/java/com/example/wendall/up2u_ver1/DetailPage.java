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
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class DetailPage extends AppCompatActivity {

    //map coordinates
    static final LatLng CINEPLEX = new LatLng(43.77605,-79.25778);
    private GoogleMap map;

    SharedPreferences pref;
    SharedPreferences.Editor prefEdit;
    RestaurantInfo id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        pref = PreferenceManager.getDefaultSharedPreferences(DetailPage.this);
        prefEdit = PreferenceManager.getDefaultSharedPreferences(DetailPage.this).edit();
        Intent i = getIntent();
        Log.d("IDCHECK: ", String.valueOf(i.getIntExtra("id", -1)));
        boolean exists = pref.getBoolean("fav" + String.valueOf(LocalData.getInstance().getInfoatID(i.getIntExtra("id", -1))), false);
        id = LocalData.getInstance().getInfoatID(i.getIntExtra("id", -1));

        TextView tv = (TextView)findViewById(R.id.textView2);

        final String name = id.ActivityName;
        tv.setText(id.ActivityName);
        TextView tv2 = (TextView)findViewById(R.id.textView4);
        tv2.setText(id.ActivityAddress);

        TextView tv3 = (TextView)findViewById(R.id.textView7);
        tv3.setText("$" + String.valueOf(id.ActivityPrice));

        Button btnFav = (Button)findViewById(R.id.favBtn);
        btnFav.setText(i.getStringExtra("btnName"));

        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("FAVOURITE", "Click");
                if (!pref.getBoolean("fav"+id.ID, false))
                {
                    prefEdit.putBoolean("fav"+id.ID, true);
                    prefEdit.apply();

                    Button btnFav = (Button)findViewById(R.id.favBtn);
                    if (pref.getBoolean("fav"+id.ID, false))
                        btnFav.setText("Remove from Favourites");
                    else
                        btnFav.setText("Add to Favourites");
                }
                else
                {
                    prefEdit.putBoolean("fav"+id.ID, false);
                    prefEdit.apply();

                    Button btnFav = (Button)findViewById(R.id.favBtn);
                    if (pref.getBoolean("fav"+id.ID, false))
                        btnFav.setText("Remove from Favourites");
                    else
                        btnFav.setText("Add to Favourites");
                }

            }
        });

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
                Intent searchIntent = new Intent(DetailPage.this, SearchResults.class);
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
                Intent homeIntent = new Intent(DetailPage.this, Home.class);
                startActivity(homeIntent);
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                Intent homeIntent = new Intent(DetailPage.this, Home.class);
                startActivity(homeIntent);
                return true;
            case R.id.action_favourites:
                Intent favouritesIntent = new Intent(DetailPage.this, Favourites.class);
                startActivity(favouritesIntent);
                return true;
            case R.id.action_categories:
                Intent categoriesIntent = new Intent(DetailPage.this, Categories.class);
                startActivity(categoriesIntent);
                return true;
            case R.id.action_preferences:
                Intent preferenceIntent = new Intent(DetailPage.this, Preferences.class);
                startActivity(preferenceIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
