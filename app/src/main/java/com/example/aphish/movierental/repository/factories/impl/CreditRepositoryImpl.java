package com.example.aphish.movierental.repository.factories.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.aphish.movierental.conf.databases.DBConstants;
import com.example.aphish.movierental.domain.Credit;
import com.example.aphish.movierental.repository.factories.CreditRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Aphish on 2016/09/02.
 */
public class CreditRepositoryImpl extends SQLiteOpenHelper implements CreditRepository {

    public static final String TABLE_NAME = "credit";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CARD_NUMBER = "card_Number";
    public static final String COLUMN_AMOUNT = "amount";


    private static final String DATABASE_CREATE = " CREATE TABLE "
            +TABLE_NAME +" ( "
            +COLUMN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_CARD_NUMBER + " TEXT NOT NULL, "
            + COLUMN_AMOUNT + " TEXT NOT NULL );";

    public CreditRepositoryImpl(Context context){
        super(context, DBConstants.DATABASE_NAME,null,DBConstants.DATABASE_VERSION);
    }

    public void open()throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close(){this.close();}

    @Override
    public Credit findById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_CARD_NUMBER,
                        COLUMN_AMOUNT},
                COLUMN_ID + "=?",
                new String[]{String.valueOf(id)},
                null,null,null,null);
        if (cursor.moveToFirst()){
            final Credit credit = new Credit.Builder()
                    .Amount(cursor.getInt(cursor.getColumnIndex(COLUMN_AMOUNT)))
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .cardNumber(cursor.getString(cursor.getColumnIndex(COLUMN_CARD_NUMBER)))
                    .build();
            return credit;
        }else {
            return null;
        }
    }

    @Override
    public Credit save(Credit entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CARD_NUMBER,entity.getCardNumber());
        values.put(COLUMN_AMOUNT,entity.getPin());
        long id = db.insertOrThrow(TABLE_NAME,null,values);
        Credit insertedEntity = new Credit.Builder()
                .copy(entity)
                .build();
        return insertedEntity;
    }

    @Override
    public Credit update(Credit entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CARD_NUMBER,entity.getCardNumber());
        values.put(COLUMN_AMOUNT,entity.getPin());
        db.update(TABLE_NAME,values,COLUMN_ID + "=?",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Credit delete(Credit entity) {
        open();
        db.delete(TABLE_NAME,COLUMN_ID + "=?",new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Credit> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Credit> credit = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                final Credit pay = new Credit.Builder()
                        .cardNumber(cursor.getString(cursor.getColumnIndex(COLUMN_CARD_NUMBER)))
                        .Amount(cursor.getInt(cursor.getColumnIndex(COLUMN_AMOUNT)))
                        .build();
                credit.add(pay);
            }while (cursor.moveToNext());
        }
        return credit;
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
