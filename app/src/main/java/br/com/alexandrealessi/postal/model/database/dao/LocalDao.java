package br.com.alexandrealessi.postal.model.database.dao;

import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;
import br.com.alexandrealessi.postal.model.domain.Local;

/**
 * Created by alexandre on 09/04/15.
 */
public class LocalDao extends BaseDao {
    protected LocalDao(SQLiteOpenHelper openHelper) {
        super(openHelper);
    }

    public Local insert(Local local) {
        open();
        ContentValues values = new ContentValues();
        values.put("nome_local", local.getDescricao());
        long id = getDatabase().insert("locais", null, values);
        close();
        return Local.create(id, local);
    }
}
