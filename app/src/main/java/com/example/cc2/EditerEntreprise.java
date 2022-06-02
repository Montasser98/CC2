package com.example.cc2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class EditerEntreprise extends AppCompatActivity {
    Spinner sp;
    EditText t1, t2, t3;
    MyDatabase db;
    ArrayList<Entreprise> prds;
    ArrayAdapter ad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editer_entreprise);

        db = new MyDatabase(this);
        sp= findViewById(R.id.sp1);
        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        t3 = findViewById(R.id.t3);


        prds = MyDatabase.getAllEntreprise(db.getReadableDatabase());

        ArrayList<String> nomsEntr = new ArrayList<>();
        for(Entreprise pp: prds)
            nomsEntr.add(pp.getID() + " _ " + pp.getCapitale());
        ad = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,nomsEntr);
        sp.setAdapter(ad);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Entreprise p = prds.get(i);
                t1.setText(p.getRaisonSociale());
                t2.setText(p.getAdresse());
                t3.setText(String.format("%f",p.getCapitale()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void suprimer(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(EditerEntreprise.this);
        alert.setTitle("Suppression Entrprise");
        alert.setMessage("Voulez-vous supprimer ce Entreprise ?");

        alert.setPositiveButton("Supprimer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Entreprise p = prds.get(sp.getSelectedItemPosition());

                if(MyDatabase.delete_entreprise(db.getWritableDatabase(),p.getID())==-1)
                    Toast.makeText(EditerEntreprise.this, "suppression echoue", Toast.LENGTH_SHORT).show();
                else {
                    Toast.makeText(EditerEntreprise.this, "Suppression reussie", Toast.LENGTH_SHORT).show();
                    ad.remove(p.getID() + " - " + p.getRaisonSociale());
                }
            }
        });

        alert.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(EditerEntreprise.this, "Operation annulee", Toast.LENGTH_SHORT).show();
            }
        });

        alert.show();
    }

    public void mod(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(EditerEntreprise.this);
        alert.setTitle("Modifier Entreprise");
        alert.setMessage("Voulez-vous modifier ce Entreprise ?");


        alert.setPositiveButton("Modifier", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Entreprise p = prds.get(sp.getSelectedItemPosition());

                p.setRaisonSociale(t1.getText().toString());
                p.setAdresse(t2.getText().toString());
                p.setCapitale(Double.valueOf(t3.getText().toString()));

                if(MyDatabase.update_entrprise(db.getWritableDatabase(),p)==-1)
                    Toast.makeText(EditerEntreprise.this, "Modification echoue", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(EditerEntreprise.this, "Modification reussie", Toast.LENGTH_SHORT).show();

            }
        });

        alert.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(EditerEntreprise.this, "Operation annulee", Toast.LENGTH_SHORT).show();
            }
        });

        alert.show();
    }
}