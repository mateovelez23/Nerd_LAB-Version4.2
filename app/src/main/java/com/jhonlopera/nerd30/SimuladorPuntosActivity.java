package com.jhonlopera.nerd30;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SimuladorPuntosActivity extends AppCompatActivity {

    Button bpuntos4im, bpuntoscon, bpuntostopo;
    TextView tpuntos4im, tpuntoscon, tpuntostopo;
    int cont1, cont2, cont3;
    SharedPreferences preferencias;
    String correoR, ident;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulador_puntos);

        bpuntos4im = (Button) findViewById(R.id.bpuntos4im);
        bpuntoscon = (Button) findViewById(R.id.bpuntoscon);
        bpuntostopo = (Button) findViewById(R.id.bpuntostopo);
        tpuntos4im = (TextView) findViewById(R.id.tpuntos4im);
        tpuntoscon = (TextView) findViewById(R.id.tpuntoscon);
        tpuntostopo = (TextView) findViewById(R.id.tpuntostopo);

        preferencias=getSharedPreferences("Preferencias", Context.MODE_PRIVATE);

    }

    public void onClick(View view) {
        int id = view.getId();
        correoR = preferencias.getString("correo",correoR);
        ident = correoR.replace(".","");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef;

        switch (id){
            case R.id.bpuntos4im:
                cont1+=3;
                tpuntos4im.setText(String.valueOf(cont1));
                myRef = database.getReference("Puntajes4im").child(ident);
                Puntajes4im puntajes4im = new Puntajes4im(String.valueOf(cont1));
                myRef.setValue(puntajes4im);
                break;

            case R.id.bpuntoscon:
                cont2+=5;
                tpuntoscon.setText(String.valueOf(cont2));
                myRef = database.getReference("PuntajesCon").child(ident);
                PuntajesCon puntajesCon = new PuntajesCon(String.valueOf(cont2));
                myRef.setValue(puntajesCon);
                break;

            case R.id.bpuntostopo:
                cont3+=7;
                tpuntostopo.setText(String.valueOf(cont3));
                myRef = database.getReference("PuntajesTopo").child(ident);
                PuntajesTopo puntajesTopo = new PuntajesTopo(String.valueOf(cont3));
                myRef.setValue(puntajesTopo);
                break;
        }

    }
}
