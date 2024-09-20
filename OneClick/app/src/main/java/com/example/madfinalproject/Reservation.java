package com.example.madfinalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Reservation extends AppCompatActivity {
    String reference;
    EditText date;
    EditText time;
    Button reservationButton;
    FirebaseFirestore db;
    TextView name;
    String resName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        Intent intent = getIntent();
        reference = intent.getStringExtra("reference");
        name = findViewById(R.id.txtReservationRestaurantName);
        resName=intent.getStringExtra("restaurantName");
        name.setText(resName);
        db = FirebaseFirestore.getInstance();
        DocumentReference documentReference = db.collection("Restaurants").document(reference);
        date = findViewById(R.id.txtDate);
        time = findViewById(R.id.txtTime);
        reservationButton = findViewById(R.id.btnSaveReservation);
        reservationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> userReservation = new HashMap<>();
                userReservation.put("Date",date.getText().toString());
                userReservation.put("Time",time.getText().toString());
                documentReference.collection("Reservations")
                        .add(userReservation).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(Reservation.this,"Reservation Added!",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Reservation.this,"Reservation Failed!",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}