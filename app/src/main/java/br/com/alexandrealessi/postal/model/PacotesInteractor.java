package br.com.alexandrealessi.postal.model;

import android.util.Log;

import br.com.alexandrealessi.postal.model.domain.Pacote;

/**
 * Created by alexandre on 12/03/15.
 */
//TODO: melhorar nomes dos interactors
public interface PacotesInteractor {


    public void requestListaPacotes(RequestListaPacotesCallback callback);
    public void save (Pacote p, SaveCallback callback);


    public static PacotesInteractor NULL = new PacotesInteractor() {
        @Override
        public void requestListaPacotes(RequestListaPacotesCallback callback) {
            Log.d(this.getClass().getSimpleName(),": não instanciado");
        }

        @Override
        public void save(Pacote p, SaveCallback callback) {
            Log.d(this.getClass().getSimpleName(),": não instanciado");
        }
    };

    public static interface RequestListaPacotesCallback {
        public void receive(Pacote[] listaPacotes);
        public void error();
    }

    public static interface SaveCallback {
        public void onSavePacoteError();
    }


}
