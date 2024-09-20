package com.example.madfinalproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class RestaurantActivity extends AppCompatActivity implements IRestaurantInterface{
    RecyclerView recyclerView;
    ArrayList<Restaurant> restaurantArrayList;
    RestaurantAdapter restaurantAdapter;
    FirebaseFirestore db;
    ArrayList<String> references;
    ArrayList<String> restaurantNames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        references = new ArrayList<>();
        restaurantNames = new ArrayList<>();
        recyclerView = findViewById(R.id.userRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        db =FirebaseFirestore.getInstance();
        restaurantArrayList = new ArrayList<Restaurant>();
        restaurantAdapter = new RestaurantAdapter(RestaurantActivity.this, restaurantArrayList,this);
        EventChangeListener();
        recyclerView.setAdapter(restaurantAdapter);
    }
    private void EventChangeListener() {
        db.collection("Restaurants").orderBy("distance")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        for (DocumentChange dc :value.getDocumentChanges()){
                            if(dc.getType() == DocumentChange.Type.ADDED){
                                restaurantArrayList.add(dc.getDocument().toObject(Restaurant.class));
                                references.add(dc.getDocument().getReference().getId());
                                restaurantNames.add(dc.getDocument().get("name").toString());
                            }
                            restaurantAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    @Override
    public void onItemClicked(int position) {
        Intent intent = new Intent(RestaurantActivity.this, MenuActivity.class);
        String reference = references.get(position);
        String name = restaurantNames.get(position);
        intent.putExtra("reference",reference);
        intent.putExtra("name",name);
        startActivity(intent);
    }
}