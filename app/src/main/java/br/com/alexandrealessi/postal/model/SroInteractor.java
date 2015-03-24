package br.com.alexandrealessi.postal.model;


import android.util.Log;

import br.com.alexpfx.api.postal.Sro;

/**
 * Created by alex on 24/02/2015.
 */
public interface SroInteractor {


    public void avaliarSro(String sro, ResultadoAvaliacaoSroCallback validoListener);

    public void consultarCorreiosSro(Sro sro, ConsultarCorreiosSroCallback receiveDetailSroListener);


    public static SroInteractor NULL = new SroInteractor() {
        @Override
        public void avaliarSro(String sro, ResultadoAvaliacaoSroCallback validoListener) {
            Log.d(this.getClass().getName(), "não instanciado");

        }

        @Override
        public void consultarCorreiosSro(Sro sro, ConsultarCorreiosSroCallback receiveDetailSroListener) {
            Log.d(this.getClass().getName(), "não instanciado");

        }
    };

}
