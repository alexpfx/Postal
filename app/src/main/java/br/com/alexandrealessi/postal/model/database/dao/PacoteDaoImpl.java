package br.com.alexandrealessi.postal.model.database.dao;

import android.content.Context;
import android.database.Cursor;
import br.com.alexandrealessi.postal.model.database.SroOpenHelper;
import br.com.alexandrealessi.postal.model.domain.Pacote;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandre on 09/04/15.
 */
public class PacoteDaoImpl extends BaseDao implements PacoteDao {

    public static final String TABLE_PACOTES = "pacotes";
    public static final String COLUMN_SRO = "sro";


    protected PacoteDaoImpl(Context context) {
        super(new SroOpenHelper(context));
    }

    @Override
    public void insert(Pacote pacote) {
        //TODO implementar
    }

    @Override
    public List<Pacote> getAll() {
        List<Pacote> pacotes = new ArrayList<>();
        Cursor cursor = getDatabase().query(TABLE_PACOTES, new String[]{COLUMN_SRO}, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Pacote p = cursorToPacote(cursor);
            pacotes.add(p);
            cursor.moveToNext();
        }
        return pacotes;
    }

    private Pacote cursorToPacote(Cursor cursor) {
        int codigo = cursor.getInt(0);
        String sro = cursor.getString(1);
        return Pacote.create(sro);
    }
}
