package com.dev.marck.prom.examen2daeva.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbManager extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "libros";
    public static final String DATABASE_NAME = "examen";
    public static final String COL_TITULO = "titulo";
    public static final String COL_ISBN = "isbn";
    public static final String COL_AUTOR = "autor";
    public static final String COL_NUM_PAG = "numPag";
    public static final String COL_LEIDO = "leido";
    public static final String COL_EDITORIAL = "ed";

    public DbManager( Context context, String name, SQLiteDatabase.CursorFactory factory, int version ) {
        super( context, name, factory, version );
    }

    @Override
    public void onCreate( SQLiteDatabase db ) {
        String sql = "CREATE TABLE "+ TABLE_NAME + "( " + COL_ISBN + " TEXT PRIMARY KEY, " +
                COL_TITULO + " TEXT," +
                COL_AUTOR + " TEXT," +
                COL_EDITORIAL + " TEXT," +
                COL_NUM_PAG +" NUMBER," +
                COL_LEIDO + " NUMBER ) ";
        db.execSQL( sql );
    }

    @Override
    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion ) {
        String sql = "DROP TABLE " + TABLE_NAME;
        db.execSQL( sql );
    }
}
