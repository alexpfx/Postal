package postaltracker.app.alexandrealessi.com.br.postal.view;

import android.util.Log;

import java.util.List;

import postaltracker.app.alexandrealessi.com.br.postal.model.domain.Pacote;

/**
 * Created by alexandre on 08/03/15.
 */
public interface ListaPacotesView {
    /**
     * Mostra listaPacotesPresenter de pacotes depois de recuperados do banco ou de obtidos do servidor.
     * @param pacotes
     */
    public void mostrarListaPacotes (Pacote [] pacotes);

    public static ListaPacotesView NULL = new ListaPacotesView() {
        @Override
        public void mostrarListaPacotes(Pacote[] pacotes) {
            Log.d(this.getClass().getSimpleName(), "não chamou método init");
        }
    };

}
