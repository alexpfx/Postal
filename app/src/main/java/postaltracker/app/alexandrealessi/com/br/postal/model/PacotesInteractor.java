package postaltracker.app.alexandrealessi.com.br.postal.model;

import java.util.Iterator;
import java.util.List;

import postaltracker.app.alexandrealessi.com.br.postal.model.domain.Pacote;

/**
 * Created by alexandre on 12/03/15.
 */
public interface PacotesInteractor {

    public void requestListaPacotes (PacotesInteractorCallback callback);

    public static interface PacotesInteractorCallback {
        public void receive (Iterator<Pacote> listaPacotes);
        public void error ();


    }


}
