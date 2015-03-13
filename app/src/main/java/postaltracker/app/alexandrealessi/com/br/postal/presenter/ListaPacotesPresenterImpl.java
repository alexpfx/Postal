package postaltracker.app.alexandrealessi.com.br.postal.presenter;

import android.view.View;

import com.google.common.collect.Iterators;

import java.util.Iterator;

import javax.inject.Inject;

import postaltracker.app.alexandrealessi.com.br.postal.common.AbstractPresenter;
import postaltracker.app.alexandrealessi.com.br.postal.model.PacotesInteractor;
import postaltracker.app.alexandrealessi.com.br.postal.model.PacotesInteractorImpl;
import postaltracker.app.alexandrealessi.com.br.postal.model.domain.Pacote;
import postaltracker.app.alexandrealessi.com.br.postal.view.ListaPacotesView;

/**
 * Created by alexandre on 12/03/15.
 */
public class ListaPacotesPresenterImpl extends AbstractPresenter <ListaPacotesView> implements ListaPacotesPresenter, PacotesInteractor.PacotesInteractorCallback {

    PacotesInteractor pacotesInteractor;


    @Inject
    public ListaPacotesPresenterImpl() {
        pacotesInteractor = new PacotesInteractorImpl();
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
    public void receive(Iterator<Pacote> listaPacotes) {
        getView().mostrarListaPacotes(Iterators.toArray(listaPacotes,Pacote.class));
    }

    @Override
    public void error() {
        //ignorar
    }



}
