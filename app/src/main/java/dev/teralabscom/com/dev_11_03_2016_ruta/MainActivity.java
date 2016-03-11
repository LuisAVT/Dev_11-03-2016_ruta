package dev.teralabscom.com.dev_11_03_2016_ruta;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity  {

    private boolean IsLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        Log.d("Prefers", String.valueOf(prefs));
        IsLogin = prefs.getBoolean("IsLogin", false); // get value of last login status

        if(!IsLogin) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            new GpsService();

            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
            finish();

            /*try {
                location.postLocalization(ID, latitude, longitude);
                location.getMyLocalization(ID);
            } catch(JSONException e){
                System.out.println(e);
            }*/

            /*RequestParams params = new RequestParams();
            params.put("idUser", 2);
            params.put("latitude", latitude);
            Log.d("Latitude", String.valueOf(latitude));
            params.put("longitude", longitude);
            Log.d("Longitude", String.valueOf(longitude));*/

            /*AsyncHttpClient client = new AsyncHttpClient();
            client.post(this, "http://preview.16aew3023jv8ia4iehm3aou7twigrpb9t32see9prpo2bj4i.box.codeanywhere.com/WebServiceRutaTiempo/postLocation.php", params, new AsyncHttpResponseHandler() {
                @Override
                public void onStart() {
                    // called before request is started
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                    // called when response HTTP status is "200 OK"
                    String Temp = String.valueOf(statusCode);

                    String responseData = String.valueOf(response);

                    Log.d("StatusCode", Temp);
                    Log.d("Header", String.valueOf(headers));
                    Log.d("Response", responseData);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                    // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                }

                @Override
                public void onRetry(int retryNo) {
                    // called when request is retried
                }
            });*/
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
}
