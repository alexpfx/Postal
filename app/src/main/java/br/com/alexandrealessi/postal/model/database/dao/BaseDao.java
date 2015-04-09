package br.com.alexandrealessi.postal.model.database.dao;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by alexandre on 09/04/15.
 */
public abstract class BaseDao {
    public static final String COLUMN_CODIGO = "codigo";
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;

    protected BaseDao(SQLiteOpenHelper openHelper) {

        this.openHelper = openHelper;
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }

    public void open() {
        database = openHelper.getWritableDatabase();
    }

    public void close() {
        openHelper.close();
    }

}
