package br.com.alexandrealessi.postal.model.database.dao;

import br.com.alexandrealessi.postal.model.domain.Local;

/**
 * Created by alexandre on 10/04/15.
 */
public interface LocalDao {
    Local insert(Local local);
    Local insertIfNotExists (Local local);
}
