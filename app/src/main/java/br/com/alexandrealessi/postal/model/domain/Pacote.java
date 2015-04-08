package br.com.alexandrealessi.postal.model.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandre on 09/03/15.
 */
public class Pacote {
    String sro;
    List<Evento> eventos = new ArrayList<>();
    List<String> tags = new ArrayList<>();

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

    public Evento obterItemAcaoRecente() {
        if (eventos.size() > 0) {
            return eventos.get(0);
        }
        return Evento.NULL;
    }

    @Override
    public String toString() {
        return sro;
    }
}
