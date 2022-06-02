package com.example.cc2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

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

        ArrayList<HashMap<String,Object>> list_etds = new ArrayList<>();

        for(Entreprise e:ent){
            HashMap<String, Object> item = new HashMap<>();
            item.put("Raison Sociale",e.getRaisonSociale());


                item.put("Capital",e.getCapitale());
            list_etds.add(item);
        }
        ArrayList<String> nomsEntr = new ArrayList<>();


        String[] from = {"Raison Sociale" +"Capital"};
        int[] to ={R.id.Res,R.id.Cap};

        SimpleAdapter ade = new SimpleAdapter(this,list_etds,R.layout.activity_main2,from,to);
        lst.setAdapter(ade);

    }
}