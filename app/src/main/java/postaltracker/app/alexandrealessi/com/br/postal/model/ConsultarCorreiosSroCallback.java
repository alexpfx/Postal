package postaltracker.app.alexandrealessi.com.br.postal.model;

import java.util.List;

import br.com.alexpfx.api.postal.Sro;
import br.com.alexpfx.api.postal.SroRetornoInfo;


/**
 * Created by alexandre on 28/02/15.
 */
public interface ConsultarCorreiosSroCallback {
    public void receive (Sro sro, List<SroRetornoInfo> listaInfos);
    public void naoEncontrado (Sro sro);
}
