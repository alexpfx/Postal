package br.com.alexandrealessi.postal.model;

import br.com.alexandrealessi.postal.utils.SroDTO;

/**
 * Created by alex on 24/02/2015.
 */
public interface ResultadoAvaliacaoSroCallback {

    public void onCodigoSroValido(SroDTO sro);

    public void onCodigoSroInvalido(String invalidSro);

}
