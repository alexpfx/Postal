package br.com.alexandrealessi.postal.model.domain;

import java.util.Date;

/**
 * Created by alexandre on 09/03/15.
 */
public class Evento extends BaseDomain {

    private Date data;
    private Local local;
    private Acao acao;
    private String detalhe;


    private Evento(Date data, Local local, Acao acao, String detalhe) {
        this.data = data;
        this.local = local;
        this.acao = acao;
        this.detalhe = detalhe;
    }

    private Evento(Long id, Date data, Local local, Acao acao, String detalhe) {
        super(id);
        this.data = data;
        this.local = local;
        this.acao = acao;
        this.detalhe = detalhe;
    }


    public static final Evento create(Long id, Date data, Local local, Acao acao, String detalhe) {
        return new Evento(id, data, local, acao, detalhe);
    }

    public static final Evento create(Date data, Local local, Acao acao, String detalhe) {
        return new Evento(data, local, acao, detalhe);
    }

    public static final Evento create(Long id, Evento evento) {
        return new Evento(id, evento.getData(), evento.getLocal(), evento.getAcao(), evento.getDetalhe());
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
