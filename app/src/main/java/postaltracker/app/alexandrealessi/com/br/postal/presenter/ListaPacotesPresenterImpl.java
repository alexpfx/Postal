package postaltracker.app.alexandrealessi.com.br.postal.presenter;

import postaltracker.app.alexandrealessi.com.br.postal.common.AbstractPresenter;
import postaltracker.app.alexandrealessi.com.br.postal.model.PacotesInteractor;
import postaltracker.app.alexandrealessi.com.br.postal.model.domain.Pacote;
import postaltracker.app.alexandrealessi.com.br.postal.view.ListaPacotesView;

/**
 * Created by alexandre on 12/03/15.
 */
public class ListaPacotesPresenterImpl extends AbstractPresenter<ListaPacotesView> implements ListaPacotesPresenter, PacotesInteractor.PacotesInteractorCallback {

    {
        super.init(ListaPacotesView.NULL);
    }

    private PacotesInteractor pacotesInteractor = PacotesInteractor.NULL;



    public void setPacotesInteractor(PacotesInteractor pacotesInteractor) {
        this.pacotesInteractor = pacotesInteractor;
    }

    @Override
    public void requestListaPacotes() {
        pacotesInteractor.requestListaPacotes(this);
    }

    @Override
    public void saveListaPacotes(Pacote[] pacotes) {

    }

    //callback

    @Override
    public void receive(Pacote[] listaPacotes) {
        getView().mostrarListaPacotes (listaPacotes);
    }

    @Override
    public void error() {
        //ignorar
    }


}
