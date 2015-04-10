package br.com.alexandrealessi.postal.model.domain;

/**
 * Created by alexandre on 09/04/15.
 */
public abstract class BaseDomain {
    private Long id;

    protected BaseDomain(Long id) {
        this.id = id;
    }

    protected BaseDomain() {
    }

    protected void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
