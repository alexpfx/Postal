package postaltracker.app.alexandrealessi.com.br.postal;

import android.widget.TextView;

import br.com.alexpfx.api.postal.SRO;

/**
 * Created by alex on 24/02/2015.
 */
public class ShowPacoteDetalhadoPresenetarImpl implements ShowPacoteDetalhadoPresenter, OnSroValidoListener {

    private SROInteractor sroInteractor;
    private DetalheSroView detalheSroView;


    public ShowPacoteDetalhadoPresenetarImpl(DetalheSroView detalheSroView) {
        this.detalheSroView = detalheSroView;
        this.sroInteractor = new SROInteractorImpl();
    }


    @Override
    public void buscarSroValido(String sro) {
        sroInteractor.avaliarSro(sro, this);
    }


    @Override
    public void onCodigoSroValido(SRO sro) {
        detalheSroView.mostrarQueEhValido();


    }

    @Override
    public void onCodigoSroInvalido(String invalidSro) {
        detalheSroView.mostrarQueEhInvalido();

    }
}
