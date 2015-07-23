package com.example.administrador.teste.controller;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrador.teste.model.entities.Client;
import com.example.administrador.teste.R;

public class ClientListActivity extends AppCompatActivity {

    private ListView listViewClients;
    private Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        bindClientList();
    }

    private void bindClientList() {
        // CHAMA A LISTA DO ADAPTER.
        listViewClients = (ListView) findViewById(R.id.listViewClients);
        ClientListAdapter clientsAdapter = new ClientListAdapter(ClientListActivity.this, Client.getAll());
        listViewClients.setAdapter(clientsAdapter);

        listViewClients.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                client = (Client) parent.getItemAtPosition(position);

                return false;
            }
        });

        registerForContextMenu(listViewClients);
    }

    @Override
    protected void onResume() {     // ATUALIZAR A LISTA  DE CLIENTES.
        super.onResume();
        refreshClientList();
    }

    // METODO PARA ATUALIZAR A LISTA.
    private void refreshClientList() {
        ClientListAdapter adapter = (ClientListAdapter) listViewClients.getAdapter();
        adapter.setClients(Client.getAll());
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {     // MENU SIMPLES.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {       // AÇÃO DO MENU => CADASTRAR.
        if (item.getItemId() == R.id.menu_add) {
            Intent goToInsertClient = new Intent(ClientListActivity.this, ClientPersistentActivity.class);
            startActivity(goToInsertClient);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_client_list_context, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menuEdit){
            Intent intent = new Intent(ClientListActivity.this, ClientPersistentActivity.class);
            intent.putExtra(ClientPersistentActivity.CLIENT_PARAM, (Parcelable) client);
            startActivity(intent);
        }else if(item.getItemId() == R.id.menuDelete){
            client.delete();
            refreshClientList();
            Toast.makeText(ClientListActivity.this, R.string.success, Toast.LENGTH_LONG).show();
        }

        return super.onContextItemSelected(item);
    }
}
