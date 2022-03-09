package com.example.ccv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Anadir extends AppCompatActivity {

    EditText nombre, serie, area, estatus, temp, corriente, horas, revoluciones;
    Button btn;
    FirebaseFirestore db = FirebaseFirestore.getInstance();;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir);
        nombre = (EditText)findViewById(R.id.etnombre);
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
                registrar();
            }
        });
    }

    public void registrar(){
        Map<String, Object> usuario = new HashMap<>();
        usuario.put("nombre", nombre.getText().toString());
        usuario.put("serie", serie.getText().toString());
        usuario.put("encendido", 0);
        usuario.put("area", area.getText().toString());
        usuario.put("estatus", estatus.getText().toString());
        usuario.put("temperatura", temp.getText().toString());
        usuario.put("corriente", corriente.getText().toString());
        usuario.put("horas en funcionamiento", horas.getText().toString());
        usuario.put("revoluciones", revoluciones.getText().toString());
        db.collection("Area").document(area.getText().toString()).collection("motores").document(nombre.getText().toString()).set(usuario);
    }
}
