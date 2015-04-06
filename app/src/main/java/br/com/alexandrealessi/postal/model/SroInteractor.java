package br.com.alexandrealessi.postal.model;


import android.util.Log;
import br.com.alexandrealessi.postal.utils.SroDTO;

/**
 * Created by alex on 24/02/2015.
 */
public interface SroInteractor {


    public void avaliarSro(String sro, ResultadoAvaliacaoSroCallback validoListener);

    public void consultarCorreiosSro(SroDTO sro, ConsultarCorreiosSroCallback receiveDetailSroListener);


    public static SroInteractor NULL = new SroInteractor() {
        @Override
        public void avaliarSro(String sro, ResultadoAvaliacaoSroCallback validoListener) {
            Log.d(this.getClass().getName(), "não instanciado");

        }

        @Override
        public void consultarCorreiosSro(SroDTO sro, ConsultarCorreiosSroCallback receiveDetailSroListener) {
            Log.d(this.getClass().getName(), "não instanciado");

        }
    };

}
