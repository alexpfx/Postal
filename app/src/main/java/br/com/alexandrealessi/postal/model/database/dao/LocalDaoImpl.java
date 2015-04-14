package br.com.alexandrealessi.postal.model.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import br.com.alexandrealessi.postal.model.database.DatabaseAdapter;
import br.com.alexandrealessi.postal.model.domain.Local;

/**
 * Created by alexandre on 09/04/15.
 */
public class LocalDaoImpl implements LocalDao {

    private DatabaseAdapter dbAdapter;
    private String tag = LocalDaoImpl.class.getName();

    public LocalDaoImpl(DatabaseAdapter dbAdapter) {
        this.dbAdapter = dbAdapter;
    }

    @Override
    public Local insertIfNotExists(Local local) {
        Local l = findByNomeLocal(local.getDescricao());
        if (l != null) {
            return local;
        }
        return insert(local);
    }

    @Override
    public Local insert(Local local) {
        ContentValues values = new ContentValues();
        values.put("nome", local.getDescricao());
        long idLocal = dbAdapter.getDatabase().insert("Local", null, values);
        local.setId(idLocal);
        return local;
    }

    public Local findByNomeLocal(String nomeLocal) {
        Cursor cursor = null;
        try {
            cursor = dbAdapter.getDatabase().rawQuery("select * from Locais", null);
        } catch (Exception e) {
            Log.e(tag, e.getMessage());
            e.printStackTrace();
        }
//        final Cursor cursor = dbAdapter.getDatabase().query("Local", new String[]{"codigo", "nome"}, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            Local local = cursorToLocal(cursor);
            return local;
        }
        return null;
    }

    private Local cursorToLocal(Cursor cursor) {
        final long codigo = cursor.getLong(1);
        final String nomeLocal = cursor.getString(2);
        return Local.create(codigo, nomeLocal);
    }

}
