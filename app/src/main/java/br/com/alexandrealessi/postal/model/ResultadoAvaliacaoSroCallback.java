package br.com.alexandrealessi.postal.model;

import br.com.alexpfx.api.postal.Sro;

/**
 * Created by alex on 24/02/2015.
 */
public interface ResultadoAvaliacaoSroCallback {

    public void onCodigoSroValido(Sro sro);

    public void onCodigoSroInvalido(String invalidSro);

}
