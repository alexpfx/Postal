package br.com.alexandrealessi.postal.model.domain;

import com.orm.SugarRecord;

/**
 * Created by alexandre on 09/03/15.
 */
public class Acao extends SugarRecord<Acao>{
    private String descricao;

    public Acao() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
