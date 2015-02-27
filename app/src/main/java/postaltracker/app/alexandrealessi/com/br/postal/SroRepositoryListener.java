package postaltracker.app.alexandrealessi.com.br.postal;

import java.util.List;

import br.com.alexpfx.api.postal.SRO;

/**
 * Created by alex on 27/02/2015.
 */
public interface SroRepositoryListener {
    public void receive (SRO sro, List<SroRetornoInfo> listaInfos);
    public void naoEncontrado (SRO sro);
}
