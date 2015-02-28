package postaltracker.app.alexandrealessi.com.br.postal;

import java.util.List;

import br.com.alexpfx.api.postal.SRO;

/**
 * Created by alexandre on 28/02/15.
 */
public interface OnReceiveDetailSroListener {
    public void receive (SRO sro, List<SroRetornoInfo> listaInfos);
    public void naoEncontrado (SRO sro);
}
