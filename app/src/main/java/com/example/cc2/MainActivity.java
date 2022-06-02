package com.example.cc2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void acces(View view) {
        Intent i = null;
        switch (view.getId()){
            case R.id.b1: i=new Intent(MainActivity.this, AddEntreprise.class); break;
            case R.id.b2: i=new Intent(MainActivity.this, EditerEntreprise.class); break;
            case R.id.b3: i=new Intent(MainActivity.this, ListeEntreprise.class); break;

        }
        startActivity(i);
    }
}