package com.example.aphish.movierental.repository.factories.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.aphish.movierental.conf.databases.DBConstants;
import com.example.aphish.movierental.domain.Cash;
import com.example.aphish.movierental.repository.factories.CashRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Aphish on 2016/09/02.
 */
public class CashRepositoryImpl extends SQLiteOpenHelper implements CashRepository {

    public static final String TABLE_NAME = "cash";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CASH_PAYED = "actorsName";
    public static final String COLUMN_DATE = "surname";

    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME +" ( "
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_CASH_PAYED + " TEXT, "
            + COLUMN_DATE + " TEXT );";

    public CashRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open()throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close(){this.close();}

    @Override
    public Cash findById(Long aLong) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_CASH_PAYED,
                        COLUMN_DATE },
                COLUMN_ID + "=?",
                new String[]{String.valueOf(aLong)},
                null,null,null,null);
        if (cursor.moveToFirst()){
            final Cash cash = new Cash.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .cashPayed(cursor.getDouble(cursor.getColumnIndex(COLUMN_CASH_PAYED)))
                    .date(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)))
                    .build();
            return cash;
        }else {
            return null;
        }
    }

    @Override
    public Cash save(Cash entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID,entity.getId());
        values.put(COLUMN_CASH_PAYED,entity.getCashPayed());
        values.put(COLUMN_DATE,entity.getDate());

        long id = db.insertOrThrow(TABLE_NAME,null,values);
        Cash insertedEntity = new Cash.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Cash update(Cash entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CASH_PAYED,entity.getCashPayed());
        values.put(COLUMN_DATE,entity.getDate());

        db.update(TABLE_NAME,values,COLUMN_ID + "=?",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Cash delete(Cash entity) {
        open();
        db.delete(TABLE_NAME,COLUMN_ID + "=?",new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Cash> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Cash> cash = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                final Cash pay = new Cash.Builder()
                        .cashPayed(cursor.getInt(cursor.getColumnIndex(COLUMN_CASH_PAYED)))
                        .date(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)))
                        .build();
                cash.add(pay);
            }while (cursor.moveToNext());
        }
        return cash;
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
