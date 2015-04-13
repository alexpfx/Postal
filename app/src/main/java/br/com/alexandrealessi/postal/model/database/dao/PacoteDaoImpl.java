package br.com.alexandrealessi.postal.model.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.alexandrealessi.postal.model.database.DatabaseAdapter;
import br.com.alexandrealessi.postal.model.domain.Evento;
import br.com.alexandrealessi.postal.model.domain.Pacote;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandre on 09/04/15.
 */
public class PacoteDaoImpl implements PacoteDao {
    private DatabaseAdapter dbAdapter;
    public static final String TABLE_PACOTES = "pacotes";
    public static final String COLUMN_SRO = "sro";

    private EventoDao eventoDao;

    public PacoteDaoImpl(DatabaseAdapter databaseAdapter) {
        dbAdapter = databaseAdapter;
        eventoDao = new EventoDaoImpl(dbAdapter);
    }

    @Override
    public Pacote insert(Pacote pacote) {
        final SQLiteDatabase database = dbAdapter.getDatabase();
        database.beginTransaction();
        try {
            final long idPacote = insertPacote(pacote, database);
            pacote.setId(idPacote);
            addEventos(pacote, pacote.getEventos());
            database.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(PacoteDaoImpl.class.getName(), e.getMessage());

        } finally {
            database.endTransaction();
        }

        return pacote;

    }

    private void addEventos(Pacote pacote, List<Evento> eventos) {
        for (Evento evento : eventos) {
            eventoDao.insert(evento, pacote);
        }
    }


    private long insertPacote(Pacote pacote, SQLiteDatabase database) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_SRO, pacote.getSro());
        return database.insert(TABLE_PACOTES, null, values);
    }


    @Override
    public List<Pacote> getAll() {
        List<Pacote> pacotes = new ArrayList<>();
        Cursor cursor = dbAdapter.getDatabase().query(TABLE_PACOTES, new String[]{"codigo", COLUMN_SRO}, null, null, null, null, null);
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
