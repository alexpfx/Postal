package postaltracker.app.alexandrealessi.com.br.postal.model;

import java.util.List;

import br.com.alexpfx.api.postal.SRO;
import postaltracker.app.alexandrealessi.com.br.postal.model.SroRetornoInfo;

/**
 * Created by alexandre on 28/02/15.
 */
public interface ConsultarCorreiosSroCallback {
    public void receive (SRO sro, List<SroRetornoInfo> listaInfos);
    public void naoEncontrado (SRO sro);
}
