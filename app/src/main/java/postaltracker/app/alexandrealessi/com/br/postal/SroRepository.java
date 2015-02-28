package postaltracker.app.alexandrealessi.com.br.postal;

import java.util.List;

import br.com.alexpfx.api.postal.SRO;

/**
 * Created by alex on 26/02/2015.
 */
public interface SroRepository {

    public List<SroRetornoInfo> send (SRO sro) throws InfraException;



}
