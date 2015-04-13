package br.com.alexandrealessi.postal.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.com.alexandrealessi.postal.model.database.SroOpenHelper;

/**
 * Created by alexandre on 09/04/15.
 */
public class DatabaseAdapter {
    public static final String COLUMN_CODIGO = "codigo";
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;

    public DatabaseAdapter(Context context) {
        this.openHelper = new SroOpenHelper(context);
        database = openHelper.getWritableDatabase();
    }



    public void beginTransaction() {
        database.beginTransaction();
    }

    public void endTransaction() {
        database.endTransaction();
    }

    public void commitTransaction() {
        database.setTransactionSuccessful();
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }
}
