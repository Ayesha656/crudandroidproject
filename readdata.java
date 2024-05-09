package com.example.crudbasicproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class readdata extends AppCompatActivity {

    MyAdapter myadapter;
    FirebaseFirestore db;
    ArrayList<Products> arrayList;
    ProgressDialog progressDialog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readdata);
       RecyclerView recyclerView = findViewById(R.id.recyclerview);


        progressDialog= new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data... ");
        progressDialog.show();



        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        db=FirebaseFirestore.getInstance();
        arrayList= new ArrayList<Products>();
        myadapter= new MyAdapter(readdata.this,arrayList);
        recyclerView.setAdapter(myadapter);
        EventChangeListner();


    }

    private void EventChangeListner() {


        db.collection("products_db").orderBy("productname", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error !=null){
                            if(progressDialog.isShowing())
                                progressDialog.dismiss();
                            Log.e("Firestore Error",error.getMessage());
                            return;
                        }
                        for (DocumentChange dc: value.getDocumentChanges()){
                            if(dc.getType()==DocumentChange.Type.ADDED){
                                arrayList.add(dc.getDocument().toObject(Products.class));
                            }
                            myadapter.notifyDataSetChanged();
                            if(progressDialog.isShowing())
                                progressDialog.dismiss();

                        }

                    }
                });
    }
}