package postaltracker.app.alexandrealessi.com.br.postal;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import br.com.alexpfx.api.postal.SRO;
import br.com.alexpfx.api.postal.SROFactory;
import br.com.alexpfx.api.postal.SROInvalidoException;

/**
 * Created by alex on 25/02/2015.
 */
public class SROInteractorImpl implements SROInteractor, SroRepositoryListener{
    private SroRepository sroRepository;

    public SROInteractorImpl() {
        this.sroRepository = new FakeSroRepository();
    }

    @Override
    public void avaliarSro(String sroString, OnSroValidoListener listener) {
        try {
            SRO objetoSro = new SROFactory().criar(sroString);
            if (objetoSro.isValid()){
                listener.onCodigoSroValido(objetoSro);
            }else{
                listener.onCodigoSroInvalido(sroString);
            }
        } catch (SROInvalidoException e){
            listener.onCodigoSroInvalido(sroString);
        }

    }

    @Override
    public void consultarCorreiosSro(SRO sro) {
        sroRepository.send(sro);
    }

    @Override
    public void receive(SRO sro, List<SroRetornoInfo> listaInfos) {


    }

    @Override
    public void naoEncontrado(SRO sro) {

    }
}
