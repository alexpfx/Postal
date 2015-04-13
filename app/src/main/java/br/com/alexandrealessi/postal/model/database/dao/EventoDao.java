package br.com.alexandrealessi.postal.model.database.dao;

import br.com.alexandrealessi.postal.model.domain.Evento;
import br.com.alexandrealessi.postal.model.domain.Pacote;

import java.util.List;

/**
 * Created by alexandre on 10/04/15.
 */
public interface EventoDao {

    Evento insert(Evento evento, Pacote pacote);

    List<Evento> getAll();
}
