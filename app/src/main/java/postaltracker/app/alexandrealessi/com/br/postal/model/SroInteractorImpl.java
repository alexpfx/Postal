package postaltracker.app.alexandrealessi.com.br.postal.model;

import java.util.List;

import br.com.alexpfx.api.postal.Sro;
import br.com.alexpfx.api.postal.SroFactory;
import br.com.alexpfx.api.postal.SroInvalidoException;
import br.com.alexpfx.api.postal.dao.FakeSroRepository;
import br.com.alexpfx.api.postal.dao.SroRepository;
import br.com.alexpfx.api.postal.dao.SroRetornoInfo;


/**
 * Created by alex on 25/02/2015.
 */
public class SroInteractorImpl implements SroInteractor {
    private SroRepository sroRepository;

    public SroInteractorImpl() {
        this.sroRepository = new FakeSroRepository();
    }

    @Override
    public void avaliarSro(String sroString, ResultadoAvaliacaoSroCallback resultadoCallback) {
        try {
            Sro objetoSro = new SroFactory().criar(sroString);
            if (objetoSro.isValid()) {
                resultadoCallback.onCodigoSroValido(objetoSro);
            } else {
                resultadoCallback.onCodigoSroInvalido(sroString);
            }
        } catch (SroInvalidoException e) {
            resultadoCallback.onCodigoSroInvalido(sroString);
        }

    }

    @Override
    public void consultarCorreiosSro(Sro sro, ConsultarCorreiosSroCallback callback) {
        List<SroRetornoInfo> retorno = sroRepository.consultarSro(sro);
        if (retorno == null || retorno.isEmpty()) {
            callback.naoEncontrado(sro);
        } else {
            callback.receive(sro, retorno);
        }
    }


}
