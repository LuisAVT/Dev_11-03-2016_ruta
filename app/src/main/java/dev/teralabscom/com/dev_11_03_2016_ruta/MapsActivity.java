package dev.teralabscom.com.dev_11_03_2016_ruta;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AutoCompleteTextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.loopj.android.http.AsyncHttpClient;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback,
                                                    NavigationView.OnNavigationItemSelectedListener {
    private GoogleMap mMap;
    private GPSTracker gps;
    private UiSettings mUiSettings;

    LocalizationsServices location = new LocalizationsServices();
    AsyncHttpClient myClient = new AsyncHttpClient();

    private AutoCompleteTextView from;

    private double latitude;
    private double longitude;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    private static final LatLng PBMTY1333 = new LatLng(25.73637314,  -100.3925772);
    private static final LatLng PCMTY1334  = new LatLng(25.73507417, -100.3906368);
    private static final LatLng PSMTY1335 = new LatLng(25.73427757, -100.3885763);
    private static final LatLng PSMTY1336 = new LatLng(25.73506971, -100.3874768);
    private static final LatLng PCMTY1337 = new LatLng(25.73628743, -100.3856738);
    private static final LatLng PSMTY1338 = new LatLng(25.73566575, -100.3849789);
    private static final LatLng PSMTY0824 = new LatLng(25.7352947, -100.3845871);
    private static final LatLng PSMTY0825 = new LatLng(25.73365377, -100.3829025);
    private static final LatLng PCMTY0826 = new LatLng(25.73292558, -100.3821128);
    private static final LatLng PSMTY0038 = new LatLng(25.73223098, -100.3808482);
    private static final LatLng PSMTY0039 = new LatLng(25.73309586, -100.379377);
    private static final LatLng PSMTY0040 = new LatLng(25.73473982, -100.3778059);
    private static final LatLng PSMTY0184 = new LatLng(25.73398542, -100.3766555);
    private static final LatLng PBMTY0185 = new LatLng(25.73327642 , -100.3757373);
    private static final LatLng PBMTY0186 = new LatLng(25.73249593, -100.3746759);
    private static final LatLng PBMTY0187 = new LatLng(25.72864506, -100.371485);
    private static final LatLng PBMTY0188 = new LatLng(25.7267192, -100.3699942);
    private static final LatLng PBMTY0189 = new LatLng(25.72426072, -100.3685727);
    private static final LatLng PCMTY0190 = new LatLng(25.72291029, -100.3677939);
    private static final LatLng PCMTY0191 = new LatLng(25.72064496, -100.3664989);
    private static final LatLng PCMTY0192 = new LatLng(25.71878439, -100.365451);
    private static final LatLng PCMTY0193 = new LatLng(25.71652897, -100.3642369);
    private static final LatLng PCMTY0194 = new LatLng(25.71294532, -100.3623418);
    private static final LatLng PBMTY0195 = new LatLng(25.71092075, -100.3605763);
    private static final LatLng PBMTY0196 = new LatLng(25.70904277, -100.3580612);
    private static final LatLng PBMTY0966 = new LatLng(25.70075728, -100.3050754);
    private static final LatLng PBMTY0967 = new LatLng(25.66659544, -100.3090048);
    private static final LatLng PBMTY0968 = new LatLng(25.66843344, -100.3086761);
    private static final LatLng PBMTY0969 = new LatLng(25.67061381, -100.3082111);
    private static final LatLng PBMTY0971 = new LatLng(25.67377348, -100.3075438);

    static LatLng HAMBURG = new LatLng(53.558, 9.927);
    static LatLng KIEL = new LatLng(53.551, 9.993);

    Marker hamburg, kiel;
    Polyline line;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent howToGo = new Intent(MapsActivity.this, HowToGoActivity.class);
                startActivity(howToGo);
                finish();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fab.setFocusable(false);
        fab.setFocusableInTouchMode(false);
    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        String result;
        ProgressDialog pdia;

        @Override
        protected String doInBackground(String... params) {
            publishProgress("Loading..."); // Calls onProgressUpdate()
            MapsActivity.HAMBURG = new LatLng(3.190393, 101.651317);
            MapsActivity.KIEL = new LatLng(3.148443,101.577099);

            return result;
        }

        @Override
        protected void onPostExecute(String result) {

            pdia.dismiss();
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            //hamburg.setPosition(HAMBURG);
            //kiel.setPosition(KIEL);
            //line.setPoints(Arrays.asList(new LatLng[]{HAMBURG, KIEL}));
            pdia = ProgressDialog.show(MapsActivity.this, "Please wait", "Loading data from database.", true, false);
        }

        @Override
        protected void onProgressUpdate(String... text) {

        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {

            case R.id.action_favorite:
                Intent intent = new Intent(this, ShowRoute.class);
                startActivity(intent);
                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camara) {

            Intent RMEActivity = new Intent(MapsActivity.this,RMEActivity.class);
            startActivity(RMEActivity);

            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

            Intent Report = new Intent(MapsActivity.this, Report.class);
            startActivity(Report);

        } else if (id == R.id.nav_slideshow) {

            Intent HelpActivity = new Intent(MapsActivity.this, HelpActivity.class);
            startActivity(HelpActivity);

        } else if (id == R.id.nav_manage) {

            Intent ConfigurationActivity = new Intent(MapsActivity.this, ConfigurationActivity.class);
            startActivity(ConfigurationActivity);

        } else if (id == R.id.nav_share) {

            Intent ShareActivity = new Intent(MapsActivity.this, ShareActivity.class);
            startActivity(ShareActivity);

        } else if (id == R.id.nav_cali) {

            Intent QualifyActivity = new Intent(MapsActivity.this, QualifyActivity.class);
            startActivity(QualifyActivity);

        } else if (id == R.id.nav_confi) {

            Intent SettingsActivity = new Intent(MapsActivity.this, SettingsActivity.class);
            startActivity(SettingsActivity);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Manipulates the map once available.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // create class object
        gps = new GPSTracker(MapsActivity.this);

        // check if GPS enabled
        if (gps.canGetLocation()) {

            latitude = gps.getLatitude();
            longitude = gps.getLongitude();

        } else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }

        AsyncTaskRunner runner = new AsyncTaskRunner();
        runner.execute();

        //LatLng myLocation = new LatLng(25.70904277, -100.3580612);
        //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation, mMap.getMaxZoomLevel() - 5));

        LatLng myLocation = new LatLng(latitude, longitude);
        CameraPosition camera = CameraPosition.builder().target(myLocation)
                .zoom(mMap.getMaxZoomLevel() - 3)
                .bearing(0)
                .tilt(0)
                .build();

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(camera), 4000, null);


        mMap.setPadding(0, 160, 0, 90);
        mMap.setBuildingsEnabled(true);

        mUiSettings=mMap.getUiSettings();
        mUiSettings.setCompassEnabled(true);
        mUiSettings.setTiltGesturesEnabled(true);
        mUiSettings.setMapToolbarEnabled(false);
        mUiSettings.setMyLocationButtonEnabled(true);
        mMap.setMyLocationEnabled(true);

        mUiSettings.setZoomControlsEnabled(true);
        //mUiSettings.setZoomControlsEnabled(true);
        addMarkers();
        //mMap.addMarker(new MarkerOptions().position(myLocation).title("My Actual Location").icon(BitmapDescriptorFactory.fromResource(R.drawable.cast_ic_notification_0)));

        final View mapView = getSupportFragmentManager().findFragmentById(R.id.map).getView();
        if (mapView.getViewTreeObserver().isAlive()) {
            mapView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @SuppressWarnings("deprecation") // We use the new method when supported
                @SuppressLint("NewApi") // We check which build version we are using.
                @Override
                public void onGlobalLayout() {
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                        mapView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    } else {
                        mapView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                    //showAustralia(null);
                }
            });
        }

        mUiSettings.isCompassEnabled();
        mUiSettings.isMapToolbarEnabled();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Maps Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://dev.teralabscom.com.dev_11_03_2016_ruta/http/host/path")
        );

        from = (AutoCompleteTextView) findViewById(R.id.from);
        //to = (AutoCompleteTextView) findViewById(R.id.to);

        from.setText("Búsqueda...");
        //to.setText("The Moscone Center, Howard Street, San Francisco, CA, United States");

        from.setAdapter(new PlacesAutoCompleteAdapter(this, android.R.layout.simple_dropdown_item_1line));
        //to.setAdapter(new PlacesAutoCompleteAdapter(this, android.R.layout.simple_dropdown_item_1line));

        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Maps Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://dev.teralabscom.com.dev_11_03_2016_ruta/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    /**
     * Add Markers with default info windows to the map.
     **/
    private void addMarkers() {

        /*RequestParams requestParams = new RequestParams();
        requestParams.put("nickname", mEmail);
        try {
            //Simulate network access.
            //Thread.sleep(2000);
            LocalizationsServices services = new LocalizationsServices();
            services.postLogin(mEmail);
        } catch (JSONException jsonEx) {
            //} catch (InterruptedException e, JSONException jsonEx) {
            return false;
        }*/

        /*TextView output = (TextView) findViewById(R.id.textView1);
        String strJson="
        {
            \"Employee\" :[
            {
                \"id\":\"01\",
                \"name\":\"Gopal Varma\",
                \"salary\":\"500000\"
            },
            {
                \"id\":\"02\",
                \"name\":\"Sairamkrishna\",
                \"salary\":\"500000\"
            },
            {
                \"id\":\"03\",
                \"name\":\"Sathish kallakuri\",
                \"salary\":\"600000\"
            }
            ]
        }";*/
        /*String data = "";
        try {
            JSONObject  jsonRootObject = new JSONObject(strJson);

            //Get the instance of JSONArray that contains JSONObjects
            JSONArray jsonArray = jsonRootObject.optJSONArray("Employee");

            //Iterate the jsonArray and print the info of JSONObjects
            for(int i=0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                int id = Integer.parseInt(jsonObject.optString("id").toString());
                String name = jsonObject.optString("name").toString();
                float salary = Float.parseFloat(jsonObject.optString("salary").toString());

                data += "Node"+i+" : \n id= "+ id +" \n Name= "+ name +" \n Salary= "+ salary +" \n ";
            }
            output.setText(data);
        } catch (JSONException e) {e.printStackTrace();}*/


        mMap.addMarker(new MarkerOptions()
                .position(PBMTY1333)
                .title("Hda. Del Refugio Pte-Ote & Hda. Santa Martha")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop)));

        mMap.addMarker(new MarkerOptions()
                .position(PCMTY1334)
                .title("Hda. Del Refugio Pte-Ote & Hda. San Nicolás")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop)));

        mMap.addMarker(new MarkerOptions()
                .position(PSMTY1335)
                .title("Hda. Del Refugio Pte-Ote & Hda. Del Molino")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop)));

        mMap.addMarker(new MarkerOptions()
                .position(PSMTY1336)
                .title("Hda. El Molino Sur-Nte & Hda. Blanca")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop)));

        mMap.addMarker(new MarkerOptions()
                .position(PCMTY1337)
                .title("Hda. El Molino Sur-Nte & Hda. Los Pinos")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop)));

        mMap.addMarker(new MarkerOptions()
                .position(PSMTY1338)
                .title("Hda. Los Pinos Pte-Ote & Seguridad Social")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop)));

        mMap.addMarker(new MarkerOptions()
                .position(PSMTY0824)
                .title("Comisión Tripartita Pte-Ote & Seguridad Social")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop)));

        mMap.addMarker(new MarkerOptions()
                .position(PSMTY0825)
                .title("Comisión Tripartita Pte-Ote & Titanio")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop)));

        mMap.addMarker(new MarkerOptions()
                .position(PCMTY0826)
                .title("1  De Mayo  Sur-Nte & Comisión Tripartita")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop)));

        mMap.addMarker(new MarkerOptions()
                .position(PSMTY0038)
                .title("1  De Mayo  Sur-Nte & Comisión Tripartita")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop)));

        mMap.addMarker(new MarkerOptions()
                .position(PSMTY0039)
                .title("1  De Mayo  Sur-Nte & Ave. De La Unidad")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop)));

        mMap.addMarker(new MarkerOptions()
                .position(PSMTY0040)
                .title("1  De Mayo  Sur-Nte & Ruiz Cortines(40 Mt. Antes)")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop)));

        mMap.addMarker(new MarkerOptions()
                .position(PSMTY0184)
                .title("A. Ruiz Cortines Pte-Ote & Tórtola(Fte)")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop)));

        mMap.addMarker(new MarkerOptions()
                .position(PBMTY0185)
                .title("A. Ruiz Cortines Pte-Ote & Ave. De La Unidad / Gaviota")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop)));
        mMap.addMarker(new MarkerOptions()
                .position(PBMTY0186)
                .title("A. Ruiz Cortines Pte-Ote & Nogal")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop)));

        mMap.addMarker(new MarkerOptions()
                .position(PBMTY0187)
                .title("A. Ruiz Cortines Pte-Ote & Sabino")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop)));

        mMap.addMarker(new MarkerOptions()
                .position(PBMTY0188)
                .title("A. Ruiz Cortines Pte-Ote & Framboyanes")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop)));

        mMap.addMarker(new MarkerOptions()
                .position(PBMTY0189)
                .title("A. Ruiz Cortines Pte-Ote & León XIII / Sauce")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop)));

        mMap.addMarker(new MarkerOptions()
                .position(PCMTY0190)
                .title("A. Ruiz Cortines Pte-Ote & San Bernabé / Ceiba")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop)));

        mMap.addMarker(new MarkerOptions()
                .position(PCMTY0191)
                .title("A. Ruiz Cortines Pte-Ote & J.J. Hinojosa")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop)));

        mMap.addMarker(new MarkerOptions()
                .position(PCMTY0192)
                .title("A. Ruiz Cortines Pte-Ote & San José")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop)));

        mMap.addMarker(new MarkerOptions()
                .position(PCMTY0193)
                .title("A. Ruiz Cortines Pte-Ote & Gregorio Morales/José Santos")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop)));

        mMap.addMarker(new MarkerOptions()
                .position(PCMTY0194)
                .title("A. Ruiz Cortines Pte-Ote & Rangel Frías")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop)));

        mMap.addMarker(new MarkerOptions()
                .position(PBMTY0195)
                .title("A. Ruiz Cortines Pte-Ote & Gobernadores")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop)));

        mMap.addMarker(new MarkerOptions()
                .position(PBMTY0196)
                .title("A. Ruiz Cortines Pte-Ote & Nueva Inglaterra")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop)));

        mMap.addMarker(new MarkerOptions()
                .position(PBMTY0966)
                .title("Dr. Coss Sur-Nte & Begonia")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop)));

        mMap.addMarker(new MarkerOptions()
                .position(PBMTY0967)
                .title("Dr. Coss Sur-Nte & Morelos")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop)));

        mMap.addMarker(new MarkerOptions()
                .position(PBMTY0968)
                .title("Dr. Coss Sur-Nte & Matamoros")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop)));

        mMap.addMarker(new MarkerOptions()
                .position(PBMTY0969)
                .title("Dr. Coss Sur-Nte & Juan Ignacio Ramón")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop)));

        mMap.addMarker(new MarkerOptions()
                .position(PBMTY0971)
                .title("Dr. Coss Sur-Nte & Washington")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop)));
    }

    /**
     * Move the camera to show all of Australia.
     * Construct a {@link com.google.android.gms.maps.model.LatLngBounds} from markers positions,
     * then move the camera.
     */
    /*public void showRoute(View v) {
        // Wait until map is ready
        if (mMap == null) {
            return;
        }

        // Create bounds that include all locations of the map
        LatLngBounds.Builder boundsBuilder = LatLngBounds.builder()
                .include(PERTH)
                .include(ADELAIDE)
                .include(MELBOURNE)
                .include(SYDNEY)
                .include(DARWIN)
                .include(BRISBANE);

        // Move camera to show all markers and locations
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(boundsBuilder.build(), 20));
    }*/
}
