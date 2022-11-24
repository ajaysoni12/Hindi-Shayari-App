package com.devdroid.hindishayariapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.devdroid.hindishayariapp.Adapter.AllShayariAdapter;
import com.devdroid.hindishayariapp.Model.ShayariModal;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AllShayariActivity extends AppCompatActivity {

    TextView txtCategoryName;
    RecyclerView rcvAllShayari;
    FirebaseFirestore firebaseFirestore;
    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_shayari);

        firebaseFirestore = FirebaseFirestore.getInstance();
//
        txtCategoryName = findViewById(R.id.txtCategoryName);
        btnBack = findViewById(R.id.btnBack);
        rcvAllShayari = findViewById(R.id.rcvAllShayari);
//
        String id = getIntent().getStringExtra("id");
        String name = getIntent().getStringExtra("name");
        txtCategoryName.setText(name);
//
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
//
//
        firebaseFirestore.collection("Shayari").document(id).collection("all").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                ArrayList<ShayariModal> shayariList = new ArrayList<>();
                if (value != null) {
                    List<ShayariModal> list = value.toObjects(ShayariModal.class);
                    shayariList.addAll(list);
                    rcvAllShayari.setLayoutManager(new LinearLayoutManager(AllShayariActivity.this));
                    rcvAllShayari.setAdapter(new AllShayariAdapter(AllShayariActivity.this, shayariList));
                } else {
                    Toast.makeText(AllShayariActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}