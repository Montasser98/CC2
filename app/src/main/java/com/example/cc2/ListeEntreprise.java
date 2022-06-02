package com.example.cc2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
            nomsEntr.add(pp.getID() + "" +
                    " " + pp.getCapitale());

        ArrayAdapter ad = new ArrayAdapter(this,android.R.layout.simple_list_item_1,nomsEntr);

        lst.setAdapter(ad);
    }
}