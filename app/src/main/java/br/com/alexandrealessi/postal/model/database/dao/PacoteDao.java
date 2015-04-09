package br.com.alexandrealessi.postal.model.database.dao;

import br.com.alexandrealessi.postal.model.domain.Pacote;

import java.util.List;

/**
 * Created by alexandre on 09/04/15.
 */
public interface PacoteDao {
    public void insert(Pacote pacote);

    public List<Pacote> getAll();
}
