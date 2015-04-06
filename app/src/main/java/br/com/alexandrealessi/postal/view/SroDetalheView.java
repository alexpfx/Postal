package br.com.alexandrealessi.postal.view;

import br.com.alexandrealessi.postal.utils.SroDTO;
import br.com.alexpfx.api.postal.dao.SroRetornoInfo;

import java.util.List;


/**
 * Created by alex on 25/02/2015.
 */
public interface SroDetalheView {
    public void mostrarQueEhInvalido();

    public void mostrarQueEhValido();

    public void mostrarDetalhesRecebidos(SroDTO sro, List<SroRetornoInfo> retornoInfo);

    public void mostrarDetalhesNaoEncontrados(SroDTO sro);

    public void mostrarSroScaneado(SroDTO sro);

    public void onQrCodeChange();


}
