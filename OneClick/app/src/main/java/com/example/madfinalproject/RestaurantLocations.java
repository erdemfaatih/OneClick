package com.example.madfinalproject;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.madfinalproject.databinding.ActivityRestaurantLocationsBinding;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

public class RestaurantLocations extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityRestaurantLocationsBinding binding;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRestaurantLocationsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng userLatlng = new LatLng(37.161496313430575,28.37597874644133);
        db = FirebaseFirestore.getInstance();
        db.collection("Restaurant Locations")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        for (DocumentChange dc :value.getDocumentChanges()){
                            if(dc.getType() == DocumentChange.Type.ADDED){
                                double latitute=dc.getDocument().getDouble("Latitude");
                                double longitude=dc.getDocument().getDouble("Longitude");
                                LatLng restaurantLatlng = new LatLng(latitute,longitude);
                                String name = dc.getDocument().getString("name");
                                mMap.addMarker(new MarkerOptions().position(restaurantLatlng).title(name));
                            }
                        }
                    }
                });
        mMap.addMarker(new MarkerOptions().position(userLatlng).title("You are here!"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLatlng,16));
    }
}