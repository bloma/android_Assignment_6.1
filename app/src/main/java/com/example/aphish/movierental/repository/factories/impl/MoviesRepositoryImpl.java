package com.example.aphish.movierental.repository.factories.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.aphish.movierental.conf.databases.DBConstants;
import com.example.aphish.movierental.domain.Movie;
import com.example.aphish.movierental.repository.factories.MoviesRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Aphish on 2016/04/23.
 */
public class MoviesRepositoryImpl extends SQLiteOpenHelper
implements MoviesRepository{

    public static final String TABLE_NAME = "movies";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "movieName";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_DURATION = "duration";


    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME +" ( "
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT NOT NULL, "
            + COLUMN_DATE + " TEXT NOT NULL, "
            + COLUMN_DURATION + " TEXT NOT NULL );";

    public MoviesRepositoryImpl(Context context){
        super(context, DBConstants.DATABASE_NAME,null,DBConstants.DATABASE_VERSION);
    }

    public void open()throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close(){this.close();}

    @Override
    public Movie findById(Long id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_NAME,
                        COLUMN_DURATION,COLUMN_DATE},
                COLUMN_ID + " =?",
                new String[]{String.valueOf(id)},
                null,null,null,null);
        if (cursor.moveToFirst()){
            final Movie movie = new Movie.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .durationTime(cursor.getString(cursor.getColumnIndex(COLUMN_DURATION)))
                    .releaseDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)))
                    .build();
            return movie;
        }else {
            return null;
        }
    }

    @Override
    public Movie save(Movie entity){
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID,entity.getId());
        values.put(COLUMN_NAME,entity.getName());
        values.put(COLUMN_DURATION,entity.getDurationTime());
        values.put(COLUMN_DATE,entity.getReleaseDate());

        long id = db.insertOrThrow(TABLE_NAME,null,values);
       Movie insertedEntity = new Movie.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Movie update(Movie entity){
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID,entity.getId());
        values.put(COLUMN_NAME,entity.getName());
        values.put(COLUMN_DURATION,entity.getDurationTime());
        values.put(COLUMN_DATE,entity.getReleaseDate());

        db.update(TABLE_NAME,values,COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Movie delete(Movie entity){
        open();
        db.delete(TABLE_NAME,COLUMN_ID + " =? ",new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Movie> findAll(){
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Movie> movies = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                final Movie movie = new Movie.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .releaseDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)))
                        .durationTime(cursor.getString(cursor.getColumnIndex(COLUMN_DURATION)))
                        .build();
                movies.add(movie);
            }while (cursor.moveToNext());
        }
        return movies;
    }

    @Override
    public int deleteAll(){
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }

    @Override
    public void onCreate(SQLiteDatabase db){db.execSQL(DATABASE_CREATE);}

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        Log.w(this.getClass().getName(),"Upgrading adatabase from version " +
        oldVersion +" to " + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }
}
