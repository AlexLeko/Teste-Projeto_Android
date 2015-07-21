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
import java.util.List;

public class Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        List<Client> clientsNames = getClients();

        ListView listViewClients = (ListView) findViewById(R.id.listViewClients);

        ClientListAdapter clientsAdapter = new ClientListAdapter(Principal.this, clientsNames );

        listViewClients.setAdapter(clientsAdapter);
    }

    private List<Client> getClients() {
        List<Client> clients = new ArrayList<>();

        Client renam = new Client();
        renam.setName("Renam");
        renam.setAge(22);

        Client luiz = new Client();
        luiz.setName("Luiz");
        luiz.setAge(22);

        clients.add(renam);
        clients.add(luiz);

        return clients;
    }


}
