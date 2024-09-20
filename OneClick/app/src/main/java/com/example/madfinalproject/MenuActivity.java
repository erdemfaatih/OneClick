package com.example.madfinalproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Menu> menuArrayList;
    MenuAdapter menuAdapter;
    FirebaseFirestore db;
    Button location;
    Button reservation;
    String reference;
    TextView restaurantName;
    String a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        recyclerView = findViewById(R.id.menusRecyclerView);
        Intent intent = getIntent();
        reference = intent.getStringExtra("reference");
        restaurantName = findViewById(R.id.txtRestaurantNames);
        a= intent.getStringExtra("name");
        restaurantName.setText(intent.getStringExtra("name"));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        db =FirebaseFirestore.getInstance();
        menuArrayList = new ArrayList<Menu>();
        menuAdapter = new MenuAdapter(MenuActivity.this,menuArrayList);
        recyclerView.setAdapter(menuAdapter);
        EventChangeListener();
        location = findViewById(R.id.btnLocation);
        reservation = findViewById(R.id.btnReservation);
        reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, Reservation.class);
                intent.putExtra("reference",reference);
                intent.putExtra("restaurantName",a);
                startActivity(intent);
            }
        });
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, RestaurantLocations.class);
                startActivity(intent);
            }
        });
    }
    private void EventChangeListener() {
        db.collection("Restaurants").
                document(reference).collection("Menus")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        for (DocumentChange dc :value.getDocumentChanges()){
                            if(dc.getType() == DocumentChange.Type.ADDED){
                                menuArrayList.add(dc.getDocument().toObject(Menu.class));
                            }
                            menuAdapter.notifyDataSetChanged();
                        }}
                });
    }


}