package br.com.alexandrealessi.postal.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by alexandre on 07/04/15.
 */
public class SroOpenHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "SRODATABASE.DB";

    private Context context;
    private String tag = SroOpenHelper.class.getName();

    public SroOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String scriptString = getSqlScriptFrom("createdatabase.sql");
        db.execSQL(scriptString);
    }

    private String getSqlScriptFrom(String filename) {
        try {
            InputStream sqlFileStream = context.getAssets().open(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(sqlFileStream));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            Log.d(tag, sb.toString());
            return sb.toString();
        } catch (IOException e) {
            Log.e(tag, "Erro ao acessar sistema de arquivos", e);
        }
        return "";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(getSqlScriptFrom("droptables.sql"));
        onCreate(db);
    }
}
