package com.example.administrador.teste.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.administrador.teste.model.entities.Client;
import com.example.administrador.teste.R;

import java.util.ArrayList;
import java.util.List;

public class Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        ListView listViewClients = (ListView) findViewById(R.id.listViewClients);
        ClientListAdapter clientsAdapter = new ClientListAdapter(Principal.this, Client.getAll());
        listViewClients.setAdapter(clientsAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_add) {
            Intent goToInsertClient = new Intent(Principal.this, InsertActivity.class);
            startActivity(goToInsertClient);
        }
        return super.onOptionsItemSelected(item);
    }


}
