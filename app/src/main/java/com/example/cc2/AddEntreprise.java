package com.example.cc2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddEntreprise extends AppCompatActivity {

    EditText t1, t2, t3;
    MyDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entreprise);
        db = new MyDatabase(this);

        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        t3 = findViewById(R.id.t3);
    }

    public void enregistre(View view) {
        Entreprise p = new Entreprise();

        p.setRaisonSociale(t1.getText().toString());
        p.setAdresse(t2.getText().toString());
        p.setCapitale(Double.parseDouble(t3.getText().toString()));

        if(MyDatabase.insert_entreprise(db.getWritableDatabase(),p)==-1)
            Toast.makeText(this, "Insertion echoue", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Insertion reussie", Toast.LENGTH_SHORT).show();

    }

    public void Annuler(View view) {
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }


}