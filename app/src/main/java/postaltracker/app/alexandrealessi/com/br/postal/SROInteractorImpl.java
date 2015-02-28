package postaltracker.app.alexandrealessi.com.br.postal;

import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;

import br.com.alexpfx.api.postal.SRO;
import br.com.alexpfx.api.postal.SROFactory;
import br.com.alexpfx.api.postal.SROInvalidoException;

/**
 * Created by alex on 25/02/2015.
 */
public class SROInteractorImpl implements SROInteractor{
    private SroRepository sroRepository;

    public SROInteractorImpl() {
        this.sroRepository = new FakeSroRepository();
    }

    @Override
    public void avaliarSro(String sroString, OnSroValidoListener validoListener) {
        try {
            SRO objetoSro = new SROFactory().criar(sroString);
            if (objetoSro.isValid()){
                validoListener.onCodigoSroValido(objetoSro);
            }else{
                validoListener.onCodigoSroInvalido(sroString);
            }
        } catch (SROInvalidoException e){
            validoListener.onCodigoSroInvalido(sroString);
        }

    }

    @Override
    public void consultarCorreiosSro(SRO sro, OnReceiveDetailSroListener listener) {
        List<SroRetornoInfo> send = sroRepository.send(sro);
        if (send == null || send.isEmpty()){
            listener.naoEncontrado(sro);
        }else {
            listener.receive(sro, send);
        }
    }


}
