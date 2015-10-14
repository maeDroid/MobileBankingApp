package mae.comp3717.bcit.ca.bankingapp;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class LocatorActivity extends AppCompatActivity {

    private GoogleMap googleMap;      // Might be null if Google Play services APK is not available.
    private Marker current;
    private EditText edittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locator);
        setUpMapIfNeeded();

        Button btn_find = (Button) findViewById(R.id.btn_find);
        edittext = (EditText) findViewById(R.id.et_location);

        edittext.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                edittext.setHint("");
                return false;
            }

        });

        edittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    edittext.setHint("Enter Location Here");
                } else {
                    edittext.setHint("");
                }
            }
        });

        // Defining button click event listener for the find button
        OnClickListener findClickListener = new OnClickListener() {
            @Override
            public void onClick(View view) {
                // Getting reference to EditText to get the user input location
                EditText etLocation = (EditText) findViewById(R.id.et_location);

                // Getting user input location
                String location = etLocation.getText().toString();

                if(location != null && !location.equals("")){
                    new GeocoderTask().execute(location);
                }
            }
        };

        // Setting button click event listener for the find button
        btn_find.setOnClickListener(findClickListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem sign_in    = menu.findItem(R.id.action_sign_in);
        MenuItem sign_out   = menu.findItem(R.id.action_sign_out);
        MenuItem messages   = menu.findItem(R.id.action_messages);
        MenuItem summary    = menu.findItem(R.id.action_acct_summary);
        MenuItem locator    = menu.findItem(R.id.action_locate_branch);

        locator.setVisible(false);

        if (!LoginActivity.loggedIn) {
            sign_out.setVisible(false);
            sign_in.setVisible(true);
            messages.setVisible(false);
            summary.setVisible(false);
        } else {
            sign_out.setVisible(true);
            sign_in.setVisible(false);
            messages.setVisible(true);
            summary.setVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Intent intent;
        int id = item.getItemId();

        if (id == R.id.action_locate_branch) {
            intent = new Intent(this, LocatorActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_acct_summary && LoginActivity.loggedIn) {
            intent = new Intent(this, TabbedActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_messages && LoginActivity.loggedIn) {
            intent = new Intent(this, MessageListActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_sign_in && !LoginActivity.loggedIn) {
            intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_sign_out && LoginActivity.loggedIn) {
            LoginActivity.loggedIn = false;
            Toast.makeText(this, "Signing out", Toast.LENGTH_SHORT).show();

            intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_settings) {
            intent = new Intent(this, SettingsActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }

        /*
        if (id == R.id.action_about) {
            intent = new Intent(this, AboutActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_news) {
            intent = new Intent(this, NewsActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_rates) {
            intent = new Intent(this, RatesActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }
        */

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the googleMap if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the googleMap has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #googleMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the googleMap.
        if (googleMap == null) {

            // Try to obtain the googleMap from the SupportMapFragment.
            SupportMapFragment supportMapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map);
            googleMap = supportMapFragment.getMap();

            // Check if we were successful in obtaining the googleMap.
            if (googleMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera.
     *
     * This should only be called once and when we are sure that {@link #googleMap} is not null.
     */

    private void setUpMap() {

        googleMap.setMyLocationEnabled(true); // false to disable
        Location myLocation = googleMap.getMyLocation();
        LatLng myLatLng;
        double latitude;
        double longitude;

        if (myLocation != null) {
            latitude        = myLocation.getLatitude();
            longitude       = myLocation.getLongitude();
            myLatLng        = new LatLng(latitude, longitude);

            current = googleMap.addMarker(new MarkerOptions().position(myLatLng).title("My Location").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLatLng, 12));

        } else {

            latitude        = 49.2485025;
            longitude       = -123.0035637;
            myLatLng        = new LatLng(latitude, longitude);

            current = googleMap.addMarker(new MarkerOptions().position(myLatLng).title("BCIT").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLatLng, 12));

            Toast.makeText(this, "Unable to fetch your current location.  Using BCIT.", Toast.LENGTH_LONG).show();
        }

        LatLng branch01     = new LatLng(49.7567837,-123.3152764);    // Squamish
        LatLng branch02     = new LatLng(50.1116860,-122.9778456);    // Whistler
        LatLng branch03     = new LatLng(50.3201487,-122.8090666);    // Pemberton
        LatLng branch04     = new LatLng(49.4587945,-123.2373526);    // Lion's Bay
        LatLng branch05     = new LatLng(49.3263156,-123.1405869);    // Park Royal, West Vancouver
        LatLng branch06     = new LatLng(49.3104116,-123.0833089);    // Lonsdale Quay, North Vancouver
        LatLng branch07     = new LatLng(49.2844278,-123.1232871);    // Georgia & Burrard, Vancouver
        LatLng branch08     = new LatLng(49.2630625,-123.1035107);    // Main & Broadway, Vancouver
        LatLng branch09     = new LatLng(49.2294875,-123.0040657);    // Metrotown, Burnaby
        String hours        = "Mon to Fri: 9:00 A.M. - 5:00 P.M. Sat & Sun: CLOSED";

        googleMap.addMarker(new MarkerOptions().position(branch01).title("Squamish Branch").snippet(hours));
        googleMap.addMarker(new MarkerOptions().position(branch02).title("Whistler Branch").snippet(hours));
        googleMap.addMarker(new MarkerOptions().position(branch03).title("Pemberton Branch").snippet(hours));
        googleMap.addMarker(new MarkerOptions().position(branch04).title("Lion's Bay Branch").snippet(hours));
        googleMap.addMarker(new MarkerOptions().position(branch05).title("Park Royal Branch").snippet(hours));
        googleMap.addMarker(new MarkerOptions().position(branch06).title("Lonsdale Quay Branch").snippet(hours));
        googleMap.addMarker(new MarkerOptions().position(branch07).title("Downtown Vancouver Branch").snippet(hours));
        googleMap.addMarker(new MarkerOptions().position(branch08).title("Vancouver Center Branch").snippet(hours));
        googleMap.addMarker(new MarkerOptions().position(branch09).title("Metrotown Branch").snippet(hours));
    }


    // An AsyncTask class for accessing the GeoCoding Web Service
    private class GeocoderTask extends AsyncTask<String, Void, List<Address>> {

        @Override
        protected List<Address> doInBackground(String... locationName) {
            // Creating an instance of Geocoder class
            Geocoder geocoder = new Geocoder(getBaseContext());
            List<Address> addresses = null;

            try {
                // Getting a maximum of 3 Address that matches the input text
                addresses = geocoder.getFromLocationName(locationName[0], 3);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return addresses;
        }

        @Override
        protected void onPostExecute(List<Address> addresses) {

            if(addresses==null || addresses.size()==0){
                Toast.makeText(getBaseContext(), "No Location found", Toast.LENGTH_LONG).show();
            }

            // Clears the user's current location on the googleMap
            current.remove();

            // Adding Markers on Google Map for each matching address
            for(int i = 0; i < addresses.size(); i++){

                Address address = (Address) addresses.get(i);

                // Creating an instance of GeoPoint, to display in Google Map
                LatLng latLng   = new LatLng(address.getLatitude(), address.getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();

                String addressText = String.format("%s, %s",
                        address.getMaxAddressLineIndex() > 0 ? address.getAddressLine(0) : "",
                        address.getCountryName());

                markerOptions.position(latLng);
                markerOptions.title(addressText);
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));

                googleMap.addMarker(markerOptions);

                // Locate the first location
                if(i == 0)
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));
            }
        }
    }

}
