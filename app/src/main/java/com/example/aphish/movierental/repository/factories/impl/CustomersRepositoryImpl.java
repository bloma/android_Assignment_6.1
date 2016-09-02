package com.example.aphish.movierental.repository.factories.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.aphish.movierental.conf.databases.DBConstants;
import com.example.aphish.movierental.domain.Customers;
import com.example.aphish.movierental.repository.factories.CustomersRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Aphish on 2016/08/12.
 */
public class CustomersRepositoryImpl extends SQLiteOpenHelper implements CustomersRepository {

    public static final String TABLE_NAME = "customers";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CUSTOMERS_NAME = "name";
    public static final String COLUMN_SURNAME = "surname";
    public static final String COLUMN_AGE = "age";




    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME +" ( "
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_CUSTOMERS_NAME + " TEXT, "
            + COLUMN_SURNAME + " TEXT, "
            + COLUMN_AGE + " TEXT );";

    public CustomersRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open()throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close(){this.close();}


    @Override
    public Customers findById(Long aLong) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_CUSTOMERS_NAME,
                        COLUMN_SURNAME,
                        COLUMN_AGE },
                COLUMN_ID + "=?",
                new String[]{String.valueOf(aLong)},
                null,null,null,null);
        if (cursor.moveToFirst()){
            final Customers actors = new Customers.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .customerName(cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMERS_NAME)))
                    .customeruSurname(cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME)))
                    .customerAge(cursor.getString(cursor.getColumnIndex(COLUMN_AGE)))
                    .build();
            return actors;
        }else {
            return null;
        }
    }

    @Override
    public Customers save(Customers entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID,entity.getId());
        values.put(COLUMN_CUSTOMERS_NAME,entity.getName());
        values.put(COLUMN_SURNAME,entity.getSurname());
        values.put(COLUMN_AGE,entity.getAge());

        long id = db.insertOrThrow(TABLE_NAME,null,values);
        Customers insertedEntity = new Customers.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Customers update(Customers entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CUSTOMERS_NAME,entity.getName());
        values.put(COLUMN_SURNAME,entity.getSurname());
        values.put(COLUMN_AGE,entity.getAge());

        db.update(TABLE_NAME,values,COLUMN_ID + "=?",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Customers delete(Customers entity) {
        open();
        db.delete(TABLE_NAME,COLUMN_ID + "=?",new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Customers> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Customers> rental = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                final Customers customer = new Customers.Builder()
                        .customerName(cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMERS_NAME)))
                        .customeruSurname(cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME)))
                        .customerAge(cursor.getString(cursor.getColumnIndex(COLUMN_AGE)))
                        .build();
                rental.add(customer);
            }while (cursor.moveToNext());
        }
        return rental;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),"Upgrading adatabase from version" +
                oldVersion +"to" + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }
}
