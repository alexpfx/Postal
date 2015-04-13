package br.com.alexandrealessi.postal.model.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import br.com.alexandrealessi.postal.model.database.DatabaseAdapter;
import br.com.alexandrealessi.postal.model.domain.Local;

/**
 * Created by alexandre on 09/04/15.
 */
public class LocalDaoImpl implements LocalDao {

    private DatabaseAdapter dbAdapter;

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
        values.put("nome_local", local.getDescricao());
        long idLocal = dbAdapter.getDatabase().insert("locais", null, values);
        return Local.create(idLocal, local);
    }

    public Local findByNomeLocal(String nomeLocal) {
        final Cursor cursor = dbAdapter.getDatabase().query(true, "locais", new String[]{"codigo", "nome-local"}, "nome-local" + " = " + nomeLocal, null, null, null, null, "1");
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
