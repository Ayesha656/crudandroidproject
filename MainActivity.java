package com.example.crudbasicproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText p1,p2,p3;
    Button b1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        p1 = findViewById(R.id.produtname);
        p2 = findViewById(R.id.produtprice);
        p3 = findViewById(R.id.productdesc);
        b1 = findViewById(R.id.Upload);



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String p_name = p1.getText().toString();
                String p_price = p2.getText().toString();
                String p_desc = p3.getText().toString();

                FirebaseFirestore db = FirebaseFirestore.getInstance();

                Map<String, Object> product = new HashMap<>();

                product.put("productname",p_name);
                product.put("productprice",p_price);
                product.put("productdescription",p_desc);



                db.collection("products_db").add(product).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(MainActivity.this, "Data Uploaded", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Data failed", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });





    }
}