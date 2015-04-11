package br.com.alexandrealessi.postal.presenter;

import br.com.alexandrealessi.postal.common.AbstractPresenter;
import br.com.alexandrealessi.postal.model.*;
import br.com.alexandrealessi.postal.model.domain.Pacote;
import br.com.alexandrealessi.postal.utils.SroDTO;
import br.com.alexandrealessi.postal.view.SroDetalheView;
import br.com.alexpfx.api.postal.dao.SroRetornoInfo;

import java.util.List;

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
    public void onCodigoSroValido(SroDTO sro) {
        getView().mostrarQueEhValido();
        // provisorio ate ter a dialog de cadastro de sro
        Pacote p = Pacote.create(sro.toString());
        pacotesInteractor.save(p, this);
        sroInteractor.consultarCorreiosSro(sro, this);

    }

    @Override
    public void onCodigoSroInvalido(String invalidSro) {
        getView().mostrarQueEhInvalido();
    }


    @Override
    public void receive(SroDTO sro, List<SroRetornoInfo> listaInfos) {
        getView().mostrarDetalhesRecebidos(sro, listaInfos);
    }

    @Override
    public void naoEncontrado(SroDTO sro) {
        getView().mostrarDetalhesNaoEncontrados(sro);
    }


    @Override
    public void onSavePacoteError() {

    }

    @Override
    public void onSavePacoteSuccess() {

    }
}
