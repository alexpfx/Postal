package postaltracker.app.alexandrealessi.com.br.postal.model;

import br.com.alexpfx.api.postal.SRO;

/**
 * Created by alex on 24/02/2015.
 */
public interface ResultadoAvaliacaoSroCallback {

    public void onCodigoSroValido(SRO sro);

    public void onCodigoSroInvalido(String invalidSro);

}
