package postaltracker.app.alexandrealessi.com.br.postal;

import br.com.alexpfx.api.postal.SRO;

/**
 * Created by alex on 24/02/2015.
 */
public interface OnSroValidoListener {

    public void onCodigoSroValido (SRO sro);

    public void onCodigoSroInvalido (String invalidSro);

}
