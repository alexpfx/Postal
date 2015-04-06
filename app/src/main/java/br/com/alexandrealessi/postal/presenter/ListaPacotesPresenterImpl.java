package br.com.alexandrealessi.postal.presenter;

import br.com.alexandrealessi.postal.common.AbstractPresenter;
import br.com.alexandrealessi.postal.model.PacotesInteractor;
import br.com.alexandrealessi.postal.model.domain.Pacote;
import br.com.alexandrealessi.postal.view.ListaPacotesView;

/**
 * Created by alexandre on 12/03/15.
 */
public class ListaPacotesPresenterImpl
        extends AbstractPresenter<ListaPacotesView> implements ListaPacotesPresenter, PacotesInteractor.RequestListaPacotesCallback, PacotesInteractor.SaveCallback {

    {
        super.init(ListaPacotesView.NULL);
    }

    private PacotesInteractor pacotesInteractor = PacotesInteractor.NULL;



    public void setPacotesInteractor(PacotesInteractor pacotesInteractor) {
        this.pacotesInteractor = pacotesInteractor;
    }

    @Override
    public void savePacote(Pacote pacote) {
        pacotesInteractor.save(pacote,this);
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
    public void onRequestListaPacotesError() {

    }


    @Override
    public void onSavePacoteError() {

    }
}
