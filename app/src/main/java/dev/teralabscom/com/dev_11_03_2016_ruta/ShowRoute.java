package dev.teralabscom.com.dev_11_03_2016_ruta;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ShowRoute extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private GPSTracker gps;
    private UiSettings mUiSettings;

    private double latitude;
    private double longitude;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_route);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Ruta A35");
        getSupportActionBar().setSubtitle(Html.fromHtml("<font color='#FFFFFF'>Hacienda Mitras - Est. Mitras</font>"));


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // create class object
        gps = new GPSTracker(this);

        // check if GPS enabled
        if (gps.canGetLocation()) {

            latitude = gps.getLatitude();
            longitude = gps.getLongitude();

            // \n is for new line
            //Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        } else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }

        LatLng myLocation = new LatLng(latitude, longitude);
        CameraPosition camera = CameraPosition.builder().target(myLocation)
                .zoom(mMap.getMaxZoomLevel() - 3)
                .bearing(0)
                .tilt(0)
                .build();

        //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation, mMap.getMaxZoomLevel() - 4));
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(camera), 5000, null);
        //mMap.moveCamera(CameraUpdateFactory.newCameraPosition(camera));

        mMap.setMyLocationEnabled(true);
        mMap.setBuildingsEnabled(true);

        mUiSettings=mMap.getUiSettings();
        mUiSettings.setCompassEnabled(true);
        mUiSettings.setTiltGesturesEnabled(true);
        mUiSettings.setMapToolbarEnabled(false);
        mUiSettings.setMyLocationButtonEnabled(true);
        mUiSettings.setZoomControlsEnabled(true);


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.show_route, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.route_report:
                Intent intent = new Intent(this, Report.class);
                startActivity(intent);
                break;

            case R.id.route_info:
                Intent intent2 = new Intent(this, InfoUnit.class);
                startActivity(intent2);
                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
