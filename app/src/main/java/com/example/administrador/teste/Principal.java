package com.example.administrador.teste;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        ArrayList<String> clientsNames = new ArrayList<>();

        clientsNames.add("Testildo");
        clientsNames.add("Testario");
        clientsNames.add("Testaria");
        clientsNames.add("Testin");

        ListView listViewClients = (ListView) findViewById(R.id.listViewClients);

        ArrayAdapter clientsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                clientsNames.toArray(new String[]{}));

        listViewClients.setAdapter(clientsAdapter);



    }



}
