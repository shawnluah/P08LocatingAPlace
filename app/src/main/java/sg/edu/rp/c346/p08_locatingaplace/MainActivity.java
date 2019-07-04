package sg.edu.rp.c346.p08_locatingaplace;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    Button btnNorth, btnCentral, btnEast;
    private GoogleMap map;
    private LatLng poi_North, poi_Central, poi_East;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)fm.findFragmentById(R.id.map);






        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;


                LatLng poi_Singapore = new LatLng(1.352083, 103.819839);


                poi_North = new LatLng(1.424450, 103.829849);
                Marker North = map.addMarker(new MarkerOptions().position(poi_North).title("HQ - North").snippet("Block 333, Admiralty Ave 3, 765654  ").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

                poi_East = new LatLng(1.352480, 103.944610);
                Marker East = map.addMarker(new MarkerOptions().position(poi_East).title("HQ - East").snippet("Block 555, Tampines Ave 3, 287788 ").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

                poi_Central = new LatLng(1.302570, 103.834686);
                Marker Central = map.addMarker(new MarkerOptions().position(poi_Central).title("HQ - Central").snippet("Block 3A, Orchard Ave 3, 134542  ").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Singapore,
                        15));



                UiSettings ui = map.getUiSettings();
                ui.setCompassEnabled(true);
                ui.setZoomControlsEnabled(true);

                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);

                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                }

            }
        });

        btnNorth = findViewById(R.id.btnNorth);
        btnCentral = findViewById(R.id.btnCentral);
        btnEast = findViewById(R.id.btnEast);

        btnNorth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (map != null) {
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_North,
                            15));

                }


            }
        });
        btnCentral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Central,
                        15));


            }
        });

        btnEast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_East,
                        15));

            }
        });
    }
}
