package postaltracker.app.alexandrealessi.com.br.postal.model;


import br.com.alexpfx.api.postal.Sro;

/**
 * Created by alex on 24/02/2015.
 */
public interface SroInteractor {
    public void avaliarSro (String sro, ResultadoAvaliacaoSroCallback validoListener);
    public void consultarCorreiosSro (Sro sro, ConsultarCorreiosSroCallback receiveDetailSroListener);
}
