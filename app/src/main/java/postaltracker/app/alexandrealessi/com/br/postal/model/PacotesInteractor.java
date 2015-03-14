package postaltracker.app.alexandrealessi.com.br.postal.model;

import android.util.Log;

import postaltracker.app.alexandrealessi.com.br.postal.model.domain.Pacote;

/**
 * Created by alexandre on 12/03/15.
 */
public interface PacotesInteractor {


    public void requestListaPacotes(PacotesInteractorCallback callback);




    public static PacotesInteractor NULL = new PacotesInteractor() {
        @Override
        public void requestListaPacotes(PacotesInteractorCallback callback) {
            Log.d(this.getClass().getSimpleName(),": não instanciado");
        }
    };

    public static interface PacotesInteractorCallback {
        public void receive(Pacote[] listaPacotes);

        public void error();
    }


}
