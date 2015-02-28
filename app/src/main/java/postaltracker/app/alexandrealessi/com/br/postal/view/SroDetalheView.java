package postaltracker.app.alexandrealessi.com.br.postal.view;

import java.util.List;

import br.com.alexpfx.api.postal.SRO;
import postaltracker.app.alexandrealessi.com.br.postal.model.SroRetornoInfo;

/**
 * Created by alex on 25/02/2015.
 */
public interface SroDetalheView {
    public void mostrarQueEhInvalido ();
    public void mostrarQueEhValido ();
    public void mostrarDetalhesRecebidos (List<SroRetornoInfo> retornoInfo);
    public void mostrarDetalhesNaoEncontrados (SRO sro);
}
