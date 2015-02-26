package postaltracker.app.alexandrealessi.com.br.postal;

import br.com.alexpfx.api.postal.SRO;
import br.com.alexpfx.api.postal.SROFactory;
import br.com.alexpfx.api.postal.SROInvalidoException;

/**
 * Created by alex on 25/02/2015.
 */
public class SROInteractorImpl implements SROInteractor {
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
}
