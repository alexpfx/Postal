package postaltracker.app.alexandrealessi.com.br.postal.view;

import java.util.List;

import postaltracker.app.alexandrealessi.com.br.postal.model.domain.Pacote;

/**
 * Created by alexandre on 08/03/15.
 */
public interface ListaPacotesView {
    /**
     * Mostra lista de pacotes depois de recuperados do banco ou de obtidos do servidor.
     * @param pacotes
     */
    public void mostrarListaPacotes (Pacote [] pacotes);

}
