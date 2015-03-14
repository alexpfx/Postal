package postaltracker.app.alexandrealessi.com.br.postal.model;

import android.util.Log;

import com.google.common.collect.Iterators;

import java.util.Iterator;

import postaltracker.app.alexandrealessi.com.br.postal.model.domain.Pacote;

/**
 * Created by alexandre on 12/03/15.
 */
public class PacotesInteractorImpl implements PacotesInteractor {


    @Override
    public void requestListaPacotes(PacotesInteractorCallback callback) {

        try {
            Iterator<Pacote> all = Pacote.findAll(Pacote.class);
            Pacote[] listaPacotes = Iterators.toArray(all, Pacote.class);
            Log.d(getClass().getSimpleName(), "size: "+listaPacotes.length);
            callback.receive(listaPacotes);
        } catch (Exception e) {
            callback.error();
        }


    }
}
