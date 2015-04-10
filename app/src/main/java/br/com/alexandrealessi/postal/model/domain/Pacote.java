package br.com.alexandrealessi.postal.model.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandre on 09/03/15.
 */
public class Pacote extends BaseDomain {
    String sro;
    List<Evento> eventos = new ArrayList<>();
    List<String> tags = new ArrayList<>();

    private Pacote(String sro) {
        this.sro = sro;
    }

    private Pacote(Long id, String sro) {
        this(sro);
        setId(id);
    }

    public static Pacote create(String sro) {
        Pacote p = new Pacote(sro);
        p.setSro(sro);
        return p;
    }

    public String getSro() {
        return sro;
    }

    public void setSro(String sro) {
        this.sro = sro;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }


    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return sro;
    }
}
