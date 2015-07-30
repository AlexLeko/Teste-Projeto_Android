package com.example.administrador.teste.controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.location.Address;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrador.teste.R;
import com.example.administrador.teste.model.entities.Client;
import com.example.administrador.teste.model.entities.ClientAddress;
import com.example.administrador.teste.model.services.CepService;
import com.example.administrador.teste.util.FormHelper;

import java.util.ArrayList;
import java.util.List;

public class ClientPersistentActivity extends AppCompatActivity{

    public static String CLIENT_PARAM = "CLIENT_PARAM";
    private Client client;

    private EditText editTextName;
    private EditText editTextAge;
    private EditText editTextPhone;
    private EditText editTextAddress;
    private EditText editTextCep;
    private Button buttonFindCep;
    private EditText editTextStreetType;
    private EditText editTextStreet;
    private EditText editTextNeighborhood;
    private EditText editTextCity;
    private EditText editTextState;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        bindFields();

        // pega o client que foi serializado no EDIT.
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            client = (Client) extras.getParcelable(CLIENT_PARAM);
            if(client == null){
                throw new IllegalArgumentException();
            }
            bindForm();
        }
    }

    private void bindFields(){

        editTextName = (EditText) findViewById(R.id.editTextename);
        editTextName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_edittext_client, 0); // COLOCA O ICONE EM UMA POSIÇÃO ESPECIFICA. ESQ-TOP-DIR-BAIXO
        editTextName.setOnTouchListener(new View.OnTouchListener() {    // EVENTOS DE CLIQUE.
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                // MAPEAMENTO DA IMAGEM PARA TOQUE DE LINK.
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (editTextName.getRight() - editTextName.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        final Intent goToSOContacts = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                        goToSOContacts.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE); // Show user only contacts w/ phone numbers
                        startActivityForResult(goToSOContacts, 999); // 999 = VALOR QUALQUER PARA VERIFICACAO.
                    }
                }
                return false;
            }
        });

        editTextAge = (EditText) findViewById(R.id.editTextAge);
        editTextAddress = (EditText) findViewById(R.id.editTextAddress);
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        editTextCep = (EditText) findViewById(R.id.editTextCep);
        buttonFindCep = (Button) findViewById(R.id.buttonFindCep);

        editTextStreetType = (EditText) findViewById(R.id.editTextStreetType);
        editTextStreet = (EditText) findViewById(R.id.editTextStreet);
        editTextNeighborhood = (EditText) findViewById(R.id.editTextneighborhood);
        editTextCity = (EditText) findViewById(R.id.editTextCity);
        editTextState = (EditText) findViewById(R.id.editTextState);

        bindButtonFindCep();
    }

    /**
     * @see <a href="http://developer.android.com/training/basics/intents/result.html">Getting a Result from an Activity</a>
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 999) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    final Uri contactUri = data.getData();
                    final String[] projection = {
                            ContactsContract.CommonDataKinds.Identity.DISPLAY_NAME,
                            ContactsContract.CommonDataKinds.Phone.NUMBER
                    };
                    final Cursor cursor = getContentResolver().query(contactUri, projection, null, null, null);
                    cursor.moveToFirst();

                    editTextName.setText(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Identity.DISPLAY_NAME)));
                    editTextPhone.setText(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));

                    cursor.close();
                } catch (Exception e) {
                    Log.d("TAG", "Unexpected error");
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void bindButtonFindCep() {              // BOTÃO PARA BUSCAR O CEP.
        buttonFindCep = (Button) findViewById(R.id.buttonFindCep);
        buttonFindCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new getAddressByCep().execute(editTextCep.getText().toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {             // CLIQUE SIMPLES DO MENU.
        getMenuInflater().inflate(R.menu.menu_client_persist, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {           // A��O DO BOT�O SALVAR.
        if(item.getItemId() == R.id.menu_save){
            Client client = bindClient();

            // VALIDACAO DO FORMULARIO.
            if(FormHelper.requiredValidate(
                    ClientPersistentActivity.this, editTextName, editTextAge, editTextAddress, editTextPhone)){
                client.save();

                Toast.makeText(ClientPersistentActivity.this, R.string.success, Toast.LENGTH_LONG).show();
                finish();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private Client bindClient(){                // RECUPERA O CLIENTE DA TELA.
        if(client == null) {
            client = new Client();
        }

        client.setName(editTextName.getText().toString());
        client.setAge(Integer.valueOf(editTextAge.getText().toString()));
        client.setAddress(editTextAddress.getText().toString());
        client.setPhone(editTextPhone.getText().toString());

        client.setCep(editTextCep.getText().toString());
        client.setStreetType(editTextStreetType.getText().toString());
        client.setStreet(editTextStreet.getText().toString());
        client.setNeighborhood(editTextNeighborhood.getText().toString());
        client.setCity(editTextCity.getText().toString());
        client.setState(editTextState.getText().toString());

        return client;
    }

    private void bindForm(){                // RECUPERA O CLIENTE PARA EDITAR.
        editTextName.setText(client.getName());
        editTextAge.setText(client.getAge().toString());
        editTextPhone.setText(client.getPhone());
        editTextAddress.setText(client.getAddress());

        editTextCep.setText(client.getCep());
        editTextStreetType.setText(client.getStreetType());
        editTextStreet.setText(client.getStreet());
        editTextNeighborhood.setText(client.getNeighborhood());
        editTextCity.setText(client.getCity());
        editTextState.setText(client.getState());
    }

    private class getAddressByCep extends AsyncTask<String, Void, ClientAddress> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {     // EXECUTA ANTES DA EXECUÇÃO
            progressDialog = new ProgressDialog(ClientPersistentActivity.this);     // CARREGANDO...
            progressDialog.setMessage(getString(R.string.loading));
            progressDialog.show();
        }

        @Override
        protected ClientAddress doInBackground(String... params) {
            return CepService.getAddressBy(params[0]);
        }

        @Override
        protected void onPostExecute(ClientAddress address) {      // EXECUTA APOS A EXECUÇÃO
            editTextStreetType.setText(address.getTipoDeLogradouro().toString());
            editTextStreet.setText(address.getLogradouro().toString());
            editTextNeighborhood.setText(address.getBairro().toString());
            editTextCity.setText(address.getCidade().toString());
            editTextState.setText(address.getEstado().toString());

            progressDialog.dismiss();
        }
    }



}
