package com.example.aphish.movierental.repository.factories.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.aphish.movierental.conf.databases.DBConstants;
import com.example.aphish.movierental.domain.Rental;
import com.example.aphish.movierental.repository.factories.RentalRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Aphish on 2016/04/23.
 */
public class RentalRepositoryImpl extends SQLiteOpenHelper implements RentalRepository {

    public static final String TABLE_NAME = "rentals";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_RENTAL_DATE = "remtal_Date";
    public static final String COLUMN_RENTAL_NUMBER = "rental_Number";
    public static final String COLUMN_CUSTOMER = "customer";
    public static final String COLUMN_MOVIE = "movie";
    public static final String COLUMN_PAYMENT_TYPE = "paymentype";

    private static final String DATABASE_CREATE = "CREATE TABLE"
            +TABLE_NAME +"("
            +COLUMN_ID +"INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_RENTAL_DATE + "TEXT NOT NULL, "
            + COLUMN_RENTAL_NUMBER + " TEXT NOT NULL"
            + COLUMN_CUSTOMER + "TEXT NOT NULL, "
            + COLUMN_PAYMENT_TYPE + "TEXT NOT NULL"
            + COLUMN_MOVIE + "TEXT NOT NULL );";

    public RentalRepositoryImpl(Context context){
        super(context, DBConstants.DATABASE_NAME,null,DBConstants.DATABASE_VERSION);
    }

    public void open()throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close(){this.close();}

    @Override
    public Rental findById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_RENTAL_DATE,
                        COLUMN_RENTAL_NUMBER,COLUMN_CUSTOMER,
                        COLUMN_MOVIE,COLUMN_PAYMENT_TYPE},
                COLUMN_ID + "=?",
                new String[]{String.valueOf(id)},
                null,null,null,null);
        if (cursor.moveToFirst()){
            final Rental rental = new Rental.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .rentalDate(cursor.getString(cursor.getColumnIndex(COLUMN_RENTAL_DATE)))
                    .rentalNumber(cursor.getString(cursor.getColumnIndex(COLUMN_RENTAL_NUMBER)))
                    //.movie(cursor.getClass(cursor.getString(cursor.getColumnIndex(COLUMN_MOVIE)))
                    //.Actors(cursor.getClass(cursor.getString(cursor.getColumnIndex(COLUMN_ACTORS_NAME))))
                    .build();
            return rental;
        }else {
            return null;
        }
    }

    @Override
    public Rental save(Rental entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_RENTAL_NUMBER,entity.getRentalNumber());
        values.put(COLUMN_RENTAL_DATE,entity.getRentalDate());
        values.put(COLUMN_CUSTOMER,entity.getCustomers().getName());
        values.put(COLUMN_MOVIE,entity.getMovie().getName());
        values.put(COLUMN_PAYMENT_TYPE,entity.getPaymentType().paymentType());
        long id = db.insertOrThrow(TABLE_NAME,null,values);
        Rental insertedEntity = new Rental.Builder()
                .copy(entity)
                .build();
        return insertedEntity;
    }

    @Override
    public Rental update(Rental entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_RENTAL_NUMBER,entity.getRentalNumber());
        values.put(COLUMN_RENTAL_DATE,entity.getRentalDate());
        values.put(COLUMN_CUSTOMER,entity.getCustomers().getName());
        values.put(COLUMN_MOVIE,entity.getMovie().getName());
        values.put(COLUMN_PAYMENT_TYPE,entity.getPaymentType().paymentType());
        db.update(TABLE_NAME,values,COLUMN_ID + "=?",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Rental delete(Rental entity) {

        open();
        db.delete(TABLE_NAME,COLUMN_ID + "=?",new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Rental> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Rental> rental = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                final Rental rentals = new Rental.Builder()
                        .rentalNumber(cursor.getString(cursor.getColumnIndex(COLUMN_RENTAL_NUMBER)))
                        .rentalDate(cursor.getString(cursor.getColumnIndex(COLUMN_RENTAL_DATE)))
                        //.age(cursor.getString(cursor.getColumnIndex(COLUMN_AGE)))
                        //.height(cursor.getString(cursor.getColumnIndex(COLUMN_HEIGHT)))
                        .build();
                rental.add(rentals);
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
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),"Upgrading adatabase from version" +
                oldVersion +"to" + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);

    }
}
