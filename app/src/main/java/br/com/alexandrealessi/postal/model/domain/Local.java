package br.com.alexandrealessi.postal.model.domain;

/**
 * Created by alexandre on 09/03/15.
 */
public class Local extends BaseDomain {
    private String descricao;

    private Local(Long id, String descricao) {
        this(descricao);
        setId(id);
    }

    private Local(String descricao) {
        this.descricao = descricao;
    }

    public static final Local create(String descricao) {
        return new Local(descricao);
    }

    public static final Local create(Long id, String descricao) {
        return new Local(id, descricao);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


}
