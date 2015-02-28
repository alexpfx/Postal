package postaltracker.app.alexandrealessi.com.br.postal;

import br.com.alexpfx.api.postal.SRO;

/**
 * Created by alex on 24/02/2015.
 */
public interface SROInteractor {
    public void avaliarSro (String sro, OnSroValidoListener validoListener);
    public void consultarCorreiosSro (SRO sro, OnReceiveDetailSroListener receiveDetailSroListener);

}
