package com.example.ccv;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Motor extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();;
    EditText nombre, serie, area, estatus, temp, corriente, horas, revoluciones, encendido;
    String nom;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motor);
        Intent intent = getIntent();
        nom = intent.getStringExtra("nombre");
        nombre = (EditText)findViewById(R.id.etnombre);
        encendido = (EditText)findViewById(R.id.etencendido);
        serie = (EditText)findViewById(R.id.etserie);
        area = (EditText)findViewById(R.id.etarea);
        estatus = (EditText)findViewById(R.id.etestatus);
        temp = (EditText)findViewById(R.id.ettemp);
        corriente = (EditText)findViewById(R.id.etcorriente);
        horas = (EditText)findViewById(R.id.ethoras);
        revoluciones = (EditText)findViewById(R.id.etrevmax);
        btn = (Button)findViewById(R.id.btnencender);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference motor = db.collection("Area").document("1").collection("motores").document(nom);
                if(btn.getText() == "Encender"){
                    btn.setText("Apagar");
                    motor
                            .update("encendido", 1)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                }
                            });
                }else{
                    btn.setText("Encender");
                    motor
                            .update("encendido", 0)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                }
                            });
                }
            }
        });

        DocumentReference docRef = db.collection("Area").document("1").collection("motores").document(nom);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {

            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    nombre.setText(document.getData().get("nombre").toString());
                    encendido.setText(document.getData().get("encendido").toString());
                    serie.setText(document.getData().get("serie").toString());
                    area.setText(document.getData().get("area").toString());
                    estatus.setText(document.getData().get("estatus").toString());
                    temp.setText(document.getData().get("temperatura").toString());
                    corriente.setText(document.getData().get("corriente").toString());
                    horas.setText(document.getData().get("horas en funcionamiento").toString());
                    revoluciones.setText(document.getData().get("revoluciones").toString());
                    if(encendido.getText().toString() == "0"){
                        btn.setText("Encender");
                    }else{
                        btn.setText("Apagar");
                    }
                } else {

                }
            }
        });
    }
}
