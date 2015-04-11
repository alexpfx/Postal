package br.com.alexandrealessi.postal.model.domain;

/**
 * Created by alexandre on 09/03/15.
 */
public class Acao extends BaseDomain {
    private String descricao;


    private Acao(Long id, String descricao) {
        super(id);
        this.descricao = descricao;
    }

    private Acao(String descricao) {
        this.descricao = descricao;
    }

    public static final Acao create(Long id, String descricao) {
        return new Acao(id, descricao);
    }

    public static final Acao create(String descricao) {
        return new Acao(descricao);
    }

    public String getDescricao() {
        return descricao;
    }

}
