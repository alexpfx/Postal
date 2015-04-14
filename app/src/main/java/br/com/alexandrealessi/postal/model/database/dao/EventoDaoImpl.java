package br.com.alexandrealessi.postal.model.database.dao;

import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;
import br.com.alexandrealessi.postal.model.database.DatabaseAdapter;
import br.com.alexandrealessi.postal.model.domain.Evento;
import br.com.alexandrealessi.postal.model.domain.Local;
import br.com.alexandrealessi.postal.model.domain.Pacote;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by alexandre on 10/04/15.
 */
public class EventoDaoImpl implements EventoDao {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss.SSS");
    DatabaseAdapter dbAdapter;
    LocalDao localDao;

    protected EventoDaoImpl(DatabaseAdapter dbAdapter) {
        this.dbAdapter = dbAdapter;
        localDao = new LocalDaoImpl(dbAdapter);
    }

    @Override
    public Evento insert(Evento evento, Pacote pacote) {
        Local local = localDao.insert(evento.getLocal());
        local = localDao.insertIfNotExists(evento.getLocal());
        ContentValues values = new ContentValues();
        values.put("data", format.format(evento.getData()));
        values.put("detalhe", evento.getDetalhe());
        values.put("acao", evento.getAcao().getDescricao());
        values.put("idLocal", local.getId());
        values.put("idPacote", pacote.getId());
        long idEvento = dbAdapter.getDatabase().insert("eventos", null, values);
        evento.setId(idEvento);
        return evento;
    }


    @Override
    public List<Evento> getAll() {
        return null;
    }
}
