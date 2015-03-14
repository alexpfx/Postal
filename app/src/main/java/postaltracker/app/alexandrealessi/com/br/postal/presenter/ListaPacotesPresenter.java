package postaltracker.app.alexandrealessi.com.br.postal.presenter;

import java.util.List;

import postaltracker.app.alexandrealessi.com.br.postal.model.PacotesInteractor;
import postaltracker.app.alexandrealessi.com.br.postal.model.domain.Pacote;

/**
 * Created by alexandre on 12/03/15.
 */
public interface ListaPacotesPresenter  {

    /**
     * Requisita a lista de todos os pacotes salvos no banco.
     */
    public void requestListaPacotes();

    public void saveListaPacotes(Pacote [] pacotes);

    public void setPacotesInteractor (PacotesInteractor interactor);


}
