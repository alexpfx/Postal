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
    private static final String tag = SroOpenHelper.class.getName();

    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME = "xxxxxx01.db";


    private Context context;

    public SroOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String[] createTableScripts = new String[]{getSqlScriptFrom("createtablepacotes.sql"), getSqlScriptFrom("createtablelocais.sql"), getSqlScriptFrom("createtableeventos.sql")};
        for (String script : createTableScripts) {
            try {
                db.execSQL(script);
            } catch (Exception e) {
                Log.e(tag, e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private String getSqlScriptFrom(String filename) {
        try {
            InputStream sqlFileStream = context.getAssets().open(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(sqlFileStream));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append(" ");
            }
            sqlFileStream.close();
            br.close();
            return sb.toString();
        } catch (IOException e) {
            Log.e(tag, "Erro ao acessar sistema de arquivos", e);
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            //db.exeSql executa apenas uma Sql de uma vez.
            db.execSQL("drop table if exists eventos");
            db.execSQL("drop table if exists pacotes");
            db.execSQL("drop table if exists locais");
        } catch (Exception e) {
            Log.e(tag, e.getMessage());
            e.printStackTrace();
        }
        onCreate(db);
    }
}
