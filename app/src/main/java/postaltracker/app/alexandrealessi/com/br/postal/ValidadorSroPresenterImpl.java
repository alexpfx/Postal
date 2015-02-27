package postaltracker.app.alexandrealessi.com.br.postal;

import br.com.alexpfx.api.postal.SRO;

/**
 * Created by alex on 24/02/2015.
 */
public class ValidadorSroPresenterImpl implements ValidadorSroPresenter, OnSroValidoListener {

    private SROInteractor sroInteractor;
    private ValidadeSroView validadeSroView;


    public ValidadorSroPresenterImpl(ValidadeSroView validadeSroView) {
        this.validadeSroView = validadeSroView;
        this.sroInteractor = new SROInteractorImpl();
    }


    @Override
    public void verificarValidadeSro(String sro) {
        sroInteractor.avaliarSro(sro, this);
    }


    @Override
    public void onCodigoSroValido(SRO sro) {
        validadeSroView.mostrarQueEhValido();


    }

    @Override
    public void onCodigoSroInvalido(String invalidSro) {
        validadeSroView.mostrarQueEhInvalido();
    }
}
