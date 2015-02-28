package postaltracker.app.alexandrealessi.com.br.postal;

import java.util.List;

/**
 * Created by alex on 25/02/2015.
 */
public interface ValidadeSroView {
    public void mostrarQueEhInvalido ();
    public void mostrarQueEhValido ();
    public void mostrarDetalhesRecebidos (List<SroRetornoInfo> retornoInfo);
}
