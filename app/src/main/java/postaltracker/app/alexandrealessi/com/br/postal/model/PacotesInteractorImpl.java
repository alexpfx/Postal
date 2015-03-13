package postaltracker.app.alexandrealessi.com.br.postal.model;

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
            callback.receive(all);
        } catch (Exception e) {
            callback.error();
        }
    }
}
