package postaltracker.app.alexandrealessi.com.br.postal;

import java.util.List;

import br.com.alexpfx.api.postal.SRO;

/**
 * Created by alex on 24/02/2015.
 */
public class ValidadorSroPresenterImpl implements ValidadorSroPresenter, OnSroValidoListener, OnReceiveDetailSroListener {

    private SROInteractor sroInteractor;
    private SroDetalheView sroDetalheView;


    public ValidadorSroPresenterImpl(SroDetalheView sroDetalheView) {
        this.sroDetalheView = sroDetalheView;
        this.sroInteractor = new SROInteractorImpl();
    }


    @Override
    public void verificarValidadeSro(String sro) {
        sroInteractor.avaliarSro(sro, this);
    }


    @Override
    public void onCodigoSroValido(SRO sro) {
        sroDetalheView.mostrarQueEhValido();
        sroInteractor.consultarCorreiosSro(sro, this);
    }

    @Override
    public void onCodigoSroInvalido(String invalidSro) {
        sroDetalheView.mostrarQueEhInvalido();
    }


    @Override
    public void receive(SRO sro, List<SroRetornoInfo> listaInfos) {
        sroDetalheView.mostrarDetalhesRecebidos(listaInfos);
    }

    @Override
    public void naoEncontrado(SRO sro) {
        sroDetalheView.mostrarDetalhesNaoEncontrados(sro);
    }
}
