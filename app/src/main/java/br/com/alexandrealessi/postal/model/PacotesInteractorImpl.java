package br.com.alexandrealessi.postal.model;

import br.com.alexandrealessi.postal.model.domain.Pacote;

/**
 * Created by alexandre on 12/03/15.
 */
public class PacotesInteractorImpl implements PacotesInteractor {


    @Override
    public void requestListaPacotes(RequestListaPacotesCallback callback) {

        try {
            /*
            Iterator<Pacote> all = Pacote.findAll(Pacote.class);
            Pacote[] listaPacotes = Iterators.toArray(all, Pacote.class);
            Log.d(getClass().getSimpleName(), "size: "+listaPacotes.length);

            callback.receive(listaPacotes);
            */
        } catch (Exception e) {
            callback.onRequestListaPacotesError();
        }


    }

    @Override
    public void save(Pacote p, SaveCallback callback) {
        try {

//            p.save();
        } catch (Exception e) {
            callback.onSavePacoteError();
        }
        callback.onSavePacoteSuccess();
    }

}
