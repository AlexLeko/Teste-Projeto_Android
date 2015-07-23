package com.example.administrador.teste.controller;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrador.teste.R;
import com.example.administrador.teste.model.entities.Client;
import com.example.administrador.teste.util.FormHelper;


/**
 * Created by Administrador on 21/07/2015.
 */
public class ClientPersistentActivity extends AppCompatActivity{

    public static String CLIENT_PARAM = "CLIENT_PARAM";
    private Client client;


    private EditText editTextName;
    private EditText editTextAge;
    private EditText editTextPhone;
    private EditText editTextAddress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        editTextName = (EditText) findViewById(R.id.editTextename);
        editTextAge = (EditText) findViewById(R.id.editTextAge);
        editTextAddress = (EditText) findViewById(R.id.editTextAddress);
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);

        // pega o client que foi serializado no EDIT.
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            client = (Client) extras.getParcelable(CLIENT_PARAM);
            if(client == null){
                throw new IllegalArgumentException();
            }
            bindForm(client);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_client_persist, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // VALIDACAO DO FORMULARIO.
        if(item.getItemId() == R.id.menu_save){
            Client client = bindClient();

            if(FormHelper.requiredValidate(ClientPersistentActivity.this, editTextName, editTextAge, editTextAddress, editTextPhone)){
                client.save();
                Toast.makeText(ClientPersistentActivity.this, R.string.success, Toast.LENGTH_LONG).show();
                finish();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private Client bindClient(){                // RECUPERA O CLIENTE DA TELA.
        Client client = new Client();
        client.setName(editTextName.getText().toString());
        client.setAge(Integer.valueOf(editTextAge.getText().toString()) );
        client.setAddress(editTextAddress.getText().toString());
        client.setPhone(editTextPhone.getText().toString());
        return client;
    }

    private void bindForm(Client client){                // RECUPERA O CLIENTE PARA EDITAR.
        editTextName.setText(client.getName());
        editTextAge.setText(client.getAge().toString());
        editTextPhone.setText(client.getPhone());
        editTextAddress.setText(client.getAddress());
    }



}
