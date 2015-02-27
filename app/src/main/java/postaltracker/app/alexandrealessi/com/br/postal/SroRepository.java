package postaltracker.app.alexandrealessi.com.br.postal;

import br.com.alexpfx.api.postal.SRO;

/**
 * Created by alex on 26/02/2015.
 */
public interface SroRepository {

    public void send (SRO sro) throws InfraException;



}
