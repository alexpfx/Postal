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
        Local localByNome = findByNomeLocal(local.getDescricao());
        return (localByNome != null) ? local : insert(local);
    }

    @Override
    public Local insert(Local local) {
        ContentValues values = new ContentValues();
        values.put("nome", local.getDescricao());
        long idLocal = dbAdapter.getDatabase().insert("Locais", null, values);
        local.setId(idLocal);
        return local;
    }

    public Local findByNomeLocal(String nomeLocal) {
        Cursor cursor = null;
        try {
            cursor = dbAdapter.getDatabase().query("Locais", new String[]{"codigo", "nome"}, "nome = '"+nomeLocal+"'", null, null, null, null, "1");
            if (cursor.moveToFirst()) {
                Local local = cursorToLocal(cursor);
                return local;
            }
        } catch (Exception e) {
            Log.e(tag, e.getMessage());

        }
        return null;
    }

    private Local cursorToLocal(Cursor cursor) {
        final long codigo = cursor.getLong(1);
        final String nomeLocal = cursor.getString(2);
        return Local.create(codigo, nomeLocal);
    }

}
