package br.com.alexandrealessi.postal.presenter;

import br.com.alexandrealessi.postal.model.PacotesInteractor;
import br.com.alexandrealessi.postal.model.domain.Pacote;

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
