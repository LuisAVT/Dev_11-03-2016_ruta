package dev.teralabscom.com.dev_11_03_2016_ruta;

import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TextView;

public class FavsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favs);

        setTitle("Reportar");
    }


}
