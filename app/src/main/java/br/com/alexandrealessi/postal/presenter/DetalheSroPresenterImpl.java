package br.com.alexandrealessi.postal.presenter;

import java.util.List;

import br.com.alexpfx.api.postal.Sro;
import br.com.alexpfx.api.postal.dao.SroRetornoInfo;
import br.com.alexandrealessi.postal.common.AbstractPresenter;
import br.com.alexandrealessi.postal.model.ConsultarCorreiosSroCallback;
import br.com.alexandrealessi.postal.model.PacotesInteractor;
import br.com.alexandrealessi.postal.model.PacotesInteractorImpl;
import br.com.alexandrealessi.postal.model.ResultadoAvaliacaoSroCallback;
import br.com.alexandrealessi.postal.model.SroInteractor;
import br.com.alexandrealessi.postal.model.SroInteractorImpl;
import br.com.alexandrealessi.postal.model.domain.Pacote;
import br.com.alexandrealessi.postal.view.SroDetalheView;

/**
 * Created by alex on 24/02/2015.
 */
public class DetalheSroPresenterImpl extends AbstractPresenter<SroDetalheView> implements DetalheSroPresenter, ResultadoAvaliacaoSroCallback, ConsultarCorreiosSroCallback, PacotesInteractor.SaveCallback {


    private SroInteractor sroInteractor;
//    private SroDetalheView sroDetalheView;
    private PacotesInteractor pacotesInteractor;

    public DetalheSroPresenterImpl() {
        this.sroInteractor = new SroInteractorImpl();
        this.pacotesInteractor = new PacotesInteractorImpl();

    }


    @Override
    public void verificarValidadeSro(String sro) {
        sroInteractor.avaliarSro(sro, this);
    }


    @Override
    public void onCodigoSroValido(Sro sro) {
        getView().mostrarQueEhValido();
        // provisorio ate ter a dialog de cadastro de sro
        Pacote p = new Pacote();
        p.setSro(sro.toString());

        pacotesInteractor.save(p, this);
        sroInteractor.consultarCorreiosSro(sro, this);

    }

    @Override
    public void onCodigoSroInvalido(String invalidSro) {
        getView().mostrarQueEhInvalido();
    }


    @Override
    public void receive(Sro sro, List<SroRetornoInfo> listaInfos) {
        getView().mostrarDetalhesRecebidos(sro, listaInfos);
    }

    @Override
    public void naoEncontrado(Sro sro) {
        getView().mostrarDetalhesNaoEncontrados(sro);
    }


    @Override
    public void onSavePacoteError() {

    }
}
