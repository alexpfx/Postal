package br.com.alexandrealessi.postal.model;

import android.util.Log;
import br.com.alexandrealessi.postal.model.domain.Pacote;
import com.google.common.collect.Iterators;

import java.util.Iterator;

/**
 * Created by alexandre on 12/03/15.
 */
public class PacotesInteractorImpl implements PacotesInteractor {


    @Override
    public void requestListaPacotes(RequestListaPacotesCallback callback) {

        try {
            Iterator<Pacote> all = Pacote.findAll(Pacote.class);
            Pacote[] listaPacotes = Iterators.toArray(all, Pacote.class);
            Log.d(getClass().getSimpleName(), "size: "+listaPacotes.length);
            callback.receive(listaPacotes);
        } catch (Exception e) {
            callback.onRequestListaPacotesError();
        }


    }

    @Override
    public void save(Pacote p, SaveCallback callback) {
        p.save();
    }

}
