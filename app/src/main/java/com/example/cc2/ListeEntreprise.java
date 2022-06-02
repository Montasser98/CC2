package com.example.cc2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;

public class ListeEntreprise extends AppCompatActivity {
    MyDatabase db;
    ListView lst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_entreprise);
        db = new MyDatabase(this);
        lst = findViewById(R.id.lst);

        ArrayList<Entreprise> ent = MyDatabase.getAllEntreprise(db.getReadableDatabase());

        ArrayList<String> nomsEntr = new ArrayList<>();
        for(Entreprise pp: ent)
            nomsEntr.add(pp.getID() + ""
                    );
        ArrayList<String> nomsEntrr = new ArrayList<>();
        for(Entreprise pp: ent)
            nomsEntr.add(
                    " " + pp.getCapitale());

        String[] from = {"Raison Sociale" +"Capital"};
        int[] to ={R.id.t1,R.id.t2};

        SimpleAdapter ade = new SimpleAdapter(this,R.layout.activity_main2,from,to);
        lst.setAdapter(ade);
    }
}