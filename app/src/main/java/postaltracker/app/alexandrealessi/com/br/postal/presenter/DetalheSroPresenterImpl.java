package postaltracker.app.alexandrealessi.com.br.postal.presenter;

import java.util.List;

import javax.inject.Inject;

import br.com.alexpfx.api.postal.Sro;
import br.com.alexpfx.api.postal.dao.SroRetornoInfo;
import postaltracker.app.alexandrealessi.com.br.postal.common.AbstractPresenter;
import postaltracker.app.alexandrealessi.com.br.postal.model.ConsultarCorreiosSroCallback;
import postaltracker.app.alexandrealessi.com.br.postal.model.ResultadoAvaliacaoSroCallback;
import postaltracker.app.alexandrealessi.com.br.postal.model.SroInteractor;
import postaltracker.app.alexandrealessi.com.br.postal.model.SroInteractorImpl;
import postaltracker.app.alexandrealessi.com.br.postal.view.SroDetalheView;

/**
 * Created by alex on 24/02/2015.
 */
public class DetalheSroPresenterImpl extends AbstractPresenter <SroDetalheView> implements DetalheSroPresenter, ResultadoAvaliacaoSroCallback, ConsultarCorreiosSroCallback {

    private SroInteractor sroInteractor;
    private SroDetalheView sroDetalheView;


    @Inject
    public DetalheSroPresenterImpl() {
        this.sroInteractor = new SroInteractorImpl();
    }


    @Override
    public void verificarValidadeSro(String sro) {
        sroInteractor.avaliarSro(sro, this);
    }


    @Override
    public void onCodigoSroValido(Sro sro) {
        sroDetalheView.mostrarQueEhValido();
        sroInteractor.consultarCorreiosSro(sro, this);
    }

    @Override
    public void onCodigoSroInvalido(String invalidSro) {
        sroDetalheView.mostrarQueEhInvalido();
    }


    @Override
    public void receive(Sro sro, List<SroRetornoInfo> listaInfos) {
        sroDetalheView.mostrarDetalhesRecebidos(sro, listaInfos);
    }

    @Override
    public void naoEncontrado(Sro sro) {
        sroDetalheView.mostrarDetalhesNaoEncontrados(sro);
    }


}
