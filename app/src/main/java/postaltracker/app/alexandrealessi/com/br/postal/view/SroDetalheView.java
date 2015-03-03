package postaltracker.app.alexandrealessi.com.br.postal.view;

import java.util.List;

import br.com.alexpfx.api.postal.Sro;
import br.com.alexpfx.api.postal.dao.SroRetornoInfo;


/**
 * Created by alex on 25/02/2015.
 */
public interface SroDetalheView {
    public void mostrarQueEhInvalido ();
    public void mostrarQueEhValido ();
    public void mostrarDetalhesRecebidos (Sro sro, List<SroRetornoInfo> retornoInfo);
    public void mostrarDetalhesNaoEncontrados (Sro sro);
    public void mostrarSroScaneado (Sro sro);
    public void onQrCodeChange ();
}
