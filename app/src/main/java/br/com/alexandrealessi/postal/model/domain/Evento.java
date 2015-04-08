package br.com.alexandrealessi.postal.model.domain;

import java.util.Date;

/**
 * Created by alexandre on 09/03/15.
 */
public class Evento {

    public static Evento NULL = new Evento() {
    };

    private Date data;
    private Local local;
    private Acao acao;
    private String detalhe;

    public Evento() {
    }


    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public Acao getAcao() {
        return acao;
    }

    public void setAcao(Acao acao) {
        this.acao = acao;
    }

    public String getDetalhe() {
        return detalhe;
    }

    public void setDetalhe(String detalhe) {
        this.detalhe = detalhe;
    }
}
