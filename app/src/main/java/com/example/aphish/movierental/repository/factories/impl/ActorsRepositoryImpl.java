package com.example.aphish.movierental.repository.factories.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.aphish.movierental.conf.databases.DBConstants;
import com.example.aphish.movierental.domain.Actors;
import com.example.aphish.movierental.repository.factories.ActorsRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Aphish on 2016/08/12.
 */
public class ActorsRepositoryImpl extends SQLiteOpenHelper implements ActorsRepository {

    public static final String TABLE_NAME = "actors";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_ACTORS_NAME = "actorsName";
    public static final String COLUMN_SURNAME = "surname";
    public static final String COLUMN_AGE = "age";
    public static final String COLUMN_HEIGHT = "height";



    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME +" ( "
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_ACTORS_NAME + " TEXT, "
            + COLUMN_SURNAME + " TEXT, "
            + COLUMN_AGE + " TEXT, "
            + COLUMN_HEIGHT + " TEXT );";

    public ActorsRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open()throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close(){this.close();}

    @Override
    public Actors findById(Long aLong) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_ACTORS_NAME,
                        COLUMN_SURNAME,
                        COLUMN_AGE },
                COLUMN_ID + "=?",
                new String[]{String.valueOf(aLong)},
                null,null,null,null);
        if (cursor.moveToFirst()){
            final Actors actors = new Actors.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_ACTORS_NAME)))
                    .surname(cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME)))
                    .age(cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME)))
                    .build();
            return actors;
        }else {
            return null;
        }
    }

    @Override
    public Actors save(Actors entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID,entity.getId());
        values.put(COLUMN_ACTORS_NAME,entity.getName());
        values.put(COLUMN_SURNAME,entity.getSurname());
        values.put(COLUMN_AGE,entity.getAge());

        long id = db.insertOrThrow(TABLE_NAME,null,values);
        Actors insertedEntity = new Actors.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Actors update(Actors entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ACTORS_NAME,entity.getName());
        values.put(COLUMN_SURNAME,entity.getSurname());
        values.put(COLUMN_AGE,entity.getAge());

        db.update(TABLE_NAME,values,COLUMN_ID + "=?",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Actors delete(Actors entity) {
        open();
        db.delete(TABLE_NAME,COLUMN_ID + "=?",new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Actors> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Actors> act = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                final Actors actors = new Actors.Builder()
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_ACTORS_NAME)))
                        .surname(cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME)))
                        .age(cursor.getString(cursor.getColumnIndex(COLUMN_AGE)))
                        .build();
                act.add(actors);
            }while (cursor.moveToNext());
        }
        return act;
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
