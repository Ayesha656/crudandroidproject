package com.example.crudbasicproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class updatedata extends AppCompatActivity {
    EditText e1, e2, e3, e4;
    Button b1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatedata);

        e1 = findViewById(R.id.docid);
        e2 = findViewById(R.id.p_name);
        e3 = findViewById(R.id.p_price);
        e4 = findViewById(R.id.product_desc);
        b1 = findViewById(R.id.Update);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String doc_id = e1.getText().toString();
                String p_name = e2.getText().toString();
                String p_price = e3.getText().toString();
                String p_desc = e4.getText().toString();

                FirebaseFirestore db = FirebaseFirestore.getInstance();

                Map<String, Object> updates = new HashMap<>();
                updates.put("productname", p_name);
                updates.put("productprice", p_price);
                updates.put("productdescription", p_desc);


                db.collection("products_db")
                        .document(doc_id)
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(updatedata.this, "Data Updated", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(updatedata.this, "Failed to update data", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}
