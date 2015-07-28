package com.example.administrador.teste.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.administrador.teste.model.entities.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientContract  {

    public static final String TABLE = "client";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String AGE = "age";
    public static final String PHONE = "phone";
    public static final String ADDRESS = "address";

    public static final String CEP = "cep";
    public static final String STREETTYPE = "streettype";
    public static final String STREET = "street";
    public static final String NEIGHBORHOOD = "neighborhood";
    public static final String CITY = "city";
    public static final String STATE = "state";


    public static final String[] COLUMNS = { ID, NAME, AGE, PHONE, ADDRESS, CEP, STREETTYPE, STREET, NEIGHBORHOOD, CITY, STATE };

    public static String getCreateTable(){
        StringBuilder sql = new StringBuilder();
        sql.append(" CREATE TABLE ");
        sql.append(TABLE);
            sql.append(" ( ");
                sql.append( ID + " INTEGER PRIMARY KEY, " );
                sql.append( NAME + " TEXT, " );
                sql.append( AGE + " INTEGER, " );
                sql.append( PHONE + " TEXT, " );
                sql.append( ADDRESS + " TEXT, " );
                    sql.append( CEP + " TEXT, " );
                    sql.append( STREETTYPE + " TEXT, " );
                    sql.append( STREET + " TEXT, " );
                    sql.append( NEIGHBORHOOD + " TEXT, " );
                    sql.append( CITY + " TEXT, " );
                    sql.append( STATE + " TEXT " );
            sql.append(" ); ");

        return sql.toString();
    }

    public static ContentValues getContentValues(Client client){        // Recupera os Valores do Cliente.
        ContentValues values = new ContentValues();

        values.put(ClientContract.ID, client.getId());
        values.put(ClientContract.NAME, client.getName());
        values.put(ClientContract.AGE, client.getAge());
        values.put(ClientContract.ADDRESS, client.getAddress());
        values.put(ClientContract.PHONE, client.getPhone());

        values.put(ClientContract.CEP, client.getCep());
        values.put(ClientContract.STREETTYPE, client.getStreetType());
        values.put(ClientContract.STREET, client.getStreet());
        values.put(ClientContract.NEIGHBORHOOD, client.getNeighborhood());
        values.put(ClientContract.CITY, client.getCity());
        values.put(ClientContract.STATE, client.getState());

        return values;
    }

    public static Client bindCursor(Cursor cursor){                 // Recupera a Listagem.
        if(!cursor.isBeforeFirst() || cursor.moveToNext() ){
            Client client = new Client();

            client.setId(cursor.getInt(cursor.getColumnIndex(ClientContract.ID)));
            client.setName(cursor.getString(cursor.getColumnIndex(ClientContract.NAME)));
            client.setAge(cursor.getInt(cursor.getColumnIndex(ClientContract.AGE)));
            client.setPhone(cursor.getString(cursor.getColumnIndex(ClientContract.PHONE)));
            client.setAddress(cursor.getString(cursor.getColumnIndex(ClientContract.ADDRESS)));

            client.setCep(cursor.getString(cursor.getColumnIndex(ClientContract.CEP)));
            client.setStreetType(cursor.getString(cursor.getColumnIndex(ClientContract.STREETTYPE)));
            client.setStreet(cursor.getString(cursor.getColumnIndex(ClientContract.STREET)));
            client.setStreet(cursor.getString(cursor.getColumnIndex(ClientContract.NEIGHBORHOOD)));
            client.setCity(cursor.getString(cursor.getColumnIndex(ClientContract.CITY)));
            client.setState(cursor.getString(cursor.getColumnIndex(ClientContract.STATE)));

            return client;
        }

        return null;
    }

    public static List<Client> bindList(Cursor cursor){

        final List<Client> clientsOrders = new ArrayList<Client>();

        while (cursor.moveToNext()){
            clientsOrders.add(bindCursor(cursor));
        }

        return clientsOrders;
    }
}
