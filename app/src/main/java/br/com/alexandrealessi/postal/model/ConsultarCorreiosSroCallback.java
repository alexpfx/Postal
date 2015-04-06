package br.com.alexandrealessi.postal.model;

import br.com.alexandrealessi.postal.utils.SroDTO;
import br.com.alexpfx.api.postal.dao.SroRetornoInfo;

import java.util.List;


/**
 * Created by alexandre on 28/02/15.
 */
public interface ConsultarCorreiosSroCallback {
    public void receive (SroDTO sro, List<SroRetornoInfo> listaInfos);
    public void naoEncontrado (SroDTO sro);
}
